package cn.backend.service.complainorder;

import cn.backend.config.constant.SysConstant;
import cn.backend.config.message.CustomMessage;
import cn.backend.dao.primary.complainorder.ComplainOrderRepository;
import cn.backend.model.primary.adviceorder.AdviceEntity;
import cn.backend.model.primary.complainorder.ComplainOrderEntity;
import cn.backend.model.primary.complainorder.ComplainOrderQuery;
import cn.backend.model.primary.delivery.DeliveryOrderEntity;
import cn.backend.model.primary.installorder.InstallOrderEntity;
import cn.backend.model.primary.ordercar.OrderCarEntity;
import cn.backend.model.primary.orderstep.OrderStepEntity;
import cn.backend.model.primary.orderstep.OrderStepParameter;
import cn.backend.model.primary.scrminfo.ScrmInfoEntity;
import cn.backend.model.primary.supplier.SupplierEntity;
import cn.backend.model.primary.user.UserEntity;
import cn.backend.model.primary.workfollow.WorkFollowEntity;
import cn.backend.model.primary.workfollowdetail.WorkFollowDetailEntity;
import cn.backend.model.primary.workfollowdetail.WorkFollowDetailQuery;
import cn.backend.service.BaseService;
import cn.backend.service.config.IConfigService;
import cn.backend.service.delivery.IDeliveryOrderService;
import cn.backend.service.installorder.IInstallOrderService;
import cn.backend.service.ordercar.IOrderCarService;
import cn.backend.service.orderstep.IOrderStepService;
import cn.backend.service.scrminfo.IScrmInfoService;
import cn.backend.service.supplier.ISupplierService;
import cn.backend.service.user.IUserService;
import cn.backend.service.workfollow.IWorkFollowService;
import cn.backend.service.workfollowdetail.IWorkFollowDetailService;
import cn.zdwl.common.context.BaseContextHandler;
import cn.zdwl.common.exception.AppException;
import cn.zdwl.common.util.BeanCopyUtil;
import cn.zdwl.common.util.CompareObj;
import cn.zdwl.common.util.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author sunkw
 * @date 2019/07/17
 */
@Service(value = "complainOrderService")
public class ComplainOrderService extends BaseService implements IComplainOrderService {

    @Resource
    private ComplainOrderRepository complainOrderRepository;

    @Autowired
    private IOrderCarService orderCarService;

    @Autowired
    private IOrderStepService orderStepService;


    @Autowired
    private ISupplierService supplierService;

    @Autowired
    private IWorkFollowService workFollowService;

    @Autowired
    private IWorkFollowDetailService workFollowDetailService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IConfigService configService;

    @Autowired
    private IScrmInfoService scrmInfoService;


    @Autowired
    private IInstallOrderService installOrderService;

    @Autowired
    private IDeliveryOrderService deliveryOrderService;

    /**
     * 分页查询
     *
     * @param complainOrderQuery
     * @return
     */
    @Override
    public Page<ComplainOrderEntity> findList(ComplainOrderQuery complainOrderQuery) {
        Sort sort = buildSort(complainOrderQuery.getSort());
        Pageable pageable = new PageRequest(complainOrderQuery.getPageNum(), complainOrderQuery.getPageSize(), sort);
        Page<ComplainOrderEntity> entityPage = complainOrderRepository.findAll(createSpecification(complainOrderQuery), pageable);
        return entityPage;
    }

    /**
     * 列表查询
     *
     * @param complainOrderQuery
     * @return
     */
    @Override
    public List<ComplainOrderEntity> findAll(ComplainOrderQuery complainOrderQuery) {
        Sort sort = buildSort(complainOrderQuery.getSort());
        List<ComplainOrderEntity> entityList = complainOrderRepository.findAll(createSpecification(complainOrderQuery), sort);
        return entityList;
    }

    /**
     * 创建查询条件
     *
     * @param complainOrderQuery
     * @return
     */
    private Specification createSpecification(ComplainOrderQuery complainOrderQuery) {
        return new Specification<ComplainOrderEntity>() {

            @Override
            public Predicate toPredicate(Root<ComplainOrderEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();

                if (!isEmpty(complainOrderQuery.getOrderNo())) {
                    predicate.add(cb.equal(root.get("orderNo").as(String.class), complainOrderQuery.getOrderNo()));
                }

                //投诉日期
                if (!isEmpty(complainOrderQuery.getComplainTimeStart())) {
                    //大于或等于安装完成日期开始
                    predicate.add(cb.greaterThanOrEqualTo(root.get("complainTime").as(Date.class), complainOrderQuery.getComplainTimeStart()));
                }
                if (!isEmpty(complainOrderQuery.getComplainTimeEnd())) {
                    //小于或等于安装完成日期结束
                    predicate.add(cb.lessThanOrEqualTo(root.get("complainTime").as(Date.class), complainOrderQuery.getComplainTimeEnd()));
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
    public ComplainOrderEntity add(ComplainOrderEntity entity) {
        //新增报修订单
        String supplierNo = "";
        String supplierName = "";
        entity.setComplainTime(new Date());
        entity.setFinishFlg(SysConstant.InstallOrder.FINISH_FLG_N);
        entity.setComplainNo(SysConstant.Complain.PREFIX_NUMBER + IdUtils.getGenerateNumber());
        //设置服务商编码
        if (!isEmpty(entity.getScrmNo())) {
            OrderCarEntity orderCarEntity = orderCarService.findByVinNoAndCorp(entity.getVinNo(), entity.getCorpNo());
            if (orderCarEntity != null) {
                InstallOrderEntity installOrderEntity = installOrderService.findByNo(orderCarEntity.getOrderNo());
                if (installOrderEntity != null) {
                    supplierNo = installOrderEntity.getSupplierNo();
                    supplierName = installOrderEntity.getSupplierName();
                } else {
                    DeliveryOrderEntity deliveryOrderEntity = deliveryOrderService.findByNo(orderCarEntity.getOrderNo());
                    if (deliveryOrderEntity != null) {
                        supplierNo = deliveryOrderEntity.getSupplierNo();
                        supplierName = deliveryOrderEntity.getSupplierName();
                    }
                }
                entity.setCarOwner(orderCarEntity.getCarOwner());
                entity.setCarOwnerPhone(orderCarEntity.getCarOwnerPhone());
            }
            entity.setSupplierNo(supplierNo);
            entity.setSupplierName(supplierName);
        }
        configService.convert(entity);
        complainOrderRepository.saveAndFlush(entity);
        //初始化订单步骤
        OrderStepParameter orderStepParameter = new OrderStepParameter();
        orderStepParameter.setOrderNo(entity.getComplainNo());
        if (!isEmpty(entity.getScrmNo())) {
            orderStepParameter.setFollowNo(SysConstant.WorkFollow.WORKFOLLOW_AFTERSELL_NO);
        } else {
            orderStepParameter.setFollowNo(SysConstant.WorkFollow.WORKFOLLOW_IN_AFTERSELL_NO);
        }

        orderStepService.orderStepAdd(orderStepParameter);
        return entity;
    }

    /**
     * 更新
     *
     * @param entity
     * @return
     */
    @Override
    public ComplainOrderEntity edit(ComplainOrderEntity entity) {
        if (!isEmpty(entity.getSupplierNo())) {
            SupplierEntity supplierEntity = supplierService.findByNo(entity.getSupplierNo());
            if (supplierEntity != null) {
                entity.setSupplierName(supplierEntity.getSupplierName());
            }
        }
        if (!isEmpty(entity.getSolutionSupplierNo())) {
            SupplierEntity supplierEntity = supplierService.findByNo(entity.getSolutionSupplierNo());
            if (supplierEntity != null) {
                entity.setSolutionSupplierName(supplierEntity.getSupplierName());
            }
        }

        OrderStepParameter orderStepParameter = new OrderStepParameter();
        ComplainOrderEntity oldEntity = findById(entity.getId());
        //校验

        BeanCopyUtil.beanCopy(entity, oldEntity);

        //是否审核（签收）
        OrderStepEntity orderStepEntity;

        //超时开始结束时间
        WorkFollowDetailQuery workFollowDetailQuery = new WorkFollowDetailQuery();
        workFollowDetailQuery.setFollowNo(SysConstant.WorkFollow.WORKFOLLOW_AFTERSELL_NO);
        workFollowDetailQuery.setSort("+followSeq");
        List<WorkFollowDetailEntity> workFollowDetailEntityList = workFollowDetailService.findAll(workFollowDetailQuery);

        OrderStepEntity currentOrderStep = orderStepService.findLastByOrderNo(oldEntity.getComplainNo(), SysConstant.WorkFollow.CONFIG_WORKFOLLOW_AFTERSELL);
        if (currentOrderStep != null) {
            switch (currentOrderStep.getFollowSeq()) {
                case SysConstant.Complain.CONTACT_SEQ:
                    WorkFollowDetailEntity contactWork = workFollowDetailEntityList.stream()
                            .filter(x -> SysConstant.InstallOrder.CONFIG_FLG_Y.equals(x.getTimeOutFlg()) && SysConstant.WorkFollow.WORKFOLLOW_TIMEOUT_TYPE07.equals(x.getTimeoutType()))
                            .findFirst().orElse(null);
                    //联系客户
                    setBaseAndEndTime(contactWork, oldEntity, orderStepParameter, SysConstant.WorkFollow.WORKFOLLOW_TIMEOUT_TYPE07);
                    break;
            }
        }

        if (SysConstant.Advice.RECEIVE_PASS.equals(entity.getReceiveStatus())) {
            //签收通过或非签收步骤

            orderStepParameter.setOrderNo(oldEntity.getComplainNo());
            orderStepParameter.setResultDesc(entity.getRemark());
            // orderStepParameter.setBaseTime(entity.getBaseTime());
            // orderStepParameter.setEndTime(entity.getEndTime());
            orderStepParameter.setTimeOutType(SysConstant.OrderStep.TIMEOUT_TYPE_WORKDAYHOUR);
            if (!isEmpty(entity.getScrmNo())) {
                orderStepParameter.setFollowNo(SysConstant.WorkFollow.WORKFOLLOW_AFTERSELL_NO);
            } else {
                orderStepParameter.setFollowNo(SysConstant.WorkFollow.WORKFOLLOW_IN_AFTERSELL_NO);
            }
            orderStepEntity = orderStepService.orderStepAdd(orderStepParameter);
        } else {
            //签收不通过
            orderStepParameter.setOrderNo(oldEntity.getComplainNo());
            orderStepParameter.setResultDesc(entity.getRemark());
            if (!isEmpty(entity.getScrmNo())) {
                orderStepParameter.setFollowNo(SysConstant.WorkFollow.WORKFOLLOW_AFTERSELL_NO);
            } else {
                orderStepParameter.setFollowNo(SysConstant.WorkFollow.WORKFOLLOW_IN_AFTERSELL_NO);
            }
            orderStepEntity = orderStepService.orderStepRejct(orderStepParameter);
            entity.setSolutionSupplierNo("");
            entity.setSolutionSupplierName("");

        }
        //如果是最后一步，结束标志置Y
        if (SysConstant.OrderStep.LAST_STEP_Y.equals(orderStepEntity.getLastStep())) {
            oldEntity.setFinishFlg(SysConstant.InstallOrder.FINISH_FLG_Y);
            oldEntity.setFinishTime(new Date());

            if (!isEmpty(oldEntity.getReceiveTime())) {
                scrmInfoService.caseResult(oldEntity);
            }

            // complainOrderRepository.saveAndFlush(oldEntity);
        }


        configService.convert(oldEntity);
        complainOrderRepository.saveAndFlush(oldEntity);

        return entity;
    }

    private void setBaseAndEndTime(WorkFollowDetailEntity workFollowDetailEntity, ComplainOrderEntity complainOrderEntity, OrderStepParameter orderStepParameter, String overType) {
        //判断接单超时
        if (workFollowDetailEntity != null && !isEmpty(complainOrderEntity)) {
            Date baseTime = new Date();
            Date endTime = new Date();
            switch (overType) {
                case SysConstant.WorkFollow.WORKFOLLOW_TIMEOUT_TYPE07:
                    baseTime = complainOrderEntity.getReceiveTime();
                    // if (!isEmpty(complainOrderEntity.getContactCustTime())) {
                    endTime = complainOrderEntity.getContactCustTime();
                    // }
                    break;
            }
            orderStepParameter.setBaseTime(baseTime);
            orderStepParameter.setEndTime(endTime);
        }
    }

    @Override
    public ComplainOrderEntity saveOrUpdate(ScrmInfoEntity scrmInfoEntity) {

        ComplainOrderEntity complain = complainOrderRepository.findByScrmNo(SysConstant.IS_DEL_N, scrmInfoEntity.getScrmNo());
        if (complain == null) {
            complain = new ComplainOrderEntity();
            complain.setComplainNo(SysConstant.Complain.PREFIX_NUMBER + IdUtils.getGenerateNumber());
            complain.setCorpNo(scrmInfoEntity.getCorpNo());

            List<OrderCarEntity> orderCars = orderCarService.findListByVinNo(scrmInfoEntity.getVin());
            if (orderCars.size() > 0) {
                complain.setOrderNo(orderCars.get(0).getOrderNo());
                complain.setComplainTime(scrmInfoEntity.getCreateTime());
            }
        }

        complain.setVinNo(scrmInfoEntity.getVin());
        complain.setCarOwner(scrmInfoEntity.getCustName());
        complain.setCarOwnerPhone(scrmInfoEntity.getCustTel());
//        complain.setComplainDescName(scrmInfoEntity.getContentDesc());
        complain.setScrmNo(scrmInfoEntity.getScrmNo());
        complain.setScrmContentDesc(scrmInfoEntity.getContentDesc());
        if (scrmInfoEntity.isNew()) {
            submitOrder(complain);
        }
        return complain;
    }


    /**
     * 提交订单
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional
    public ComplainOrderEntity submitOrder(ComplainOrderEntity entity) {
        entity.setFinishFlg(SysConstant.InstallOrder.CONFIG_FLG_N);
        entity.setComplainTime(new Date());
        //新增安装订单
        add(entity);
        //判断是内部订单还是外部订单
        int initFollowSeq;
        if (isEmpty(entity.getScrmNo())) {
            //内部
            initFollowSeq = workFollowDetailService.getMinStep(SysConstant.WorkFollow.WORKFOLLOW_AFTERSELL_NO).getFollowSeq();
        } else {
            //外部
            initFollowSeq = workFollowDetailService.getMinStep(SysConstant.WorkFollow.WORKFOLLOW_AFTERSELL_NO).getFollowSeq();
        }
        //初始化订单步骤记录
        OrderStepEntity orderStepEntity = orderStepService.workFollow(SysConstant.WorkFollow.CONFIG_WORKFOLLOW_AFTERSELL
                , initFollowSeq);
        orderStepEntity.setOrderNo(entity.getOrderNo());
        orderStepEntity.setStepSeq(1);
        orderStepService.add(orderStepEntity);
        return entity;
    }

    /**
     * 保存
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional
    public ComplainOrderEntity saveOrderAndStep(ComplainOrderEntity entity) {
        //改变当前orderstep
        ComplainOrderEntity oldEntity = checkById(entity.getId());
        //获取最新的订单步骤记录
        OrderStepEntity lastOrderStepEntity = orderStepService.findLastByOrderNo(entity.getOrderNo(), SysConstant.WorkFollow.CONFIG_WORKFOLLOW_AFTERSELL);
        if (!isEmpty(entity.getBaseTime())) {
            lastOrderStepEntity.setBaseTime(entity.getBaseTime());
        } else {
            lastOrderStepEntity.setBaseTime(new Date());
        }
        CompareObj<AdviceEntity> compareObj = new CompareObj<>();
        String compare = compareObj.compare(oldEntity, entity);
        lastOrderStepEntity.setStepRemark(compare);
        //新值赋值
        BeanCopyUtil.beanCopy(entity, oldEntity);
        orderStepService.edit(lastOrderStepEntity);
        return edit(oldEntity);
    }

    /**
     * 订单工作流
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional
    public ComplainOrderEntity orderWorkFollow(ComplainOrderEntity entity) {
        //获取最新的订单步骤记录
        OrderStepEntity lastOrderStepEntity = orderStepService.findLastByOrderNo(entity.getOrderNo(), SysConstant.WorkFollow.CONFIG_WORKFOLLOW_AFTERSELL);
        //校验
        verify(entity, lastOrderStepEntity);
        ComplainOrderEntity oldEntity = checkById(entity.getId());
        BeanCopyUtil.beanCopy(entity, oldEntity);
        //获取此步骤工作流
        WorkFollowEntity workFollowEntity = workFollowService.findByCode(SysConstant.WorkFollow.CONFIG_WORKFOLLOW_AFTERSELL);
        WorkFollowDetailEntity workFollowDetailEntity = workFollowEntity.getWorkFollowDetailEntityList()
                .stream().filter(e -> e.getFollowSeq().equals(lastOrderStepEntity.getFollowSeq())).findFirst().orElse(null);
        //该步是否撤回
        if (!isEmpty(entity.getRollBackFlg()) && SysConstant.InstallOrder.CONFIG_FLG_Y.equals(entity.getRollBackFlg())) {
            //校验目前是否可以撤回
            if (workFollowDetailEntity != null && SysConstant.InstallOrder.CONFIG_FLG_Y.equals(workFollowDetailEntity.getRollbackFlg())) {
                edit(oldEntity);
                //初始化新订单步骤记录
                orderStep(workFollowDetailEntity.getRollbackSeq(), oldEntity, lastOrderStepEntity);
                //回撤备注
                lastOrderStepEntity.setStepRemark(entity.getRemark());
                orderStepService.edit(lastOrderStepEntity);
            } else {
                throw new AppException(CustomMessage.CAN_NOT_OPERATE);
            }
        } else {
            //更新安装订单
            edit(oldEntity);
            //取出下一步步骤号，如果是当前步骤已经最大则取最大
            int maxSeq = workFollowDetailService.getMaxStep(workFollowEntity.getFollowNo()).getFollowSeq();
            int nextSeq;
            if (maxSeq == workFollowDetailEntity.getFollowSeq()) {
                nextSeq = workFollowDetailEntity.getFollowSeq();
            } else {
                nextSeq = workFollowDetailService.getNextStep(workFollowEntity.getFollowNo(), workFollowDetailEntity.getFollowSeq()).getFollowSeq();
            }
            orderStep(nextSeq, oldEntity, lastOrderStepEntity);
            //超时
            if (SysConstant.InstallOrder.CONFIG_FLG_Y.equals(workFollowDetailEntity.getTimeOutFlg())) {
                // int minutes = orderStepService.judgeTimeoutHoursWithWorking(workFollowDetailEntity.getTimeOutRadio(), entity.getComplainTime(), entity.getTimeoutEndTime(), SysConstant.Advice.WORKDAY_STARTTIME, SysConstant.Advice.WORKDAY_ENDTIME);
                int minutes = 0;
                if (minutes == 0) {
                    //未超时
                    lastOrderStepEntity.setTimeoutStatus(SysConstant.OrderStep.CONFIG_TYPE_TIMEOUT_N);
                    lastOrderStepEntity.setOutTime(minutes);
                } else {
                    //超时
                    lastOrderStepEntity.setTimeoutStatus(SysConstant.OrderStep.CONFIG_TYPE_TIMEOUT_Y);
                    lastOrderStepEntity.setOutTime(minutes);
                }
                orderStepService.edit(lastOrderStepEntity);
            }
        }
        return oldEntity;
    }

    /**
     * 校验
     *
     * @param complainOrderEntity
     */
    private void verify(ComplainOrderEntity complainOrderEntity, OrderStepEntity lastOrderStepEntity) {
        //判断是否为最后一步，如果是，不可操作
        if (SysConstant.OrderStep.CURRENT_STEP_Y.equals(lastOrderStepEntity.getLastStep())) {
            ComplainOrderEntity checkEntity = findById(complainOrderEntity.getId());
            if (SysConstant.InstallOrder.CONFIG_FLG_Y.equals(checkEntity.getFinishFlg())) {
                throw new AppException(CustomMessage.CAN_NOT_OPERATE);
            }
        }
        //判断是否开始作废
        // if(isEmpty(installOrderEntity.getBeginCacle()) && SysConstant.InstallOrder.CONFIG_FLG_Y.equals(installOrderEntity.getBeginCacle())){
        //     throw new AppException(CustomMessage.CAN_NOT_OPERATE);
        // }
        //判断当前步骤是否一致
        if (isEmpty(complainOrderEntity.getCurrentStep())
                || !complainOrderEntity.getCurrentStep().equals(lastOrderStepEntity.getFollowSeq())) {
            throw new AppException(CustomMessage.CAN_NOT_OPERATE);
        }
        //判断该人员是否有权限操作
        String position = Optional
                .ofNullable(lastOrderStepEntity)
                .orElseThrow(() -> new AppException(CustomMessage.NO_DATA_FIND))
                .getPositionCodes();
        UserEntity userEntity = userService.findById(BaseContextHandler.getUserId());
        if (userEntity == null) {
            throw new AppException(CustomMessage.NO_DATA_FIND);
        }
        //无权限不能操作
        if (!position.contains(userEntity.getUserType())) {
            throw new AppException(CustomMessage.CAN_NOT_OPERATE);
        }
    }

    /**
     * 处理订单步骤
     *
     * @param followStep
     * @param complainOrderEntity
     * @param lastOrderStepEntity
     * @return
     */
    private OrderStepEntity orderStep(int followStep, ComplainOrderEntity complainOrderEntity, OrderStepEntity lastOrderStepEntity) {
        OrderStepEntity newOrderStepEntity;
        //获取用户
        UserEntity userEntity = userService.findById(BaseContextHandler.getUserId());
        if (userEntity != null) {
            lastOrderStepEntity.setStepUserNo(userEntity.getUserNo());
            lastOrderStepEntity.setStepUserName(userEntity.getUserName());
        }
        WorkFollowDetailEntity maxWorkFollowDetailEntity = workFollowDetailService.getMaxStep(SysConstant.WorkFollow.WORKFOLLOW_AFTERSELL_NO);
        int stepNums = maxWorkFollowDetailEntity.getFollowSeq();
        if (stepNums != followStep || lastOrderStepEntity.getFollowSeq() != stepNums) {
            //初始化新订单步骤记录
            newOrderStepEntity = orderStepService.workFollow(SysConstant.WorkFollow.CONFIG_WORKFOLLOW_AFTERSELL
                    , followStep);
            newOrderStepEntity.setOrderNo(complainOrderEntity.getOrderNo());
            //业务流程在之前的步骤上加一
            newOrderStepEntity.setStepSeq(lastOrderStepEntity.getStepSeq() + 1);
            //上一订单步骤 是否当前步骤置N
            lastOrderStepEntity.setCurrentStepFlg(SysConstant.OrderStep.CURRENT_STEP_N);
            orderStepService.edit(lastOrderStepEntity);
            orderStepService.add(newOrderStepEntity);
        } else {
            //如果步骤是最后一步，不生成新步骤
            newOrderStepEntity = lastOrderStepEntity;
        }
        return newOrderStepEntity;
    }


    /**
     * 删除（逻辑删除）
     *
     * @param id
     * @return
     */
    @Override
    public boolean delete(String id) {
        ComplainOrderEntity entity = complainOrderRepository.findById(SysConstant.IS_DEL_N, id);
        if (entity == null) {
            throw new AppException(CustomMessage.NO_DATA_FIND);
        }
        //删除标记
        complainOrderRepository.delete(SysConstant.IS_DEL_Y, id);
        return true;
    }

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    @Override
    public ComplainOrderEntity findById(String id) {
        ComplainOrderEntity entity = complainOrderRepository.findById(SysConstant.IS_DEL_N, id);
        return entity;
    }

    /**
     * 根据id校验
     *
     * @param id
     * @return
     */
    @Override
    public ComplainOrderEntity checkById(String id) {
        ComplainOrderEntity entity = complainOrderRepository.findById(SysConstant.IS_DEL_N, id);
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
    public ComplainOrderEntity findByNo(String no) {
        ComplainOrderEntity entity = complainOrderRepository.findByNo(SysConstant.IS_DEL_N, no);
        return entity;
    }

    /**
     * 报修订单关闭
     *
     * @param entity
     * @return
     */
    @Override
    public ComplainOrderEntity closeComplain(ComplainOrderEntity entity) {
        ComplainOrderEntity oldEntity = findById(entity.getId());
        //校验
        verifyOrder(oldEntity);
        BeanCopyUtil.beanCopy(entity, oldEntity);
        OrderStepEntity orderStepEntity;
        //签收不通过
        OrderStepParameter orderStepParameter = new OrderStepParameter();
        orderStepParameter.setOrderNo(oldEntity.getComplainNo());
        orderStepParameter.setResultDesc(entity.getRemark());
        orderStepParameter.setFollowNo(SysConstant.WorkFollow.WORKFOLLOW_AFTERSELL_NO);
        orderStepEntity = orderStepService.orderStepFinish(orderStepParameter);

        //如果是最后一步，结束标志置Y
        if (SysConstant.OrderStep.LAST_STEP_Y.equals(orderStepEntity.getLastStep())) {
            oldEntity.setFinishFlg(SysConstant.InstallOrder.FINISH_FLG_Y);
            oldEntity.setFinishTime(new Date());
            scrmInfoService.caseResult(oldEntity);
        }
        configService.convert(oldEntity);
        complainOrderRepository.saveAndFlush(oldEntity);
        return oldEntity;
    }

    /**
     * 校验
     *
     * @param checkEntity
     */
    private void verifyOrder(ComplainOrderEntity checkEntity) {
        //判断是否完成
        if (SysConstant.InstallOrder.CONFIG_FLG_Y.equals(checkEntity.getFinishFlg())) {
            throw new AppException(CustomMessage.CAN_NOT_OPERATE);
        }
    }


}

