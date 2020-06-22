package cn.backend.service.installorder;

import cn.backend.config.constant.SysConstant;
import cn.backend.config.message.CustomMessage;
import cn.backend.dao.primary.installorder.InstallOrderRepository;
import cn.backend.model.primary.config.ConfigEntity;
import cn.backend.model.primary.corp.CorpEntity;
import cn.backend.model.primary.delivery.DeliveryOrderEntity;
import cn.backend.model.primary.installorder.InstallOrderEntity;
import cn.backend.model.primary.installorder.InstallOrderQuery;
import cn.backend.model.primary.orderauto.OrderAutoQuery;
import cn.backend.model.primary.ordercancel.OrderCancelEntity;
import cn.backend.model.primary.ordercar.OrderCarEntity;
import cn.backend.model.primary.ordercar.OrderCarQuery;
import cn.backend.model.primary.orderfee.OrderFeeQuery;
import cn.backend.model.primary.ordergrade.OrderGradeEntity;
import cn.backend.model.primary.orderseq.OrderSeqQuery;
import cn.backend.model.primary.orderstep.OrderStepEntity;
import cn.backend.model.primary.orderstep.OrderStepParameter;
import cn.backend.model.primary.pile.PileEntity;
import cn.backend.model.primary.scrminfo.ScrmCarInfoEntity;
import cn.backend.model.primary.supplier.SupplierEntity;
import cn.backend.model.primary.user.UserEntity;
import cn.backend.model.primary.vehicle.VehicleEntity;
import cn.backend.model.primary.vin.VinEntity;
import cn.backend.model.primary.workfollow.WorkFollowEntity;
import cn.backend.model.primary.workfollowdetail.WorkFollowDetailEntity;
import cn.backend.model.primary.workfollowdetail.WorkFollowDetailQuery;
import cn.backend.service.BaseService;
import cn.backend.service.config.IConfigService;
import cn.backend.service.corp.ICorpService;
import cn.backend.service.delivery.IDeliveryOrderService;
import cn.backend.service.orderattach.IOrderAttachService;
import cn.backend.service.orderauto.IOrderAutoService;
import cn.backend.service.ordercancel.IOrderCancelService;
import cn.backend.service.ordercar.IOrderCarService;
import cn.backend.service.orderfee.IOrderFeeService;
import cn.backend.service.ordergrade.IOrderGradeService;
import cn.backend.service.orderseq.IOrderSeqService;
import cn.backend.service.orderstep.IOrderStepService;
import cn.backend.service.pile.IPileService;
import cn.backend.service.scrminfo.IScrmInfoService;
import cn.backend.service.supplier.ISupplierService;
import cn.backend.service.user.IUserService;
import cn.backend.service.vehicle.IVehicleService;
import cn.backend.service.vehicledetail.IVehicleDetailService;
import cn.backend.service.vin.IVinService;
import cn.backend.service.workfollow.IWorkFollowService;
import cn.backend.service.workfollowdetail.IWorkFollowDetailService;
import cn.zdwl.common.context.BaseContextHandler;
import cn.zdwl.common.exception.AppException;
import cn.zdwl.common.util.BeanCopyUtil;
import cn.zdwl.common.util.CompareObj;
import cn.zdwl.common.util.DateUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

/**
 * @author sunkw
 * @date 2019/07/17
 */
@Service(value = "installOrderService")
public class InstallOrderService extends BaseService implements IInstallOrderService {

    @Resource
    private InstallOrderRepository installOrderRepository;

    @Autowired
    private IConfigService configService;

    @Autowired
    private IWorkFollowService workFollowService;

    @Autowired
    private IWorkFollowDetailService workFollowDetailService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IVehicleService vehicleService;

    @Autowired
    private IVinService vinService;

    @Autowired
    private ICorpService corpService;

    @Autowired
    private IOrderCarService orderCarService;

    @Autowired
    private IOrderStepService orderStepService;

    @Autowired
    private IOrderGradeService orderGradeService;

    @Autowired
    private IOrderFeeService orderFeeService;

    @Autowired
    private ISupplierService supplierService;

    @Autowired
    private IPileService pileService;

    @Autowired
    private IScrmInfoService _scrmInfoService;

    @Autowired
    private IOrderSeqService orderSeqService;

    @Autowired
    private IOrderAutoService orderAutoService;

    @Autowired
    private IDeliveryOrderService deliveryOrderService;

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private IOrderCancelService orderCancelService;

    @Autowired
    private IOrderAttachService orderAttachService;

    @Autowired
    private IVehicleDetailService vehicleDetailService;

    /**
     * 分页查询
     *
     * @param installOrderQuery
     * @return
     */
    @Override
    public Page<InstallOrderEntity> findList(InstallOrderQuery installOrderQuery) {
        Sort sort = buildSort(installOrderQuery.getSort());
        Pageable pageable = new PageRequest(installOrderQuery.getPageNum(), installOrderQuery.getPageSize(), sort);
        Page<InstallOrderEntity> entityPage = installOrderRepository.findAll(createSpecification(installOrderQuery),
                pageable);
        return entityPage;
    }

    /**
     * 列表查询
     *
     * @param installOrderQuery
     * @return
     */
    @Override
    public List<InstallOrderEntity> findAll(InstallOrderQuery installOrderQuery) {
        Sort sort = buildSort(installOrderQuery.getSort());
        List<InstallOrderEntity> entityList = installOrderRepository.findAll(createSpecification(installOrderQuery),
                sort);
        return entityList;
    }

    /**
     * 创建查询条件
     *
     * @param installOrderQuery
     * @return
     */
    private Specification createSpecification(InstallOrderQuery installOrderQuery) {
        return new Specification<InstallOrderEntity>() {

            @Override
            public Predicate toPredicate(Root<InstallOrderEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();

                //服务商
                if (!isEmpty(installOrderQuery.getSupplierNo())) {
                    predicate.add(cb.equal(root.get("supplierNo").as(String.class), installOrderQuery.getSupplierNo()));
                }

                //服务商
                if (!isEmpty(installOrderQuery.getPileCode())) {
                    predicate.add(cb.equal(root.get("pileCode").as(String.class), installOrderQuery.getPileCode()));
                }

                //公司
                if (!isEmpty(installOrderQuery.getCorpNo())) {
                    predicate.add(cb.equal(root.get("corpNo").as(String.class), installOrderQuery.getCorpNo()));
                } else {
                    predicate.add(cb.equal(root.get("corpNo").as(String.class), BaseContextHandler.getCorpNo()));
                }

                //订单创建时间
                if (!isEmpty(installOrderQuery.getOrderTimeStart())) {
                    //大于或等于报修时间开始
                    predicate.add(cb.greaterThanOrEqualTo(root.get("orderTime").as(Date.class),
                            installOrderQuery.getOrderTimeStart()));
                }
                if (!isEmpty(installOrderQuery.getOrderTimeEnd())) {
                    //小于或等于报修时间结束
                    predicate.add(cb.lessThanOrEqualTo(root.get("orderTime").as(Date.class),
                            installOrderQuery.getOrderTimeEnd()));
                }

                //根据订单号查询
                if (!StringUtils.isEmpty(installOrderQuery.getOrderNos())) {

                    Expression<String> exp = root.<String>get("orderNo");
                    String str = installOrderQuery.getOrderNos();
                    String[] strarr = str.split(",");
                    List<String> stringList = new ArrayList<>();
                    for (int i = 0; i < strarr.length; i++) {
                        stringList.add(strarr[i]);

                    }

                    predicate.add(exp.in(stringList));
                }

                //是否开始废弃动作
                if (!isEmpty(installOrderQuery.getBeginCacle())) {
                    predicate.add(cb.equal(root.get("beginCacle").as(String.class), installOrderQuery.getBeginCacle()));
                }

                //是否CRM撤回
                if (!isEmpty(installOrderQuery.getCrmRollbackFlg())) {
                    predicate.add(cb.equal(root.get("crmRollbackFlg").as(String.class), installOrderQuery.getCrmRollbackFlg()));
                }

                //筛选已删除
                predicate.add(cb.equal(root.get("isDel").as(String.class), SysConstant.IS_DEL_N));

                Predicate[] p = new Predicate[predicate.size()];
                return cb.and(predicate.toArray(p));
            }

        };
    }

    /**
     * 提交订单
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional
    public InstallOrderEntity submitOrder(InstallOrderEntity entity) {
        //获取车主信息
        OrderCarQuery orderCarQuery = new OrderCarQuery();
        orderCarQuery.setVin(entity.getVin());
        OrderCarEntity orderCarEntity = orderCarService.getOrderCarInfo(orderCarQuery);
        //车型名是否匹配
        VehicleEntity vehicleEntity = vehicleService.findByNameAndCorp(orderCarEntity.getCarVehicle(),
                entity.getCorpNo());
        if (vehicleEntity == null) {
            throw new AppException(CustomMessage.CAN_NOT_CREATE_ORDER);
        }
        //VIN中不能有记录
//        VinEntity vinEntity = vinService.findByCode(orderCarEntity.getVinNo());
//        if (vinEntity != null) {
//            throw new AppException(CustomMessage.CAN_NOT_CREATE_ORDER);
//        }
        InstallOrderEntity installOrderEntity = this.findByVin(orderCarEntity.getVinNo(), orderCarEntity.getCorpNo());
        //logger.info("--------"+installOrderEntity.toString()+"-------");
        if (installOrderEntity != null) {
            throw new AppException(CustomMessage.CAN_NOT_CREATE_ORDER);
        } else {
            DeliveryOrderEntity deliveryOrderEntity = deliveryOrderService.findByVin(orderCarEntity.getVinNo(), orderCarEntity.getCorpNo());
            //logger.info("--------"+deliveryOrderEntity.toString()+"-------");
            if (deliveryOrderEntity != null) {
                if (SysConstant.IS_DEL_N.equals(deliveryOrderEntity.getSecondInstall())) {
                    throw new AppException(CustomMessage.CAN_NOT_CREATE_ORDER);
                }
            }
        }
        entity.setOrderTime(new Date());
        entity.setPileCode(orderCarEntity.getVinNo());
        entity.setSupplierConfirmFlg(SysConstant.InstallOrder.RECEIVE_FLG_SIGN);
        entity.setVerifyFlg(SysConstant.InstallOrder.CONFIG_VERIFY_N);
        entity.setBeginCacle(SysConstant.InstallOrder.CONFIG_FLG_N);
        entity.setSettleFlg(SysConstant.InstallOrder.CONFIG_FLG_N);
        entity.setFinishFlg(SysConstant.InstallOrder.CONFIG_FLG_N);
        entity.setInspectionTourFlg(SysConstant.InstallOrder.CONFIG_FLG_N);
        //新增安装订单
        add(entity);
        //车主信息新增
        orderCarEntity.setCorpNo(entity.getCorpNo());
        orderCarEntity.setOrderNo(entity.getOrderNo());
        orderCarService.add(orderCarEntity);
        //初始化订单步骤记录
        int initFollowSeq =
                workFollowDetailService.getMinStep(SysConstant.WorkFollow.WORKFOLLOW_INSTALL_NO).getFollowSeq();
        OrderStepEntity orderStepEntity = orderStepService.workFollow(SysConstant.WorkFollow.CONFIG_WORKFOLLOW_INSTALL
                , initFollowSeq);
        orderStepEntity.setOrderNo(entity.getOrderNo());
        orderStepEntity.setStepSeq(1);
        orderStepService.add(orderStepEntity);
        //是否自动派单
        // ConfigEntity configEntity = configService.findByTypeAndCode(SysConstant.Supplier.CONFIG_TYPE_AUTO_ORDER
        //         , SysConstant.Supplier.CONFIG_VAL_AUTO_ORDER, entity.getCorpNo());
        // if (SysConstant.InstallOrder.CONFIG_FLG_Y.equals(configEntity.getConfigCont1())) {
        //     //根据地区查找服务商
        //     List<SupplierEntity> supplierEntity = supplierService.findListByArea(entity.getInstallProvince(),
        //     entity.getInstallCity());
        //     Map<String, Double> supplierMap = supplierEntity.stream()
        //             .collect(Collectors.toMap(SupplierEntity::getSupplierNo, SupplierEntity::getOrderScale, (key1,
        //             key2) -> key1));
        //     String supplierNo = doDistribution(supplierMap);
        //     SupplierEntity supplierCheckEntity = supplierEntity.stream().filter(e -> supplierNo.equals(e
        //     .getSupplierNo())).findFirst().orElse(null);
        //     if (supplierCheckEntity == null) {
        //         throw new AppException(CustomMessage.NO_DATA_FIND);
        //     }
        //     entity.setSupplierNo(supplierNo);
        //     entity.setSupplierName(supplierCheckEntity.getSupplierName());
        //     orderWorkFollow(entity);
        // }
        return entity;
    }

    /**
     * 根据百分比分配服务商
     *
     * @param map
     * @return
     */
    private String doDistribution(Map<String, Double> map) {

        Double[] vals = new Double[]{};
        vals = map.values().toArray(vals);
        String[] keys = new String[]{};
        keys = map.keySet().toArray(keys);
        double sum = getSum(vals);
        double last = 0;

        for (int i = 0; i < vals.length; i++) {
            sum = sum - last;
            double random = Math.random();
            if (random <= vals[i] / sum) {
                return keys[i];
            }
            last = vals[i];
        }
        Random rand = new Random();
        return keys[rand.nextInt(vals.length + 1)];
    }

    /**
     * 求和
     *
     * @param weight
     * @return
     */
    private double getSum(Double[] weight) {
        double sum = 0;
        for (double d : weight) {
            sum += d;
        }
        return sum;
    }

    /**
     * 订单工作流
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional
    public InstallOrderEntity orderWorkFollow(InstallOrderEntity entity) {
        //获取最新的订单步骤记录
        OrderStepEntity lastOrderStepEntity = orderStepService.findLastByOrderNo(entity.getOrderNo(),
                SysConstant.WorkFollow.CONFIG_WORKFOLLOW_INSTALL);
        //校验
        verify(entity, lastOrderStepEntity);
        //获取此步骤工作流
        WorkFollowEntity workFollowEntity =
                workFollowService.findByCode(SysConstant.WorkFollow.CONFIG_WORKFOLLOW_INSTALL);
        WorkFollowDetailEntity workFollowDetailEntity = workFollowEntity.getWorkFollowDetailEntityList()
                .stream().filter(e -> e.getFollowSeq().equals(lastOrderStepEntity.getFollowSeq())).findFirst().orElse(null);
        //该步是否撤回
        if (!isEmpty(entity.getRollBackFlg()) && SysConstant.InstallOrder.CONFIG_FLG_Y.equals(entity.getRollBackFlg())) {
            //校验目前是否可以撤回
            if (workFollowDetailEntity != null && SysConstant.InstallOrder.CONFIG_FLG_Y.equals(workFollowDetailEntity.getRollbackFlg())) {
                edit(entity);
                //初始化新订单步骤记录
                orderStep(workFollowDetailEntity.getRollbackSeq(), entity, lastOrderStepEntity);
                //回撤备注
                lastOrderStepEntity.setStepRemark(entity.getRemark());
                orderStepService.edit(lastOrderStepEntity);
            } else {
                throw new AppException(CustomMessage.CAN_NOT_OPERATE);
            }
        } else {
            //该步是否审核
            if (workFollowDetailEntity != null && SysConstant.InstallOrder.CONFIG_FLG_Y.equals(workFollowDetailEntity.getCheckFlg())) {
                //如果审核不通过，订单步骤继续增加，stepSeq+1，但followSeq退回指定步骤号
                if (SysConstant.InstallOrder.VERIFY_FLG_REFUSE.equals(entity.getVerifyFlg())) {
                    //回到指定步骤
                    //初始化新订单步骤记录
                    orderStep(workFollowDetailEntity.getRollbackSeq(), entity, lastOrderStepEntity);
                    return entity;
                }
            }
            //更新安装订单
            edit(entity);
            //满意度
            // if (workFollowDetailEntity != null && SysConstant.InstallOrder.CONFIG_FLG_Y.equals
            // (workFollowDetailEntity.getGradeFlg())) {
            //     orderGradeService.add(entity.getOrderGradeEntity());
            // }
            //取出下一步步骤号，如果是当前步骤已经最大则取最大
            int maxSeq = workFollowDetailService.getMaxStep(workFollowEntity.getFollowNo()).getFollowSeq();
            int nextSeq;
            if (maxSeq == workFollowDetailEntity.getFollowSeq()) {
                nextSeq = workFollowDetailEntity.getFollowSeq();
            } else {
                nextSeq = workFollowDetailService.getNextStep(workFollowEntity.getFollowNo(),
                        workFollowDetailEntity.getFollowSeq()).getFollowSeq();
            }
            orderStep(nextSeq, entity, lastOrderStepEntity);
            //超时
            if (SysConstant.InstallOrder.CONFIG_FLG_Y.equals(workFollowDetailEntity.getTimeOutFlg())) {
                int minutes = orderStepService.judgeTimeoutHours(workFollowDetailEntity.getTimeOutRadio(),
                        lastOrderStepEntity.getBaseTime(), new Date());
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
            //如果订单完成，生成费用表
            if (SysConstant.InstallOrder.CONFIG_FLG_Y.equals(entity.getFinishFlg())) {
                orderFeeService.addByInstallOrder(entity);
            }
        }
        return entity;
    }

    /**
     * 校验
     *
     * @param installOrderEntity
     * @param lastOrderStepEntity
     */
    private void verify(InstallOrderEntity installOrderEntity, OrderStepEntity lastOrderStepEntity) {
        //判断是否为最后一步，如果是，不可操作
        if (SysConstant.OrderStep.CURRENT_STEP_Y.equals(lastOrderStepEntity.getLastStep())) {
            InstallOrderEntity checkEntity = findById(installOrderEntity.getId());
            if (SysConstant.InstallOrder.CONFIG_FLG_Y.equals(checkEntity.getFinishFlg())) {
                throw new AppException(CustomMessage.CAN_NOT_OPERATE);
            }
        }
        //判断是否开始作废
        if (isEmpty(installOrderEntity.getBeginCacle()) && SysConstant.InstallOrder.CONFIG_FLG_Y.equals(installOrderEntity.getBeginCacle())) {
            throw new AppException(CustomMessage.CAN_NOT_OPERATE);
        }
        //判断当前步骤是否一致
        if (isEmpty(installOrderEntity.getCurrentStep())
                || !installOrderEntity.getCurrentStep().equals(lastOrderStepEntity.getFollowSeq())) {
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
     * @param installOrderEntity
     * @param lastOrderStepEntity
     * @return
     */
    private OrderStepEntity orderStep(int followStep, InstallOrderEntity installOrderEntity,
                                      OrderStepEntity lastOrderStepEntity) {
        OrderStepEntity newOrderStepEntity;
        //获取用户
        UserEntity userEntity = userService.findById(BaseContextHandler.getUserId());
        if (userEntity != null) {
            lastOrderStepEntity.setStepUserNo(userEntity.getUserNo());
            lastOrderStepEntity.setStepUserName(userEntity.getUserName());
        }
        WorkFollowDetailEntity maxWorkFollowDetailEntity =
                workFollowDetailService.getMaxStep(SysConstant.WorkFollow.WORKFOLLOW_INSTALL_NO);
        int stepNums = maxWorkFollowDetailEntity.getFollowSeq();
        if (stepNums != followStep || lastOrderStepEntity.getFollowSeq() != stepNums) {
            List<OrderStepEntity> checkOrderStepEntityList =
                    orderStepService.findByOrderNoAndFollowSeq(installOrderEntity.getOrderNo(), followStep);
            if (checkOrderStepEntityList.size() > 0) {
                //步骤已有
                //判断是否需要重新生成
                WorkFollowDetailEntity workFollowDetailEntity =
                        workFollowDetailService.findByFollowNoAndFollowSeq(lastOrderStepEntity.getFollowNo(),
                                followStep);
                if (SysConstant.InstallOrder.CONFIG_FLG_Y.equals(workFollowDetailEntity.getCheckRebuildFlg())) {
                    //初始化新订单步骤记录
                    newOrderStepEntity = orderStepService.workFollow(SysConstant.WorkFollow.CONFIG_WORKFOLLOW_INSTALL
                            , followStep);
                } else {
                    //继续向下查询，直到找到需要生成的
                    boolean flag = true;
                    int seq = followStep;
                    WorkFollowDetailEntity checkEntity;
                    while (flag) {
                        seq = workFollowDetailService.getNextStep(SysConstant.WorkFollow.WORKFOLLOW_INSTALL_NO,
                                followStep).getFollowSeq();
                        checkEntity =
                                workFollowDetailService.findByFollowNoAndFollowSeq(workFollowDetailEntity.getFollowNo(), seq);
                        if (checkEntity == null) {
                            throw new AppException(CustomMessage.NO_DATA_FIND);
                        } else if (SysConstant.InstallOrder.CONFIG_FLG_Y.equals(checkEntity.getCheckRebuildFlg())) {
                            flag = false;
                        }
                    }
                    //初始化新订单步骤记录
                    newOrderStepEntity = orderStepService.workFollow(SysConstant.WorkFollow.CONFIG_WORKFOLLOW_INSTALL
                            , seq);
                }
            } else {
                //初始化新订单步骤记录
                newOrderStepEntity = orderStepService.workFollow(SysConstant.WorkFollow.CONFIG_WORKFOLLOW_INSTALL
                        , followStep);
            }
            newOrderStepEntity.setOrderNo(installOrderEntity.getOrderNo());
            //业务流程在之前的步骤上加一
            newOrderStepEntity.setStepSeq(lastOrderStepEntity.getStepSeq() + 1);
            //上一订单步骤 是否当前步骤置N
            lastOrderStepEntity.setCurrentStepFlg(SysConstant.OrderStep.CURRENT_STEP_N);
            orderStepService.edit(lastOrderStepEntity);
            orderStepService.add(newOrderStepEntity);
        } else {
            orderStepService.edit(lastOrderStepEntity);
            //如果步骤是最后一步，不生成新步骤
            newOrderStepEntity = lastOrderStepEntity;
        }
        return newOrderStepEntity;
    }

    /**
     * 处理订单步骤
     *
     * @param followStep
     * @param installOrderEntity
     * @param lastOrderStepEntity
     * @return
     */
    private OrderStepEntity orderStepCancel(int followStep, InstallOrderEntity installOrderEntity,
                                            OrderStepEntity lastOrderStepEntity) {
        OrderStepEntity newOrderStepEntity;
        //获取用户
        UserEntity userEntity = userService.findById(BaseContextHandler.getUserId());
        if (userEntity != null) {
            lastOrderStepEntity.setStepUserNo(userEntity.getUserNo());
            lastOrderStepEntity.setStepUserName(userEntity.getUserName());
        }
        WorkFollowDetailEntity maxWorkFollowDetailEntity =
                workFollowDetailService.getMaxStep(SysConstant.WorkFollow.WORKFOLLOW_INVALID_NO);
        int stepNums = maxWorkFollowDetailEntity.getFollowSeq();
        if (stepNums != followStep || lastOrderStepEntity.getFollowSeq() != stepNums) {
            List<OrderStepEntity> checkOrderStepEntityList =
                    orderStepService.findByOrderNoAndFollowSeq(installOrderEntity.getOrderNo(), followStep);
            if (checkOrderStepEntityList.size() > 0) {
                //步骤已有
                //判断是否需要重新生成
                WorkFollowDetailEntity workFollowDetailEntity =
                        workFollowDetailService.findByFollowNoAndFollowSeq(lastOrderStepEntity.getFollowNo(),
                                followStep);
                if (SysConstant.InstallOrder.CONFIG_FLG_Y.equals(workFollowDetailEntity.getCheckRebuildFlg())) {
                    //初始化新订单步骤记录
                    newOrderStepEntity = orderStepService.workFollow(SysConstant.WorkFollow.CONFIG_WORKFOLLOW_INVALID
                            , followStep);
                } else {
                    //继续向下查询，直到找到需要生成的
                    boolean flag = true;
                    int seq = followStep;
                    WorkFollowDetailEntity checkEntity;
                    while (flag) {
                        seq = workFollowDetailService.getNextStep(SysConstant.WorkFollow.WORKFOLLOW_INVALID_NO,
                                followStep).getFollowSeq();
                        checkEntity =
                                workFollowDetailService.findByFollowNoAndFollowSeq(workFollowDetailEntity.getFollowNo(), seq);
                        if (checkEntity == null) {
                            throw new AppException(CustomMessage.NO_DATA_FIND);
                        } else if (SysConstant.InstallOrder.CONFIG_FLG_Y.equals(checkEntity.getCheckRebuildFlg())) {
                            flag = false;
                        }
                    }
                    //初始化新订单步骤记录
                    newOrderStepEntity = orderStepService.workFollow(SysConstant.WorkFollow.CONFIG_WORKFOLLOW_INVALID
                            , seq);
                }
            } else {
                //初始化新订单步骤记录
                newOrderStepEntity = orderStepService.workFollow(SysConstant.WorkFollow.CONFIG_WORKFOLLOW_INVALID
                        , followStep);
            }
            newOrderStepEntity.setOrderNo(installOrderEntity.getOrderNo());
            //业务流程在之前的步骤上加一
            newOrderStepEntity.setStepSeq(lastOrderStepEntity.getStepSeq() + 1);
            //上一订单步骤 是否当前步骤置N
            lastOrderStepEntity.setCurrentStepFlg(SysConstant.OrderStep.CURRENT_STEP_N);
            orderStepService.edit(lastOrderStepEntity);
            orderStepService.add(newOrderStepEntity);
        } else {
            orderStepService.edit(lastOrderStepEntity);
            //如果步骤是最后一步，不生成新步骤
            newOrderStepEntity = lastOrderStepEntity;
        }
        return newOrderStepEntity;
    }

    /**
     * 新建
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional
    public InstallOrderEntity add(InstallOrderEntity entity) {
        Date date = new Date();
        InstallOrderQuery query = new InstallOrderQuery();
        query.setOrderTimeStart(DateUtils.parseDate(DateFormatUtils.format(date, "yyyy-MM-dd 00:00:00"), "yyyy-MM-dd " +
                "hh:mm:ss"));
        query.setOrderTimeEnd(DateUtils.parseDate(DateFormatUtils.format(date, "yyyy-MM-dd 23:59:59"), "yyyy-MM-dd " +
                "hh:mm:ss"));
        query.setCorpNo(entity.getCorpNo());
        //生成编号
        // List<InstallOrderEntity> listNum = findAll(query);
        // if (entity.getCorpNo().equals(SysConstant.Delivery.CORP_ORA)) {
        //     Integer count = listNum.size() + 1;
        //     String num = countSize(count);
        //     entity.setOrderNo(SysConstant.Delivery.ORA + DateUtils.dateToStr(date, "yyyyMMdd") + num + SysConstant
        //     .InstallOrder.PREFIX_NUMBER);
        // } else if (entity.getCorpNo().equals(SysConstant.Delivery.CORP_WEIPA)) {
        //     Integer count = listNum.size() + 1;
        //     String num = countSize(count);
        //     entity.setOrderNo(SysConstant.Delivery.WEY + DateUtils.dateToStr(date, "yyyyMMdd") + num + SysConstant
        //     .InstallOrder.PREFIX_NUMBER);
        // }
        OrderSeqQuery orderSeqQuery = new OrderSeqQuery();
        orderSeqQuery.setOrderType(SysConstant.WorkFollow.CONFIG_WORKFOLLOW_INSTALL);
        orderSeqQuery.setCorpNo(entity.getCorpNo());
        if (isEmpty(entity.getOrderNo())) {
            String orderNo = orderSeqService.getOrderNo(orderSeqQuery);
            entity.setOrderNo(orderNo);
        }
        configService.convert(entity);
        installOrderRepository.saveAndFlush(entity);
        return entity;
    }

    // protected String countSize(int size) {
    //     String num = "";
    //     if (size > 0 && size < 10) {
    //         num = "0000" + String.valueOf(size);
    //     } else if (size > 9 && size < 100) {
    //         num = "000" + String.valueOf(size);
    //     } else if (size > 99 && size < 1000) {
    //         num = "00" + String.valueOf(size);
    //     } else if (size > 999 && size < 10000) {
    //         num = "0" + String.valueOf(size);
    //     }
    //     return num;
    // }

    /**
     * 更新
     *
     * @param entity
     * @return
     */
    @Override
    public InstallOrderEntity edit(InstallOrderEntity entity) {

        //充电桩编号不能重复
        // if (!isEmpty(entity.getPileCode())) {
        //     InstallOrderEntity installCheckEntity = findByPileCodeAndCorp(entity.getPileCode(), entity.getCorpNo());
        //     DeliveryOrderEntity deliveryCheckEntity = deliveryOrderService.findByPileCodeAndCorp(entity
        //     .getPileCode(), entity.getCorpNo());
        //     if (deliveryCheckEntity != null || (installCheckEntity != null && !installCheckEntity.getId().equals
        //     (entity.getId()))) {
        //         throw new AppException(CustomMessage.PILE_CODE_CANNOT_RSD);
        //     }
        // }

        //桩型名称
        if (!isEmpty(entity.getPileModel())) {
            PileEntity pileEntity = pileService.findByNo(entity.getPileModel());
            if (pileEntity == null) {
                throw new AppException(CustomMessage.NO_DATA_FIND);
            }
            entity.setPileModelName(pileEntity.getPileName());
        }
        //服务商名称
        if (!isEmpty(entity.getSupplierNo())) {
            SupplierEntity supplierEntity = supplierService.findByNo(entity.getSupplierNo());
            if (supplierEntity == null) {
                throw new AppException(CustomMessage.NO_DATA_FIND);
            }
            entity.setSupplierName(supplierEntity.getSupplierName());
        }
        //勘察人员名称
        if (!isEmpty(entity.getCheckUserNo())) {
            StringBuffer userName = new StringBuffer();
            StringBuffer userPhone = new StringBuffer();
            String[] userArray = entity.getCheckUserNo().split(",");
            for (int i = 0; i < userArray.length; i++) {
                UserEntity user = userService.findByNo(userArray[i]);
                if (user != null && i == 0) {
                    userName.append(user.getUserName());
                    userPhone.append(user.getUserPhone());
                } else if (user != null && i > 0) {
                    userName.append("," + user.getUserName());
                    userPhone.append("," + user.getUserPhone());
                } else if (user == null) {
                    throw new AppException(CustomMessage.NO_DATA_FIND);
                }
            }
            entity.setCheckUserName(userName.toString());
            entity.setCheckUserPhone(userPhone.toString());
        }
        //安装人员
        if (!isEmpty(entity.getInstallUserNo())) {
            StringBuffer userName = new StringBuffer();
            StringBuffer userPhone = new StringBuffer();
            String[] userArray = entity.getInstallUserNo().split(",");
            for (int i = 0; i < userArray.length; i++) {
                UserEntity user = userService.findByNo(userArray[i]);
                if (user != null && i == 0) {
                    userName.append(user.getUserName());
                    userPhone.append(user.getUserPhone());
                } else if (user != null && i > 0) {
                    userName.append("," + user.getUserName());
                    userPhone.append("," + user.getUserPhone());
                } else if (user == null) {
                    throw new AppException(CustomMessage.NO_DATA_FIND);
                }
            }
            entity.setInstallUserName(userName.toString());
            entity.setInstallUserPhone(userPhone.toString());
        }
        configService.convert(entity);
        installOrderRepository.saveAndFlush(entity);
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
    public InstallOrderEntity saveOrderAndStep(InstallOrderEntity entity) {

        //充电桩编号不能重复
        // if (!isEmpty(entity.getPileCode())) {
        //     InstallOrderEntity installCheckEntity = findByPileCodeAndCorp(entity.getPileCode(), entity.getCorpNo());
        //     DeliveryOrderEntity deliveryCheckEntity = deliveryOrderService.findByPileCodeAndCorp(entity
        //     .getPileCode(), entity.getCorpNo());
        //     if (deliveryCheckEntity != null || (installCheckEntity != null && !installCheckEntity.getId().equals
        //     (entity.getId()))) {
        //         throw new AppException(CustomMessage.PILE_CODE_CANNOT_RSD);
        //     }
        // }
        //改变当前orderstep
        InstallOrderEntity checkEntity = checkById(entity.getId());
        //获取最新的订单步骤记录
        // OrderStepEntity lastOrderStepEntity = orderStepService.findLastByOrderNo(entity.getOrderNo(), SysConstant
        // .WorkFollow.CONFIG_WORKFOLLOW_INSTALL);
        // if (!isEmpty(entity.getTimeoutStartTime())) {
        //     lastOrderStepEntity.setBaseTime(entity.getTimeoutStartTime());
        // } else {
        //     lastOrderStepEntity.setBaseTime(new Date());
        // }
        BeanCopyUtil.beanCopy(entity, checkEntity);
        CompareObj<InstallOrderEntity> compareObj = new CompareObj<>();
        String compare = compareObj.compare(checkEntity, entity);
        OrderStepParameter orderStepParameter = new OrderStepParameter();
        orderStepParameter.setOrderNo(checkEntity.getOrderNo());
        orderStepParameter.setFollowNo(SysConstant.WorkFollow.WORKFOLLOW_INSTALL_NO);
        orderStepParameter.setRemark(compare);
        orderStepService.orderStepEdit(orderStepParameter);
        // lastOrderStepEntity.setStepRemark(lastOrderStepEntity.getStepRemark() + compare);
        // orderStepService.edit(lastOrderStepEntity);
        return edit(checkEntity);
    }

    /**
     * 删除（逻辑删除）
     *
     * @param id
     * @return
     */
    @Override
    public boolean delete(String id) {
        InstallOrderEntity entity = installOrderRepository.findById(SysConstant.IS_DEL_N, id);
        if (entity == null) {
            throw new AppException(CustomMessage.NO_DATA_FIND);
        }
        //删除标记
        installOrderRepository.delete(SysConstant.IS_DEL_Y, id);
        return true;
    }

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    @Override
    public InstallOrderEntity findById(String id) {
        InstallOrderEntity entity = installOrderRepository.findById(SysConstant.IS_DEL_N, id);
        return entity;
    }

    /**
     * 根据id校验
     *
     * @param id
     * @return
     */
    @Override
    public InstallOrderEntity checkById(String id) {
        InstallOrderEntity entity = installOrderRepository.findById(SysConstant.IS_DEL_N, id);
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
    public InstallOrderEntity findByNo(String no) {
        InstallOrderEntity entity = installOrderRepository.findByNo(SysConstant.IS_DEL_N, no);
        return entity;
    }


    //申请作废
    @Transactional
    @Override
    public InstallOrderEntity applyCancel(InstallOrderQuery query) {
        //检查原来是否已作废
        InstallOrderEntity entity = findByNo(query.getOrderNo());
        if (SysConstant.InstallOrder.CONFIG_FLG_Y.equals(entity.getBeginCacle())) {
            throw new AppException(CustomMessage.CANCEL_CANNOT_AGAIN);
        }
        //将开始作废标志置为Y
        entity.setBeginCacle(SysConstant.IS_DEL_Y);
        installOrderRepository.saveAndFlush(entity);
        //OrderStepEntity install = orderStepService.findLastByOrderNo(query.getOrderNo(), SysConstant.WorkFollow
        // .CONFIG_WORKFOLLOW_INSTALL);
        //作废订单权限校验
        if (!isEmpty(entity.getCheckTime())) {
            throw new AppException(CustomMessage.CANCEL_NOT_FOUND);
        }

        OrderCancelEntity orderCancelEntity = new OrderCancelEntity();
        orderCancelEntity.setCorpNo(entity.getCorpNo());


        //初始化订单步骤
        OrderStepParameter orderStepParameter = new OrderStepParameter();
        orderStepParameter.setOrderNo(entity.getOrderNo());

        //服务商没签收，直接复审
        if (entity.getReceiveStatus().equals(SysConstant.InstallOrder.CONFIG_VERIFY_N) || entity.getReceiveStatus().equals(SysConstant.InstallOrder.CONFIG_VERIFY_REFUSE)) {
            orderStepParameter.setFollowNo(SysConstant.WorkFollow.WORKFOLLOW_IN_INVALID_NO);

        } else if (entity.getReceiveStatus().equals(SysConstant.InstallOrder.RECEIVE_FLG_SIGN)) {
            //服务商签收，走正常流程
            orderStepParameter.setFollowNo(SysConstant.WorkFollow.WORKFOLLOW_INVALID_NO);

        } else {
            throw new AppException(CustomMessage.RECEIVE_STATUS);
        }


        OrderStepEntity orderStepEntity = orderStepService.orderStepAdd(orderStepParameter);


        //申请作废
        OrderCarEntity orderCarEntity = orderCarService.findByNo(entity.getOrderNo());
        CorpEntity corpEntity = getCorpEntity();
        logger.error("CRM推送（作废申请）：" + SysConstant.Crm.CRM_CANCAL);
        _scrmInfoService.submitPileOrder(null, orderCarEntity.getVinNo(), corpEntity.getCorpBrand(),
                SysConstant.Crm.CRM_CANCAL);

        return entity;
    }

    //作废
    @Transactional
    @Override
    public InstallOrderEntity cancel(InstallOrderQuery query) {
        InstallOrderEntity entity = findByNo(query.getOrderNo());
        //获取安装最新的订单步骤记录
        OrderStepEntity orderStep = orderStepService.findLastByOrderNo(query.getOrderNo(),
                SysConstant.WorkFollow.CONFIG_WORKFOLLOW_INVALID);
        OrderStepEntity orderStepIn = orderStepService.findLastByOrderNo(query.getOrderNo(),
                SysConstant.WorkFollow.CONFIG_WORKFOLLOW_INVALID_IN);
        //是否审核（签收）
        if (SysConstant.Advice.RECEIVE_PASS.equals(query.getReceiveStatus())) {
            //签收通过或非签收步骤
            OrderStepParameter orderStepParameter = new OrderStepParameter();
            orderStepParameter.setOrderNo(entity.getOrderNo());
            orderStepParameter.setResultDesc(query.getRemark());
            if (orderStep != null) {
                orderStepParameter.setFollowNo(SysConstant.WorkFollow.WORKFOLLOW_INVALID_NO);
            }
            if (orderStepIn != null) {
                orderStepParameter.setFollowNo(SysConstant.WorkFollow.WORKFOLLOW_IN_INVALID_NO);
            }
            //orderStepParameter.setBaseTime(entity.getBaseTime());
            //orderStepParameter.setEndTime(entity.getEndTime());
            OrderStepEntity orderStepEntity = orderStepService.orderStepAdd(orderStepParameter);
            if (SysConstant.OrderStep.LAST_STEP_Y.equals(orderStepEntity.getLastStep())) {

                OrderCarEntity orderCarEntity = orderCarService.findByNo(entity.getOrderNo());
                CorpEntity corpEntity = getCorpEntity();
                logger.error("CRM推送（作废完成）：" + SysConstant.Crm.CRM_RETURN);
                _scrmInfoService.submitPileOrder(null, orderCarEntity.getVinNo(), corpEntity.getCorpBrand(),
                        SysConstant.Crm.CRM_RETURN);
                orderCarEntity.setVinNo(orderCarEntity.getVinNo() + "(已作废)");
                orderCarService.edit(orderCarEntity);

            }
            entity.setBeginCacle(SysConstant.IS_DEL_Y);
            installOrderRepository.saveAndFlush(entity);
        } else {
            //签收不通过
            OrderStepEntity orderStepEntity = new OrderStepEntity();
            OrderStepParameter orderStepParameter = new OrderStepParameter();
            orderStepParameter.setOrderNo(entity.getOrderNo());
            orderStepParameter.setResultDesc(query.getRemark());
            if (orderStep != null) {
                orderStepParameter.setFollowNo(SysConstant.WorkFollow.WORKFOLLOW_INVALID_NO);
            }
            if (orderStepIn != null) {
                orderStepParameter.setFollowNo(SysConstant.WorkFollow.WORKFOLLOW_IN_INVALID_NO);
            }
            orderStepEntity = orderStepService.orderStepRejct(orderStepParameter);
            orderStepEntity.setCurrentStepFlg(SysConstant.IS_DEL_N);
            orderStepService.edit(orderStepEntity);
            entity.setBeginCacle(SysConstant.IS_DEL_N);
            installOrderRepository.saveAndFlush(entity);
            //CRM推送 审核不通过
            OrderCarEntity orderCarEntity = orderCarService.findByNo(entity.getOrderNo());
            CorpEntity corpEntity = getCorpEntity();
            logger.error("CRM推送（作废审核不通过）：" + SysConstant.Crm.CRM_SCHEDULE);
            _scrmInfoService.submitPileOrder(null, orderCarEntity.getVinNo(), corpEntity.getCorpBrand(),
                    SysConstant.Crm.CRM_SCHEDULE);
        }
        //如果是最后一步，结束标志置Y
//        if (SysConstant.OrderStep.LAST_STEP_Y.equals(orderStepEntity.getLastStep())) {
//            oldEntity.setFinishFlg(SysConstant.InstallOrder.FINISH_FLG_Y);
//            oldEntity.setFinishTime(new Date());
//        }
        return entity;
    }


    /**
     * 批量新增
     *
     * @param list
     * @return
     */
    @Override
    public List<InstallOrderEntity> addList(List<InstallOrderEntity> list) {
        return installOrderRepository.save(list);
    }

    /**
     * 提交订单
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional
    public InstallOrderEntity addInstall(InstallOrderEntity entity) {
        //初始化 回退标志
        entity.setCrmRollbackFlg(SysConstant.InstallOrder.CONFIG_FLG_N);
        entity.setOrderNo(null);
        //获取车主信息
        OrderCarEntity orderCarEntity = new OrderCarEntity();
        orderCarEntity.setCorpNo(entity.getCorpNo());
        //20190819 baimin crm接口现在由前端调用，数据直接传给后台
        if (SysConstant.InstallOrder.CONFIG_FLG_Y.equals(entity.getCrmTestFlg())) {
            OrderCarQuery orderCarQuery = new OrderCarQuery();
            orderCarQuery.setVin(entity.getVin());
            orderCarEntity = orderCarService.getOrderCarInfo(orderCarQuery);
        } else {
            orderCarEntity.setCarOwner(entity.getCarOwner());
            orderCarEntity.setCarOwnerPhone(entity.getCarOwnerPhone());
            orderCarEntity.setDealerName(entity.getDealerName());
            orderCarEntity.setDealerContact(entity.getDealerContact());
            orderCarEntity.setDealerTel(entity.getDealerTel());
            orderCarEntity.setCarVehicle(entity.getCarVehicle());
            orderCarEntity.setVehicleDetail(entity.getVehicleDetail());
            orderCarEntity.setVinNo(entity.getVinNo());
            orderCarEntity.setEngineNo(entity.getEngineNo());
            orderCarEntity.setSaleDate(entity.getSaleDate());
        }
        //车型名是否匹配
        VehicleEntity vehicleEntity = vehicleService.findByNameAndCorp(orderCarEntity.getCarVehicle(),
                entity.getCorpNo());
        if (vehicleEntity == null) {
            logger.info("车型名不匹配");
            throw new AppException(CustomMessage.DO_NOT_MEET_PILE_POLICY);
        }
        //VIN中不能有记录
        VinEntity vinEntity = vinService.findByCode(orderCarEntity.getVinNo());
        if (vinEntity != null) {
            logger.info("VIN中有记录");
            throw new AppException(CustomMessage.DO_NOT_MEET_PILE_POLICY);
        }
        //二次安装校验
        String secondInstall = "N";
        InstallOrderEntity installOrderEntity = this.findByVin(orderCarEntity.getVinNo(), orderCarEntity.getCorpNo());
        if (installOrderEntity != null && SysConstant.InstallOrder.CONFIG_FLG_N.equals(installOrderEntity.getCrmRollbackFlg())) {
            throw new AppException(CustomMessage.CAN_NOT_CREATE_ORDER);
        } else {
            DeliveryOrderEntity deliveryOrderEntity = deliveryOrderService.findByVin(orderCarEntity.getVinNo(), orderCarEntity.getCorpNo());
            if (deliveryOrderEntity != null) {
                secondInstall = deliveryOrderEntity.getSecondInstall();
                if (SysConstant.IS_DEL_N.equals(deliveryOrderEntity.getSecondInstall())) {
                    throw new AppException(CustomMessage.CAN_NOT_CREATE_ORDER);
                }
            }
        }
        //orderCar中不可存在该vin
        OrderCarEntity checkOrderCarEntity = orderCarService.findByVinNoAndCorp(orderCarEntity.getVinNo(),
                entity.getCorpNo());
        if (SysConstant.IS_DEL_N.equals(secondInstall)) {
            if (checkOrderCarEntity != null) {
                //判断 vin对应的订单状态 如果为撤回操作 则 删除car 对应的数据
                InstallOrderEntity checkRurn = findByNo(checkOrderCarEntity.getOrderNo());
                if (checkRurn != null && SysConstant.InstallOrder.CONFIG_FLG_Y.equals(checkRurn.getCrmRollbackFlg())) {
                    orderCarService.delete(checkOrderCarEntity.getId());
                    entity.setId(checkRurn.getId());
                    entity.setOrderNo(checkRurn.getOrderNo());
                } else {
                    logger.info("orderCar中存在该vin");
                    throw new AppException(CustomMessage.DO_NOT_MEET_PILE_POLICY);
                }
            }
        } else if (SysConstant.IS_DEL_Y.equals(secondInstall)) {
            OrderCarEntity orderCarEntity1 = orderCarService.findByVinNoAndCorp(orderCarEntity.getVinNo(), orderCarEntity.getCorpNo());
            if (orderCarEntity1 != null) {
                orderCarService.delete(orderCarEntity1.getId());
            }
        }
        entity.setSupplierConfirmFlg(SysConstant.InstallOrder.RECEIVE_FLG_SIGN);
        entity.setVerifyFlg(SysConstant.InstallOrder.CONFIG_VERIFY_N);
        entity.setBeginCacle(SysConstant.InstallOrder.CONFIG_FLG_N);
        entity.setSettleFlg(SysConstant.InstallOrder.CONFIG_FLG_N);
        entity.setFinishFlg(SysConstant.InstallOrder.CONFIG_FLG_N);
        entity.setInspectionTourFlg(SysConstant.InstallOrder.CONFIG_FLG_N);
        entity.setOrderTime(new Date());
        entity.setReceiveStatus(SysConstant.InstallOrder.CONFIG_VERIFY_N);
        //新增报修订单
        //   entity.setFinishFlg(SysConstant.InstallOrder.FINISH_FLG_N);
        add(entity);

        //初始化订单步骤
        OrderStepParameter orderStepParameter = new OrderStepParameter();
        orderStepParameter.setOrderNo(entity.getOrderNo());
        orderStepParameter.setFollowNo(SysConstant.WorkFollow.WORKFOLLOW_INSTALL_NO);
        orderStepService.orderStepAdd(orderStepParameter);
        //是否自动派单
        //提单完成后orderAuto数量增加
        ConfigEntity configEntity = configService.findByTypeAndCode(SysConstant.Supplier.CONFIG_TYPE_AUTO_ORDER
                , SysConstant.Supplier.CONFIG_VAL_AUTO_ORDER, entity.getCorpNo());
        if (SysConstant.InstallOrder.CONFIG_FLG_Y.equals(configEntity.getConfigCont1())) {
            OrderAutoQuery orderAutoQuery = new OrderAutoQuery();
            orderAutoQuery.setOrderType(SysConstant.WorkFollow.CONFIG_WORKFOLLOW_INSTALL);
            orderAutoQuery.setProvinceName(entity.getInstallProvince());
            orderAutoQuery.setCityName(entity.getInstallCity());
            orderAutoQuery.setCorpNo(entity.getCorpNo());
            orderAutoQuery.setOrderNo(entity.getOrderNo());

            String strSupplierNo = orderAutoService.findAutoOrderSupplierNo(orderAutoQuery);
            if (!isEmpty(strSupplierNo)) {
                entity.setSupplierNo(strSupplierNo);
                entity.setDispatchTime(new Date());
                entity.setDispatchRemark("自动派单");
                edit(entity);
                //订单步骤前往下一步
                orderStepParameter.setRemark(SysConstant.InstallOrder.AUTO_ORDER_REMARK);
                orderStepService.orderStepAdd(orderStepParameter);
            }
        }
        //车主信息新增
        orderCarEntity.setCorpNo(entity.getCorpNo());
        orderCarEntity.setOrderNo(entity.getOrderNo());
        // orderCarEntity.setCarOwner(entity.getInstallContact());
        // orderCarEntity.setCarOwnerPhone(entity.getInstallContactPhone());
        orderCarService.add(orderCarEntity);

        //订单提报以后推送信息
        CorpEntity corpEntity = getCorpEntity();
        logger.error("CRM推送（新建订单）：" + SysConstant.Crm.CRM_SCHEDULE);
        _scrmInfoService.submitPileOrder(null, orderCarEntity.getVinNo(), corpEntity.getCorpBrand(),
                SysConstant.Crm.CRM_SCHEDULE);
        return entity;
    }

    private OrderCarEntity getCarOwnerInfo(String token, String vin, Integer brand) {
        OrderCarEntity orderCarEntity = new OrderCarEntity();
        ScrmCarInfoEntity scrmCarInfo = _scrmInfoService.carInfo(token, vin, brand);
        orderCarEntity.setCarOwnerPhone(scrmCarInfo.getContactPhone());
        orderCarEntity.setCarVehicle(scrmCarInfo.getCarType());
        orderCarEntity.setDealerName(scrmCarInfo.getSimpleName());
        orderCarEntity.setVinNo(scrmCarInfo.getVin());
        orderCarEntity.setEngineNo(scrmCarInfo.getEngine());
        orderCarEntity.setSaleDate(new Date(scrmCarInfo.getSaleDate()));
        return orderCarEntity;
    }

    /**
     * 编辑订单
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional
    public InstallOrderEntity editInstall(InstallOrderEntity entity) {
        //充电桩编号不能重复
        // if (!isEmpty(entity.getPileCode())) {
        //     InstallOrderEntity installCheckEntity = findByPileCodeAndCorp(entity.getPileCode(), entity.getCorpNo());
        //     DeliveryOrderEntity deliveryCheckEntity = deliveryOrderService.findByPileCodeAndCorp(entity
        //     .getPileCode(), entity.getCorpNo());
        //     if (deliveryCheckEntity != null || (installCheckEntity != null && !installCheckEntity.getId().equals
        //     (entity.getId()))) {
        //         throw new AppException(CustomMessage.PILE_CODE_CANNOT_RSD);
        //     }
        // }
        OrderStepParameter orderStepParameter = new OrderStepParameter();
        InstallOrderEntity oldEntity = findById(entity.getId());
        //校验
        verifyOrder(oldEntity);
        BeanCopyUtil.beanCopy(entity, oldEntity);
        //是否审核
        OrderStepEntity orderStepEntity;

        //超时开始结束时间
        WorkFollowDetailQuery workFollowDetailQuery = new WorkFollowDetailQuery();
        workFollowDetailQuery.setFollowNo(SysConstant.WorkFollow.WORKFOLLOW_INSTALL_NO);
        workFollowDetailQuery.setSort("+followSeq");
        List<WorkFollowDetailEntity> workFollowDetailEntityList = workFollowDetailService.findAll(workFollowDetailQuery);

        OrderStepEntity currentOrderStep = orderStepService.findLastByOrderNo(oldEntity.getOrderNo(), SysConstant.WorkFollow.CONFIG_WORKFOLLOW_INSTALL);
        switch (currentOrderStep.getFollowSeq()) {
            case SysConstant.InstallOrder.RECEIVE_SEQ:
                WorkFollowDetailEntity appointWork = workFollowDetailEntityList.stream()
                        .filter(x -> SysConstant.InstallOrder.CONFIG_FLG_Y.equals(x.getTimeOutFlg()) && SysConstant.WorkFollow.WORKFOLLOW_TIMEOUT_TYPE01.equals(x.getTimeoutType()))
                        .findFirst().orElse(null);
                //签收
                setBaseAndEndTime(appointWork, oldEntity, orderStepParameter, SysConstant.WorkFollow.WORKFOLLOW_TIMEOUT_TYPE01);
                break;
            case SysConstant.InstallOrder.CONTACT_SEQ:
                WorkFollowDetailEntity linkCust = workFollowDetailEntityList.stream()
                        .filter(x -> SysConstant.InstallOrder.CONFIG_FLG_Y.equals(x.getTimeOutFlg()) && SysConstant.WorkFollow.WORKFOLLOW_TIMEOUT_TYPE02.equals(x.getTimeoutType()))
                        .findFirst().orElse(null);
                //联系客户
                setBaseAndEndTime(linkCust, oldEntity, orderStepParameter, SysConstant.WorkFollow.WORKFOLLOW_TIMEOUT_TYPE02);
                break;
            case SysConstant.InstallOrder.CHECK_SEQ:
                WorkFollowDetailEntity check = workFollowDetailEntityList.stream()
                        .filter(x -> SysConstant.InstallOrder.CONFIG_FLG_Y.equals(x.getTimeOutFlg()) && SysConstant.WorkFollow.WORKFOLLOW_TIMEOUT_TYPE03.equals(x.getTimeoutType()))
                        .findFirst().orElse(null);
                //勘察
                setBaseAndEndTime(check, oldEntity, orderStepParameter, SysConstant.WorkFollow.WORKFOLLOW_TIMEOUT_TYPE03);
                break;
            case SysConstant.InstallOrder.INSTALL_SEQ:
                WorkFollowDetailEntity install = workFollowDetailEntityList.stream()
                        .filter(x -> SysConstant.InstallOrder.CONFIG_FLG_Y.equals(x.getTimeOutFlg()) && SysConstant.WorkFollow.WORKFOLLOW_TIMEOUT_TYPE04.equals(x.getTimeoutType()))
                        .findFirst().orElse(null);
                //安装
                setBaseAndEndTime(install, oldEntity, orderStepParameter, SysConstant.WorkFollow.WORKFOLLOW_TIMEOUT_TYPE04);
                break;
        }

        if (SysConstant.Advice.RECEIVE_PASS.equals(entity.getVerifyStatus())) {
            //审核通过或非审核步骤
            orderStepParameter.setOrderNo(oldEntity.getOrderNo());
            orderStepParameter.setResultDesc(entity.getRemark());
            // orderStepParameter.setBaseTime(entity.getBaseTime());
            // orderStepParameter.setEndTime(entity.getEndTime());
            orderStepParameter.setTimeOutType(SysConstant.OrderStep.TIMEOUT_TYPE_WORKDAY);
            orderStepParameter.setOrderGradeEntity(entity.getOrderGradeEntity());
            orderStepParameter.setRebuildOrderType(SysConstant.OrderStep.REBUILD_TYPE_INSTALL);
            orderStepParameter.setFollowNo(SysConstant.WorkFollow.WORKFOLLOW_INSTALL_NO);
            orderStepEntity = orderStepService.orderStepAdd(orderStepParameter);
        } else {
            //审核不通过
            orderStepParameter.setOrderNo(oldEntity.getOrderNo());
            orderStepParameter.setResultDesc(entity.getRemark());
            orderStepParameter.setFollowNo(SysConstant.WorkFollow.WORKFOLLOW_INSTALL_NO);
            orderStepEntity = orderStepService.orderStepRejct(orderStepParameter);
            // 20190916 baimin 签收不通过清空服务商
            if (!isEmpty(entity.getReceiveStatus()) && SysConstant.InstallOrder.CONFIG_VERIFY_REFUSE.equals(entity.getReceiveStatus())) {
                oldEntity.setSupplierNo(null);
                oldEntity.setSupplierName(null);
            }
        }
        if (SysConstant.WorkFollow.CONFIRM_FLG_Y.equals(orderStepEntity.getConfirmStep())) {
            //如果服务商确认，生成费用表
            OrderFeeQuery orderFeeQuery = new OrderFeeQuery();
            orderFeeQuery.setOrderNo(oldEntity.getOrderNo());
            orderFeeQuery.setCorpNo(oldEntity.getCorpNo());
            orderFeeQuery.setOrderTime(oldEntity.getOrderTime());
            orderFeeQuery.setOrderType(SysConstant.WorkFollow.CONFIG_WORKFOLLOW_INSTALL);
            orderFeeQuery.setPileModel(oldEntity.getPileModel());
            orderFeeQuery.setSupplierNo(oldEntity.getSupplierNo());
            orderFeeQuery.setSupplierName(oldEntity.getSupplierName());
            orderFeeService.addOrderFee(orderFeeQuery);
        }
        //CRM推送用数据
        OrderCarEntity orderCarEntity = orderCarService.findByNo(oldEntity.getOrderNo());
        CorpEntity corpEntity = getCorpEntity();
        //判断是否为满意度
        if (entity.getOrderGradeEntity() != null) {
            OrderGradeEntity orderGradeEntity = entity.getOrderGradeEntity();
            orderGradeEntity.setOrderNo(oldEntity.getOrderNo());
            orderGradeEntity.setOrderType(SysConstant.WorkFollow.CONFIG_WORKFOLLOW_INSTALL);
            orderGradeService.add(orderGradeEntity);
            //CRM推送 满意度回访
            logger.error("CRM推送（满意度回访）：" + SysConstant.Crm.CRM_SCHEDULE);
            _scrmInfoService.submitPileOrder(null, orderCarEntity.getVinNo(), corpEntity.getCorpBrand(),
                    SysConstant.Crm.CRM_SCHEDULE);
        }

        //如果是最后一步，结束标志置Y
        if (SysConstant.OrderStep.LAST_STEP_Y.equals(orderStepEntity.getLastStep())) {
            oldEntity.setFinishFlg(SysConstant.InstallOrder.FINISH_FLG_Y);
            oldEntity.setFinishTime(new Date());
            ////CRM推送 订单完成
            // OrderCarEntity orderCarEntity = orderCarService.findByNo(oldEntity.getOrderNo());
            // CorpEntity corpEntity = getCorpEntity();
            logger.error("CRM推送（订单完成）：" + SysConstant.Crm.CRM_FINISH);
            _scrmInfoService.submitPileOrder(null, orderCarEntity.getVinNo(), corpEntity.getCorpBrand(),
                    SysConstant.Crm.CRM_FINISH);
        }
        edit(oldEntity);
        //派单数量验证 20190816 baimin 自动派单不再记录手动派单数量

        //判断是否为经销商提交
        OrderGradeEntity orderGradeEntity = orderGradeService.findByOrderNo(oldEntity.getOrderNo());
        if (SysConstant.OrderStep.VERIFY_RESULT_PASS.equals(oldEntity.getSupplierConfirmFlg()) && orderGradeEntity == null) {
            //CRM推送 待满意度回访
            logger.error("CRM推送（待满意度回访）：" + SysConstant.Crm.CRM_GRADE);
            _scrmInfoService.submitPileOrder(null, orderCarEntity.getVinNo(), corpEntity.getCorpBrand(),
                    SysConstant.Crm.CRM_GRADE);
        }
        return oldEntity;
    }

    private void setBaseAndEndTime(WorkFollowDetailEntity workFollowDetailEntity, InstallOrderEntity installOrderEntity, OrderStepParameter orderStepParameter, String overType) {
        //判断接单超时
        if (workFollowDetailEntity != null && !isEmpty(installOrderEntity)) {
            Date baseTime = new Date();
            Date endTime = new Date();
            switch (overType) {
                case SysConstant.WorkFollow.WORKFOLLOW_TIMEOUT_TYPE01:
                    baseTime = installOrderEntity.getDispatchTime();
                    // if (!isEmpty(installOrderEntity.getReceiveTime())) {
                    endTime = installOrderEntity.getReceiveTime();
                    // }
                    break;
                case SysConstant.WorkFollow.WORKFOLLOW_TIMEOUT_TYPE02:
                    baseTime = installOrderEntity.getReceiveTime();
                    // if (!isEmpty(installOrderEntity.getContactCustTime())) {
                    endTime = installOrderEntity.getContactCustTime();
                    // }
                    break;
                case SysConstant.WorkFollow.WORKFOLLOW_TIMEOUT_TYPE03:
                    baseTime = installOrderEntity.getCheckTime();
                    // if (!isEmpty(installOrderEntity.getCheckTime())) {
                    endTime = installOrderEntity.getCheckFinishTime();
                    // }
                    break;
                case SysConstant.WorkFollow.WORKFOLLOW_TIMEOUT_TYPE04:
                    baseTime = installOrderEntity.getInstallTime();
                    // if (isEmpty(installOrderEntity.getInstallFinishTime())) {
                    endTime = installOrderEntity.getInstallFinishTime();
                    // }
                    break;
            }
            orderStepParameter.setBaseTime(baseTime);
            orderStepParameter.setEndTime(endTime);
        }
    }

    /**
     * 根据桩编码查找
     *
     * @param pileCode
     * @param corpNo
     * @param corpNo
     * @return
     */
    @Override
    public InstallOrderEntity findByPileCodeAndCorp(String pileCode, String corpNo) {


        InstallOrderEntity installOrderEntity = installOrderRepository.findByPileCodeAndCorp(SysConstant.IS_DEL_N,
                pileCode, corpNo);
        return installOrderEntity;

    }

    /**
     * 撤回操作
     *
     * @param no
     * @return
     */

    @Override
    @Transactional
    public InstallOrderEntity installReturn(String no, String remark) {

        InstallOrderEntity installOrderEntity = findByNo(no);

        if (!isEmpty(installOrderEntity.getReceiveTime())) {
            throw new AppException(CustomMessage.CAN_NOT_ROLLBACK);
        }

        installOrderEntity.setCrmRollbackFlg(SysConstant.InstallOrder.CONFIG_FLG_Y);
        installOrderEntity.setCrmRollbackRemark(remark);
        installOrderRepository.saveAndFlush(installOrderEntity);

        OrderStepEntity orderStepEntity = orderStepService.findLastByOrderNo(installOrderEntity.getOrderNo(),
                SysConstant.WorkFollow.CONFIG_WORKFOLLOW_INSTALL);
        orderStepEntity.setCurrentStepFlg(SysConstant.InstallOrder.CONFIG_FLG_N);
        orderStepEntity.setStepName("订单撤回");
        orderStepEntity.setStepTime(new Date());
        orderStepEntity.setStepRemark(remark);
        String userId = BaseContextHandler.getUserId();
        UserEntity userEntity = userService.findById(userId);
        orderStepEntity.setStepUserNo(userEntity.getUserNo());
        orderStepEntity.setStepUserName(userEntity.getUserName());
        orderStepService.edit(orderStepEntity);

        OrderCarEntity orderCarEntity = orderCarService.findByNo(no);
        CorpEntity corpEntity = getCorpEntity();
        logger.error("CRM推送（充电经理退回）：" + SysConstant.Crm.CRM_RETURN);
        _scrmInfoService.submitPileOrder(null, orderCarEntity.getVinNo(), corpEntity.getCorpBrand(),
                SysConstant.Crm.CRM_RETURN);
        //20191018 baimin 撤回后vin变更，否则不能重新提单另一类型
        orderCarEntity.setVinNo(orderCarEntity.getVinNo() + "(已撤回)");
        orderCarService.edit(orderCarEntity);

        return installOrderEntity;
    }

    /**
     * 根据VIN查找
     *
     * @param vin
     * @param corpNo
     * @return
     */
    @Override
    public InstallOrderEntity findByVin(String vin, String corpNo) {
        InstallOrderEntity result = null;
        OrderCarQuery orderCarQuery = new OrderCarQuery();
        orderCarQuery.setVin(vin);
        orderCarQuery.setCorpNo(corpNo);
        orderCarQuery.setSort("-createdWhen");
        List<OrderCarEntity> orderCarEntityList = orderCarService.findAll(orderCarQuery);
        // OrderCarEntity orderCarEntity = orderCarService.findByVinNoAndCorp(vin, corpNo);
        // if (orderCarEntity != null) {
        if (orderCarEntityList.size() > 0) {
            OrderCarEntity orderCarEntity = orderCarEntityList.stream().findFirst().orElse(null);
            InstallOrderEntity installOrderEntity = findByNo(orderCarEntity.getOrderNo());
            if (installOrderEntity != null) {
                // List<OrderAttachEntity> orderAttachEntityList =
                //         orderAttachService.findByOrderNo(installOrderEntity.getOrderNo());
                // for (OrderAttachEntity item : orderAttachEntityList) {
                //     item.setAttachPath(SysConstant.ATTACH_VISIT_PERF + item.getAttachPath());
                // }

                //20190916 baimin 现在查询附件统一放到控制层，否则内部调用此方法再保存，附件地址会被更改
                // OrderAttachQuery orderAttachQuery = new OrderAttachQuery();
                // orderAttachQuery.setOrderNo(installOrderEntity.getOrderNo());
                // orderAttachQuery.setSort("+createdWhen");
                // List<OrderAttachEntity> orderAttachEntityList = orderAttachService.findAll(orderAttachQuery);
                // if (orderAttachEntityList == null) {
                //     orderAttachEntityList = new ArrayList<>();
                // } else {
                //     for (OrderAttachEntity item : orderAttachEntityList) {
                //         item.setAttachPath(SysConstant.ATTACH_VISIT_PERF + item.getAttachPath());
                //     }
                // }
                // installOrderEntity.setOrderAttachEntityList(orderAttachEntityList);

                //20190903 增加查询二次安装
                // String secondInstall = "";
                // VehicleDetailEntity vehicleDetailEntity =
                //         vehicleDetailService.findByDetailNameAndCorp(orderCarEntity.getVehicleDetail(),
                //                 orderCarEntity.getCorpNo());
                // if (vehicleDetailEntity != null) {
                //     secondInstall = vehicleDetailEntity.getSecondInstall();
                // }
                // installOrderEntity.setSecondInstall(secondInstall);
                result = installOrderEntity;
            }
        }
        return result;
    }

    /**
     * 校验
     *
     * @param checkEntity
     */
    private void verifyOrder(InstallOrderEntity checkEntity) {
        //判断是否完成
        if (SysConstant.InstallOrder.CONFIG_FLG_Y.equals(checkEntity.getFinishFlg())) {
            throw new AppException(CustomMessage.CAN_NOT_OPERATE);
        }
        //判断是否开始作废
        if (isEmpty(checkEntity.getBeginCacle()) && SysConstant.InstallOrder.CONFIG_FLG_Y.equals(checkEntity.getBeginCacle())) {
            throw new AppException(CustomMessage.CAN_NOT_OPERATE);
        }
    }


    /**
     * 获取用户信息
     *
     * @return
     */
    protected CorpEntity getCorpEntity() {
        String userId = BaseContextHandler.getUserId();
        UserEntity user = userService.findById(userId);
        CorpEntity corpEntity = corpService.findByNo(user.getCorpNo());

        return corpEntity;
    }

    /**
     * 结算审核未通过，订单状态变为待服务商确认
     *
     * @return
     */
    @Override
    public InstallOrderEntity settleVerifyFail(String orderNo, String remark) {
        InstallOrderEntity installOrderEntity = findByNo(orderNo);
        installOrderEntity.setSettleFlg(SysConstant.InstallOrder.SETTLE_FLG_N);
        installOrderEntity.setFinishFlg(SysConstant.InstallOrder.FINISH_FLG_N);
        installOrderEntity.setSettleVerifyFlg(SysConstant.IS_DEL_Y);
        installOrderEntity.setSettleRemark(remark);

        edit(installOrderEntity);

        //生成待确认步骤
        OrderStepParameter orderStepParameter = new OrderStepParameter();
        orderStepParameter.setOrderNo(installOrderEntity.getOrderNo());
        orderStepParameter.setRemark("结算审核未通过");
        orderStepParameter.setBaseTime(new Date());
        // orderStepParameter.setEndTime(entity.getEndTime());
        // if (entity.getEndTime() != null) {
        //     orderStepParameter.setEndTime(entity.getEndTime());
        // }
        orderStepParameter.setFollowNo(SysConstant.WorkFollow.WORKFOLLOW_INSTALL_NO);
        OrderStepEntity orderStepEntity = orderStepService.orderStepSettleFail(orderStepParameter);
        return installOrderEntity;
    }

    /**
     * 根据公司查找撤回订单
     *
     * @param crmRollbackFlg
     * @param corpNo
     * @return
     */
    @Override
    public List<InstallOrderEntity> findRollBackOrder(String crmRollbackFlg, String vinNo, String corpNo) {
        return installOrderRepository.findRollBackOrderByVinAndCorpNo(SysConstant.IS_DEL_N, crmRollbackFlg, vinNo, corpNo);
    }

}

