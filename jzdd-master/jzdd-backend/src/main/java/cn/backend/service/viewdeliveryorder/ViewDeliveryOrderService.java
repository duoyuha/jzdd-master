package cn.backend.service.viewdeliveryorder;

import cn.backend.config.constant.SysConstant;
import cn.backend.config.message.CustomMessage;
import cn.backend.dao.primary.viewdeliveryorder.ViewDeliveryOrderRepository;
import cn.backend.model.primary.orderattach.OrderAttachEntity;
import cn.backend.model.primary.orderattach.OrderAttachQuery;
import cn.backend.model.primary.settle.SettleEntity;
import cn.backend.model.primary.settle.SettleQuery;
import cn.backend.model.primary.user.UserEntity;
import cn.backend.model.primary.viewdeliveryorder.ViewDeliveryOrderEntity;
import cn.backend.model.primary.viewdeliveryorder.ViewDeliveryOrderQuery;
import cn.backend.model.primary.workfollowdetail.WorkFollowDetailEntity;
import cn.backend.model.primary.workfollowdetail.WorkFollowDetailQuery;
import cn.backend.service.BaseService;
import cn.backend.service.orderattach.IOrderAttachService;
import cn.backend.service.orderstep.IOrderStepService;
import cn.backend.service.settle.ISettleService;
import cn.backend.service.settledetail.ISettleDetailService;
import cn.backend.service.user.IUserService;
import cn.backend.service.workfollowdetail.IWorkFollowDetailService;
import cn.zdwl.common.context.BaseContextHandler;
import cn.zdwl.common.exception.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author sunkw
 * @date 2019/07/23
 */
@Service(value = "viewDeliveryOrderService")
public class ViewDeliveryOrderService extends BaseService implements IViewDeliveryOrderService {

    @Resource
    private ViewDeliveryOrderRepository viewDeliveryOrderRepository;

    @Autowired
    private IOrderAttachService orderAttachService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IWorkFollowDetailService workFollowDetailService;

    @Autowired
    private IOrderStepService orderStepService;

    @Autowired
    private ISettleDetailService settleDetailService;

    @Autowired
    private ISettleService settleService;

    /**
     * 分页查询
     *
     * @param viewDeliveryOrderQuery
     * @return
     */
    @Override
    public Page<ViewDeliveryOrderEntity> findList(ViewDeliveryOrderQuery viewDeliveryOrderQuery) {
        Sort sort = buildSort("+orderTime");
        Pageable pageable = new PageRequest(viewDeliveryOrderQuery.getPageNum(), viewDeliveryOrderQuery.getPageSize(), sort);
        Page<ViewDeliveryOrderEntity> entityPage = viewDeliveryOrderRepository.findAll(createSpecification(viewDeliveryOrderQuery), pageable);

        WorkFollowDetailQuery workFollowDetailQuery = new WorkFollowDetailQuery();
        workFollowDetailQuery.setFollowNo(SysConstant.WorkFollow.WORKFOLLOW_DELIVERY_NO);
        workFollowDetailQuery.setSort("+followSeq");
        List<WorkFollowDetailEntity> workFollowDetailEntityList = workFollowDetailService.findAll(workFollowDetailQuery);
        WorkFollowDetailEntity receiveWork = workFollowDetailEntityList.stream()
                .filter(x -> SysConstant.InstallOrder.CONFIG_FLG_Y.equals(x.getTimeOutFlg()) && SysConstant.WorkFollow.WORKFOLLOW_TIMEOUT_TYPE05.equals(x.getTimeoutType()))
                .findFirst().orElse(null);
        WorkFollowDetailEntity delWrok = workFollowDetailEntityList.stream()
                .filter(x -> SysConstant.InstallOrder.CONFIG_FLG_Y.equals(x.getTimeOutFlg()) && SysConstant.WorkFollow.WORKFOLLOW_TIMEOUT_TYPE06.equals(x.getTimeoutType()))
                .findFirst().orElse(null);

        for (ViewDeliveryOrderEntity viewDeliveryOrderEntity : entityPage) {
            //计算签收时间是否超时
            calcOVerTime(receiveWork, viewDeliveryOrderEntity, SysConstant.WorkFollow.WORKFOLLOW_TIMEOUT_TYPE05);


            //联系客户时间是否超时
            calcOVerTime(delWrok, viewDeliveryOrderEntity, SysConstant.WorkFollow.WORKFOLLOW_TIMEOUT_TYPE06);

            //结算状态
            // if (SysConstant.InstallOrder.SETTLE_FLG_N.equals(viewDeliveryOrderEntity.getSettleFlg())) {
            //     SettleDetailQuery settleDetailQuery = new SettleDetailQuery();
            //     settleDetailQuery.setCorpNo(viewDeliveryOrderEntity.getOrderNo());
            //     settleDetailQuery.setOrderNo(viewDeliveryOrderEntity.getOrderNo());
            //     List<SettleDetailEntity> settleDetailEntityList = settleDetailService.findAll(settleDetailQuery);
            //     if (settleDetailEntityList.size() > 0) {
            //         viewDeliveryOrderEntity.setSettleVerifyFlg(SysConstant.InstallOrder.CONFIG_FLG_Y);
            //     }
            // }
            //20190923 现在列表需要显示是初审退回还是复审退回
            SettleQuery settleQuery = new SettleQuery();
            settleQuery.setOrderNo(viewDeliveryOrderEntity.getOrderNo());
            settleQuery.setSort("-createdWhen");
            List<SettleEntity> settleEntityList = settleService.findAll(settleQuery);
            if (settleEntityList.size() > 0) {
                SettleEntity settleEntity = settleEntityList.stream().findFirst().orElse(null);
                if (isEmpty(settleEntity.getFirstVerifyFlg()) || SysConstant.InstallOrder.CONFIG_VERIFY_REFUSE.equals(settleEntity.getFirstVerifyFlg())) {
                    //初审不通过
                    viewDeliveryOrderEntity.setSettleRefuseFlg(SysConstant.InstallOrder.CONFIG_VERIFY_N);
                } else if (isEmpty(settleEntity.getSecondVerifyFlg()) || SysConstant.InstallOrder.CONFIG_VERIFY_REFUSE.equals(settleEntity.getSecondVerifyFlg())) {
                    //复审不通过
                    viewDeliveryOrderEntity.setSettleRefuseFlg(SysConstant.InstallOrder.CONFIG_VERIFY_Y);
                }
            }

        }


        return entityPage;

    }

    private void calcOVerTime(WorkFollowDetailEntity workFollowDetailEntity, ViewDeliveryOrderEntity viewDeliveryOrderEntity, String overType) {

        //判断接单超时
        if (workFollowDetailEntity != null && !isEmpty(viewDeliveryOrderEntity)) {

            int overMinutes = 0;

            switch (overType) {
                case SysConstant.WorkFollow.WORKFOLLOW_TIMEOUT_TYPE05:
                    if (!isEmpty(viewDeliveryOrderEntity.getDispatchTime())) {
                        if (isEmpty(viewDeliveryOrderEntity.getReceiveTime())) {
                            overMinutes = orderStepService.judgeTimeoutHours(workFollowDetailEntity.getTimeOutRadio(), viewDeliveryOrderEntity.getDispatchTime(), new Date());
                            viewDeliveryOrderEntity.setDispatchOverTime(overMinutes);
                            if (overMinutes > 0) {
                                viewDeliveryOrderEntity.setDispatchOverTimeFlg(SysConstant.InstallOrder.CONFIG_FLG_Y);
                            }
                        } else {
                            overMinutes = orderStepService.judgeTimeoutHours(workFollowDetailEntity.getTimeOutRadio(), viewDeliveryOrderEntity.getDispatchTime(), viewDeliveryOrderEntity.getReceiveTime());
                            viewDeliveryOrderEntity.setDispatchOverTime(overMinutes);
                            // if (overMinutes > 0) {
                            //     viewDeliveryOrderEntity.setDispatchOverTimeFlg(SysConstant.InstallOrder.CONFIG_FLG_Y);
                            // }
                            viewDeliveryOrderEntity.setDispatchOverTimeFlg(SysConstant.InstallOrder.CONFIG_FLG_N);
                        }
                    }
                    break;

                case SysConstant.WorkFollow.WORKFOLLOW_TIMEOUT_TYPE06:
                    if (!isEmpty(viewDeliveryOrderEntity.getReceiveTime())) {
                        if (isEmpty(viewDeliveryOrderEntity.getContactCustTime())) {
                            overMinutes = orderStepService.judgeTimeoutHours(workFollowDetailEntity.getTimeOutRadio(), viewDeliveryOrderEntity.getReceiveTime(), new Date());
                            viewDeliveryOrderEntity.setContractOverTime(overMinutes);
                            if (overMinutes > 0) {
                                viewDeliveryOrderEntity.setContractOverTimeFlg(SysConstant.InstallOrder.CONFIG_FLG_Y);
                            }
                        } else {
                            overMinutes = orderStepService.judgeTimeoutHours(workFollowDetailEntity.getTimeOutRadio(), viewDeliveryOrderEntity.getReceiveTime(), viewDeliveryOrderEntity.getContactCustTime());
                            viewDeliveryOrderEntity.setContractOverTime(overMinutes);
                            // if (overMinutes > 0) {
                            //     viewDeliveryOrderEntity.setContractOverTimeFlg(SysConstant.InstallOrder.CONFIG_FLG_Y);
                            // }
                            viewDeliveryOrderEntity.setContractOverTimeFlg(SysConstant.InstallOrder.CONFIG_FLG_N);
                        }
                    }
                    break;
            }


        }

    }


    /**
     * 列表查询
     *
     * @param viewDeliveryOrderQuery
     * @return
     */
    @Override
    public List<ViewDeliveryOrderEntity> findAll(ViewDeliveryOrderQuery viewDeliveryOrderQuery) {
        Sort sort = buildSort(viewDeliveryOrderQuery.getSort());
        List<ViewDeliveryOrderEntity> entityList = viewDeliveryOrderRepository.findAll(createSpecification(viewDeliveryOrderQuery), sort);
        return entityList;
    }

    /**
     * 创建查询条件
     *
     * @param viewDeliveryOrderQuery
     * @return
     */
    private Specification createSpecification(ViewDeliveryOrderQuery viewDeliveryOrderQuery) {
        return new Specification<ViewDeliveryOrderEntity>() {

            @Override
            public Predicate toPredicate(Root<ViewDeliveryOrderEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();

                //省
                if (!isEmpty(viewDeliveryOrderQuery.getProvince())) {
                    predicate.add(cb.equal(root.get("installProvince").as(String.class), viewDeliveryOrderQuery.getProvince()));
                }

                //订单编号
                if (!isEmpty(viewDeliveryOrderQuery.getOrderNo())) {
                    predicate.add(cb.equal(root.get("orderNo").as(String.class), viewDeliveryOrderQuery.getOrderNo()));
                }

                //流程编号
                if (!isEmpty(viewDeliveryOrderQuery.getFollowCode())) {
                    predicate.add(cb.equal(root.get("followCode").as(String.class), viewDeliveryOrderQuery.getFollowCode()));
                }

                if (!isEmpty(viewDeliveryOrderQuery.getBeginCacle())) {
                    predicate.add(cb.equal(root.get("beginCacle").as(String.class), viewDeliveryOrderQuery.getBeginCacle()));
                }

                // 市
                if (!isEmpty(viewDeliveryOrderQuery.getCity())) {
                    predicate.add(cb.equal(root.get("installCity").as(String.class), viewDeliveryOrderQuery.getCity()));
                }

                if (SysConstant.Common.LIST_ALL_FLG_NO.equals(viewDeliveryOrderQuery.getAllOrderFlg())) {
                    if (!isEmpty(viewDeliveryOrderQuery.getUserType())) {
                        predicate.add(cb.like(root.get("positionCodes").as(String.class), "%" + viewDeliveryOrderQuery.getUserType() + "%"));
                    }
                    if (!isEmpty(viewDeliveryOrderQuery.getUserTypeWeb())) {
                        predicate.add(cb.like(root.get("positionCodes").as(String.class), "%" + viewDeliveryOrderQuery.getUserTypeWeb() + "%"));
                    }
                    //是否完成
                    predicate.add(cb.equal(root.get("finishFlg").as(String.class), SysConstant.InstallOrder.CONFIG_FLG_N));
                }

                //APP查询
                if (!isEmpty(viewDeliveryOrderQuery.getAppSearch()) && SysConstant.InstallOrder.CONFIG_FLG_Y.equals(viewDeliveryOrderQuery.getAppSearch())) {
                    if (!isEmpty(viewDeliveryOrderQuery.getUserType())) {
                        predicate.add(cb.like(root.get("positionCodes").as(String.class), "%" + viewDeliveryOrderQuery.getUserType() + "%"));
                    }
                    if (!isEmpty(viewDeliveryOrderQuery.getNoNeedStepMatrix())) {
                        predicate.add(cb.notEqual(root.get("stepMatrix").as(String.class), viewDeliveryOrderQuery.getNoNeedStepMatrix()));
                    }
                    predicate.add(cb.equal(root.get("finishFlg").as(String.class), SysConstant.InstallOrder.CONFIG_FLG_N));
                }
                //充电桩样式
                if (!isEmpty(viewDeliveryOrderQuery.getPileType())) {
                    predicate.add(cb.equal(root.get("pileType").as(String.class), viewDeliveryOrderQuery.getPileType()));
                }

                // 服务商
                if (SysConstant.Delivery.PILE_MANGER.equals(viewDeliveryOrderQuery.getType())){
                    UserEntity user = userService.findById(BaseContextHandler.getUserId());
//                    if (!isEmpty(viewDeliveryOrderQuery.getSupplierNo())) {
//                        predicate.add(cb.equal(root.get("supplierNo").as(String.class), viewDeliveryOrderQuery.getSupplierNo()));
//                    }
                    if (!isEmpty(user.getSupplierNo())) {
                        predicate.add(cb.equal(root.get("supplierNo").as(String.class), user.getSupplierNo()));
                    }
                }else if (SysConstant.Delivery.DELIVERY.equals(viewDeliveryOrderQuery.getType())){
                    if (!isEmpty(viewDeliveryOrderQuery.getSupplierNo())) {
                        predicate.add(cb.equal(root.get("supplierNo").as(String.class), viewDeliveryOrderQuery.getSupplierNo()));
                    }
                }


                // 步骤号
                if (!isEmpty(viewDeliveryOrderQuery.getFollowSeq())) {
                    predicate.add(cb.equal(root.get("followSeq").as(Integer.class), viewDeliveryOrderQuery.getFollowSeq()));
                }

                // 已完成
                if (!isEmpty(viewDeliveryOrderQuery.getFinishFlg())) {
                    predicate.add(cb.equal(root.get("finishFlg").as(String.class), viewDeliveryOrderQuery.getFinishFlg()));
                }

                //车型查询
                if (!StringUtils.isEmpty(viewDeliveryOrderQuery.getCarVehicle())) {
                    predicate.add(cb.equal(root.get("carVehicle").as(String.class), viewDeliveryOrderQuery.getCarVehicle()));
                }

                //经销商名称
                if (!StringUtils.isEmpty(viewDeliveryOrderQuery.getDealerName())) {
                    predicate.add(cb.like(root.get("dealerName").as(String.class), "%" + viewDeliveryOrderQuery.getDealerName() + "%"));
                }

                //签收状态
                if (!StringUtils.isEmpty(viewDeliveryOrderQuery.getReceiveStatus())) {
                    predicate.add(cb.equal(root.get("receiveStatus").as(String.class), viewDeliveryOrderQuery.getReceiveStatus()));
                }

                //多条件查询
                if (!isEmpty(viewDeliveryOrderQuery.getMultipleQuery())) {
                    predicate.add(cb.or(
                            cb.like(root.get("orderNo").as(String.class), viewDeliveryOrderQuery.getMultipleQuery()),
                            cb.like(root.get("vinNo").as(String.class), viewDeliveryOrderQuery.getMultipleQuery()),
                            cb.like(root.get("carOwner").as(String.class), viewDeliveryOrderQuery.getMultipleQuery()),
                            cb.like(root.get("carOwnerPhone").as(String.class), viewDeliveryOrderQuery.getMultipleQuery()),
                            cb.like(root.get("installContact").as(String.class), viewDeliveryOrderQuery.getMultipleQuery()),
                            cb.like(root.get("installContactPhone").as(String.class), viewDeliveryOrderQuery.getMultipleQuery())
                    ));
                }


                //订单创建时间
                if (!org.springframework.util.StringUtils.isEmpty(viewDeliveryOrderQuery.getOrderStartTime())) {
                    //大于或等于创建时间
                    predicate.add(cb.greaterThanOrEqualTo(root.get("orderTime").as(Date.class), viewDeliveryOrderQuery.getOrderStartTime()));
                }
                if (!org.springframework.util.StringUtils.isEmpty(viewDeliveryOrderQuery.getOrderOverTime())) {
                    //小于或等于修改时间
                    predicate.add(cb.lessThanOrEqualTo(root.get("orderTime").as(Date.class), viewDeliveryOrderQuery.getOrderOverTime()));
                }

                //配送完成创建时间
                if (!org.springframework.util.StringUtils.isEmpty(viewDeliveryOrderQuery.getDeliveryStartTime())) {
                    //大于或等于创建时间
                    predicate.add(cb.greaterThanOrEqualTo(root.get("deliveryFinishTime").as(Date.class), viewDeliveryOrderQuery.getDeliveryStartTime()));
                }
                if (!org.springframework.util.StringUtils.isEmpty(viewDeliveryOrderQuery.getDeliveryOverTime())) {
                    //小于或等于修改时间
                    predicate.add(cb.lessThanOrEqualTo(root.get("deliveryFinishTime").as(Date.class), viewDeliveryOrderQuery.getDeliveryOverTime()));
                }

                //订单完成时间
                if (!org.springframework.util.StringUtils.isEmpty(viewDeliveryOrderQuery.getFinishStartTime())) {
                    //大于或等于创建时间
                    predicate.add(cb.greaterThanOrEqualTo(root.get("orderFinishTime").as(Date.class), viewDeliveryOrderQuery.getFinishStartTime()));
                }
                if (!org.springframework.util.StringUtils.isEmpty(viewDeliveryOrderQuery.getFinishOverTime())) {
                    //小于或等于修改时间
                    predicate.add(cb.lessThanOrEqualTo(root.get("orderFinishTime").as(Date.class), viewDeliveryOrderQuery.getFinishOverTime()));
                }

                //派单时间
                if (!isEmpty(viewDeliveryOrderQuery.getDispatchStartTime())) {
                    //大于或等于创建时间
                    predicate.add(cb.greaterThanOrEqualTo(root.get("dispatchTime").as(Date.class), viewDeliveryOrderQuery.getDispatchStartTime()));
                }
                if (!isEmpty(viewDeliveryOrderQuery.getDispatchOverTime())) {
                    //小于或等于修改时间
                    predicate.add(cb.lessThanOrEqualTo(root.get("dispatchTime").as(Date.class), viewDeliveryOrderQuery.getDispatchOverTime()));
                }

                //销售日期
                if (!isEmpty(viewDeliveryOrderQuery.getSaleDateStart())) {
                    //大于或等于报修时间开始
                    predicate.add(cb.greaterThanOrEqualTo(root.get("saleDate").as(Date.class), viewDeliveryOrderQuery.getSaleDateStart()));
                }
                if (!isEmpty(viewDeliveryOrderQuery.getSaleDateEnd())) {
                    //小于或等于报修时间结束
                    predicate.add(cb.lessThanOrEqualTo(root.get("saleDate").as(Date.class), viewDeliveryOrderQuery.getSaleDateEnd()));
                }

                //服务商确认时间
                if (!StringUtils.isEmpty(viewDeliveryOrderQuery.getSupplierConfirmTimeStart())) {
                    //大于或等于创建时间
                    predicate.add(cb.greaterThanOrEqualTo(root.get("supplierConfirmTime").as(Date.class), viewDeliveryOrderQuery.getSupplierConfirmTimeStart()));
                }
                if (!StringUtils.isEmpty(viewDeliveryOrderQuery.getSupplierConfirmTimeEnd())) {
                    //小于或等于修改时间
                    predicate.add(cb.lessThanOrEqualTo(root.get("supplierConfirmTime").as(Date.class), viewDeliveryOrderQuery.getSupplierConfirmTimeEnd()));
                }

                // 车型详细
                if (!isEmpty(viewDeliveryOrderQuery.getVehicleDetail())) {
                    predicate.add(cb.equal(root.get("vehicleDetail").as(String.class), viewDeliveryOrderQuery.getVehicleDetail()));
                }

                //步骤矩阵
                if (!StringUtils.isEmpty(viewDeliveryOrderQuery.getStepMatrix())) {
                    predicate.add(cb.equal(root.get("stepMatrix").as(String.class), viewDeliveryOrderQuery.getStepMatrix()));
                }

                //职位
                // if (!isEmpty(viewDeliveryOrderQuery.getUserPosition())) {
                //     predicate.add(cb.like(root.get("positionCodes").as(String.class), "%" + viewDeliveryOrderQuery.getUserPosition() + "%"));
                // }
                // if (!isEmpty(viewDeliveryOrderQuery.getUserType())) {
                //     predicate.add(cb.like(root.get("positionCodes").as(String.class), "%" + viewDeliveryOrderQuery.getUserType() + "%"));
                // }

                //公司
                if (!isEmpty(viewDeliveryOrderQuery.getCorpNo())) {
                    predicate.add(cb.equal(root.get("corpNo").as(String.class), viewDeliveryOrderQuery.getCorpNo()));
                } else {
                    predicate.add(cb.equal(root.get("corpNo").as(String.class), BaseContextHandler.getCorpNo()));
                }

                //是否结算
                if (!StringUtils.isEmpty(viewDeliveryOrderQuery.getSettleFlg())) {
                    predicate.add(cb.equal(root.get("settleFlg").as(String.class), viewDeliveryOrderQuery.getSettleFlg()));
                }

                //VIN
                if (!StringUtils.isEmpty(viewDeliveryOrderQuery.getVinNo())) {
                    predicate.add(cb.equal(root.get("vinNo").as(String.class), viewDeliveryOrderQuery.getVinNo()));
                }

                //筛选已删除
                predicate.add(cb.equal(root.get("isDel").as(String.class), SysConstant.IS_DEL_N));

                Predicate[] p = new Predicate[predicate.size()];
                return cb.and(predicate.toArray(p));
            }

        };
    }

    /**
     * 新建
     *
     * @param entity
     * @return
     */
    @Override
    public ViewDeliveryOrderEntity add(ViewDeliveryOrderEntity entity) {
        viewDeliveryOrderRepository.saveAndFlush(entity);
        return entity;
    }

    /**
     * 更新
     *
     * @param entity
     * @return
     */
    @Override
    public ViewDeliveryOrderEntity edit(ViewDeliveryOrderEntity entity) {
        viewDeliveryOrderRepository.saveAndFlush(entity);
        return entity;
    }

    /**
     * 删除（逻辑删除）
     *
     * @param id
     * @return
     */
    @Override
    public boolean delete(String id) {
        ViewDeliveryOrderEntity entity = viewDeliveryOrderRepository.findById(SysConstant.IS_DEL_N, id);
        if (entity == null) {
            throw new AppException(CustomMessage.NO_DATA_FIND);
        }
        //删除标记
        viewDeliveryOrderRepository.delete(SysConstant.IS_DEL_Y, id);
        return true;
    }

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    @Override
    public ViewDeliveryOrderEntity findById(String id) {
        ViewDeliveryOrderEntity entity = viewDeliveryOrderRepository.findById(SysConstant.IS_DEL_N, id);
        return entity;
    }

    /**
     * 根据id校验
     *
     * @param id
     * @return
     */
    @Override
    public ViewDeliveryOrderEntity checkById(String id) {
        ViewDeliveryOrderEntity entity = viewDeliveryOrderRepository.findById(SysConstant.IS_DEL_N, id);
        if (entity == null) {
            throw new AppException(CustomMessage.NO_DATA_FIND);
        }
        return entity;
    }

    /**
     * 根据编号查找
     *
     * @param no
     * @return
     */
    @Override
    public ViewDeliveryOrderEntity findByNo(String no) {
        ViewDeliveryOrderEntity entity = viewDeliveryOrderRepository.findByNo(SysConstant.IS_DEL_N, no);
        if (entity != null) {
            // List<OrderAttachEntity> list = orderAttachService.findByOrderNo(entity.getOrderNo());
            OrderAttachQuery orderAttachQuery = new OrderAttachQuery();
            orderAttachQuery.setOrderNo(entity.getOrderNo());
            orderAttachQuery.setSort("+createdWhen");
            List<OrderAttachEntity> list = orderAttachService.findAll(orderAttachQuery);
            if (list != null && list.size() > 0) {
                for (OrderAttachEntity orderAttachEntity : list) {
                    orderAttachEntity.setAttachPath(SysConstant.ATTACH_VISIT_PERF + orderAttachEntity.getAttachPath());
                }
                entity.setOrderAttachEntityList(list);
            }
        }
        return entity;
    }


}

