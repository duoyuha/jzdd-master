package cn.backend.service.installorderview;

import cn.backend.config.constant.SysConstant;
import cn.backend.config.message.CustomMessage;
import cn.backend.dao.primary.installorderview.InstallOrderViewRepository;
import cn.backend.model.primary.installorder.InstallOrderQuery;
import cn.backend.model.primary.orderattach.OrderAttachEntity;
import cn.backend.model.primary.orderattach.OrderAttachQuery;
import cn.backend.model.primary.orderstep.OrderStepEntity;
import cn.backend.model.primary.orderstep.OrderStepQuery;
import cn.backend.model.primary.settle.SettleEntity;
import cn.backend.model.primary.settledetail.SettleDetailEntity;
import cn.backend.model.primary.settledetail.SettleDetailQuery;
import cn.backend.model.primary.user.UserEntity;
import cn.backend.model.primary.viewinstallorder.ViewInstallOrderEntity;
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
 * @date 2019/07/17
 */
@Service(value = "installOrderViewService")
public class InstallOrderViewService extends BaseService implements IInstallOrderViewService {

    @Resource
    private InstallOrderViewRepository installOrderViewRepository;

    @Autowired
    private IOrderAttachService orderAttachService;

    @Autowired
    private IWorkFollowDetailService workFollowDetailService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IOrderStepService orderStepService;

    @Autowired
    private ISettleDetailService settleDetailService;

    @Autowired
    private ISettleService settleService;


    /**
     * 分页查询
     *将查询条件改为按订单生成时间升序，订单申请时间降序
     * @param installOrderQuery
     * @return
     */
    @Override
    public Page<ViewInstallOrderEntity> findList(InstallOrderQuery installOrderQuery) {
        Sort sort = buildSort("+createdWhen,-orderTime");
        Pageable pageable = new PageRequest(installOrderQuery.getPageNum(), installOrderQuery.getPageSize(), sort);
        Page<ViewInstallOrderEntity> entityPage = installOrderViewRepository.findAll(createSpecification(installOrderQuery), pageable);
        //超时
        WorkFollowDetailQuery workFollowDetailQuery = new WorkFollowDetailQuery();
        workFollowDetailQuery.setFollowNo(SysConstant.WorkFollow.WORKFOLLOW_INSTALL_NO);
        workFollowDetailQuery.setSort("+followSeq");
        List<WorkFollowDetailEntity> workFollowDetailEntityList = workFollowDetailService.findAll(workFollowDetailQuery);
        WorkFollowDetailEntity appointWork = workFollowDetailEntityList.stream()
                .filter(x -> SysConstant.InstallOrder.CONFIG_FLG_Y.equals(x.getTimeOutFlg()) && SysConstant.WorkFollow.WORKFOLLOW_TIMEOUT_TYPE01.equals(x.getTimeoutType()))
                .findFirst().orElse(null);
        WorkFollowDetailEntity linkCust = workFollowDetailEntityList.stream()
                .filter(x -> SysConstant.InstallOrder.CONFIG_FLG_Y.equals(x.getTimeOutFlg()) && SysConstant.WorkFollow.WORKFOLLOW_TIMEOUT_TYPE02.equals(x.getTimeoutType()))
                .findFirst().orElse(null);

        WorkFollowDetailEntity check = workFollowDetailEntityList.stream()
                .filter(x -> SysConstant.InstallOrder.CONFIG_FLG_Y.equals(x.getTimeOutFlg()) && SysConstant.WorkFollow.WORKFOLLOW_TIMEOUT_TYPE03.equals(x.getTimeoutType()))
                .findFirst().orElse(null);

        WorkFollowDetailEntity install = workFollowDetailEntityList.stream()
                .filter(x -> SysConstant.InstallOrder.CONFIG_FLG_Y.equals(x.getTimeOutFlg()) && SysConstant.WorkFollow.WORKFOLLOW_TIMEOUT_TYPE04.equals(x.getTimeoutType()))
                .findFirst().orElse(null);

        for (ViewInstallOrderEntity viewInstallOrderEntity : entityPage) {
            //计算签收时间是否超时
            calcOverTime(appointWork, viewInstallOrderEntity, SysConstant.WorkFollow.WORKFOLLOW_TIMEOUT_TYPE01);

            //联系客户时间是否超时
            calcOverTime(linkCust, viewInstallOrderEntity, SysConstant.WorkFollow.WORKFOLLOW_TIMEOUT_TYPE02);

            //勘察时间是否超时
            calcOverTime(check, viewInstallOrderEntity, SysConstant.WorkFollow.WORKFOLLOW_TIMEOUT_TYPE03);

            //安装时间是否超时
            calcOverTime(install, viewInstallOrderEntity, SysConstant.WorkFollow.WORKFOLLOW_TIMEOUT_TYPE04);

            //结算状态
            if (SysConstant.InstallOrder.SETTLE_FLG_N.equals(viewInstallOrderEntity.getSettleFlg())) {
                // SettleDetailQuery settleDetailQuery = new SettleDetailQuery();
                // settleDetailQuery.setCorpNo(viewInstallOrderEntity.getCorpNo());
                // settleDetailQuery.setOrderNo(viewInstallOrderEntity.getOrderNo());
                // List<SettleDetailEntity> settleDetailEntityList = settleDetailService.findAll(settleDetailQuery);
                // if (settleDetailEntityList.size() > 0) {
                //     viewInstallOrderEntity.setSettleVerifyFlg(SysConstant.InstallOrder.CONFIG_FLG_Y);
                // }
                //20190923 现在列表需要显示是初审退回还是复审退回
//                SettleQuery settleQuery = new SettleQuery();
//                settleQuery.setOrderNo(viewInstallOrderEntity.getOrderNo());
//                settleQuery.setSort("-createdWhen");
                SettleDetailQuery detailQuery=new SettleDetailQuery();
                detailQuery.setOrderNo(viewInstallOrderEntity.getOrderNo());
                List<SettleDetailEntity> detailEntityList = settleDetailService.findAll(detailQuery);
                if (detailEntityList.size() > 0) {
                    SettleDetailEntity detailEntity = detailEntityList.stream().findFirst().orElse(null);
                    if (detailEntity.getSettleNo()!=null){
                        SettleEntity settleEntity=settleService.findByNo(detailEntity.getSettleNo());
                        if (isEmpty(settleEntity.getFirstVerifyFlg()) || SysConstant.InstallOrder.CONFIG_VERIFY_REFUSE.equals(settleEntity.getFirstVerifyFlg())) {
                            //初审不通过
                            viewInstallOrderEntity.setSettleRefuseFlg(SysConstant.InstallOrder.CONFIG_VERIFY_N);
                        } else if (isEmpty(settleEntity.getSecondVerifyFlg()) || SysConstant.InstallOrder.CONFIG_VERIFY_REFUSE.equals(settleEntity.getSecondVerifyFlg())) {
                            //复审不通过
                            viewInstallOrderEntity.setSettleRefuseFlg(SysConstant.InstallOrder.CONFIG_VERIFY_Y);
                        }
                    }
                }
            }
        }

        return entityPage;
    }

    private void calcOverTime(WorkFollowDetailEntity workFollowDetailEntity, ViewInstallOrderEntity viewInstallOrderEntity, String overType) {

        //判断接单超时
        if (workFollowDetailEntity != null && !isEmpty(viewInstallOrderEntity)) {

            int overMinutes = 0;

            switch (overType) {
                case SysConstant.WorkFollow.WORKFOLLOW_TIMEOUT_TYPE01:
                    if (!isEmpty(viewInstallOrderEntity.getDispatchTime())) {
                        if (isEmpty(viewInstallOrderEntity.getReceiveTime())) {
                            overMinutes = orderStepService.judgeTimeoutHours(workFollowDetailEntity.getTimeOutRadio(), viewInstallOrderEntity.getDispatchTime(), new Date());
                            viewInstallOrderEntity.setDispatchOverTime(overMinutes);
                            if (overMinutes > 0) {
                                viewInstallOrderEntity.setDispatchOverTimeFlg(SysConstant.InstallOrder.CONFIG_FLG_Y);
                            }
                        } else {
                            overMinutes = orderStepService.judgeTimeoutHours(workFollowDetailEntity.getTimeOutRadio(), viewInstallOrderEntity.getDispatchTime(), viewInstallOrderEntity.getReceiveTime());
                            viewInstallOrderEntity.setDispatchOverTime(overMinutes);
                            // if (overMinutes > 0) {
                            //     viewInstallOrderEntity.setDispatchOverTimeFlg(SysConstant.InstallOrder.CONFIG_FLG_Y);
                            // }
                            viewInstallOrderEntity.setDispatchOverTimeFlg(SysConstant.InstallOrder.CONFIG_FLG_N);
                        }
                    }
                    break;

                case SysConstant.WorkFollow.WORKFOLLOW_TIMEOUT_TYPE02:
                    if (!isEmpty(viewInstallOrderEntity.getReceiveTime())) {
                        if (isEmpty(viewInstallOrderEntity.getContactCustTime())) {
                            overMinutes = orderStepService.judgeTimeoutHours(workFollowDetailEntity.getTimeOutRadio(), viewInstallOrderEntity.getReceiveTime(), new Date());
                            viewInstallOrderEntity.setContractOverTime(overMinutes);
                            if (overMinutes > 0) {
                                viewInstallOrderEntity.setContractOverTimeFlg(SysConstant.InstallOrder.CONFIG_FLG_Y);
                            }
                        } else {
                            overMinutes = orderStepService.judgeTimeoutHours(workFollowDetailEntity.getTimeOutRadio(), viewInstallOrderEntity.getReceiveTime(), viewInstallOrderEntity.getContactCustTime());
                            viewInstallOrderEntity.setContractOverTime(overMinutes);
                            // if (overMinutes > 0) {
                            //     viewInstallOrderEntity.setContractOverTimeFlg(SysConstant.InstallOrder.CONFIG_FLG_Y);
                            // }
                            viewInstallOrderEntity.setContractOverTimeFlg(SysConstant.InstallOrder.CONFIG_FLG_N);
                        }
                    }
                    break;

                case SysConstant.WorkFollow.WORKFOLLOW_TIMEOUT_TYPE03:
                    if (!isEmpty(viewInstallOrderEntity.getCheckTime())) {
                        if (isEmpty(viewInstallOrderEntity.getCheckFinishTime())) {
                            overMinutes = orderStepService.judgeTimeoutHours(workFollowDetailEntity.getTimeOutRadio(), viewInstallOrderEntity.getCheckTime(), new Date());
                            viewInstallOrderEntity.setCheckOverTime(overMinutes);
                            if (overMinutes > 0) {
                                viewInstallOrderEntity.setCheckOverTimeFlg(SysConstant.InstallOrder.CONFIG_FLG_Y);
                            }
                        } else {
                            overMinutes = orderStepService.judgeTimeoutHours(workFollowDetailEntity.getTimeOutRadio(), viewInstallOrderEntity.getCheckTime(), viewInstallOrderEntity.getCheckFinishTime());
                            viewInstallOrderEntity.setDispatchOverTime(overMinutes);
                            // if (overMinutes > 0) {
                            //     viewInstallOrderEntity.setCheckOverTimeFlg(SysConstant.InstallOrder.CONFIG_FLG_Y);
                            // }
                            viewInstallOrderEntity.setDispatchOverTimeFlg(SysConstant.InstallOrder.CONFIG_FLG_N);
                        }
                    }
                    break;

                case SysConstant.WorkFollow.WORKFOLLOW_TIMEOUT_TYPE04:
                    if (!isEmpty(viewInstallOrderEntity.getInstallTime())) {
                        if (isEmpty(viewInstallOrderEntity.getInstallFinishTime())) {
                            overMinutes = orderStepService.judgeTimeoutHours(workFollowDetailEntity.getTimeOutRadio(), viewInstallOrderEntity.getInstallTime(), new Date());
                            viewInstallOrderEntity.setInstallkOverTime(overMinutes);
                            if (overMinutes > 0) {
                                viewInstallOrderEntity.setInstallOverFlg(SysConstant.InstallOrder.CONFIG_FLG_Y);
                            }
                        } else {
                            overMinutes = orderStepService.judgeTimeoutHours(workFollowDetailEntity.getTimeOutRadio(), viewInstallOrderEntity.getInstallTime(), viewInstallOrderEntity.getInstallFinishTime());
                            viewInstallOrderEntity.setInstallkOverTime(overMinutes);
                            // if (overMinutes > 0) {
                            //     viewInstallOrderEntity.setInstallOverFlg(SysConstant.InstallOrder.CONFIG_FLG_Y);
                            // }
                            viewInstallOrderEntity.setInstallOverFlg(SysConstant.InstallOrder.CONFIG_FLG_N);
                        }
                    }
                    break;
            }

        }

    }

    private void calcOVerTimebak(WorkFollowDetailEntity appointWork, ViewInstallOrderEntity viewInstallOrderEntity, Date fromTime, Date endTime, String overType) {

        //判断接单超时
        if (appointWork != null && isEmpty(viewInstallOrderEntity)) {
            OrderStepQuery orderStepQuery = new OrderStepQuery();
            orderStepQuery.setOrderNo(viewInstallOrderEntity.getOrderNo());
            orderStepQuery.setFollowSeq(appointWork.getFollowSeq());
            orderStepQuery.setSort("-stepSeq");
            List<OrderStepEntity> orderStepEntityList = orderStepService.findAll(orderStepQuery);
            OrderStepEntity orderStepEntity = new OrderStepEntity();
            if (orderStepEntityList != null && orderStepEntityList.size() > 0) {
                orderStepEntity = orderStepEntityList.stream().findFirst().orElse(null);
            }
            if (orderStepEntity != null) {
                //是否接单 如果未接单 计算超时时长
                if (isEmpty(orderStepEntity.getStepTime())) {
                    int overMinutes = orderStepService.judgeTimeoutHours(appointWork.getTimeOutRadio(), fromTime, endTime);
                    if (overMinutes > 0) {
                        switch (overType) {
                            case SysConstant.WorkFollow.WORKFOLLOW_TIMEOUT_TYPE01:
                                viewInstallOrderEntity.setDispatchOverTime(overMinutes);
                                viewInstallOrderEntity.setDispatchOverTimeFlg(SysConstant.InstallOrder.CONFIG_FLG_Y);
                            case SysConstant.WorkFollow.WORKFOLLOW_TIMEOUT_TYPE02:
                                viewInstallOrderEntity.setContractOverTime(overMinutes);
                                viewInstallOrderEntity.setContractOverTimeFlg(SysConstant.InstallOrder.CONFIG_FLG_Y);

                            case SysConstant.WorkFollow.WORKFOLLOW_TIMEOUT_TYPE03:
                                viewInstallOrderEntity.setCheckOverTime(overMinutes);
                                viewInstallOrderEntity.setCheckOverTimeFlg(SysConstant.InstallOrder.CONFIG_FLG_Y);

                            case SysConstant.WorkFollow.WORKFOLLOW_TIMEOUT_TYPE04:
                                viewInstallOrderEntity.setInstallkOverTime(overMinutes);
                                viewInstallOrderEntity.setInstallOverFlg(SysConstant.InstallOrder.CONFIG_FLG_Y);


                        }


                    } else {
                        switch (overType) {
                            case SysConstant.WorkFollow.WORKFOLLOW_TIMEOUT_TYPE01:
                                viewInstallOrderEntity.setDispatchOverTime(0);
                                viewInstallOrderEntity.setDispatchOverTimeFlg(SysConstant.InstallOrder.CONFIG_FLG_N);
                            case SysConstant.WorkFollow.WORKFOLLOW_TIMEOUT_TYPE02:
                                viewInstallOrderEntity.setContractOverTime(0);
                                viewInstallOrderEntity.setContractOverTimeFlg(SysConstant.InstallOrder.CONFIG_FLG_N);
                            case SysConstant.WorkFollow.WORKFOLLOW_TIMEOUT_TYPE03:
                                viewInstallOrderEntity.setCheckOverTime(0);
                                viewInstallOrderEntity.setCheckOverTimeFlg(SysConstant.InstallOrder.CONFIG_FLG_N);
                            case SysConstant.WorkFollow.WORKFOLLOW_TIMEOUT_TYPE04:
                                viewInstallOrderEntity.setInstallkOverTime(0);
                                viewInstallOrderEntity.setInstallOverFlg(SysConstant.InstallOrder.CONFIG_FLG_N);

                        }

                    }

                } else {
                    //如果已经接单则直接用超时时长计算

                    switch (overType) {
                        case SysConstant.WorkFollow.WORKFOLLOW_TIMEOUT_TYPE01:
                            viewInstallOrderEntity.setDispatchOverTime(orderStepEntity.getOutTime());
                            viewInstallOrderEntity.setDispatchOverTimeFlg(SysConstant.InstallOrder.CONFIG_FLG_N);
                        case SysConstant.WorkFollow.WORKFOLLOW_TIMEOUT_TYPE02:
                            viewInstallOrderEntity.setContractOverTime(orderStepEntity.getOutTime());
                            viewInstallOrderEntity.setContractOverTimeFlg(SysConstant.InstallOrder.CONFIG_FLG_N);
                        case SysConstant.WorkFollow.WORKFOLLOW_TIMEOUT_TYPE03:
                            viewInstallOrderEntity.setCheckOverTime(orderStepEntity.getOutTime());
                            viewInstallOrderEntity.setCheckOverTimeFlg(SysConstant.InstallOrder.CONFIG_FLG_N);

                        case SysConstant.WorkFollow.WORKFOLLOW_TIMEOUT_TYPE04:
                            viewInstallOrderEntity.setInstallkOverTime(orderStepEntity.getOutTime());
                            viewInstallOrderEntity.setInstallOverFlg(SysConstant.InstallOrder.CONFIG_FLG_N);

                    }


                }


            }


        }

    }

    /**
     * 列表查询
     *
     * @param installOrderQuery
     * @return
     */
    @Override
    public List<ViewInstallOrderEntity> findAll(InstallOrderQuery installOrderQuery) {
        Sort sort = buildSort(installOrderQuery.getSort());
        List<ViewInstallOrderEntity> entityList = installOrderViewRepository.findAll(createSpecification(installOrderQuery), sort);
        return entityList;
    }

    /**
     * 创建查询条件
     *
     * @param installOrderQuery
     * @return
     */
    private Specification createSpecification(InstallOrderQuery installOrderQuery) {
        return new Specification<ViewInstallOrderEntity>() {

            @Override
            public Predicate toPredicate(Root<ViewInstallOrderEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();

              UserEntity user=  getUserInfo();
                if(!StringUtils.isEmpty(user.getUserType()) && (user.getUserType().contains(SysConstant.User.AZRY_USER) || user.getUserType().contains( SysConstant.User.KCRY_USER))){
                    predicate.add(cb.equal(root.get("checkUserQuery").as(String.class), user.getUserNo()));
                }

//              if(user.getUserType().equals( SysConstant.User.KCRY_USER)){
//
//                  predicate.add(cb.equal(root.get("checkUserNo").as(String.class), user.getUserNo()));
//
//              }
//
//              if(user.getUserType().equals( SysConstant.User.AZRY_USER)){
//
//                    predicate.add(cb.equal(root.get("installUserNo").as(String.class), user.getUserNo()));
//
//              }
//
//              if(user.getUserType().contains(SysConstant.User.AZRY_USER) && user.getUserType().contains( SysConstant.User.KCRY_USER)){
//
//                  predicate.add(cb.or(
//
//                          cb.equal(root.get("installUserNo").as(String.class),  user.getUserNo()),
//                          cb.equal(root.get("checkUserNo").as(String.class),  user.getUserNo())
//                  ));
//
//              }





                if (SysConstant.Common.LIST_ALL_FLG_NO.equals(installOrderQuery.getAllOrderFlg())) {
                    if (!isEmpty(installOrderQuery.getUserType())) {

                        String str = installOrderQuery.getUserType();
                        String[] strarr = str.split(",");
                        String position1 = "", position2 = "";
                        switch (strarr.length) {
                            case 1:
                                position1 = strarr[0];
                                break;
                            case 2:
                                position1 = strarr[0];
                                position2 = strarr[1];
                                break;


                        }
                        if (!isEmpty(position2)) {
                            predicate.add(cb.or(

                                    cb.like(root.get("positionCodes").as(String.class), "%" + position2 + "%"),
                                    cb.like(root.get("positionCodes").as(String.class), "%" + position1 + "%")
                            ));
                        } else if (!isEmpty(position1)) {
                            predicate.add(cb.or(
                                    cb.like(root.get("positionCodes").as(String.class), "%" + position1 + "%")
                            ));
                        }


                        // predicate.add(cb.like(root.get("positionCodes").as(String.class), "%" + installOrderQuery.getUserType() + "%"));
                    }
                    if (!isEmpty(installOrderQuery.getUserTypeWeb())) {
                        predicate.add(cb.like(root.get("positionCodes").as(String.class), "%" + installOrderQuery.getUserTypeWeb() + "%"));
                    }
                    //是否完成
                    predicate.add(cb.equal(root.get("finishFlg").as(String.class), SysConstant.InstallOrder.CONFIG_FLG_N));
                }

                //APP查询
                if (!isEmpty(installOrderQuery.getAppSearch()) && SysConstant.InstallOrder.CONFIG_FLG_Y.equals(installOrderQuery.getAppSearch())) {
                    if (!isEmpty(installOrderQuery.getUserType())) {
                        //predicate.add(cb.like(root.get("positionCodes").as(String.class), "%" + installOrderQuery.getUserType() + "%"));
                        String str = installOrderQuery.getUserType();
                        String[] strarr = str.split(",");
                        String position1 = "", position2 = "";
                        switch (strarr.length) {
                            case 1:
                                position1 = strarr[0];
                                break;
                            case 2:
                                position1 = strarr[0];
                                position2 = strarr[1];
                                break;


                        }
                        if (!isEmpty(position2)) {
                            predicate.add(cb.or(

                                    cb.like(root.get("positionCodes").as(String.class), "%" + position2 + "%"),
                                    cb.like(root.get("positionCodes").as(String.class), "%" + position1 + "%")
                            ));
                        } else if (!isEmpty(position1)) {
                            predicate.add(cb.or(
                                    cb.like(root.get("positionCodes").as(String.class), "%" + position1 + "%")
                            ));
                        }

                    }



                    if (!isEmpty(installOrderQuery.getNoNeedStepMatrix())) {
                        predicate.add(cb.notEqual(root.get("stepMatrix").as(String.class), installOrderQuery.getNoNeedStepMatrix()));
                    }
                    predicate.add(cb.equal(root.get("finishFlg").as(String.class), SysConstant.InstallOrder.CONFIG_FLG_N));
                }


                //省
                if (!isEmpty(installOrderQuery.getInstallProvince())) {
                    predicate.add(cb.equal(root.get("installProvince").as(String.class), installOrderQuery.getInstallProvince()));
                }

                //市
                if (!isEmpty(installOrderQuery.getInstallCity())) {
                    predicate.add(cb.equal(root.get("installCity").as(String.class), installOrderQuery.getInstallCity()));
                }

                // 服务商
                if (SysConstant.Delivery.PILE_MANGER.equals(installOrderQuery.getType())){

//                    if (!isEmpty(installOrderQuery.getSupplierNo())) {
//                        predicate.add(cb.equal(root.get("supplierNo").as(String.class), installOrderQuery.getSupplierNo()));
//                    }
                    if (!isEmpty(user.getSupplierNo())) {
                        predicate.add(cb.equal(root.get("supplierNo").as(String.class), user.getSupplierNo()));
                    }
                }else if (SysConstant.Delivery.DELIVERY.equals(installOrderQuery.getType())){
                    if (!isEmpty(installOrderQuery.getSupplierNo())) {
                        predicate.add(cb.equal(root.get("supplierNo").as(String.class), installOrderQuery.getSupplierNo()));
                    }
                }


                // 车型
                if (!isEmpty(installOrderQuery.getCarVehicle())) {
                    predicate.add(cb.equal(root.get("carVehicle").as(String.class), installOrderQuery.getCarVehicle()));
                }

                // 车型详细
                if (!isEmpty(installOrderQuery.getVehicleDetail())) {
                    predicate.add(cb.equal(root.get("vehicleDetail").as(String.class), installOrderQuery.getVehicleDetail()));
                }

                //订单创建日期
                if (!isEmpty(installOrderQuery.getOrderTimeStart())) {
                    //大于或等于报修时间开始
                    predicate.add(cb.greaterThanOrEqualTo(root.get("orderTime").as(Date.class), installOrderQuery.getOrderTimeStart()));
                }
                if (!isEmpty(installOrderQuery.getOrderTimeEnd())) {
                    //小于或等于报修时间结束
                    predicate.add(cb.lessThanOrEqualTo(root.get("orderTime").as(Date.class), installOrderQuery.getOrderTimeEnd()));
                }

                //预约勘察日期
                if (!isEmpty(installOrderQuery.getAppointmentTimeStart())) {
                    //大于或等于报修时间开始
                    predicate.add(cb.greaterThanOrEqualTo(root.get("appointmentTime").as(Date.class), installOrderQuery.getAppointmentTimeStart()));
                }
                if (!isEmpty(installOrderQuery.getAppointmentTimeEnd())) {
                    //小于或等于报修时间结束
                    predicate.add(cb.lessThanOrEqualTo(root.get("appointmentTime").as(Date.class), installOrderQuery.getAppointmentTimeEnd()));
                }

                //勘察完成日期
                if (!isEmpty(installOrderQuery.getCheckTimeStart())) {
                    //大于或等于报修时间开始
                    predicate.add(cb.greaterThanOrEqualTo(root.get("checkTime").as(Date.class), installOrderQuery.getCheckTimeStart()));
                }
                if (!isEmpty(installOrderQuery.getCheckTimeEnd())) {
                    //小于或等于报修时间结束
                    predicate.add(cb.lessThanOrEqualTo(root.get("checkTime").as(Date.class), installOrderQuery.getCheckTimeEnd()));
                }

                //预约安装日期
                if (!isEmpty(installOrderQuery.getAppointmentInstallTimeStart())) {
                    //大于或等于报修时间开始
                    predicate.add(cb.greaterThanOrEqualTo(root.get("appointmentInstallTime").as(Date.class), installOrderQuery.getAppointmentInstallTimeStart()));
                }
                if (!isEmpty(installOrderQuery.getAppointmentInstallTimeEnd())) {
                    //小于或等于报修时间结束
                    predicate.add(cb.lessThanOrEqualTo(root.get("appointmentInstallTime").as(Date.class), installOrderQuery.getAppointmentInstallTimeEnd()));
                }

                //安装开始日期
                // if (!isEmpty(installOrderQuery.getInstallTimeStart())) {
                //     //大于或等于安装完成日期开始
                //     predicate.add(cb.greaterThanOrEqualTo(root.get("installTime").as(Date.class), installOrderQuery.getInstallTimeStart()));
                // }
                // if (!isEmpty(installOrderQuery.getInstallTimeEnd())) {
                //     //小于或等于安装完成日期结束
                //     predicate.add(cb.lessThanOrEqualTo(root.get("installTime").as(Date.class), installOrderQuery.getInstallTimeEnd()));
                // }

                //充电桩样式
                if (!isEmpty(installOrderQuery.getPileType())) {
                    predicate.add(cb.equal(root.get("pileType").as(String.class), installOrderQuery.getPileType()));
                }

                //安装完成日期
                if (!isEmpty(installOrderQuery.getInstallFinishTimeStart())) {
                    //大于或等于安装完成日期开始
                    predicate.add(cb.greaterThanOrEqualTo(root.get("installFinishTime").as(Date.class), installOrderQuery.getInstallFinishTimeStart()));
                }
                if (!isEmpty(installOrderQuery.getInstallFinishTimeEnd())) {
                    //小于或等于安装完成日期结束
                    predicate.add(cb.lessThanOrEqualTo(root.get("installFinishTime").as(Date.class), installOrderQuery.getInstallFinishTimeEnd()));
                }

                //订单完成日期
                if (!isEmpty(installOrderQuery.getOrderFinishTimeStart())) {
                    //大于或等于订单完成日期开始
                    predicate.add(cb.greaterThanOrEqualTo(root.get("orderFinishTime").as(Date.class), installOrderQuery.getOrderFinishTimeStart()));
                }
                if (!isEmpty(installOrderQuery.getOrderFinishTimeEnd())) {
                    //小于或等于订单完成日期结束
                    predicate.add(cb.lessThanOrEqualTo(root.get("orderFinishTime").as(Date.class), installOrderQuery.getOrderFinishTimeEnd()));
                }

                //销售日期
                if (!isEmpty(installOrderQuery.getSaleDateStart())) {
                    //大于或等于报修时间开始
                    predicate.add(cb.greaterThanOrEqualTo(root.get("saleDate").as(Date.class), installOrderQuery.getSaleDateStart()));
                }
                if (!isEmpty(installOrderQuery.getSaleDateEnd())) {
                    //小于或等于报修时间结束
                    predicate.add(cb.lessThanOrEqualTo(root.get("saleDate").as(Date.class), installOrderQuery.getSaleDateEnd()));
                }

                //派单时间
                if (!isEmpty(installOrderQuery.getDispatchStartTime())) {
                    //大于或等于创建时间
                    predicate.add(cb.greaterThanOrEqualTo(root.get("dispatchTime").as(Date.class), installOrderQuery.getDispatchStartTime()));
                }
                if (!isEmpty(installOrderQuery.getDispatchOverTime())) {
                    //小于或等于修改时间
                    predicate.add(cb.lessThanOrEqualTo(root.get("dispatchTime").as(Date.class), installOrderQuery.getDispatchOverTime()));
                }

                //多条件查询
                if (!isEmpty(installOrderQuery.getMultipleQuery())) {
                    predicate.add(cb.or(
                            cb.like(root.get("orderNo").as(String.class), installOrderQuery.getMultipleQuery()),
                            cb.like(root.get("vinNo").as(String.class), installOrderQuery.getMultipleQuery()),
                            cb.like(root.get("carOwner").as(String.class), installOrderQuery.getMultipleQuery()),
                            cb.like(root.get("carOwnerPhone").as(String.class), installOrderQuery.getMultipleQuery()),
                            cb.like(root.get("installContact").as(String.class), installOrderQuery.getMultipleQuery()),
                            cb.like(root.get("installContactPhone").as(String.class), installOrderQuery.getMultipleQuery())
                    ));
                }

                //不需要的步骤
                // if (!StringUtils.isEmpty(installOrderQuery.getNoUseSteps())) {
                //     Set<String> allList = workFollowDetailService.getStepSet(SysConstant.WorkFollow.WORKFOLLOW_INSTALL_NO);
                //     String[] noCodes = installOrderQuery.getNoUseSteps().split(",");
                //     if (noCodes.length > 1) {
                //         Set<String> noList = new HashSet<>();
                //         Collections.addAll(noList, noCodes);
                //         Set<String> addSet = (Set<String>) CustomCollectionUtils.subtract(allList, noList);
                //         CriteriaBuilder.In<String> in = cb.in(root.get("followSeq").as(String.class));
                //         for (String code : addSet) {
                //             in.value(code);
                //         }
                //         predicate.add(in);
                //     } else {
                //         predicate.add(cb.notEqual(root.get("followSeq").as(String.class), installOrderQuery.getNoUseSteps()));
                //     }
                // }

                //需要的步骤
                // if (!StringUtils.isEmpty(installOrderQuery.getUseSteps())) {
                //     String[] useCodes = installOrderQuery.getUseSteps().split(",");
                //     if (useCodes.length > 1) {
                //         Set<String> useList = new HashSet<>();
                //         Collections.addAll(useList, useCodes);
                //         CriteriaBuilder.In<String> in = cb.in(root.get("followSeq").as(String.class));
                //         for (String code : useList) {
                //             in.value(code);
                //         }
                //         predicate.add(in);
                //     } else {
                //         predicate.add(cb.equal(root.get("followSeq").as(String.class), installOrderQuery.getUseSteps()));
                //     }
                // }

                //是否开始做废动作
                if (!StringUtils.isEmpty(installOrderQuery.getBeginCacle())) {
                    predicate.add(cb.equal(root.get("beginCacle").as(String.class), installOrderQuery.getBeginCacle()));
                }

                //是否结算
                if (!StringUtils.isEmpty(installOrderQuery.getSettleFlg())) {
                    predicate.add(cb.equal(root.get("settleFlg").as(String.class), installOrderQuery.getSettleFlg()));
                }

                //步骤矩阵
                if (!StringUtils.isEmpty(installOrderQuery.getStepMatrix())) {
                    predicate.add(cb.equal(root.get("stepMatrix").as(String.class), installOrderQuery.getStepMatrix()));
                }

                // 步骤号
                if (!isEmpty(installOrderQuery.getFollowSeq())) {
                    predicate.add(cb.equal(root.get("followSeq").as(Integer.class), installOrderQuery.getFollowSeq()));
                }

                //VIN
                if (!StringUtils.isEmpty(installOrderQuery.getVinNo())) {
                    predicate.add(cb.equal(root.get("vinNo").as(String.class), installOrderQuery.getVinNo()));
                }

                //步骤矩阵
                // if (!StringUtils.isEmpty(installOrderQuery.getStepMatrix())) {
                //     predicate.add(cb.equal(root.get("stepMatrix").as(String.class), installOrderQuery.getStepMatrix()));
                // }

                //经销商名称
                if (!StringUtils.isEmpty(installOrderQuery.getDealerName())) {
                    predicate.add(cb.like(root.get("dealerName").as(String.class), "%" + installOrderQuery.getDealerName() + "%"));
                }

                //流程编码
                if (!StringUtils.isEmpty(installOrderQuery.getFollowCode())) {
                    predicate.add(cb.equal(root.get("followCode").as(String.class), installOrderQuery.getFollowCode()));
                }

                //是否完成
                if (!StringUtils.isEmpty(installOrderQuery.getFinishFlg())) {
                    predicate.add(cb.equal(root.get("finishFlg").as(String.class), installOrderQuery.getFinishFlg()));
                }

                //是否巡检
                if (!StringUtils.isEmpty(installOrderQuery.getInspectionTourFlg())) {
                    predicate.add(cb.equal(root.get("inspectionTourFlg").as(String.class), installOrderQuery.getInspectionTourFlg()));
                }

                //公司
                if (!isEmpty(installOrderQuery.getCorpNo())) {
                    predicate.add(cb.equal(root.get("corpNo").as(String.class), installOrderQuery.getCorpNo()));
                } else {
                    predicate.add(cb.equal(root.get("corpNo").as(String.class), BaseContextHandler.getCorpNo()));
                }

                //职位筛选
                // if (!isEmpty(installOrderQuery.getUserPosition())) {
                //     predicate.add(cb.like(root.get("positionCodes").as(String.class), "%" + installOrderQuery.getUserPosition() + "%"));
                // }
                // if (!isEmpty(installOrderQuery.getUserType())) {
                //     predicate.add(cb.like(root.get("positionCodes").as(String.class), "%" + installOrderQuery.getUserType() + "%"));
                // }

                // predicate.add(cb.like(root.get("positionCodes").as(String.class), "%" + user.getUserType() + "%"));


                //车型查询
                if (!StringUtils.isEmpty(installOrderQuery.getCarVehicle())) {
                    predicate.add(cb.equal(root.get("carVehicle").as(String.class), installOrderQuery.getCarVehicle()));
                }

                //是否接单
                if (!StringUtils.isEmpty(installOrderQuery.getReceiveStatus())) {
                    predicate.add(cb.equal(root.get("receiveStatus").as(String.class), installOrderQuery.getReceiveStatus()));
                }


                //筛选已删除
                predicate.add(cb.equal(root.get("isDel").as(String.class), SysConstant.IS_DEL_N));

                Predicate[] p = new Predicate[predicate.size()];
                return cb.and(predicate.toArray(p));
            }

        };
    }

    /**
     * 根据ID校验
     *
     * @param id
     * @return
     */
    @Override
    public ViewInstallOrderEntity checkById(String id) {
        ViewInstallOrderEntity viewInstallOrderEntity = installOrderViewRepository.findById(SysConstant.IS_DEL_N, id);
        if (viewInstallOrderEntity == null) {
            throw new AppException(CustomMessage.NO_DATA_FIND);
        }
        return viewInstallOrderEntity;
    }

    /**
     * 根据ID查找
     *
     * @param id
     * @return
     */
    @Override
    public ViewInstallOrderEntity findById(String id) {
        ViewInstallOrderEntity viewInstallOrderEntity = checkById(id);
        // List<OrderAttachEntity> orderAttachEntityList = orderAttachService.findByOrderNo(viewInstallOrderEntity.getOrderNo());
        if (viewInstallOrderEntity != null) {
            OrderAttachQuery orderAttachQuery = new OrderAttachQuery();
            orderAttachQuery.setOrderNo(viewInstallOrderEntity.getOrderNo());
            orderAttachQuery.setSort("+createdWhen");
            List<OrderAttachEntity> orderAttachEntityList = orderAttachService.findAll(orderAttachQuery);
            if (orderAttachEntityList == null) {
                orderAttachEntityList = new ArrayList<>();
            } else {
                for (OrderAttachEntity item : orderAttachEntityList) {
                    item.setAttachPath(SysConstant.ATTACH_VISIT_PERF + item.getAttachPath());
                }
            }
            viewInstallOrderEntity.setOrderAttachEntityList(orderAttachEntityList);
        }
        return viewInstallOrderEntity;
    }

    @Override
    public ViewInstallOrderEntity findByOrderNo(String orderNo, String corpNo) {
        ViewInstallOrderEntity viewInstallOrderEntity = installOrderViewRepository.findByOrderNo(SysConstant.IS_DEL_N, orderNo, corpNo);
        if (viewInstallOrderEntity != null) {
            List<OrderAttachEntity> orderAttachEntityList = orderAttachService.findByOrderNo(viewInstallOrderEntity.getOrderNo());
//            for (OrderAttachEntity item : orderAttachEntityList) {
////                String oldPath = SysConstant.ATTACH_VISIT_PERF + item.getAttachPath();
////                String newPath=oldPath.replaceAll("/api/v1","");
////                item.setAttachPath(SysConstant.ATTACH_VISIT_PERF+newPath);
//            }
            viewInstallOrderEntity.setOrderAttachEntityList(orderAttachEntityList);
        }
        return viewInstallOrderEntity;
    }

    private UserEntity getUserInfo(){

      return   userService.findById(BaseContextHandler.getUserId());


    }


}

