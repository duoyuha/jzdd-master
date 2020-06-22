package cn.backend.service.adviceorder;

import cn.backend.config.constant.SysConstant;
import cn.backend.config.message.CustomMessage;
import cn.backend.dao.primary.adviceorder.AdviceRepository;
import cn.backend.model.primary.adviceorder.AdviceEntity;
import cn.backend.model.primary.adviceorder.AdviceQuery;
import cn.backend.model.primary.complainorder.ComplainOrderEntity;
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
@Service(value = "adviceService")
public class AdviceService extends BaseService implements IAdviceService {

    @Resource
    private AdviceRepository adviceRepository;

    @Autowired
    private IOrderCarService orderCarService;

    @Autowired
    private IOrderStepService orderStepService;

    @Autowired
    private IWorkFollowService workFollowService;

    @Autowired
    private IWorkFollowDetailService workFollowDetailService;

    @Autowired
    private IUserService userService;

    @Autowired
    private ISupplierService supplierService;

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
     * @param adviceQuery
     * @return
     */
    @Override
    public Page<AdviceEntity> findList(AdviceQuery adviceQuery) {
        Sort sort = buildSort(adviceQuery.getSort());
        Pageable pageable = new PageRequest(adviceQuery.getPageNum(), adviceQuery.getPageSize(), sort);
        Page<AdviceEntity> entityPage = adviceRepository.findAll(createSpecification(adviceQuery), pageable);
        return entityPage;
    }

    /**
     * 列表查询
     *
     * @param adviceQuery
     * @return
     */
    @Override
    public List<AdviceEntity> findAll(AdviceQuery adviceQuery) {
        Sort sort = buildSort(adviceQuery.getSort());
        List<AdviceEntity> entityList = adviceRepository.findAll(createSpecification(adviceQuery), sort);
        return entityList;
    }

    /**
     * 创建查询条件
     *
     * @param adviceQuery
     * @return
     */
    private Specification createSpecification(AdviceQuery adviceQuery) {
        return new Specification<AdviceEntity>() {

            @Override
            public Predicate toPredicate(Root<AdviceEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();

                //订单号

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
    public AdviceEntity submitOrder(AdviceEntity entity) {
        entity.setFinishFlg(SysConstant.InstallOrder.CONFIG_FLG_N);
        entity.setAdviceTime(new Date());
        //新增安装订单
        add(entity);
        //判断是内部订单还是外部订单
        int initFollowSeq;
        if ("".equals(entity.getScrmNo())) {
            //内部
            initFollowSeq = workFollowDetailService.getMinStep(SysConstant.WorkFollow.WORKFOLLOW_ADVICE_NO).getFollowSeq();
        } else {
            //外部
            initFollowSeq = workFollowDetailService.getMinStep(SysConstant.WorkFollow.WORKFOLLOW_ADVICE_NO).getFollowSeq();
        }
        OrderStepEntity orderStepEntity = orderStepService.workFollow(SysConstant.WorkFollow.CONFIG_WORKFOLLOW_ADVICE
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
    public AdviceEntity saveOrderAndStep(AdviceEntity entity) {
        //改变当前orderstep
        AdviceEntity oldEntity = checkById(entity.getId());
        //获取最新的订单步骤记录
        OrderStepEntity lastOrderStepEntity = orderStepService.findLastByOrderNo(entity.getOrderNo(), SysConstant.WorkFollow.CONFIG_WORKFOLLOW_ADVICE);
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
    public AdviceEntity orderWorkFollow(AdviceEntity entity) {
        //获取最新的订单步骤记录
        OrderStepEntity lastOrderStepEntity = orderStepService.findLastByOrderNo(entity.getOrderNo(), SysConstant.WorkFollow.CONFIG_WORKFOLLOW_ADVICE);
        //校验
        verify(entity, lastOrderStepEntity);
        AdviceEntity oldEntity = checkById(entity.getId());
        BeanCopyUtil.beanCopy(entity, oldEntity);
        //获取此步骤工作流
        WorkFollowEntity workFollowEntity = workFollowService.findByCode(SysConstant.WorkFollow.CONFIG_WORKFOLLOW_ADVICE);
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
                // int minutes = orderStepService.judgeTimeoutHoursWithWorking(workFollowDetailEntity.getTimeOutRadio(), entity.getAdviceTime(), entity.getTimeoutEndTime(), SysConstant.Advice.WORKDAY_STARTTIME, SysConstant.Advice.WORKDAY_ENDTIME);
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
     * @param adviceEntity
     */
    private void verify(AdviceEntity adviceEntity, OrderStepEntity lastOrderStepEntity) {
        //判断是否为最后一步，如果是，不可操作
        if (SysConstant.OrderStep.CURRENT_STEP_Y.equals(lastOrderStepEntity.getLastStep())) {
            AdviceEntity checkEntity = findById(adviceEntity.getId());
            if (SysConstant.InstallOrder.CONFIG_FLG_Y.equals(checkEntity.getFinishFlg())) {
                throw new AppException(CustomMessage.CAN_NOT_OPERATE);
            }
        }
        //判断是否开始作废
        // if(isEmpty(installOrderEntity.getBeginCacle()) && SysConstant.InstallOrder.CONFIG_FLG_Y.equals(installOrderEntity.getBeginCacle())){
        //     throw new AppException(CustomMessage.CAN_NOT_OPERATE);
        // }
        //判断当前步骤是否一致
        if (isEmpty(adviceEntity.getCurrentStep())
                || !adviceEntity.getCurrentStep().equals(lastOrderStepEntity.getFollowSeq())) {
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
     * @param adviceEntity
     * @param lastOrderStepEntity
     * @return
     */
    private OrderStepEntity orderStep(int followStep, AdviceEntity adviceEntity, OrderStepEntity lastOrderStepEntity) {
        OrderStepEntity newOrderStepEntity;
        //获取用户
        UserEntity userEntity = userService.findById(BaseContextHandler.getUserId());
        if (userEntity != null) {
            lastOrderStepEntity.setStepUserNo(userEntity.getUserNo());
            lastOrderStepEntity.setStepUserName(userEntity.getUserName());
        }
        WorkFollowDetailEntity maxWorkFollowDetailEntity = workFollowDetailService.getMaxStep(SysConstant.WorkFollow.WORKFOLLOW_ADVICE_NO);
        int stepNums = maxWorkFollowDetailEntity.getFollowSeq();
        if (stepNums != followStep || lastOrderStepEntity.getFollowSeq() != stepNums) {
            //初始化新订单步骤记录
            newOrderStepEntity = orderStepService.workFollow(SysConstant.WorkFollow.CONFIG_WORKFOLLOW_ADVICE
                    , followStep);
            newOrderStepEntity.setOrderNo(adviceEntity.getOrderNo());
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
     * 新建
     *
     * @param entity
     * @return
     */
    @Override
    public AdviceEntity add(AdviceEntity entity) {
        //生成编号
        entity.setAdviceNo(SysConstant.InstallOrder.PREFIX_NUMBER_ADVICE + IdUtils.getGenerateNumber());
        configService.convert(entity);
        adviceRepository.saveAndFlush(entity);
        return entity;
    }

    /**
     * 更新
     *
     * @param entity
     * @return
     */
    @Override
    public AdviceEntity edit(AdviceEntity entity) {
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
        configService.convert(entity);
        adviceRepository.saveAndFlush(entity);
        return entity;
    }

    @Override
    public AdviceEntity saveOrUpdate(ScrmInfoEntity scrmInfoEntity) {

        AdviceEntity advice = adviceRepository.findByScrmNo(SysConstant.IS_DEL_N, scrmInfoEntity.getScrmNo());
        if (advice == null) {
            advice = new AdviceEntity();
            advice.setAdviceNo(SysConstant.Advice.PREFIX_NUMBER + IdUtils.getGenerateNumber());
            advice.setCorpNo(scrmInfoEntity.getCorpNo());

            List<OrderCarEntity> orderCars = orderCarService.findListByVinNo(scrmInfoEntity.getVin());
            if (orderCars.size() > 0) {
                advice.setOrderNo(orderCars.get(0).getOrderNo());
                advice.setAdviceTime(scrmInfoEntity.getCreateTime());
            }
        }

        advice.setVinNo(scrmInfoEntity.getVin());
        advice.setCarOwner(scrmInfoEntity.getCustName());
        advice.setCarOwnerPhone(scrmInfoEntity.getCustTel());
        advice.setAdviceDesc(scrmInfoEntity.getContentDesc());
        advice.setScrmNo(scrmInfoEntity.getScrmNo());
        if (scrmInfoEntity.isNew()) {
            addAdvice(advice);
        }
        return advice;
    }

    /**
     * 删除（逻辑删除）
     *
     * @param id
     * @return
     */
    @Override
    public boolean delete(String id) {
        AdviceEntity entity = adviceRepository.findById(SysConstant.IS_DEL_N, id);
        if (entity == null) {
            throw new AppException(CustomMessage.NO_DATA_FIND);
        }
        //删除标记
        adviceRepository.delete(SysConstant.IS_DEL_Y, id);
        return true;
    }

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    @Override
    public AdviceEntity findById(String id) {
        AdviceEntity entity = adviceRepository.findById(SysConstant.IS_DEL_N, id);
        return entity;
    }

    /**
     * 根据id校验
     *
     * @param id
     * @return
     */
    @Override
    public AdviceEntity checkById(String id) {
        AdviceEntity entity = adviceRepository.findById(SysConstant.IS_DEL_N, id);
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
    public AdviceEntity findByNo(String no) {
        AdviceEntity entity = adviceRepository.findByNo(SysConstant.IS_DEL_N, no);
        return entity;
    }

    /**
     * 新建报修订单
     */
    @Override
    @Transactional
    public AdviceEntity addAdvice(AdviceEntity entity) {
        //新增报修订单
        String supplierNo = "";
        String supplierName = "";
        entity.setAdviceTime(new Date());
        entity.setFinishFlg(SysConstant.InstallOrder.FINISH_FLG_N);
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
        add(entity);
        //初始化订单步骤
        OrderStepParameter orderStepParameter = new OrderStepParameter();
        orderStepParameter.setOrderNo(entity.getAdviceNo());
        if (!isEmpty(entity.getScrmNo())) {

            orderStepParameter.setFollowNo(SysConstant.WorkFollow.WORKFOLLOW_ADVICE_NO);
        } else {
            orderStepParameter.setFollowNo(SysConstant.WorkFollow.WORKFOLLOW_IN_AFTERSELL_NO);
        }
        orderStepService.orderStepAdd(orderStepParameter);
        return entity;
    }

    /**
     * 编辑报修订单
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional
    public AdviceEntity editAdvice(AdviceEntity entity) {
        AdviceEntity oldEntity = findById(entity.getId());
        OrderStepParameter orderStepParameter = new OrderStepParameter();
        //校验
        verifyOrder(oldEntity);
        BeanCopyUtil.beanCopy(entity, oldEntity);
        //是否审核（签收）
        OrderStepEntity orderStepEntity;

        //超时开始结束时间
        WorkFollowDetailQuery workFollowDetailQuery = new WorkFollowDetailQuery();
        workFollowDetailQuery.setFollowNo(SysConstant.WorkFollow.WORKFOLLOW_ADVICE_NO);
        workFollowDetailQuery.setSort("+followSeq");
        List<WorkFollowDetailEntity> workFollowDetailEntityList = workFollowDetailService.findAll(workFollowDetailQuery);

        OrderStepEntity currentOrderStep = orderStepService.findLastByOrderNo(oldEntity.getAdviceNo(), SysConstant.WorkFollow.CONFIG_WORKFOLLOW_ADVICE);
        if (currentOrderStep != null) {
            switch (currentOrderStep.getFollowSeq()) {
                case SysConstant.Advice.CONTACT_SEQ:
                    WorkFollowDetailEntity contactWork = workFollowDetailEntityList.stream()
                            .filter(x -> SysConstant.InstallOrder.CONFIG_FLG_Y.equals(x.getTimeOutFlg()) && SysConstant.WorkFollow.WORKFOLLOW_TIMEOUT_TYPE08.equals(x.getTimeoutType()))
                            .findFirst().orElse(null);
                    //联系客户
                    setBaseAndEndTime(contactWork, oldEntity, orderStepParameter, SysConstant.WorkFollow.WORKFOLLOW_TIMEOUT_TYPE08);
                    break;
            }
        }

        if (SysConstant.Advice.RECEIVE_PASS.equals(entity.getReceiveStatus())) {
            //签收通过或非签收步骤
            orderStepParameter.setOrderNo(oldEntity.getAdviceNo());
            orderStepParameter.setResultDesc(entity.getRemark());
            if (!isEmpty(oldEntity.getScrmNo())) {
                orderStepParameter.setFollowNo(SysConstant.WorkFollow.WORKFOLLOW_ADVICE_NO);
            } else {
                orderStepParameter.setFollowNo(SysConstant.WorkFollow.WORKFOLLOW_IN_AFTERSELL_NO);
            }
            // orderStepParameter.setBaseTime(entity.getBaseTime());
            // orderStepParameter.setEndTime(entity.getEndTime());
            orderStepParameter.setTimeOutType(SysConstant.OrderStep.TIMEOUT_TYPE_WORKDAYHOUR);
            orderStepEntity = orderStepService.orderStepAdd(orderStepParameter);
        } else {
            //签收不通过
            orderStepParameter.setOrderNo(oldEntity.getAdviceNo());
            orderStepParameter.setResultDesc(entity.getRemark());
            if (!isEmpty(oldEntity.getScrmNo())) {
                orderStepParameter.setFollowNo(SysConstant.WorkFollow.WORKFOLLOW_ADVICE_NO);
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
            // scrmInfoService.caseResult(oldEntity);
        }
        edit(oldEntity);
        return oldEntity;
    }

    private void setBaseAndEndTime(WorkFollowDetailEntity workFollowDetailEntity, AdviceEntity adviceEntity, OrderStepParameter orderStepParameter, String overType) {
        //判断接单超时
        if (workFollowDetailEntity != null && !isEmpty(adviceEntity)) {
            Date baseTime = new Date();
            Date endTime = new Date();
            switch (overType) {
                case SysConstant.WorkFollow.WORKFOLLOW_TIMEOUT_TYPE07:
                    baseTime = adviceEntity.getReceiveTime();
                    // if (!isEmpty(adviceEntity.getContactCustTime())) {
                    endTime = adviceEntity.getContactCustTime();
                    // }
                    break;
            }
            orderStepParameter.setBaseTime(baseTime);
            orderStepParameter.setEndTime(endTime);
        }
    }

    /**
     * 报修订单关闭
     *
     * @param entity
     * @return
     */
    @Override
    public AdviceEntity closeAdvice(AdviceEntity entity) {
        AdviceEntity oldEntity = findById(entity.getId());
        //校验
        verifyOrder(oldEntity);
        BeanCopyUtil.beanCopy(entity, oldEntity);
        OrderStepEntity orderStepEntity;
        //签收不通过
        OrderStepParameter orderStepParameter = new OrderStepParameter();
        orderStepParameter.setOrderNo(oldEntity.getAdviceNo());
        orderStepParameter.setResultDesc(entity.getRemark());
        orderStepParameter.setFollowNo(SysConstant.WorkFollow.WORKFOLLOW_ADVICE_NO);
        orderStepEntity = orderStepService.orderStepFinish(orderStepParameter);

        //如果是最后一步，结束标志置Y
        if (SysConstant.OrderStep.LAST_STEP_Y.equals(orderStepEntity.getLastStep())) {
            oldEntity.setFinishFlg(SysConstant.InstallOrder.FINISH_FLG_Y);
            oldEntity.setFinishTime(new Date());
            scrmInfoService.caseResult(oldEntity);
        }
        edit(oldEntity);
        return oldEntity;
    }

    /**
     * 校验
     *
     * @param checkEntity
     */
    private void verifyOrder(AdviceEntity checkEntity) {
        //判断是否完成
        if (SysConstant.InstallOrder.CONFIG_FLG_Y.equals(checkEntity.getFinishFlg())) {
            throw new AppException(CustomMessage.CAN_NOT_OPERATE);
        }
    }


}

