package cn.backend.service.orderstep;

import cn.backend.config.constant.SysConstant;
import cn.backend.config.message.CustomMessage;
import cn.backend.dao.primary.orderstep.OrderStepRepository;
import cn.backend.model.primary.orderstep.OrderStepEntity;
import cn.backend.model.primary.orderstep.OrderStepParameter;
import cn.backend.model.primary.orderstep.OrderStepQuery;
import cn.backend.model.primary.user.UserEntity;
import cn.backend.model.primary.viewdeliveryorder.ViewDeliveryOrderEntity;
import cn.backend.model.primary.viewinstallorder.ViewInstallOrderEntity;
import cn.backend.model.primary.workfollow.WorkFollowEntity;
import cn.backend.model.primary.workfollowdetail.WorkFollowDetailEntity;
import cn.backend.service.BaseService;
import cn.backend.service.config.IConfigService;
import cn.backend.service.installorderview.IInstallOrderViewService;
import cn.backend.service.ordergrade.IOrderGradeService;
import cn.backend.service.user.IUserService;
import cn.backend.service.viewdeliveryorder.IViewDeliveryOrderService;
import cn.backend.service.workfollow.IWorkFollowService;
import cn.backend.service.workfollowdetail.IWorkFollowDetailService;
import cn.zdwl.common.context.BaseContextHandler;
import cn.zdwl.common.exception.AppException;
import cn.zdwl.common.util.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author sunkw
 * @date 2019/07/16
 */
@Service(value = "orderStepService")
public class OrderStepService extends BaseService implements IOrderStepService {

    @Resource
    private OrderStepRepository orderStepRepository;

    @Autowired
    private IWorkFollowService workFollowService;

    @Autowired
    private IWorkFollowDetailService workFollowDetailService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IConfigService configService;

    @Autowired
    private IViewDeliveryOrderService viewDeliveryOrderService;

    @Autowired
    private IInstallOrderViewService installOrderViewService;

    @Autowired
    private IOrderGradeService orderGradeService;


    /**
     * sunkw add 2019 0809 如果是最后一步 由调用处自己更新状态为Y
     *
     * @param parameter 业务订单号
     * @return
     */
    @Override
    public OrderStepEntity orderStepAdd(OrderStepParameter parameter) {
        OrderStepEntity orderStepEntity = new OrderStepEntity();
        //获取该订单所有步骤
        List<OrderStepEntity> orderStepEntities = orderStepRepository.findByOrderNoAndFollowNo(SysConstant.IS_DEL_N, parameter.getOrderNo(), parameter.getFollowNo());
        if (orderStepEntities != null && orderStepEntities.size() > 0) {
            //如果之前有步骤记录，则获取编辑当前步骤，并初始化下一步骤
            OrderStepEntity currentOrderStep = orderStepEntities.stream().filter(x -> SysConstant.OrderStep.CURRENT_STEP_Y.equals(x.getCurrentStepFlg())).findFirst().orElse(null);
            if (currentOrderStep != null) {

                orderStepEntity = currentOrderStep;
                WorkFollowDetailEntity workFollowNext = workFollowDetailService.getNextStep(currentOrderStep.getFollowNo(), currentOrderStep.getFollowSeq());
                //检查是否需要重新生成
                if (workFollowNext != null && SysConstant.InstallOrder.REBUILD_FLG_NO.equals(workFollowNext.getCheckRebuildFlg())) {
                    //查找该步骤是否有生成过
                    Integer nextFollowSeq = workFollowNext.getFollowSeq();
                    List<OrderStepEntity> noReBuildOrderStepList = orderStepEntities.stream().filter(x -> nextFollowSeq.equals(x.getFollowSeq())).collect(Collectors.toList());
                    if (noReBuildOrderStepList.size() > 0) {
                        //如果该步骤不需要重新生成，继续向下查询，直到找到需要生成的
                        //判断重新生成订单类型，目前判断是否需要重新生成的只有 安装和配送
                        boolean flag = true;
                        WorkFollowDetailEntity checkEntity = new WorkFollowDetailEntity();
                        while (flag) {
                            checkEntity = workFollowDetailService.getNextStep(parameter.getFollowNo(), workFollowNext.getFollowSeq());
                            if (checkEntity == null) {
                                throw new AppException(CustomMessage.NO_DATA_FIND);
                            } else if (SysConstant.InstallOrder.CONFIG_FLG_Y.equals(checkEntity.getCheckRebuildFlg())) {
                                flag = false;
                            }
                        }
                        workFollowNext = checkEntity;
                    }
                }
                //超时 2019/08/10 baimin 前端需传baseTime过来
                if (SysConstant.InstallOrder.CONFIG_FLG_Y.equals(currentOrderStep.getTimeOutFlg())) {
                    int minutes = 0;
                    //根据超时计算类型选择不同计算方式
                    if (SysConstant.OrderStep.TIMEOUT_TYPE_WORKDAY.equals(parameter.getTimeOutType())) {
                        //baimin 20190811 超时结束时间也应传来，原因：安装、配送超时结束时间并非都为当前时间
                        minutes = judgeTimeoutHours(currentOrderStep.getTimeOutRadio(), parameter.getBaseTime(), parameter.getEndTime());
                    } else if (SysConstant.OrderStep.TIMEOUT_TYPE_WORKDAYHOUR.equals(parameter.getTimeOutType())) {
                        minutes = judgeTimeoutHoursWithWorking(currentOrderStep.getTimeOutRadio(), parameter.getBaseTime(), parameter.getEndTime(), SysConstant.Advice.WORKDAY_STARTTIME, SysConstant.Advice.WORKDAY_ENDTIME);
                    }
                    if (minutes == 0) {
                        //未超时
                        currentOrderStep.setTimeoutStatus(SysConstant.OrderStep.CONFIG_TYPE_TIMEOUT_N);
                        currentOrderStep.setOutTime(minutes);
                    } else {
                        //超时
                        currentOrderStep.setTimeoutStatus(SysConstant.OrderStep.CONFIG_TYPE_TIMEOUT_Y);
                        currentOrderStep.setOutTime(minutes);
                    }
                }
                //设置当前步骤
                currentOrderStep.setResultCode(SysConstant.OrderStep.CHECK_FLG_AGREE);
                currentOrderStep.setStepTime(new Date());
                UserEntity userEntity = getCurrentUser();
                currentOrderStep.setStepUserNo(userEntity.getUserNo());
                currentOrderStep.setStepUserName(userEntity.getUserName());
                currentOrderStep.setStepRemark(parameter.getRemark());
                currentOrderStep.setResultDesc(parameter.getResultDesc());
                //初始化下一步数据
                if (workFollowNext != null) {
                    OrderStepEntity nextStep = createOrderStep(workFollowNext, parameter.getOrderNo(), parameter.getBaseTime());
                    //业务号在之前基础上+1
                    nextStep.setStepSeq(currentOrderStep.getStepSeq() + 1);
                    add(nextStep);
                    //上一步重制为N
                    currentOrderStep.setCurrentStepFlg(SysConstant.OrderStep.CURRENT_STEP_N);
                }
                //保存当前步骤
                edit(currentOrderStep);
//                //满意度
//                if (currentOrderStep != null && SysConstant.InstallOrder.GRADE_STEP_MATRIX.equals(currentOrderStep.getStepMatrix())) {
//                    orderGradeService.add(parameter.getOrderGradeEntity());
//
//                }

            } else {
                //对于取消订单，需要重新生成步骤
                WorkFollowDetailEntity workFollowDetailEntity = workFollowDetailService.getMinStep(parameter.getFollowNo());
                OrderStepEntity nextStep = createOrderStep(workFollowDetailEntity, parameter.getOrderNo(), parameter.getBaseTime());
                nextStep.setStepSeq(nextStep.getFollowSeq() + orderStepEntities.size());
                add(nextStep);
            }

        } else {
            //如果之前没有步骤记录，初始化第一步
            WorkFollowDetailEntity workFollowDetailEntity = workFollowDetailService.getMinStep(parameter.getFollowNo());
            OrderStepEntity nextStep = createOrderStep(workFollowDetailEntity, parameter.getOrderNo(), parameter.getBaseTime());
            nextStep.setStepSeq(nextStep.getFollowSeq());
            add(nextStep);
        }
        return orderStepEntity;
    }


    /**
     * sunkw 用于审批回退或者审批拒绝 2019 0809
     *
     * @param parameter 业务订单号
     * @return
     */
    @Override
    public OrderStepEntity orderStepRejct(OrderStepParameter parameter) {
        OrderStepEntity orderStepEntity = new OrderStepEntity();
        List<OrderStepEntity> orderStepEntities = orderStepRepository.findByOrderNoAndFollowNo(SysConstant.IS_DEL_N, parameter.getOrderNo(), parameter.getFollowNo());
        if (orderStepEntities != null && orderStepEntities.size() > 0) {
            OrderStepEntity currentOrderStep = orderStepEntities.stream().filter(x -> SysConstant.OrderStep.CURRENT_STEP_Y.equals(x.getCurrentStepFlg())).findFirst().orElse(null);
            //返回当前处理的步骤
            // orderStepEntity = currentOrderStep;
            //处理当前步骤
            currentOrderStep.setResultCode(SysConstant.OrderStep.CHECK_FLG_REJECT);
            currentOrderStep.setStepTime(new Date());
            UserEntity userEntity = getCurrentUser();
            currentOrderStep.setStepUserNo(userEntity.getUserNo());
            currentOrderStep.setStepUserName(userEntity.getUserName());
            currentOrderStep.setStepRemark(parameter.getRemark());
            currentOrderStep.setResultDesc(parameter.getResultDesc());
            if (SysConstant.OrderStep.ROLLBACK_FLG_YES.equals(currentOrderStep.getRollbackFlg())) {
                WorkFollowDetailEntity workFollowRollback = workFollowDetailService.findByFollowNoAndFollowSeq(currentOrderStep.getFollowNo(), currentOrderStep.getRollbackSeq());
                orderStepEntity = createOrderStep(workFollowRollback, parameter.getOrderNo(), parameter.getBaseTime());
                orderStepEntity.setStepSeq(currentOrderStep.getStepSeq() + 1);
                add(orderStepEntity);
                //上一步重制为N
                currentOrderStep.setCurrentStepFlg(SysConstant.OrderStep.CURRENT_STEP_N);
            } else {
                orderStepEntity = currentOrderStep;
            }
            //保存当前步骤
            edit(currentOrderStep);
        }
        return orderStepEntity;
    }

    /**
     * 用于直接关闭订单 20190909
     *
     * @param parameter 业务订单号
     * @return
     */
    @Override
    public OrderStepEntity orderStepFinish(OrderStepParameter parameter) {
        OrderStepEntity orderStepEntity = new OrderStepEntity();
        List<OrderStepEntity> orderStepEntities = orderStepRepository.findByOrderNoAndFollowNo(SysConstant.IS_DEL_N, parameter.getOrderNo(), parameter.getFollowNo());
        if (orderStepEntities != null && orderStepEntities.size() > 0) {
            OrderStepEntity currentOrderStep = orderStepEntities.stream().filter(x -> SysConstant.OrderStep.CURRENT_STEP_Y.equals(x.getCurrentStepFlg())).findFirst().orElse(null);
            //返回当前处理的步骤
            // orderStepEntity = currentOrderStep;
            //处理当前步骤
            currentOrderStep.setStepTime(new Date());
            UserEntity userEntity = getCurrentUser();
            currentOrderStep.setStepUserNo(userEntity.getUserNo());
            currentOrderStep.setStepUserName(userEntity.getUserName());
            currentOrderStep.setStepRemark(parameter.getRemark());
            currentOrderStep.setResultDesc(parameter.getResultDesc());

            WorkFollowDetailEntity workFollowFinish = workFollowDetailService.getMaxStep(currentOrderStep.getFollowNo());
            orderStepEntity = createOrderStep(workFollowFinish, parameter.getOrderNo(), parameter.getBaseTime());
            orderStepEntity.setStepSeq(currentOrderStep.getStepSeq() + 1);
            add(orderStepEntity);
            //上一步重制为N
            currentOrderStep.setCurrentStepFlg(SysConstant.OrderStep.CURRENT_STEP_N);

            //保存当前步骤
            edit(currentOrderStep);
        }
        return orderStepEntity;
    }

    /**
     * 结算审核不通过 20190909
     *
     * @param parameter 业务订单号
     * @return
     */
    @Override
    public OrderStepEntity orderStepSettleFail(OrderStepParameter parameter) {
        OrderStepEntity orderStepEntity = new OrderStepEntity();
        List<OrderStepEntity> orderStepEntities = orderStepRepository.findByOrderNoAndFollowNo(SysConstant.IS_DEL_N, parameter.getOrderNo(), parameter.getFollowNo());
        if (orderStepEntities != null && orderStepEntities.size() > 0) {
            OrderStepEntity currentOrderStep = orderStepEntities.stream().filter(x -> SysConstant.OrderStep.CURRENT_STEP_Y.equals(x.getCurrentStepFlg())).findFirst().orElse(null);
            //返回当前处理的步骤
            // orderStepEntity = currentOrderStep;
            //处理当前步骤
            // currentOrderStep.setStepTime(new Date());
            // UserEntity userEntity = getCurrentUser();
            // currentOrderStep.setStepUserNo(userEntity.getUserNo());
            // currentOrderStep.setStepUserName(userEntity.getUserName());
            // currentOrderStep.setStepRemark(parameter.getRemark());
            // currentOrderStep.setResultDesc(parameter.getResultDesc());

            WorkFollowDetailEntity workFollowConfirm = workFollowDetailService.getConfirmStep(currentOrderStep.getFollowNo());
            orderStepEntity = createOrderStep(workFollowConfirm, parameter.getOrderNo(), parameter.getBaseTime());
            orderStepEntity.setStepSeq(currentOrderStep.getStepSeq() + 1);
            add(orderStepEntity);
            //上一步重制为N
            currentOrderStep.setCurrentStepFlg(SysConstant.OrderStep.CURRENT_STEP_N);

            //保存当前步骤
            edit(currentOrderStep);
        }
        return orderStepEntity;
    }

    /**
     * 获取当前用户
     *
     * @return
     */
    private UserEntity getCurrentUser() {
        UserEntity userEntity = userService.findById(BaseContextHandler.getUserId());
        return userEntity;
    }

    /**
     * 用于编辑当前流程，不产生下一流程 对应安装、配送中的保存
     *
     * @param parameter
     * @return
     */
    @Override
    public OrderStepEntity orderStepEdit(OrderStepParameter parameter) {
        OrderStepEntity orderStepEntity = new OrderStepEntity();
        //获取该订单所有步骤
        List<OrderStepEntity> orderStepEntities = orderStepRepository.findByOrderNoAndFollowNo(SysConstant.IS_DEL_N, parameter.getOrderNo(), parameter.getFollowNo());
        if (orderStepEntities != null && orderStepEntities.size() > 0) {
            //如果之前有步骤记录，则获取编辑当前步骤
            OrderStepEntity currentOrderStep = orderStepEntities.stream().filter(x -> SysConstant.OrderStep.CURRENT_STEP_Y.equals(x.getCurrentStepFlg())).findFirst().orElse(null);
            currentOrderStep.setStepTime(new Date());
            UserEntity userEntity = getCurrentUser();
            currentOrderStep.setStepUserNo(userEntity.getUserNo());
            currentOrderStep.setStepUserName(userEntity.getUserName());
            currentOrderStep.setStepRemark(currentOrderStep.getStepRemark() + " " + parameter.getRemark());
            edit(currentOrderStep);
        }
        return orderStepEntity;
    }

    /**
     * 创建OrderStep
     *
     * @param workFollowDetailEntity
     * @param orderNo
     * @return
     */
    private OrderStepEntity createOrderStep(WorkFollowDetailEntity workFollowDetailEntity, String orderNo, Date baseTime) {
        OrderStepEntity orderStepEntity = new OrderStepEntity();

        orderStepEntity.setFollowNo(workFollowDetailEntity.getFollowNo());
        orderStepEntity.setCorpNo(BaseContextHandler.getCorpNo());
        orderStepEntity.setFollowCode(workFollowDetailEntity.getFollowCode());
        orderStepEntity.setFollowName(workFollowDetailEntity.getFollowName());
        orderStepEntity.setDetailId(workFollowDetailEntity.getId());
        orderStepEntity.setDetailNo(workFollowDetailEntity.getDetailNo());
        orderStepEntity.setFollowSeq(workFollowDetailEntity.getFollowSeq());
        orderStepEntity.setCancleFlg(workFollowDetailEntity.getCancleFlg());
        orderStepEntity.setCheckFlg(workFollowDetailEntity.getCheckFlg());
        orderStepEntity.setPositionCodes(workFollowDetailEntity.getPositionCodes());
        orderStepEntity.setCurrentStepFlg(SysConstant.OrderStep.CURRENT_STEP_Y);
        orderStepEntity.setLastStep(workFollowDetailEntity.getLastStep());
        orderStepEntity.setStepMatrix(workFollowDetailEntity.getStepMatrix());
        // orderStepEntity.setDispatchFlg(workFollowDetailEntity.getDispatchFlg());
        // orderStepEntity.setDispatchKanChaFlg(workFollowDetailEntity.getDispatchKanChaFlg());
        // orderStepEntity.setKanChaFlg(workFollowDetailEntity.getKanChaFlg());
        // orderStepEntity.setDispatchInstallFlg(workFollowDetailEntity.getDispatchInstallFlg());
        // orderStepEntity.setSupConfirmFlg(workFollowDetailEntity.getSupConfirmFlg());
        // orderStepEntity.setVerifyFlg(workFollowDetailEntity.getVerifyFlg());
        // orderStepEntity.setStepTime(new Date());
        if (baseTime != null) {
            orderStepEntity.setBaseTime(baseTime);
        } else {
            orderStepEntity.setBaseTime(new Date());
        }
        orderStepEntity.setStepName(workFollowDetailEntity.getStepName());
        // orderStepEntity.setInstallFlg(workFollowDetailEntity.getInstallFlg());
        // orderStepEntity.setReceiveFlg(workFollowDetailEntity.getReceiveFlg());
        orderStepEntity.setStepRemark("");
        orderStepEntity.setRollbackSeq(workFollowDetailEntity.getRollbackSeq());
        orderStepEntity.setRollbackFlg(workFollowDetailEntity.getRollbackFlg());
        orderStepEntity.setOrderNo(orderNo);
        orderStepEntity.setParentSeq(workFollowDetailEntity.getParentSeq());
        orderStepEntity.setTimeOutFlg(workFollowDetailEntity.getTimeOutFlg());
        orderStepEntity.setTimeOutRadio(workFollowDetailEntity.getTimeOutRadio());
        orderStepEntity.setConfirmStep(workFollowDetailEntity.getConfirmStep());
        //orderStepEntity.setGradeFlg(workFollowDetailEntity.getGradeFlg());
        return orderStepEntity;
    }


    /**
     * 分页查询
     *
     * @param orderStepQuery
     * @return
     */
    @Override
    public Page<OrderStepEntity> findList(OrderStepQuery orderStepQuery) {
        Sort sort = buildSort(orderStepQuery.getSort());
        Pageable pageable = new PageRequest(orderStepQuery.getPageNum(), orderStepQuery.getPageSize(), sort);
        Page<OrderStepEntity> entityPage = orderStepRepository.findAll(createSpecification(orderStepQuery), pageable);
        return entityPage;
    }

    /**
     * 仪表盘查找已完成订单的步骤
     *
     * @param orderStepQuery
     * @return
     */
    @Override
    public Page<OrderStepEntity> findFinishList(OrderStepQuery orderStepQuery) {
        Sort sort = buildSort(orderStepQuery.getSort());
        Pageable pageable = new PageRequest(orderStepQuery.getPageNum(), orderStepQuery.getPageSize(), sort);
        Page<OrderStepEntity> entityPage = orderStepRepository.findAll(createSpecification(orderStepQuery), pageable);
        List<OrderStepEntity> list = entityPage.getContent();

        List<OrderStepEntity> orderStepEntityList = new ArrayList<>();
        for (OrderStepEntity orderStepEntity : list) {
            ViewInstallOrderEntity installOrderEntity = installOrderViewService.findByOrderNo(orderStepEntity.getOrderNo(), orderStepQuery.getCorpNo());
            ViewDeliveryOrderEntity deliveryOrderEntity = viewDeliveryOrderService.findByNo(orderStepEntity.getOrderNo());
            if (installOrderEntity != null && installOrderEntity.getFinishFlg().equals(SysConstant.InstallOrder.FINISH_FLG_N)) {
                orderStepEntity.setOrderTime(installOrderEntity.getOrderTime());
                orderStepEntity.setOrderId(installOrderEntity.getId());
                orderStepEntityList.add(orderStepEntity);
            }
            if (deliveryOrderEntity != null && deliveryOrderEntity.getFinishFlg().equals(SysConstant.InstallOrder.FINISH_FLG_N)) {
                orderStepEntity.setOrderTime(deliveryOrderEntity.getOrderTime());
                orderStepEntity.setOrderId(deliveryOrderEntity.getId());
                orderStepEntityList.add(orderStepEntity);
            }
        }
        Page<OrderStepEntity> result = new PageImpl<>(orderStepEntityList, pageable, orderStepEntityList.size());
        return result;
    }

    /**
     * 列表查询
     *
     * @param orderStepQuery
     * @return
     */
    @Override
    public List<OrderStepEntity> findAll(OrderStepQuery orderStepQuery) {
        Sort sort = buildSort(orderStepQuery.getSort());
        List<OrderStepEntity> entityList = orderStepRepository.findAll(createSpecification(orderStepQuery), sort);
        return entityList;
    }

    /**
     * 创建查询条件
     *
     * @param orderStepQuery
     * @return
     */
    private Specification createSpecification(OrderStepQuery orderStepQuery) {
        return new Specification<OrderStepEntity>() {

            @Override
            public Predicate toPredicate(Root<OrderStepEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();

                //订单编号
                if (!StringUtils.isEmpty(orderStepQuery.getOrderNo())) {
                    predicate.add(cb.equal(root.get("orderNo").as(String.class), orderStepQuery.getOrderNo()));
                }

                if (orderStepQuery.getOrderNos() != null && orderStepQuery.getOrderNos().size() > 0) {
                    Expression<String> exp = root.<String>get("orderNo");
                    predicate.add(exp.in(orderStepQuery.getOrderNos()));
                }

                if (!StringUtils.isEmpty(orderStepQuery.getCorpNo())) {
                    predicate.add(cb.equal(root.get("corpNo").as(String.class), orderStepQuery.getCorpNo()));
                }

                //流程编号
                if (!StringUtils.isEmpty(orderStepQuery.getFollowCode())) {
                    predicate.add(cb.equal(root.get("followCode").as(String.class), orderStepQuery.getFollowCode()));
                }

                //流程编码
                if (!StringUtils.isEmpty(orderStepQuery.getFollowNo())) {
                    predicate.add(cb.equal(root.get("followNo").as(String.class), orderStepQuery.getFollowNo()));
                }

                //步骤矩阵
                if (!org.springframework.util.StringUtils.isEmpty(orderStepQuery.getStepMatrix())) {
                    predicate.add(cb.equal(root.get("stepMatrix").as(String.class), orderStepQuery.getStepMatrix()));
                }

                //步骤号
                if (orderStepQuery.getFollowSeq() != null) {
                    predicate.add(cb.equal(root.get("followSeq").as(Integer.class), orderStepQuery.getFollowSeq()));
                }

                //超时状态
                if (!StringUtils.isEmpty(orderStepQuery.getTimeoutStatus())) {
                    predicate.add(cb.equal(root.get("timeoutStatus").as(String.class), orderStepQuery.getTimeoutStatus()));
                }

                //是否是最新步骤
                if (!StringUtils.isEmpty(orderStepQuery.getCurrentStepFlg())) {
                    predicate.add(cb.equal(root.get("currentStepFlg").as(String.class), orderStepQuery.getCurrentStepFlg()));
                }

                //是否是最后步骤
                if (!StringUtils.isEmpty(orderStepQuery.getLastStep())) {
                    predicate.add(cb.equal(root.get("lastStep").as(String.class), orderStepQuery.getLastStep()));
                }

                //职位
                if (!StringUtils.isEmpty(orderStepQuery.getPositionCode())) {
                    predicate.add(cb.like(root.get("positionCodes").as(String.class), "%" + orderStepQuery.getPositionCode() + "%"));
                }

                //类型
                if (!StringUtils.isEmpty(orderStepQuery.getFollowCodes())) {

                    Expression<String> exp = root.<String>get("followCode");
                    String str = orderStepQuery.getFollowCodes();
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
    public OrderStepEntity add(OrderStepEntity entity) {
        configService.convert(entity);
        orderStepRepository.saveAndFlush(entity);
        return entity;
    }

    /**
     * 更新
     *
     * @param entity
     * @return
     */
    @Override
    public OrderStepEntity edit(OrderStepEntity entity) {
        configService.convert(entity);
        orderStepRepository.saveAndFlush(entity);
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
        OrderStepEntity entity = orderStepRepository.findById(SysConstant.IS_DEL_N, id);
        if (entity == null) {
            throw new AppException(CustomMessage.NO_DATA_FIND);
        }
        //删除标记
        orderStepRepository.delete(SysConstant.IS_DEL_Y, id);
        return true;
    }

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    @Override
    public OrderStepEntity findById(String id) {
        OrderStepEntity entity = orderStepRepository.findById(SysConstant.IS_DEL_N, id);
        UserEntity userEntity = userService.findById(BaseContextHandler.getUserId());
        ViewInstallOrderEntity installOrderEntity = installOrderViewService.findByOrderNo(entity.getOrderNo(), userEntity.getCorpNo());
        ViewDeliveryOrderEntity deliveryOrderEntity = viewDeliveryOrderService.findByNo(entity.getOrderNo());
        if (installOrderEntity != null) {
            entity.setInstallOrderEntity(installOrderEntity);
        }
        if (deliveryOrderEntity != null) {
            entity.setDeliveryOrderEntity(deliveryOrderEntity);
        }
        return entity;
    }

    /**
     * 根据id校验
     *
     * @param id
     * @return
     */
    @Override
    public OrderStepEntity checkById(String id) {
        OrderStepEntity entity = orderStepRepository.findById(SysConstant.IS_DEL_N, id);
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
    public List<OrderStepEntity> findByNo(String no) {
        List<OrderStepEntity> list = orderStepRepository.findByNo(SysConstant.IS_DEL_N, no);
        return list;
    }

    /**
     * 根据订单编号查找最后一步
     *
     * @param orderNo
     * @return
     */
    @Override
    public OrderStepEntity findLastByOrderNo(String orderNo, String followCode) {
        return orderStepRepository.findLastByOrderNo(SysConstant.IS_DEL_N, orderNo, followCode);
    }

    /**
     * 根据订单编号和步骤号查找
     *
     * @param orderNo
     * @param followSeq
     * @return
     */
    @Override
    public List<OrderStepEntity> findByOrderNoAndFollowSeq(String orderNo, int followSeq) {
        return orderStepRepository.findByOrderNoAndFollowSeq(SysConstant.IS_DEL_N, orderNo, followSeq);
    }

    /**
     * 工作流处理
     *
     * @param followCode
     * @param followStep
     * @return
     */
    @Override
    public OrderStepEntity workFollow(String followCode, int followStep) {
        //获取工作流
        WorkFollowEntity workFollowEntity = workFollowService.findByCode(followCode);
        WorkFollowDetailEntity workFollowDetailEntity = workFollowEntity.getWorkFollowDetailEntityList()
                .stream().filter(e -> e.getFollowSeq().equals(followStep)).findFirst().orElse(null);
        //获取用户
        UserEntity userEntity = userService.findById(BaseContextHandler.getUserId());
        //生成OrderStep
        OrderStepEntity orderStepEntity = initOrderStep(workFollowEntity
                , workFollowDetailEntity, userEntity);
        return orderStepEntity;
    }

    /**
     * 初始化OrderStep,审批步骤相关未处理
     *
     * @param workFollowEntity
     * @param workFollowDetailEntity
     * @param userEntity
     * @return
     */
    @Override
    public OrderStepEntity initOrderStep(WorkFollowEntity workFollowEntity
            , WorkFollowDetailEntity workFollowDetailEntity, UserEntity userEntity) {
        OrderStepEntity orderStepEntity = new OrderStepEntity();
        orderStepEntity.setFollowNo(workFollowEntity.getFollowNo());
        orderStepEntity.setFollowCode(workFollowEntity.getFollowCode());
        orderStepEntity.setFollowName(workFollowEntity.getFollowName());
        orderStepEntity.setDetailId(workFollowDetailEntity.getId());
        orderStepEntity.setDetailNo(workFollowDetailEntity.getDetailNo());
        orderStepEntity.setFollowSeq(workFollowDetailEntity.getFollowSeq());
        orderStepEntity.setCancleFlg(workFollowDetailEntity.getCancleFlg());
        orderStepEntity.setCheckFlg(workFollowDetailEntity.getCheckFlg());
        orderStepEntity.setPositionCodes(workFollowDetailEntity.getPositionCodes());
        orderStepEntity.setCurrentStepFlg(SysConstant.OrderStep.CURRENT_STEP_Y);
        orderStepEntity.setStepTime(new Date());
        orderStepEntity.setBaseTime(new Date());
//        orderStepEntity.setDispatchFlg(workFollowDetailEntity.getDispatchFlg());
        //安装、提单可能没有token，故要做判断
        // if (userEntity != null) {
        //     orderStepEntity.setStepUserNo(userEntity.getUserNo());
        //     orderStepEntity.setStepUserName(userEntity.getUserName());
        // }
        orderStepEntity.setStepName(workFollowDetailEntity.getStepName());
        // orderStepEntity.setInstallFlg(workFollowDetailEntity.getInstallFlg());
        // orderStepEntity.setReceiveFlg(workFollowDetailEntity.getReceiveFlg());
        orderStepEntity.setStepRemark("");
        // 审批步骤相关此处不处理
        // orderStepEntity.setResultCode();
        // orderStepEntity.setResultName();
        // orderStepEntity.setResultDesc();
        // 部分字段 orderNo, stepRemark, stepSeq 需要自己设置
        // orderStepEntity.setOrderNo();
        // orderStepEntity.setStepRemark();
        // orderStepEntity.setStepSeq(stepSeq);
        orderStepEntity.setLastStep(workFollowDetailEntity.getLastStep());
        return orderStepEntity;
    }

    /**
     * 判断是否超时,返回0未超时,非0则为超时时间
     *
     * @param days      限期
     * @param startDate 开始时间
     * @return
     */
    @Override
    public int judgeTimeoutDays(int days, Date startDate, Date endDate) {
        LocalDateTime startTime = dateToLocalDateTime(startDate);
        LocalDateTime startCompareTime = startTime;
        LocalDateTime endTime = dateToLocalDateTime(endDate);
        int workDay = 0;
        int weekendDay = 0;
        while (startCompareTime.compareTo(endTime) <= 0) {
            if (startCompareTime.getDayOfWeek() != DayOfWeek.SATURDAY && startCompareTime.getDayOfWeek() != DayOfWeek.SUNDAY) {
                workDay++;
            } else {
                weekendDay++;
            }
            startCompareTime = startCompareTime.plusDays(1);
        }
        // System.out.println(workDay);
        // System.out.println(weekendDay);
        if (workDay <= 1) {
            return 0;
        } else {
            startTime = startTime.plusDays(weekendDay);
            Duration duration = Duration.between(startTime, endTime);
            return (int) duration.toMinutes();
        }
    }

    /**
     * 判断是否超时 安装和配送
     *
     * @param hours     限期
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return
     */
    @Override
    public int judgeTimeoutHours(int hours, Date startDate, Date endDate) {
        LocalDateTime startTime = dateToLocalDateTime(startDate);
        LocalDateTime startCompareTime = startTime;
        LocalDateTime endTime = dateToLocalDateTime(endDate);
        int workHour = 0;
        int weekendHour = 0;
        while (startCompareTime.compareTo(endTime) < 0) {
            if (startCompareTime.getDayOfWeek() != DayOfWeek.SATURDAY && startCompareTime.getDayOfWeek() != DayOfWeek.SUNDAY) {
                workHour++;
            } else {
                weekendHour++;
            }
            startCompareTime = startCompareTime.plusHours(1);
        }
        // System.out.println(workHour);
        // System.out.println(weekendHour);
        if (workHour <= hours) {
            return 0;
        } else {
            startTime = startTime.plusHours(weekendHour + hours);
            Duration duration = Duration.between(startTime, endTime);
            return (int) duration.toMinutes();
        }
    }

    /**
     * 判断是否超时-指定每天工作时间 投诉和报修
     *
     * @param hours     限期
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return
     */
    @Override
    public int judgeTimeoutHoursWithWorking(int hours, Date startDate, Date endDate, String dayWorkStart, String dayWorkEnd) {
        LocalDateTime startTime = dateToLocalDateTime(startDate);
        LocalDateTime endTime = dateToLocalDateTime(endDate);

        LocalDate startLocalDate = startTime.toLocalDate();
        LocalDate endLocalDate = endTime.toLocalDate();
        //开始时间为左，结束时间为右，开始时间早于dayWorkStart称为外，否则为内，结束时间晚于dayWorkEnd称为外，否则为内
        //判断类型
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        String startHourMin = dtf.format(startTime);
        String endHourMin = dtf.format(endTime);
        String type = "";
        //判断是否工作日
        if (startTime.getDayOfWeek() == DayOfWeek.SATURDAY || startTime.getDayOfWeek() == DayOfWeek.SUNDAY) {
            type = "-1";
        } else {
            int startType = TimeCompare(startHourMin, dayWorkStart);
            switch (startType) {
                //左外
                case -1:
                    type = "-1";
                    break;
                //左内
                case 0:
                case 1:
                    type = "1";
                    break;
            }
        }
        type += ",";
        //判断是否工作日
        if (endTime.getDayOfWeek() == DayOfWeek.SATURDAY || endTime.getDayOfWeek() == DayOfWeek.SUNDAY) {
            type += "1";
        } else {
            int endType = TimeCompare(endHourMin, dayWorkEnd);
            switch (endType) {
                //右内
                case -1:
                    type += "-1";
                    break;
                //右外
                case 0:
                case 1:
                    type += "1";
                    break;
            }
        }
        //一天的工作时间
        int dayWorkMin = TimeDiffInOneDay(dayWorkEnd, dayWorkStart);
        int useMin = 0;
        //根据类型，采用相应算式
        switch (type) {
            //左外右内
            case "-1,-1":
                useMin = dayWorkMin + TimeDiffInOneDay(endHourMin, dayWorkStart);
                break;
            //左内右外
            case "1,1":
                useMin = dayWorkMin + TimeDiffInOneDay(dayWorkEnd, startHourMin);
                break;
            //左内右内
            case "1,-1":
                useMin = TimeDiffInOneDay(dayWorkEnd, startHourMin) + TimeDiffInOneDay(endHourMin, dayWorkStart);
                break;
            //左外右外
            case "-1,1":
                useMin = dayWorkMin + dayWorkMin;
                break;
        }
        //非工作日天数
        int noWorkDay = 0;
        LocalDate startCompareDate = startLocalDate;
        while (startCompareDate.compareTo(endLocalDate) < 0) {
            if (startCompareDate.getDayOfWeek() == DayOfWeek.SATURDAY || startCompareDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
                noWorkDay++;
            }
            startCompareDate = startCompareDate.plusDays(1);
        }
        //两时间相差天数
        int day = (int) (endLocalDate.toEpochDay() - startLocalDate.toEpochDay());
        useMin += (day - 1 - noWorkDay) * dayWorkMin;
        int outTime = useMin - hours * 60;
        if (outTime < 0) {
            outTime = 0;
        }
        return outTime;
    }

    /**
     * date转localDateTime
     *
     * @param date
     * @return
     */
    private LocalDateTime dateToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    /**
     * 计算同一天内的两时间之差
     *
     * @return
     */
    private int TimeDiffInOneDay(String timeBefore, String timeFront) {

        Date before = DateUtils.parseDate(timeBefore, "HH:mm");
        Date front = DateUtils.parseDate(timeFront, "HH:mm");
        //时间校验
        //如果被减数小于减数，根据需要默认返回0
        if (before.before(front)) {
            return 0;
        }
        long timeInMillisBefore = before.getTime();

        long timeInMillisFront = front.getTime();

        long mins = (timeInMillisBefore - timeInMillisFront) / 1000 / 60;
        return (int) mins;
    }

    /**
     * 两时间比较早晚
     *
     * @return
     */
    private int TimeCompare(String time1, String time2) {

        long timeInMillis1 = DateUtils.parseDate(time1, "HH:mm").getTime();

        long timeInMillis2 = DateUtils.parseDate(time2, "HH:mm").getTime();

        if (timeInMillis1 > timeInMillis2) {
            return 1;
        } else if (timeInMillis1 == timeInMillis2) {
            return 0;
        } else {
            return -1;
        }
    }


}

