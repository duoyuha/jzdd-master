package cn.backend.service.settle;

import cn.backend.config.constant.SysConstant;
import cn.backend.config.message.CustomMessage;
import cn.backend.dao.primary.settle.SettleRepository;
import cn.backend.model.primary.delivery.DeliveryOrderEntity;
import cn.backend.model.primary.delivery.DeliveryOrderQuery;
import cn.backend.model.primary.installorder.InstallOrderEntity;
import cn.backend.model.primary.installorder.InstallOrderQuery;
import cn.backend.model.primary.orderfee.OrderFeeEntity;
import cn.backend.model.primary.orderfee.OrderFeeQuery;
import cn.backend.model.primary.orderstep.OrderStepEntity;
import cn.backend.model.primary.orderstep.OrderStepParameter;
import cn.backend.model.primary.settle.SettleEntity;
import cn.backend.model.primary.settle.SettleQuery;
import cn.backend.model.primary.settledetail.SettleDetailEntity;
import cn.backend.model.primary.viewdeliveryorder.ViewDeliveryOrderEntity;
import cn.backend.model.primary.viewinstallorder.ViewInstallOrderEntity;
import cn.backend.model.primary.viewsettle.SettleViewEntity;
import cn.backend.model.primary.workfollowdetail.WorkFollowDetailEntity;
import cn.backend.service.BaseService;
import cn.backend.service.config.IConfigService;
import cn.backend.service.corp.ICorpService;
import cn.backend.service.delivery.DeliveryOrderService;
import cn.backend.service.installorder.IInstallOrderService;
import cn.backend.service.installorderview.IInstallOrderViewService;
import cn.backend.service.ordercar.IOrderCarService;
import cn.backend.service.orderfee.IOrderFeeService;
import cn.backend.service.orderstep.IOrderStepService;
import cn.backend.service.settledetail.ISettleDetailService;
import cn.backend.service.viewdeliveryorder.IViewDeliveryOrderService;
import cn.backend.service.viewsettle.ISettleViewService;
import cn.backend.service.workfollowdetail.IWorkFollowDetailService;
import cn.zdwl.common.exception.AppException;
import cn.zdwl.common.util.IdUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author James
 * @date 2019/07/29
 */
@Slf4j
@Service(value = "settleService")
public class SettleService extends BaseService implements ISettleService {

    @Resource
    private SettleRepository settleRepository;

    @Autowired
    private ISettleDetailService settleDetailService;


    @Autowired
    private IWorkFollowDetailService workFollowDetailService;


    @Autowired
    private IInstallOrderService installOrderService;

    @Autowired
    private IInstallOrderViewService installOrderViewService;

    @Autowired
    private ICorpService corpService;

    @Autowired
    private IConfigService configService;


    @Autowired
    private IOrderFeeService orderFeeService;


    @Autowired
    private IOrderStepService orderStepService;

    @Autowired
    private ISettleViewService settleViewService;

    @Autowired
    private IOrderCarService orderCarService;

    @Autowired
    private DeliveryOrderService deliveryOrderService;

    @Autowired
    private IViewDeliveryOrderService viewDeliveryOrderService;


    /**
     * 分页查询
     *
     * @param query 查询条件
     * @return 查询结果
     */
    @Override
    public Page<SettleEntity> findList(SettleQuery query) {
        Sort sort = buildSort(query.getSort());
        Pageable pageable = new PageRequest(query.getPageNum(), query.getPageSize(), sort);
        return settleRepository.findAll(createSpecification(query), pageable);
    }

    /**
     * 列表查询
     *
     * @param query 查询条件
     * @return 查询结果
     */
    @Override
    public List<SettleEntity> findAll(SettleQuery query) {
        Sort sort = buildSort(query.getSort());
        return settleRepository.findAll(createSpecification(query), sort);
    }

    /**
     * 根据结算单Id获取
     *
     * @param id 结算单Id
     * @return 结算单
     */
    @Override
    public SettleEntity findById(String id) {
        SettleViewEntity settleViewEntity = settleViewService.findById(id);
        SettleEntity settleEntity = settleRepository.findById(SysConstant.IS_DEL_N, id);
        settleEntity.setStepName(settleViewEntity.getStepName());
        if (settleEntity != null) {

            List<SettleDetailEntity> settleDetailEntities = settleDetailService.findByNo(settleEntity.getSettleNo());
            settleEntity.setSettleDetailEntityList(settleDetailEntities);
        }
        return settleEntity;
    }

    /**
     * 根据结算单Id校验
     *
     * @param id 结算单Id
     * @return 结算单Id
     */
    @Override
    public SettleEntity checkById(String id) {
        return Optional.ofNullable(findById(id))
                .orElseThrow(() -> new AppException(CustomMessage.SETTLE_NOT_FOUND));
    }

    /**
     * 根据结算单编号查找
     *
     * @param settleNo 结算单编号
     * @return 结算单
     */
    @Override
    public SettleEntity findByNo(String settleNo) {
        return settleRepository.findByNo(SysConstant.IS_DEL_N, settleNo);
    }

    /**
     * 安装结算单导出
     *
     * @param query 查询条件
     * @return 查询结果
     */
    @Override
    public List<ViewInstallOrderEntity> export(SettleQuery query) {
        List<ViewInstallOrderEntity> installOrderEntities = new ArrayList<>();
        //List<SettleEntity> settleEntities = findAll(query);
//        for (SettleEntity settleEntity : settleEntities) {
//            SettleDetailQuery detailQuery = new SettleDetailQuery();
//            detailQuery.setSettleNo(settleEntity.getSettleNo());
//            detailQuery.setCorpNo(settleEntity.getCorpNo());
//            List<SettleDetailEntity> settleDetailEntities = settleDetailService.findAll(detailQuery);
//            for (SettleDetailEntity detailEntity : settleDetailEntities) {
//                ViewInstallOrderEntity installOrderEntity = installOrderViewService.findByOrderNo(detailEntity.getOrderNo(), detailEntity.getCorpNo());
//                if (installOrderEntity == null) {
//                    continue;
//                }
//                String corpName = Optional.ofNullable(corpService.findByNo(installOrderEntity.getCorpNo()))
//                        .map(CorpEntity::getCorpName)
//                        .orElseGet(() -> "");
//                installOrderEntity.setCorpNo(corpName);
//                installOrderEntities.add(installOrderEntity);
//            }
//        }
        if (SysConstant.Settle.EXPORT_IN.equals(query.getExportType())) {
            SettleViewEntity viewEntity=settleViewService.findBySettleNo(query.getSettleNo());
            String[] nos = query.getOrderNos().split(",");
            if (nos.length > 0) {
                for (String no : nos) {
                    ViewInstallOrderEntity entity = installOrderViewService.findByOrderNo(no, query.getCorpNo());
                    if (viewEntity!=null){
                        entity.setStepName(viewEntity.getStepName());
                        entity.setInvoiceStutas(viewEntity.getInvoiceStatusName());
                        entity.setFinishFlg(viewEntity.getFinishFlg());
                    }
                    installOrderEntities.add(entity);
                }
            }
        } else if (SysConstant.Settle.EXPORT_OUT.equals(query.getExportType())) {
            List<SettleViewEntity> list = settleViewService.findAll(query);
            for (SettleViewEntity settleEntity : list) {
                if (settleDetailService.findByNo(settleEntity.getSettleNo()).size() > 0) {
                    List<SettleDetailEntity> detailEntityList = settleDetailService.findByNo(settleEntity.getSettleNo());
                    for (SettleDetailEntity detailEntity : detailEntityList) {
                        ViewInstallOrderEntity entity = installOrderViewService.findByOrderNo(detailEntity.getOrderNo(), query.getCorpNo());
                        SettleViewEntity viewEntity=settleViewService.findBySettleNo(settleEntity.getSettleNo());
                        if (viewEntity!=null){
                            entity.setStepName(viewEntity.getStepName());
                            entity.setInvoiceStutas(viewEntity.getInvoiceStatusName());

                        }
                        if (entity.getFinishFlg().equals(SysConstant.IS_DEL_Y)&&SysConstant.Settle.EXPORT_OUT.equals(viewEntity.getSettleStatus())){
                            entity.setFinishFlg(viewEntity.getFinishFlg());
                            installOrderEntities.add(entity);
                        }
                    }
                }
            }
        }

        //OrderCarEntity carEntity=orderCarService.findByNo(query.getOrderNo());
        return installOrderEntities;
    }

    /**
     * 配送结算单导出
     *
     * @param query 查询条件
     * @return 查询结果
     */
    @Override
    public List<ViewDeliveryOrderEntity> delExport(SettleQuery query) {
        List<ViewDeliveryOrderEntity> deliveryOrderEntities = new ArrayList<>();
        //List<SettleEntity> settleEntities = findAll(query);
//        for (SettleEntity settleEntity : settleEntities) {
//            SettleDetailQuery detailQuery = new SettleDetailQuery();
//            detailQuery.setSettleNo(settleEntity.getSettleNo());
//            detailQuery.setCorpNo(settleEntity.getCorpNo());
//            List<SettleDetailEntity> settleDetailEntities = settleDetailService.findAll(detailQuery);
//            for (SettleDetailEntity detailEntity : settleDetailEntities) {
//                ViewInstallOrderEntity installOrderEntity = installOrderViewService.findByOrderNo(detailEntity.getOrderNo(), detailEntity.getCorpNo());
//                if (installOrderEntity == null) {
//                    continue;
//                }
//                String corpName = Optional.ofNullable(corpService.findByNo(installOrderEntity.getCorpNo()))
//                        .map(CorpEntity::getCorpName)
//                        .orElseGet(() -> "");
//                installOrderEntity.setCorpNo(corpName);
//                installOrderEntities.add(installOrderEntity);
//            }
//        }
        if (SysConstant.Settle.EXPORT_IN.equals(query.getExportType())) {
            SettleViewEntity viewEntity=settleViewService.findBySettleNo(query.getSettleNo());
            String[] nos = query.getOrderNos().split(",");
            for (String no : nos) {
                ViewDeliveryOrderEntity entity = viewDeliveryOrderService.findByNo(no);
                if (viewEntity!=null){
                    entity.setStepName(viewEntity.getStepName());
                    entity.setInvoiceStutas(viewEntity.getInvoiceStatusName());
                    entity.setFinishFlg(viewEntity.getFinishFlg());
                }
                deliveryOrderEntities.add(entity);
            }
        } else if (SysConstant.Settle.EXPORT_OUT.equals(query.getExportType())) {
            List<SettleViewEntity> list = settleViewService.findAll(query);
            for (SettleViewEntity settleEntity : list) {
                if (settleDetailService.findByNo(settleEntity.getSettleNo()).size() > 0) {
                    List<SettleDetailEntity> detailEntityList = settleDetailService.findByNo(settleEntity.getSettleNo());
                    for (SettleDetailEntity detailEntity : detailEntityList) {
                        ViewDeliveryOrderEntity entity = viewDeliveryOrderService.findByNo(detailEntity.getOrderNo());
                        SettleViewEntity viewEntity=settleViewService.findBySettleNo(settleEntity.getSettleNo());
                        if (viewEntity!=null){
                            entity.setStepName(viewEntity.getStepName());
                            entity.setInvoiceStutas(viewEntity.getInvoiceStatusName());

                        }
                        if (entity.getFinishFlg().equals(SysConstant.IS_DEL_Y)&&SysConstant.Settle.EXPORT_OUT.equals(viewEntity.getSettleStatus())){
                            entity.setFinishFlg(viewEntity.getFinishFlg());
                            deliveryOrderEntities.add(entity);
                        }
                    }
                }
            }
        }
        //OrderCarEntity carEntity=orderCarService.findByNo(query.getOrderNo());
        return deliveryOrderEntities;
    }

    /**
     * 新增
     *
     * @param entity 结算单
     * @return 结算单
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public SettleEntity add(SettleEntity entity) {

        if (StringUtils.isEmpty(entity.getInstallOrderNos()) && StringUtils.isEmpty(entity.getDeliveryOrderNos())) {

            throw new AppException(CustomMessage.SETTLE_NOT_FOUND);

        }

        WorkFollowDetailEntity createWorkFollow = workFollowDetailService.getMinStep(SysConstant.WorkFollow.WORKFOLLOW_SETTLE_NO);

        // 校验是否有提交结算的权限


        //安装结算
        if (entity.getOrderType().equals(SysConstant.WorkFollow.CONFIG_WORKFOLLOW_INSTALL)) {
            //获取经销商编码

            String[] installOrderNoArray = entity.getInstallOrderNos().split(",");

            if (installOrderNoArray.length == 0) {

                throw new AppException(CustomMessage.SETTLE_NOT_FOUND);
            }

            //安装订单是否存在
            InstallOrderQuery installOrderQuery = new InstallOrderQuery();
            installOrderQuery.setOrderNos(entity.getInstallOrderNos());
            List<InstallOrderEntity> installOrderEntities = installOrderService.findAll(installOrderQuery);

            if (installOrderEntities == null || installOrderEntities.size() == 0) {

                throw new AppException(CustomMessage.SETTLE_NOT_FOUND);
            }

            InstallOrderEntity installOrderEntity = installOrderEntities.stream().findFirst().orElse(null);
            entity.setSettleNo(SysConstant.Settle.PREFIX_NUMBER + IdUtils.getGenerateNumber());
            entity.setSupplierNo(installOrderEntity.getSupplierNo());
            entity.setSupplierName(installOrderEntity.getSupplierName());
            entity.setSettleStatus(SysConstant.Settle.SETTLE_STATUS_EFFECTIVE);
            configService.convert(entity);
            BigDecimal ttlAmt = BigDecimal.ZERO;
            for (InstallOrderEntity install : installOrderEntities) {
                //SettleEntity settleEntity= new SettleEntity();
                OrderFeeQuery orderFeeQuery = new OrderFeeQuery();
                orderFeeQuery.setOrderNo(install.getOrderNo());
                List<OrderFeeEntity> feeEntities = orderFeeService.findAll(orderFeeQuery);


                for (OrderFeeEntity orderFeeEntity : feeEntities) {

                    //保存结算明细
                    SettleDetailEntity settleDetailEntity = new SettleDetailEntity();
                    settleDetailEntity.setCorpNo(orderFeeEntity.getCorpNo());
                    settleDetailEntity.setOrderNo(orderFeeEntity.getOrderNo());
                    settleDetailEntity.setSettleAmt(orderFeeEntity.getSettleAmt());
                    settleDetailEntity.setSupplierName(entity.getSupplierName());
                    settleDetailEntity.setSupplierNo(entity.getSupplierNo());
                    settleDetailEntity.setSettleNo(entity.getSettleNo());
                    settleDetailEntity.setOrderNo(orderFeeEntity.getOrderNo());
                    ttlAmt = ttlAmt.add(settleDetailEntity.getSettleAmt());

                    settleDetailService.add(settleDetailEntity);

                    install.setSettleFlg(SysConstant.InstallOrder.SETTLE_FLG_Y);

                    installOrderService.edit(install);


                }

            }
            entity.setTtlAmt(ttlAmt);
            entity.setCorpNo(installOrderEntity.getCorpNo());
            entity.setOrderType(SysConstant.WorkFollow.CONFIG_WORKFOLLOW_INSTALL);
            //entity.setOrderNo(entity.getInstallOrderNos());
            settleRepository.save(entity);

            OrderStepParameter orderStepParameter = new OrderStepParameter();
            orderStepParameter.setOrderNo(entity.getSettleNo());
            orderStepParameter.setFollowNo(SysConstant.WorkFollow.WORKFOLLOW_SETTLE_NO);
            orderStepService.orderStepAdd(orderStepParameter);


        }
        //配送结算
        else if (entity.getOrderType().equals(SysConstant.WorkFollow.CONFIG_WORKFOLLOW_DELIVERY)) {
            //获取经销商编码

            String[] deliveryOrderNoArray = entity.getDeliveryOrderNos().split(",");

            if (deliveryOrderNoArray.length == 0) {

                throw new AppException(CustomMessage.SETTLE_NOT_FOUND);
            }

            //配送订单是否存在
            DeliveryOrderQuery deliveryOrderQuery = new DeliveryOrderQuery();
            if (isEmpty(entity.getDeliveryOrderNos())) {
                throw new AppException(CustomMessage.SETTLE_NOT_FOUND);
            }
            deliveryOrderQuery.setOrderNos(entity.getDeliveryOrderNos());
            List<DeliveryOrderEntity> deliveryOrderEntities = deliveryOrderService.findAll(deliveryOrderQuery);

            if (deliveryOrderEntities == null || deliveryOrderEntities.size() == 0) {

                throw new AppException(CustomMessage.SETTLE_NOT_FOUND);
            }

            DeliveryOrderEntity deliveryOrderEntity = deliveryOrderEntities.stream().findFirst().orElse(null);
            entity.setSettleNo(SysConstant.Settle.PREFIX_NUMBER + IdUtils.getGenerateNumber());
            entity.setSupplierNo(deliveryOrderEntity.getSupplierNo());
            entity.setSupplierName(deliveryOrderEntity.getSupplierName());
            entity.setSettleStatus(SysConstant.Settle.SETTLE_STATUS_EFFECTIVE);
            configService.convert(entity);
            BigDecimal ttlAmt = BigDecimal.ZERO;
            for (DeliveryOrderEntity delivery : deliveryOrderEntities) {
                //SettleEntity settleEntity = new SettleEntity();
                OrderFeeQuery orderFeeQuery = new OrderFeeQuery();
                orderFeeQuery.setOrderNo(delivery.getOrderNo());
                List<OrderFeeEntity> feeEntities = orderFeeService.findAll(orderFeeQuery);
                for (OrderFeeEntity orderFeeEntity : feeEntities) {

                    //保存结算明细
                    SettleDetailEntity settleDetailEntity = new SettleDetailEntity();
                    settleDetailEntity.setCorpNo(orderFeeEntity.getCorpNo());
                    settleDetailEntity.setOrderNo(orderFeeEntity.getOrderNo());
                    settleDetailEntity.setSettleAmt(orderFeeEntity.getSettleAmt());
                    settleDetailEntity.setSupplierName(entity.getSupplierName());
                    settleDetailEntity.setSupplierNo(entity.getSupplierNo());
                    settleDetailEntity.setSettleNo(entity.getSettleNo());
                    settleDetailEntity.setOrderNo(orderFeeEntity.getOrderNo());
                    ttlAmt = ttlAmt.add(settleDetailEntity.getSettleAmt());

                    settleDetailService.add(settleDetailEntity);
                    //更新安装订单的结算状态

                    delivery.setSettleFlg(SysConstant.InstallOrder.SETTLE_FLG_Y);
                    deliveryOrderService.edit(delivery);


                }

            }
            entity.setTtlAmt(ttlAmt);
            entity.setCorpNo(deliveryOrderEntity.getCorpNo());
            entity.setOrderType(SysConstant.WorkFollow.CONFIG_WORKFOLLOW_DELIVERY);
            //entity.setOrderNo(entity.getDeliveryOrderNos());
            settleRepository.save(entity);

            OrderStepParameter orderStepParameter = new OrderStepParameter();
            orderStepParameter.setOrderNo(entity.getSettleNo());
            orderStepParameter.setFollowNo(SysConstant.WorkFollow.WORKFOLLOW_SETTLE_NO);
            orderStepService.orderStepAdd(orderStepParameter);


        }


        return entity;
    }

    @Override
    public SettleEntity submit(SettleEntity entity) {
        entity.setInvoiceStatus(SysConstant.Settle.TRIAL_PASS);
        configService.convert(entity);
        settleRepository.saveAndFlush(entity);
        return entity;
    }


    /**
     * 编辑
     *
     * @param entity 结算单
     * @return 结算单
     */
    @Override
    public SettleEntity edit(SettleEntity entity) {


        SettleEntity oldEntity = findById(entity.getId());
        verifyOrder(oldEntity);

        if (SysConstant.OrderStep.VERIFY_RESULT_PASS.equals(entity.getVerifyStatus())) {

            // settleRepository.saveAndFlush(entity);
            OrderStepParameter orderStepParameter = new OrderStepParameter();
            orderStepParameter.setOrderNo(oldEntity.getSettleNo());
            orderStepParameter.setFollowNo(SysConstant.WorkFollow.WORKFOLLOW_SETTLE_NO);
            orderStepParameter.setResultDesc(entity.getVerifyRemark());
            // orderStepParameter.setBaseTime();
            // orderStepParameter.setEndTime();
            OrderStepEntity orderStepEntity = orderStepService.orderStepAdd(orderStepParameter);
            if (SysConstant.OrderStep.LAST_STEP_Y.equals(orderStepEntity.getLastStep())) {

                oldEntity.setFinishFlg(SysConstant.InstallOrder.FINISH_FLG_Y);
                oldEntity.setFinishTime(new Date());
                if (entity.getType().equals(SysConstant.Settle.TRIAL_PASS)) {
                    oldEntity.setFirstVerifyFlg(entity.getFirstVerifyFlg());
                    oldEntity.setFirstVerifyRemark(entity.getVerifyRemark());
                } else if (entity.getType().equals(SysConstant.Settle.TRIAL_REFUSE)) {
                    oldEntity.setSecondVerifyFlg(entity.getFirstVerifyFlg());
                    oldEntity.setSecondVerifyRemark(entity.getVerifyRemark());
                }

                settleRepository.save(oldEntity);
            }


        } else {
            OrderStepParameter orderStepParameter = new OrderStepParameter();
            orderStepParameter.setOrderNo(oldEntity.getSettleNo());
            orderStepParameter.setFollowNo(SysConstant.WorkFollow.WORKFOLLOW_SETTLE_NO);
            orderStepParameter.setResultDesc(entity.getVerifyRemark());
            orderStepService.orderStepRejct(orderStepParameter);


            if (entity.getType().equals(SysConstant.Settle.TRIAL_PASS)) {
                oldEntity.setFirstVerifyFlg(entity.getFirstVerifyFlg());
                oldEntity.setFirstVerifyRemark(entity.getVerifyRemark());
            } else if (entity.getType().equals(SysConstant.Settle.TRIAL_REFUSE)) {
                oldEntity.setSecondVerifyFlg(entity.getFirstVerifyFlg());
                oldEntity.setSecondVerifyRemark(entity.getVerifyRemark());
            }
            oldEntity.setSettleStatus(SysConstant.Settle.SETTLE_STATUS_INVALID);


            configService.convert(oldEntity);

            settleRepository.save(oldEntity);


            List<SettleDetailEntity> detailEntityList = settleDetailService.findByNo(oldEntity.getSettleNo());

            //20190909 baimin 如果结算审核拒绝，原订单状态从已完成变为待服务商确认
            for (SettleDetailEntity settleDetailEntity : detailEntityList) {

                if (oldEntity.getOrderType().equals(SysConstant.WorkFollow.CONFIG_WORKFOLLOW_INSTALL)) {
                    // InstallOrderEntity installOrderEntity= installOrderService.findByNo(settleDetailEntity.getOrderNo());
                    // installOrderEntity.setSettleFlg(SysConstant.InstallOrder.SETTLE_FLG_N);
                    installOrderService.settleVerifyFail(settleDetailEntity.getOrderNo(), entity.getVerifyRemark());
                } else if (oldEntity.getOrderType().equals(SysConstant.WorkFollow.CONFIG_WORKFOLLOW_DELIVERY)) {
                    // DeliveryOrderEntity deliveryOrderEntity=deliveryOrderService.findByNo(settleDetailEntity.getOrderNo());
                    // deliveryOrderEntity.setSettleFlg(SysConstant.InstallOrder.SETTLE_FLG_N);
                    deliveryOrderService.settleVerifyFail(settleDetailEntity.getOrderNo(), entity.getVerifyRemark());
                }

            }


        }

        return oldEntity;
    }

    /**
     * 校验
     *
     * @param checkEntity
     */
    private void verifyOrder(SettleEntity checkEntity) {
        //判断是否完成
        if (SysConstant.InstallOrder.CONFIG_FLG_Y.equals(checkEntity.getFinishFlg())) {
            throw new AppException(CustomMessage.CAN_NOT_OPERATE);
        }
    }

    /***
     * 回退释放结算
     * @param settleQuery
     * @return
     */
    @Override
    @Transactional
    public boolean back(SettleQuery settleQuery) {
         SettleDetailEntity detailEntity = settleDetailService.findById(settleQuery.getId());
         String settleNo=detailEntity.getSettleNo();
         BigDecimal deleteAmt = detailEntity.getSettleAmt();
        settleDetailService.delete(settleQuery.getId());
        if (SysConstant.WorkFollow.CONFIG_WORKFOLLOW_INSTALL.equals(settleQuery.getOrderType())) {
            if (installOrderService.findByNo(settleQuery.getOrderNo()) != null) {
                // InstallOrderEntity entity = installOrderService.findByNo(settleQuery.getOrderNo());
                // entity.setSettleFlg(SysConstant.IS_DEL_N);
                // entity.setSettleVerifyFlg(SysConstant.IS_DEL_Y);
                // entity.setSettleRemark(settleQuery.getSettleRemark());
                // installOrderService.edit(entity);
                //20190909 baimin 如果结算审核拒绝，原订单状态从已完成变为待服务商确认
                installOrderService.settleVerifyFail(settleQuery.getOrderNo(), settleQuery.getSettleRemark());
            }
        } else if (SysConstant.WorkFollow.CONFIG_WORKFOLLOW_DELIVERY.equals(settleQuery.getOrderType())) {
            if (deliveryOrderService.findByNo(settleQuery.getOrderNo()) != null) {
                // DeliveryOrderEntity entity = deliveryOrderService.findByNo(settleQuery.getOrderNo());
                //ViewDeliveryOrderEntity deliveryOrderEntity=viewDeliveryOrderService.findByNo(settleQuery.getOrderNo());
                // entity.setSettleVerifyFlg(SysConstant.IS_DEL_Y);
                // entity.setSettleFlg(SysConstant.IS_DEL_N);
                // entity.setSettleRemark(settleQuery.getSettleRemark());
                // deliveryOrderService.save(entity);
                //20190909 baimin 如果结算审核拒绝，原订单状态从已完成变为待服务商确认
                deliveryOrderService.settleVerifyFail(settleQuery.getOrderNo(), settleQuery.getSettleRemark());
            }
        }
         SettleEntity settleEntity = this.findByNo(settleQuery.getSettleNo());
         BigDecimal allAmt = settleEntity.getTtlAmt();
         double amt = allAmt.doubleValue() - deleteAmt.doubleValue();
         settleEntity.setTtlAmt(BigDecimal.valueOf(amt));
         settleRepository.save(settleEntity);
         List<SettleDetailEntity> detailEntityList=settleDetailService.findByNo(settleNo);
         if (detailEntityList.size()==0){
             this.delete(settleEntity.getId());
         }
        return true;
    }


    /**
     * 删除（逻辑删除）
     *
     * @param id 结算单Id
     * @return 删除是否成功
     */
    @Override
    public boolean delete(String id) {
        checkById(id);
        //删除标记
        settleRepository.delete(SysConstant.IS_DEL_Y, id);
        return true;
    }

    /**
     * 创建查询条件
     *
     * @param settleQuery 查询条件
     * @return 查询条件
     */
    private Specification<SettleEntity> createSpecification(SettleQuery settleQuery) {
        return new Specification<SettleEntity>() {

            @Override
            public Predicate toPredicate(Root<SettleEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();

                //订单号
                if (!isEmpty(settleQuery.getOrderNo())) {
                    predicate.add(cb.equal(root.get("orderNo").as(String.class), settleQuery.getOrderNo()));
                }

                //筛选已删除
                predicate.add(cb.equal(root.get("isDel").as(String.class), SysConstant.IS_DEL_N));

                Predicate[] p = new Predicate[predicate.size()];
                return cb.and(predicate.toArray(p));
            }

        };
    }


}

