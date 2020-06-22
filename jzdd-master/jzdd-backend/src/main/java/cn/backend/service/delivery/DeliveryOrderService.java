package cn.backend.service.delivery;

import cn.backend.config.constant.SysConstant;
import cn.backend.config.message.CustomMessage;
import cn.backend.dao.primary.delivery.DeliveryOrderRepository;
import cn.backend.model.primary.corp.CorpEntity;
import cn.backend.model.primary.delivery.DeliveryOrderEntity;
import cn.backend.model.primary.delivery.DeliveryOrderQuery;
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
import cn.backend.model.primary.vehicledetail.VehicleDetailEntity;
import cn.backend.model.primary.vin.VinEntity;
import cn.backend.model.primary.workfollow.WorkFollowEntity;
import cn.backend.model.primary.workfollowdetail.WorkFollowDetailEntity;
import cn.backend.model.primary.workfollowdetail.WorkFollowDetailQuery;
import cn.backend.service.BaseService;
import cn.backend.service.config.IConfigService;
import cn.backend.service.corp.ICorpService;
import cn.backend.service.installorder.IInstallOrderService;
import cn.backend.service.orderattach.IOrderAttachService;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author sunkw
 * @date 2019/07/16
 */
@Slf4j
@Service(value = "deliveryOrderService")
public class DeliveryOrderService extends BaseService implements IDeliveryOrderService {

    @Resource
    private DeliveryOrderRepository deliveryOrderRepository;

    @Autowired
    private IConfigService configService;

    @Autowired
    private ISupplierService supplierService;

    @Autowired
    private IOrderStepService orderStepService;

    @Autowired
    private IVehicleService vehicleService;

    @Autowired
    private IVinService vinService;

    @Autowired
    private IWorkFollowService workFollowService;

    @Autowired
    private IOrderCarService orderCarService;

    @Autowired
    private IWorkFollowDetailService workFollowDetailService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IOrderGradeService orderGradeService;

    @Autowired
    private IOrderAttachService orderAttachService;

    @Autowired
    private IOrderSeqService orderSeqService;

    @Autowired
    private IOrderFeeService orderFeeService;

    @Autowired
    private IPileService pileService;


    @Autowired
    private IScrmInfoService scrmInfoService;

    @Autowired
    private ICorpService corpService;

    @Autowired
    private IInstallOrderService installOrderService;

    @Autowired
    private IVehicleDetailService vehicleDetailService;


    /**
     * 分页查询
     *
     * @param deliveryOrderQuery
     * @return
     */
    @Override
    public Page<DeliveryOrderEntity> findList(DeliveryOrderQuery deliveryOrderQuery) {
        Sort sort = buildSort(deliveryOrderQuery.getSort());
        Pageable pageable = new PageRequest(deliveryOrderQuery.getPageNum(), deliveryOrderQuery.getPageSize(), sort);
        Page<DeliveryOrderEntity> entityPage = deliveryOrderRepository.findAll(createSpecification(deliveryOrderQuery), pageable);
        return entityPage;
    }

    /**
     * 列表查询
     *
     * @param deliveryOrderQuery
     * @return
     */
    @Override
    public List<DeliveryOrderEntity> findAll(DeliveryOrderQuery deliveryOrderQuery) {
        Sort sort = buildSort(deliveryOrderQuery.getSort());
        List<DeliveryOrderEntity> entityList = deliveryOrderRepository.findAll(createSpecification(deliveryOrderQuery), sort);
        return entityList;
    }

    /**
     * 创建查询条件
     *
     * @param deliveryOrderQuery
     * @return
     */
    private Specification createSpecification(DeliveryOrderQuery deliveryOrderQuery) {
        return new Specification<DeliveryOrderEntity>() {

            @Override
            public Predicate toPredicate(Root<DeliveryOrderEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();

                //公司
                if (!isEmpty(deliveryOrderQuery.getCorpNo())) {
                    predicate.add(cb.equal(root.get("corpNo").as(String.class), deliveryOrderQuery.getCorpNo()));
                } else {
                    predicate.add(cb.equal(root.get("corpNo").as(String.class), BaseContextHandler.getCorpNo()));
                }

                //省
                if (!isEmpty(deliveryOrderQuery.getProvince())) {
                    predicate.add(cb.equal(root.get("installProvince").as(String.class), deliveryOrderQuery.getProvince()));
                }

                // 市
                if (!isEmpty(deliveryOrderQuery.getCity())) {
                    predicate.add(cb.equal(root.get("installCity").as(String.class), deliveryOrderQuery.getCity()));
                }

                // 状态
//               if (!isEmpty(traceQuery.getRuntimeStatus())) {
//                   predicate.add(cb.equal(root.get("runtimeStatus").as(String.class), traceQuery.getRuntimeStatus()));
//               }

                //接单人和随行人员
//               if (!StringUtils.isEmpty(traceQuery.getStaff())) {
//                   predicate.add(cb.or(cb.equal(root.get("runtimePersonName").as(String.class), traceQuery.getStaff()),
//                           cb.like(root.get("followPersonNames").as(String.class), "%"+traceQuery.getStaff()+"%")));
//               }

                //订单创建时间
                if (!org.springframework.util.StringUtils.isEmpty(deliveryOrderQuery.getOrderStartTime())) {
                    //大于或等于创建时间
                    predicate.add(cb.greaterThanOrEqualTo(root.get("orderTime").as(Date.class), deliveryOrderQuery.getOrderStartTime()));
                }
                if (!org.springframework.util.StringUtils.isEmpty(deliveryOrderQuery.getOrderOverTime())) {
                    //小于或等于修改时间
                    predicate.add(cb.lessThanOrEqualTo(root.get("orderTime").as(Date.class), deliveryOrderQuery.getOrderOverTime()));
                }

                //配送完成创建时间
                if (!org.springframework.util.StringUtils.isEmpty(deliveryOrderQuery.getDeliveryStartTime())) {
                    //大于或等于创建时间
                    predicate.add(cb.greaterThanOrEqualTo(root.get("deliveryFinishTime").as(Date.class), deliveryOrderQuery.getDeliveryStartTime()));
                }
                if (!org.springframework.util.StringUtils.isEmpty(deliveryOrderQuery.getDeliveryOverTime())) {
                    //小于或等于修改时间
                    predicate.add(cb.lessThanOrEqualTo(root.get("deliveryFinishTime").as(Date.class), deliveryOrderQuery.getDeliveryOverTime()));
                }

                //是否开始废弃动作
                if (!isEmpty(deliveryOrderQuery.getBeginCacle())) {
                    predicate.add(cb.equal(root.get("beginCacle").as(String.class), deliveryOrderQuery.getBeginCacle()));
                }

                //是否CRM撤回
                if (!isEmpty(deliveryOrderQuery.getCrmRollbackFlg())) {
                    predicate.add(cb.equal(root.get("crmRollbackFlg").as(String.class), deliveryOrderQuery.getCrmRollbackFlg()));
                }

                //根据订单号查询
                if (!StringUtils.isEmpty(deliveryOrderQuery.getOrderNos())) {

                    Expression<String> exp = root.<String>get("orderNo");
                    String str = deliveryOrderQuery.getOrderNos();
                    String[] strarr = str.split(",");
                    List<String> stringList = new ArrayList<>();
                    for (int i = 0; i < strarr.length; i++) {
                        stringList.add(strarr[i]);

                    }

                    predicate.add(exp.in(stringList));
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
    public DeliveryOrderEntity add(DeliveryOrderEntity entity) {
        log.info("==========新增-DeliveryOrderService.add()==========1");
        //初始化 回退标志
        entity.setCrmRollbackFlg(SysConstant.InstallOrder.CONFIG_FLG_N);
        entity.setOrderNo(null);

        //获取车主信息
        OrderCarQuery orderCarQuery = new OrderCarQuery();
        orderCarQuery.setVin(entity.getVin());
        OrderCarEntity orderCarEntity = new OrderCarEntity();
//        if(SysConstant.InstallOrder.CONFIG_FLG_Y.equals(entity.getCrmTestFlg())){
//            orderCarEntity = orderCarService.getOrderCarInfo(orderCarQuery);
//        }else{
//            orderCarEntity = getCarOwnerInfo(entity.getToken(),entity.getVin(),entity.getBrand());
//        };
        if (SysConstant.InstallOrder.CONFIG_FLG_Y.equals(entity.getCrmTestFlg())) {
            orderCarEntity = orderCarService.getOrderCarInfo(orderCarQuery);
        } else {
            orderCarEntity.setCorpNo(entity.getCorpNo());
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

        // OrderCarEntity
        //车型名是否匹配
        VehicleEntity vehicleEntity = vehicleService.findByNameAndCorp(orderCarEntity.getCarVehicle(), entity.getCorpNo());
        if (vehicleEntity == null) {
            throw new AppException(CustomMessage.CAN_NOT_CREATE_ORDER);
        }
        //VIN中不能有记录
        VinEntity vinEntity = vinService.findByCode(orderCarEntity.getVinNo());
        if (vinEntity != null) {
            throw new AppException(CustomMessage.CAN_NOT_CREATE_ORDER);
        }
        //orderCar中不可存在该vin
        OrderCarEntity checkOrderCarEntity = orderCarService.findByVinNoAndCorp(orderCarEntity.getVinNo(), entity.getCorpNo());
        if (checkOrderCarEntity != null) {
//            throw new AppException(CustomMessage.DO_NOT_MEET_PILE_POLICY);
            //判断 vin对应的订单状态 如果为撤回操作 则 删除car 对应的数据
            DeliveryOrderEntity checkRurn = findByNo(checkOrderCarEntity.getOrderNo());
            if (checkRurn != null && SysConstant.InstallOrder.CONFIG_FLG_Y.equals(checkRurn.getCrmRollbackFlg())) {
                orderCarService.delete(checkOrderCarEntity.getId());
                entity.setId(checkRurn.getId());
                entity.setOrderNo(checkRurn.getOrderNo());
            } else {
                throw new AppException(CustomMessage.DO_NOT_MEET_PILE_POLICY);
            }
        }

        entity.setSupplierConfirmFlg(SysConstant.InstallOrder.RECEIVE_FLG_SIGN);
        entity.setOrderTime(new Date());
        entity.setVerifyFlg(SysConstant.InstallOrder.CONFIG_VERIFY_N);
        entity.setBeginCacle(SysConstant.InstallOrder.CONFIG_FLG_N);
        entity.setSettleFlg(SysConstant.InstallOrder.CONFIG_FLG_N);
        entity.setFinishFlg(SysConstant.InstallOrder.CONFIG_FLG_N);
        entity.setReceiveStatus(SysConstant.InstallOrder.CONFIG_VERIFY_N);

        OrderSeqQuery query = new OrderSeqQuery();
        query.setCorpNo(entity.getCorpNo());
        query.setOrderType(SysConstant.WorkFollow.CONFIG_WORKFOLLOW_DELIVERY);
        if(isEmpty(entity.getOrderNo())){
            String no = orderSeqService.getOrderNo(query);
            entity.setOrderNo(no);
        }
        configService.convert(entity);
        deliveryOrderRepository.saveAndFlush(entity);

        //初始化订单步骤
        OrderStepParameter orderStepParameter = new OrderStepParameter();
        orderStepParameter.setOrderNo(entity.getOrderNo());
        orderStepParameter.setFollowNo(SysConstant.WorkFollow.WORKFOLLOW_DELIVERY_NO);
        orderStepService.orderStepAdd(orderStepParameter);

        //车主信息新增
        orderCarEntity.setCorpNo(entity.getCorpNo());
        orderCarEntity.setOrderNo(entity.getOrderNo());
        orderCarEntity.setCarOwner(entity.getCarOwner());
        orderCarEntity.setCarOwnerPhone(entity.getCarOwnerPhone());
        orderCarService.add(orderCarEntity);

        //订单提报以后推送信息
        CorpEntity corpEntity = getCorpEntity();
        log.info("==========新增-DeliveryOrderService.add()=====订单提报以后推送信息=====2");
        scrmInfoService.submitPileOrder(null, orderCarEntity.getVinNo(), corpEntity.getCorpBrand(), SysConstant.Crm.CRM_SCHEDULE);
        return entity;

    }

    private OrderCarEntity getCarOwnerInfo(String token, String vin, Integer brand) {
        OrderCarEntity orderCarEntity = new OrderCarEntity();
        ScrmCarInfoEntity scrmCarInfo = scrmInfoService.carInfo(token, vin, brand);
        orderCarEntity.setCarOwnerPhone(scrmCarInfo.getContactPhone());
        orderCarEntity.setCarVehicle(scrmCarInfo.getCarType());
        orderCarEntity.setDealerName(scrmCarInfo.getSimpleName());
        orderCarEntity.setVinNo(scrmCarInfo.getVin());
        orderCarEntity.setEngineNo(scrmCarInfo.getEngine());
        orderCarEntity.setSaleDate(new Date(scrmCarInfo.getSaleDate()));
        return orderCarEntity;
    }


    /**
     * 更新
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional
    public DeliveryOrderEntity edit(DeliveryOrderEntity entity) {
        OrderStepParameter orderStepParameter = new OrderStepParameter();

        log.info("==========更新-DeliveryOrderService.edit()==========1");
        DeliveryOrderEntity oldEntity = findById(entity.getId());

        //充电桩编号不能重复
        // if (!isEmpty(entity.getPileCode())) {
        //     InstallOrderEntity installCheckEntity = installOrderService.findByPileCodeAndCorp(entity.getPileCode(), entity.getCorpNo());
        //     DeliveryOrderEntity deliveryCheckEntity = findByPileCodeAndCorp(entity.getPileCode(), entity.getCorpNo());
        //     if (installCheckEntity != null || (deliveryCheckEntity != null && !deliveryCheckEntity.getId().equals(entity.getId()))) {
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

        BeanCopyUtil.beanCopy(entity, oldEntity);
        //是否审核
        OrderStepEntity orderStepEntity;

        //超时开始结束时间
        WorkFollowDetailQuery workFollowDetailQuery = new WorkFollowDetailQuery();
        workFollowDetailQuery.setFollowNo(SysConstant.WorkFollow.WORKFOLLOW_DELIVERY_NO);
        workFollowDetailQuery.setSort("+followSeq");
        List<WorkFollowDetailEntity> workFollowDetailEntityList = workFollowDetailService.findAll(workFollowDetailQuery);

        OrderStepEntity currentOrderStep = orderStepService.findLastByOrderNo(oldEntity.getOrderNo(), SysConstant.WorkFollow.CONFIG_WORKFOLLOW_DELIVERY);
        switch (currentOrderStep.getFollowSeq()) {
            case SysConstant.Delivery.RECEIVE_SEQ:
                WorkFollowDetailEntity receiveWork = workFollowDetailEntityList.stream()
                        .filter(x -> SysConstant.InstallOrder.CONFIG_FLG_Y.equals(x.getTimeOutFlg()) && SysConstant.WorkFollow.WORKFOLLOW_TIMEOUT_TYPE05.equals(x.getTimeoutType()))
                        .findFirst().orElse(null);
                //签收
                setBaseAndEndTime(receiveWork, oldEntity, orderStepParameter, SysConstant.WorkFollow.WORKFOLLOW_TIMEOUT_TYPE05);
                break;
            case SysConstant.Delivery.DELIVERY_SEQ:
                WorkFollowDetailEntity delWrok = workFollowDetailEntityList.stream()
                        .filter(x -> SysConstant.InstallOrder.CONFIG_FLG_Y.equals(x.getTimeOutFlg()) && SysConstant.WorkFollow.WORKFOLLOW_TIMEOUT_TYPE06.equals(x.getTimeoutType()))
                        .findFirst().orElse(null);
                //联系客户
                setBaseAndEndTime(delWrok, oldEntity, orderStepParameter, SysConstant.WorkFollow.WORKFOLLOW_TIMEOUT_TYPE06);
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
            orderStepParameter.setRebuildOrderType(SysConstant.OrderStep.REBUILD_TYPE_DELIVERY);
            orderStepParameter.setFollowNo(SysConstant.WorkFollow.WORKFOLLOW_DELIVERY_NO);
            orderStepEntity = orderStepService.orderStepAdd(orderStepParameter);
        } else {
            //审核不通过
            orderStepParameter.setOrderNo(oldEntity.getOrderNo());
            orderStepParameter.setResultDesc(entity.getRemark());
            orderStepParameter.setFollowNo(SysConstant.WorkFollow.WORKFOLLOW_DELIVERY_NO);
            orderStepEntity = orderStepService.orderStepRejct(orderStepParameter);
//            // 20190916 baimin 签收不通过清空服务商
            if (!isEmpty(entity.getReceiveStatus()) && SysConstant.InstallOrder.CONFIG_VERIFY_REFUSE.equals(entity.getReceiveStatus())) {
                oldEntity.setSupplierNo(null);
                oldEntity.setSupplierName(null);
            }
            //如果待签收订单被撤回，派单时间、服务商、服务商签收时间设置为空
            if(SysConstant.WorkFollow.Recall_Y.equals(entity.getRemark())){
                oldEntity.setDispatchTime(null);
                oldEntity.setSupplierName(null);
                oldEntity.setReceiveTime(null);
            }

        }
        if (SysConstant.WorkFollow.CONFIRM_FLG_Y.equals(orderStepEntity.getConfirmStep())) {
            //如果服务商确认，生成费用表
            OrderFeeQuery orderFeeQuery = new OrderFeeQuery();
            orderFeeQuery.setOrderNo(oldEntity.getOrderNo());
            orderFeeQuery.setCorpNo(oldEntity.getCorpNo());
            orderFeeQuery.setOrderTime(oldEntity.getOrderTime());
            orderFeeQuery.setOrderType(SysConstant.WorkFollow.CONFIG_WORKFOLLOW_DELIVERY);
            orderFeeQuery.setPileModel(oldEntity.getPileModel());
            orderFeeQuery.setSupplierNo(oldEntity.getSupplierNo());
            orderFeeQuery.setSupplierName(oldEntity.getSupplierName());
            orderFeeService.addOrderFee(orderFeeQuery);
        }
        //判断是否为满意度
        log.info("==========更新-DeliveryOrderService.edit()====判断是否为满意度======2");
        if (entity.getOrderGradeEntity() != null) {
            OrderGradeEntity orderGradeEntity = entity.getOrderGradeEntity();
            orderGradeEntity.setOrderNo(oldEntity.getOrderNo());
            orderGradeEntity.setOrderType(SysConstant.WorkFollow.CONFIG_WORKFOLLOW_DELIVERY);
            orderGradeService.add(orderGradeEntity);
            log.info("==========更新-DeliveryOrderService.edit()=====判断是否为满意度==推送===3");
            OrderCarEntity orderCarEntity = orderCarService.findByNo(oldEntity.getOrderNo());
            CorpEntity corpEntity = getCorpEntity();
            scrmInfoService.submitPileOrder(null, orderCarEntity.getVinNo(), corpEntity.getCorpBrand(), SysConstant.Crm.CRM_SCHEDULE);
        }

        //如果是最后一步，结束标志置Y
        log.info("==========更新-DeliveryOrderService.edit()====如果是最后一步，结束标志置Y======4");
        if (SysConstant.OrderStep.LAST_STEP_Y.equals(orderStepEntity.getLastStep())) {
            oldEntity.setFinishFlg(SysConstant.InstallOrder.FINISH_FLG_Y);
            oldEntity.setFinishTime(new Date());


            OrderCarEntity orderCarEntity = orderCarService.findByNo(oldEntity.getOrderNo());
            CorpEntity corpEntity = getCorpEntity();
            log.info("==========更新-DeliveryOrderService.edit()====如果是最后一步，结束标志置Y===推送===5");
            scrmInfoService.submitPileOrder(null, orderCarEntity.getVinNo(), corpEntity.getCorpBrand(), SysConstant.Crm.CRM_FINISH);

        }

        //判断是否为经销商提交
        OrderGradeEntity orderGradeEntity = orderGradeService.findByOrderNo(oldEntity.getOrderNo());
        if (SysConstant.OrderStep.VERIFY_RESULT_PASS.equals(oldEntity.getSupplierConfirmFlg()) && orderGradeEntity == null) {

            //DeliveryOrderEntity delivery= findById(entity.getId());
            OrderCarEntity orderCarEntity = orderCarService.findByNo(oldEntity.getOrderNo());
            CorpEntity corpEntity = getCorpEntity();
            // todo 验证
            // orderGradeService.add(entity.getOrderGradeEntity());
            log.info("==========更新-DeliveryOrderService.edit()=====判断是否为服务商确认==推送===6");
            scrmInfoService.submitPileOrder(null, orderCarEntity.getVinNo(), corpEntity.getCorpBrand(), SysConstant.Crm.CRM_GRADE);
        }


        configService.convert(oldEntity);
        deliveryOrderRepository.saveAndFlush(oldEntity);
        return oldEntity;
    }

    private void setBaseAndEndTime(WorkFollowDetailEntity workFollowDetailEntity, DeliveryOrderEntity deliveryOrderEntity, OrderStepParameter orderStepParameter, String overType) {
        //判断接单超时
        if (workFollowDetailEntity != null && !isEmpty(deliveryOrderEntity)) {
            Date baseTime = new Date();
            Date endTime = new Date();
            switch (overType) {
                case SysConstant.WorkFollow.WORKFOLLOW_TIMEOUT_TYPE05:
                    baseTime = deliveryOrderEntity.getDispatchTime();
                    // if (!isEmpty(deliveryOrderEntity.getReceiveTime())) {
                    endTime = deliveryOrderEntity.getReceiveTime();
                    // }
                    break;
                case SysConstant.WorkFollow.WORKFOLLOW_TIMEOUT_TYPE06:
                    baseTime = deliveryOrderEntity.getReceiveTime();
                    // if (!isEmpty(deliveryOrderEntity.getContactCustTime())) {
                    endTime = deliveryOrderEntity.getContactCustTime();
                    // }
                    break;
            }
            orderStepParameter.setBaseTime(baseTime);
            orderStepParameter.setEndTime(endTime);
        }
    }

    /**
     * 订单工作流
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional
    public DeliveryOrderEntity orderWorkFollow(DeliveryOrderEntity entity) {
        //获取最新的订单步骤记录
        OrderStepEntity lastOrderStepEntity = orderStepService.findLastByOrderNo(entity.getOrderNo(), SysConstant.WorkFollow.CONFIG_WORKFOLLOW_DELIVERY);
        //校验
        verify(entity, lastOrderStepEntity);
        //获取此步骤工作流
        WorkFollowEntity workFollowEntity = workFollowService.findByCode(SysConstant.WorkFollow.CONFIG_WORKFOLLOW_DELIVERY);
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
            // 如果满意度已经存在，则不做满意度
            //满意度
            // if (workFollowDetailEntity != null && SysConstant.InstallOrder.CONFIG_FLG_Y.equals(workFollowDetailEntity.getGradeFlg())) {
            //     orderGradeService.add(entity.getOrderGradeEntity());
            // }
            //表中是否已有当前步骤
//            int newStep = workFollowDetailService.getNextStep(SysConstant.WorkFollow.WORKFOLLOW_DELIVERY_NO, lastOrderStepEntity.getFollowSeq()).getFollowSeq();
//            List<OrderStepEntity> checkOrderStepEntityList = orderStepService.findByOrderNoAndFollowSeq(entity.getOrderNo(), newStep);
//            if (checkOrderStepEntityList.size() > 0) {
//                //步骤已有
//                //判断是否需要重新生成
//                if (SysConstant.InstallOrder.CONFIG_FLG_Y.equals(workFollowDetailEntity.getCheckRebuildFlg())) {
//                    //初始化新订单步骤记录
//                    orderStep(newStep, entity, lastOrderStepEntity);
//                } else {
//                    //继续向下查询，直到找到需要生成的
//                    boolean flag = true;
//                    int seq = workFollowDetailService.getNextStep(SysConstant.WorkFollow.WORKFOLLOW_DELIVERY_NO, newStep).getFollowSeq();
//                    WorkFollowDetailEntity checkEntity = null;
//                    while (flag) {
//                        checkEntity = workFollowDetailService.findByFollowNoAndFollowSeq(workFollowEntity.getFollowNo(), seq);
//                        if (checkEntity == null) {
//                            throw new AppException(CustomMessage.NO_DATA_FIND);
//                        } else if (SysConstant.InstallOrder.CONFIG_FLG_Y.equals(checkEntity.getCheckRebuildFlg())) {
//                            flag = false;
//                        }
//                        seq = workFollowDetailService.getNextStep(SysConstant.WorkFollow.WORKFOLLOW_DELIVERY_NO, seq).getFollowSeq();
//                    }
//                    orderStep(checkEntity.getFollowSeq(), entity, lastOrderStepEntity);
//                }
//            } else {
//                int nextSeq = workFollowDetailService.getNextStep(workFollowEntity.getFollowNo(), workFollowDetailEntity.getFollowSeq()).getFollowSeq();
//            }
            int maxSeq = workFollowDetailService.getMaxStep(workFollowEntity.getFollowNo()).getFollowSeq();
            int nextSeq;
            if (maxSeq == workFollowDetailEntity.getFollowSeq()) {
                nextSeq = workFollowDetailEntity.getFollowSeq();
            } else {
                nextSeq = workFollowDetailService.getNextStep(workFollowEntity.getFollowNo(), workFollowDetailEntity.getFollowSeq()).getFollowSeq();
            }
            orderStep(nextSeq, entity, lastOrderStepEntity);
            //超时
            if (SysConstant.InstallOrder.CONFIG_FLG_Y.equals(workFollowDetailEntity.getTimeOutFlg())) {
                int minutes = orderStepService.judgeTimeoutHours(workFollowDetailEntity.getTimeOutRadio(), lastOrderStepEntity.getBaseTime(), new Date());
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
        return entity;
    }

    /**
     * 校验
     *
     * @param deliveryOrderEntity
     * @param lastOrderStepEntity
     */
    private void verify(DeliveryOrderEntity deliveryOrderEntity, OrderStepEntity lastOrderStepEntity) {
        //判断是否为最后一步，如果是，不可操作
        if (SysConstant.OrderStep.CURRENT_STEP_Y.equals(lastOrderStepEntity.getLastStep())) {
            DeliveryOrderEntity checkEntity = findById(deliveryOrderEntity.getId());
            if (SysConstant.InstallOrder.CONFIG_FLG_Y.equals(checkEntity.getFinishFlg())) {
                throw new AppException(CustomMessage.CAN_NOT_OPERATE);
            }
        }
        //判断是否开始作废
        if (isEmpty(deliveryOrderEntity.getBeginCacle()) && SysConstant.InstallOrder.CONFIG_FLG_Y.equals(deliveryOrderEntity.getBeginCacle())) {
            throw new AppException(CustomMessage.CAN_NOT_OPERATE);
        }
        //判断当前步骤是否一致
        if (isEmpty(deliveryOrderEntity.getCurrentStep())
                || !deliveryOrderEntity.getCurrentStep().equals(lastOrderStepEntity.getFollowSeq())) {
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
     * 保存
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional
    public DeliveryOrderEntity saveOrderAndStep(DeliveryOrderEntity entity) {
        // //改变当前orderstep
        // DeliveryOrderEntity checkEntity = checkById(entity.getId());
        // //获取最新的订单步骤记录
        // OrderStepEntity lastOrderStepEntity = orderStepService.findLastByOrderNo(entity.getOrderNo(), SysConstant.WorkFollow.CONFIG_WORKFOLLOW_DELIVERY);
        // if (!isEmpty(entity.getBaseTime())) {
        //     lastOrderStepEntity.setBaseTime(entity.getBaseTime());
        // } else {
        //     lastOrderStepEntity.setBaseTime(new Date());
        // }
        // CompareObj<DeliveryOrderEntity> compareObj = new CompareObj<>();
        // String compare = compareObj.compare(checkEntity, entity);
        // lastOrderStepEntity.setStepRemark(lastOrderStepEntity.getStepRemark() + compare);
        // orderStepService.edit(lastOrderStepEntity);
        // return edit(entity);
        //改变当前orderstep
        DeliveryOrderEntity checkEntity = checkById(entity.getId());
        //充电桩编号不能重复
        // if (!isEmpty(entity.getPileCode())) {
        //     InstallOrderEntity installCheckEntity = installOrderService.findByPileCodeAndCorp(entity.getPileCode(), entity.getCorpNo());
        //     DeliveryOrderEntity deliveryCheckEntity = findByPileCodeAndCorp(entity.getPileCode(), entity.getCorpNo());
        //     if (installCheckEntity != null || (deliveryCheckEntity != null && !deliveryCheckEntity.getId().equals(entity.getId()))) {
        //         throw new AppException(CustomMessage.PILE_CODE_CANNOT_RSD);
        //     }
        // }
        //桩型名称
        if (!isEmpty(entity.getPileModel())) {
            PileEntity pileEntity = pileService.findByNo(entity.getPileModel());
            if (pileEntity == null) {
                throw new AppException(CustomMessage.NO_DATA_FIND);
            }
            checkEntity.setPileModelName(pileEntity.getPileName());
        }
        //服务商名称
        if (!isEmpty(entity.getSupplierNo())) {
            SupplierEntity supplierEntity = supplierService.findByNo(entity.getSupplierNo());
            if (supplierEntity == null) {
                throw new AppException(CustomMessage.NO_DATA_FIND);
            }
            checkEntity.setSupplierName(supplierEntity.getSupplierName());
        }
        BeanCopyUtil.beanCopy(entity, checkEntity);
        CompareObj<DeliveryOrderEntity> compareObj = new CompareObj<>();
        String compare = compareObj.compare(checkEntity, entity);
        OrderStepParameter orderStepParameter = new OrderStepParameter();
        orderStepParameter.setOrderNo(checkEntity.getOrderNo());
        orderStepParameter.setFollowNo(SysConstant.WorkFollow.WORKFOLLOW_DELIVERY_NO);
        orderStepParameter.setRemark(compare);
        orderStepService.orderStepEdit(orderStepParameter);
        return deliveryOrderRepository.saveAndFlush(checkEntity);
    }

    protected String countSize(int size) {
        String num = "";
        if (size > 0 && size < 10) {
            num = "0000" + String.valueOf(size);
        } else if (size > 9 && size < 100) {
            num = "000" + String.valueOf(size);
        } else if (size > 99 && size < 1000) {
            num = "00" + String.valueOf(size);
        } else if (size > 999 && size < 10000) {
            num = "0" + String.valueOf(size);
        }
        return num;
    }

    //申请作废
    @Transactional
    @Override
    public DeliveryOrderEntity applyCancel(DeliveryOrderQuery query) {
        log.info("==========申请作废-DeliveryOrderService.applyCancel()====申请作废======1");
        //检查原来是否已作废
        DeliveryOrderEntity entity = findByNo(query.getOrderNo());
        if (SysConstant.InstallOrder.CONFIG_FLG_Y.equals(entity.getBeginCacle())) {
            throw new AppException(CustomMessage.CANCEL_CANNOT_AGAIN);
        }
        //将开始作废标志置为Y
        entity.setBeginCacle(SysConstant.IS_DEL_Y);
        deliveryOrderRepository.saveAndFlush(entity);
        OrderStepEntity delivery = orderStepService.findLastByOrderNo(query.getOrderNo(), SysConstant.WorkFollow.CONFIG_WORKFOLLOW_DELIVERY);
        //作废订单权限校验
        if (delivery.getFollowSeq() > 3) {
            throw new AppException(CustomMessage.CANCEL_NOT_FOUND);
        }
        //初始化订单步骤
        OrderStepParameter orderStepParameter = new OrderStepParameter();
        orderStepParameter.setOrderNo(entity.getOrderNo());
        //服务商没签收，直接复审
        if (entity.getReceiveStatus().equals(SysConstant.InstallOrder.CONFIG_VERIFY_N) || entity.getReceiveStatus().equals(SysConstant.InstallOrder.CONFIG_VERIFY_REFUSE)) {
            orderStepParameter.setFollowNo(SysConstant.WorkFollow.WORKFOLLOW_IN_INVALID_NO);
            orderStepService.orderStepAdd(orderStepParameter);
        } else if (entity.getReceiveStatus().equals(SysConstant.InstallOrder.RECEIVE_FLG_SIGN)) {
            //服务商签收，走正常流程
            orderStepParameter.setFollowNo(SysConstant.WorkFollow.WORKFOLLOW_INVALID_NO);
            orderStepService.orderStepAdd(orderStepParameter);
        } else {
            throw new AppException(CustomMessage.RECEIVE_STATUS);
        }

        //申请作废
        OrderCarEntity orderCarEntity = orderCarService.findByNo(entity.getOrderNo());
        CorpEntity corpEntity = getCorpEntity();
        log.info("==========申请作废-DeliveryOrderService.applyCancel()====申请作废===推送===2");
        scrmInfoService.submitPileOrder(null, orderCarEntity.getVinNo(), corpEntity.getCorpBrand(), SysConstant.Crm.CRM_CANCAL);

        return entity;
    }

    //作废
    @Transactional
    @Override
    public DeliveryOrderEntity cancel(DeliveryOrderQuery query) {
        log.info("==========申请作废-DeliveryOrderService.cancel()====作废======1");
        DeliveryOrderEntity entity = findByNo(query.getOrderNo());
        //获取安装最新的订单步骤记录
        OrderStepEntity orderStep = orderStepService.findLastByOrderNo(query.getOrderNo(), SysConstant.WorkFollow.CONFIG_WORKFOLLOW_INVALID);
        OrderStepEntity orderStepIn = orderStepService.findLastByOrderNo(query.getOrderNo(), SysConstant.WorkFollow.CONFIG_WORKFOLLOW_INVALID_IN);
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
            log.info("==========申请作废-DeliveryOrderService.cancel()====作废判断======2");
            if (SysConstant.OrderStep.LAST_STEP_Y.equals(orderStepEntity.getLastStep())) {

                OrderCarEntity orderCarEntity = orderCarService.findByNo(entity.getOrderNo());
                CorpEntity corpEntity = getCorpEntity();
                log.info("==========申请作废-DeliveryOrderService.cancel()====作废===推送===3");
                scrmInfoService.submitPileOrder(null, orderCarEntity.getVinNo(), corpEntity.getCorpBrand(), SysConstant.Crm.CRM_RETURN);
                //作废成功后 需要把vin删除 继续操作
                orderCarEntity.setVinNo(orderCarEntity.getVinNo() + "(已作废)");
                orderCarService.edit(orderCarEntity);
            }
            entity.setBeginCacle(SysConstant.IS_DEL_Y);
            deliveryOrderRepository.saveAndFlush(entity);
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
            deliveryOrderRepository.saveAndFlush(entity);
            //CRM推送 审核不通过
            OrderCarEntity orderCarEntity = orderCarService.findByNo(entity.getOrderNo());
            CorpEntity corpEntity = getCorpEntity();
            scrmInfoService.submitPileOrder(null, orderCarEntity.getVinNo(), corpEntity.getCorpBrand(), SysConstant.Crm.CRM_SCHEDULE);
        }
        //如果是最后一步，结束标志置Y
//        if (SysConstant.OrderStep.LAST_STEP_Y.equals(orderStepEntity.getLastStep())) {
//            oldEntity.setFinishFlg(SysConstant.InstallOrder.FINISH_FLG_Y);
//            oldEntity.setFinishTime(new Date());
//        }
        return entity;
    }


    /**
     * 处理订单步骤
     *
     * @param followStep
     * @param deliveryOrderEntity
     * @param lastOrderStepEntity
     * @return
     */
    private OrderStepEntity orderStep(int followStep, DeliveryOrderEntity deliveryOrderEntity, OrderStepEntity lastOrderStepEntity) {
        OrderStepEntity newOrderStepEntity;
        //获取用户
        UserEntity userEntity = userService.findById(BaseContextHandler.getUserId());
        if (userEntity != null) {
            lastOrderStepEntity.setStepUserNo(userEntity.getUserNo());
            lastOrderStepEntity.setStepUserName(userEntity.getUserName());
        }
        WorkFollowDetailEntity maxWorkFollowDetailEntity = workFollowDetailService.getMaxStep(SysConstant.WorkFollow.WORKFOLLOW_DELIVERY_NO);
        int stepNums = maxWorkFollowDetailEntity.getFollowSeq();
        if (stepNums != followStep || lastOrderStepEntity.getFollowSeq() != stepNums) {
            List<OrderStepEntity> checkOrderStepEntityList = orderStepService.findByOrderNoAndFollowSeq(deliveryOrderEntity.getOrderNo(), followStep);
            if (checkOrderStepEntityList.size() > 0) {
                //步骤已有
                //判断是否需要重新生成
                WorkFollowDetailEntity workFollowDetailEntity = workFollowDetailService.findByFollowNoAndFollowSeq(lastOrderStepEntity.getFollowNo(), followStep);
                if (SysConstant.InstallOrder.CONFIG_FLG_Y.equals(workFollowDetailEntity.getCheckRebuildFlg())) {
                    //初始化新订单步骤记录
                    newOrderStepEntity = orderStepService.workFollow(SysConstant.WorkFollow.CONFIG_WORKFOLLOW_DELIVERY
                            , followStep);
                } else {
                    //继续向下查询，直到找到需要生成的
                    boolean flag = true;
                    int seq = followStep;
                    WorkFollowDetailEntity checkEntity;
                    while (flag) {
                        seq = workFollowDetailService.getNextStep(SysConstant.WorkFollow.WORKFOLLOW_DELIVERY_NO, followStep).getFollowSeq();
                        checkEntity = workFollowDetailService.findByFollowNoAndFollowSeq(workFollowDetailEntity.getFollowNo(), seq);
                        if (checkEntity == null) {
                            throw new AppException(CustomMessage.NO_DATA_FIND);
                        } else if (SysConstant.InstallOrder.CONFIG_FLG_Y.equals(checkEntity.getCheckRebuildFlg())) {
                            flag = false;
                        }
                    }
                    //初始化新订单步骤记录
                    newOrderStepEntity = orderStepService.workFollow(SysConstant.WorkFollow.CONFIG_WORKFOLLOW_DELIVERY
                            , seq);
                }
            } else {
                //初始化新订单步骤记录
                newOrderStepEntity = orderStepService.workFollow(SysConstant.WorkFollow.CONFIG_WORKFOLLOW_DELIVERY
                        , followStep);
            }
            newOrderStepEntity.setOrderNo(deliveryOrderEntity.getOrderNo());
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
     * @param deliveryOrderEntity
     * @param lastOrderStepEntity
     * @return
     */
    private OrderStepEntity orderStepCancel(int followStep, DeliveryOrderEntity deliveryOrderEntity, OrderStepEntity lastOrderStepEntity) {
        OrderStepEntity newOrderStepEntity;
        //获取用户
        UserEntity userEntity = userService.findById(BaseContextHandler.getUserId());
        if (userEntity != null) {
            lastOrderStepEntity.setStepUserNo(userEntity.getUserNo());
            lastOrderStepEntity.setStepUserName(userEntity.getUserName());
        }
        WorkFollowDetailEntity maxWorkFollowDetailEntity = workFollowDetailService.getMaxStep(SysConstant.WorkFollow.WORKFOLLOW_INVALID_NO);
        int stepNums = maxWorkFollowDetailEntity.getFollowSeq();
        if (stepNums != followStep || lastOrderStepEntity.getFollowSeq() != stepNums) {
            List<OrderStepEntity> checkOrderStepEntityList = orderStepService.findByOrderNoAndFollowSeq(deliveryOrderEntity.getOrderNo(), followStep);
            if (checkOrderStepEntityList.size() > 0) {
                //步骤已有
                //判断是否需要重新生成
                WorkFollowDetailEntity workFollowDetailEntity = workFollowDetailService.findByFollowNoAndFollowSeq(lastOrderStepEntity.getFollowNo(), followStep);
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
                        seq = workFollowDetailService.getNextStep(SysConstant.WorkFollow.WORKFOLLOW_INVALID_NO, followStep).getFollowSeq();
                        checkEntity = workFollowDetailService.findByFollowNoAndFollowSeq(workFollowDetailEntity.getFollowNo(), seq);
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
            newOrderStepEntity.setOrderNo(deliveryOrderEntity.getOrderNo());
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
     * 根据服务商NO查询
     *
     * @param supplierNo
     * @return
     */
    @Override
    public List<DeliveryOrderEntity> findBySupplierNo(String supplierNo) {
        return deliveryOrderRepository.findBySupplierNo(SysConstant.IS_DEL_N, supplierNo);
    }

    /**
     * 删除（逻辑删除）
     *
     * @param id
     * @return
     */
    @Override
    public boolean delete(String id) {
        DeliveryOrderEntity entity = deliveryOrderRepository.findById(SysConstant.IS_DEL_N, id);
        if (entity == null) {
            throw new AppException(CustomMessage.NO_DATA_FIND);
        }
        //删除标记
        deliveryOrderRepository.delete(SysConstant.IS_DEL_Y, id);
        return true;
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
     * 根据id获取
     *
     * @param id
     * @return
     */
    @Override
    public DeliveryOrderEntity findById(String id) {
        DeliveryOrderEntity entity = deliveryOrderRepository.findById(SysConstant.IS_DEL_N, id);
        //获取车辆信息
        OrderCarEntity orderCarEntity = orderCarService.findByNo(entity.getOrderNo());
        entity.setOrderCarEntity(orderCarEntity);
        return entity;
    }

    /**
     * 根据id校验
     *
     * @param id
     * @return
     */
    @Override
    public DeliveryOrderEntity checkById(String id) {
        DeliveryOrderEntity entity = deliveryOrderRepository.findById(SysConstant.IS_DEL_N, id);
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
    public DeliveryOrderEntity findByNo(String no) {
        DeliveryOrderEntity entity = deliveryOrderRepository.findByNo(SysConstant.IS_DEL_N, no);

        return entity;
    }

    /**
     * crm撤回
     *
     * @param no
     * @return
     */
    @Override
    public DeliveryOrderEntity deliveryReturn(String no, String remark) {
        DeliveryOrderEntity deliveryOrderEntity = findByNo(no);

        if (!isEmpty(deliveryOrderEntity.getSupplierNo())) {
            throw new AppException(CustomMessage.CANCEL_NOT_FOUND);
        }

        deliveryOrderEntity.setCrmRollbackFlg(SysConstant.InstallOrder.CONFIG_FLG_Y);
        deliveryOrderEntity.setCrmRollbackRemark(remark);
        deliveryOrderRepository.saveAndFlush(deliveryOrderEntity);

        OrderStepEntity orderStepEntity = orderStepService.findLastByOrderNo(deliveryOrderEntity.getOrderNo(), SysConstant.WorkFollow.CONFIG_WORKFOLLOW_DELIVERY);
        orderStepEntity.setCurrentStepFlg(SysConstant.InstallOrder.CONFIG_FLG_N);
        orderStepEntity.setStepName("订单撤回");
        orderStepEntity.setStepRemark(remark);
        String userId = BaseContextHandler.getUserId();
        UserEntity userEntity = userService.findById(userId);
        orderStepEntity.setStepUserNo(userEntity.getUserNo());
        orderStepEntity.setStepUserName(userEntity.getUserName());
        orderStepService.edit(orderStepEntity);

        OrderCarEntity orderCarEntity = orderCarService.findByNo(no);
        CorpEntity corpEntity = getCorpEntity();
        scrmInfoService.submitPileOrder(null, orderCarEntity.getVinNo(), corpEntity.getCorpBrand(), SysConstant.Crm.CRM_RETURN);
        //20191120 撤回后vin变更，否则不能重新提单另一类型
        orderCarEntity.setVinNo(orderCarEntity.getVinNo() + "(已撤回)");
        orderCarService.edit(orderCarEntity);
        return deliveryOrderEntity;
    }

    /**
     * 根据桩编码查找
     *
     * @param pileCode
     * @param corpNo
     * @return
     */
    @Override
    public DeliveryOrderEntity findByPileCodeAndCorp(String pileCode, String corpNo) {
        return deliveryOrderRepository.findByPileCodeAndCorp(SysConstant.IS_DEL_N, pileCode, corpNo);
    }

    /**
     * 根据车架号查找
     *
     * @param vin
     * @param corpNo
     * @return
     */
    @Override
    public DeliveryOrderEntity findByVin(String vin, String corpNo) {
        DeliveryOrderEntity result = null;
        OrderCarEntity orderCarEntity = orderCarService.findByVinNoAndCorp(vin, corpNo);
        if (orderCarEntity != null) {
            DeliveryOrderEntity deliveryOrderEntity = findByNo(orderCarEntity.getOrderNo());
            if (deliveryOrderEntity != null) {
                //20190903 增加查询二次安装
                String secondInstall = "";
                VehicleDetailEntity vehicleDetailEntity =
                        vehicleDetailService.findByDetailNameAndCorp(orderCarEntity.getVehicleDetail(),
                                orderCarEntity.getCorpNo());
                if (vehicleDetailEntity != null) {
                    secondInstall = vehicleDetailEntity.getSecondInstall();
                }
                deliveryOrderEntity.setSecondInstall(secondInstall);
                result = deliveryOrderEntity;
            }
        }

        return result;
    }

    /**
     * 结算审核未通过，订单状态变为待服务商确认
     *
     * @return
     */
    @Override
    public DeliveryOrderEntity settleVerifyFail(String orderNo, String remark) {
        DeliveryOrderEntity deliveryOrderEntity = findByNo(orderNo);
        deliveryOrderEntity.setSettleFlg(SysConstant.InstallOrder.SETTLE_FLG_N);
        deliveryOrderEntity.setFinishFlg(SysConstant.InstallOrder.FINISH_FLG_N);
        deliveryOrderEntity.setSettleRemark(remark);
        deliveryOrderEntity.setSettleVerifyFlg(SysConstant.IS_DEL_Y);

        // deliveryOrderRepository.saveAndFlush(deliveryOrderEntity);
        save(deliveryOrderEntity);

        //生成待确认步骤
        OrderStepParameter orderStepParameter = new OrderStepParameter();
        orderStepParameter.setOrderNo(deliveryOrderEntity.getOrderNo());
        orderStepParameter.setRemark("结算审核未通过");
        orderStepParameter.setBaseTime(new Date());
        // orderStepParameter.setEndTime(entity.getEndTime());
        // if (entity.getEndTime() != null) {
        //     orderStepParameter.setEndTime(entity.getEndTime());
        // }
        orderStepParameter.setFollowNo(SysConstant.WorkFollow.WORKFOLLOW_DELIVERY_NO);
        OrderStepEntity orderStepEntity = orderStepService.orderStepSettleFail(orderStepParameter);
        return deliveryOrderEntity;
    }

    @Override
    public DeliveryOrderEntity save(DeliveryOrderEntity entity) {
        DeliveryOrderEntity checkEntity = checkById(entity.getId());
        //桩型名称
        if (!isEmpty(entity.getPileModel())) {
            PileEntity pileEntity = pileService.findByNo(entity.getPileModel());
            if (pileEntity == null) {
                throw new AppException(CustomMessage.NO_DATA_FIND);
            }
            checkEntity.setPileModelName(pileEntity.getPileName());
        }
        //服务商名称
        if (!isEmpty(entity.getSupplierNo())) {
            SupplierEntity supplierEntity = supplierService.findByNo(entity.getSupplierNo());
            if (supplierEntity == null) {
                throw new AppException(CustomMessage.NO_DATA_FIND);
            }
            checkEntity.setSupplierName(supplierEntity.getSupplierName());
        }
        configService.convert(entity);
        return deliveryOrderRepository.saveAndFlush(entity);
    }

    /**
     * 根据公司查找撤回订单
     *
     * @param crmRollbackFlg
     * @param vinNo
     * @param corpNo
     * @return
     */
    @Override
    public List<DeliveryOrderEntity> findRollBackOrder(String crmRollbackFlg, String vinNo, String corpNo) {
        return deliveryOrderRepository.findRollBackOrderByVinAndCorpNo(SysConstant.IS_DEL_N, crmRollbackFlg, vinNo, corpNo);
    }


}

