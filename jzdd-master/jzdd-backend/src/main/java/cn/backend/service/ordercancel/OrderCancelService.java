package cn.backend.service.ordercancel;

import cn.backend.config.constant.SysConstant;
import cn.backend.config.message.CustomMessage;
import cn.backend.dao.primary.ordercancel.OrderCancelRepository;
import cn.backend.model.primary.corp.CorpEntity;
import cn.backend.model.primary.delivery.DeliveryOrderEntity;
import cn.backend.model.primary.installorder.InstallOrderEntity;
import cn.backend.model.primary.ordercancel.OrderCancelEntity;
import cn.backend.model.primary.ordercancel.OrderCancelQuery;
import cn.backend.model.primary.ordercar.OrderCarEntity;
import cn.backend.model.primary.orderstep.OrderStepEntity;
import cn.backend.model.primary.orderstep.OrderStepParameter;
import cn.backend.model.primary.user.UserEntity;
import cn.backend.service.BaseService;
import cn.backend.service.config.IConfigService;
import cn.backend.service.corp.ICorpService;
import cn.backend.service.delivery.IDeliveryOrderService;
import cn.backend.service.installorder.IInstallOrderService;
import cn.backend.service.ordercar.IOrderCarService;
import cn.backend.service.orderstep.IOrderStepService;
import cn.backend.service.scrminfo.IScrmInfoService;
import cn.backend.service.user.IUserService;
import cn.zdwl.common.context.BaseContextHandler;
import cn.zdwl.common.exception.AppException;
import cn.zdwl.common.util.BeanCopyUtil;
import cn.zdwl.common.util.IdUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import java.util.List;

/**
 * @author sunkw
 * @date 2019/08/26
 */
@Service(value = "orderCancelService")
public class OrderCancelService extends BaseService implements IOrderCancelService {

    @Resource
    private OrderCancelRepository orderCancelRepository;

    @Autowired
    private IConfigService configService;

    @Autowired
    private IInstallOrderService installOrderService;

    @Autowired
    private IUserService userService;

    @Autowired
    private ICorpService corpService;

    @Autowired
    private IOrderStepService orderStepService;

    @Autowired
    private IOrderCarService orderCarService;

    @Autowired
    private IScrmInfoService _scrmInfoService;

    @Autowired
    private IDeliveryOrderService deliveryOrderService;


    protected final Log logger = LogFactory.getLog(getClass());

    /**
     * 分页查询
     *
     * @param orderCancelQuery
     * @return
     */
    @Override
    public Page<OrderCancelEntity> findList(OrderCancelQuery orderCancelQuery) {
        Sort sort = buildSort(orderCancelQuery.getSort());
        Pageable pageable = new PageRequest(orderCancelQuery.getPageNum(), orderCancelQuery.getPageSize(), sort);
        Page<OrderCancelEntity> entityPage = orderCancelRepository.findAll(createSpecification(orderCancelQuery), pageable);
        return entityPage;
    }

    /**
     * 列表查询
     *
     * @param orderCancelQuery
     * @return
     */
    @Override
    public List<OrderCancelEntity> findAll(OrderCancelQuery orderCancelQuery) {
        Sort sort = buildSort(orderCancelQuery.getSort());
        List<OrderCancelEntity> entityList = orderCancelRepository.findAll(createSpecification(orderCancelQuery), sort);
        return entityList;
    }

    /**
     * 创建查询条件
     *
     * @param orderCancelQuery
     * @return
     */
    private Specification createSpecification(OrderCancelQuery orderCancelQuery) {
        return new Specification<OrderCancelEntity>() {

            @Override
            public Predicate toPredicate(Root<OrderCancelEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();

                //订单编号
                if (!StringUtils.isEmpty(orderCancelQuery.getOrderNo())) {
                    predicate.add(cb.equal(root.get("orderNo").as(String.class), orderCancelQuery.getOrderNo()));
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
    public OrderCancelEntity addInstall(OrderCancelEntity entity) {
        // orderCancelRepository.saveAndFlush(entity);


        //检查原来是否已作废
        InstallOrderEntity installOrderEntity = installOrderService.findByNo(entity.getOrderNo());
        if (SysConstant.InstallOrder.CONFIG_FLG_Y.equals(installOrderEntity.getBeginCacle())) {
            throw new AppException(CustomMessage.CANCEL_CANNOT_AGAIN);
        }
        if (!isEmpty(installOrderEntity.getCheckTime())) {
            throw new AppException(CustomMessage.CANCEL_NOT_FOUND);
        }
        //将开始作废标志置为Y
        installOrderEntity.setBeginCacle(SysConstant.IS_DEL_Y);
        installOrderService.edit(installOrderEntity);
        //OrderStepEntity install = orderStepService.findLastByOrderNo(query.getOrderNo(), SysConstant.WorkFollow.CONFIG_WORKFOLLOW_INSTALL);
        //作废订单权限校验


        entity.setCorpNo(installOrderEntity.getCorpNo());

        entity.setOrderType(SysConstant.WorkFollow.CONFIG_WORKFOLLOW_INSTALL);

        if (!isEmpty(installOrderEntity.getSupplierNo())) {
            entity.setSupplierNo(installOrderEntity.getSupplierNo());
        }
        ;

        if (!isEmpty(installOrderEntity.getSupplierName())) {
            entity.setSupplierName(installOrderEntity.getSupplierName());
        }
        ;

        entity.setReceiveStatus(installOrderEntity.getReceiveStatus());

        add(entity);

        return entity;


    }

    @Override
    public OrderCancelEntity addDelivery(OrderCancelEntity entity) {
        // orderCancelRepository.saveAndFlush(entity);


        //检查原来是否已作废
        DeliveryOrderEntity deliveryOrderEntity = deliveryOrderService.findByNo(entity.getOrderNo());
        if (SysConstant.InstallOrder.CONFIG_FLG_Y.equals(deliveryOrderEntity.getBeginCacle())) {
            throw new AppException(CustomMessage.CANCEL_CANNOT_AGAIN);
        }
        if (!isEmpty(deliveryOrderEntity.getDeliveryTime())) {
            throw new AppException(CustomMessage.CANCEL_NOT_FOUND);
        }
        //将开始作废标志置为Y
        deliveryOrderEntity.setBeginCacle(SysConstant.IS_DEL_Y);
        deliveryOrderService.saveOrderAndStep(deliveryOrderEntity);


        entity.setCorpNo(deliveryOrderEntity.getCorpNo());

        entity.setOrderType(SysConstant.WorkFollow.CONFIG_WORKFOLLOW_DELIVERY);

        if (!isEmpty(deliveryOrderEntity.getSupplierNo())) {
            entity.setSupplierNo(deliveryOrderEntity.getSupplierNo());
        }
        ;

        if (!isEmpty(deliveryOrderEntity.getSupplierName())) {
            entity.setSupplierName(deliveryOrderEntity.getSupplierName());
        }
        ;

        entity.setReceiveStatus(deliveryOrderEntity.getReceiveStatus());

        add(entity);

        return entity;


    }

    @Override
    public OrderCancelEntity add(OrderCancelEntity entity) {


        entity.setApplyNo(SysConstant.Cancel.PREFIX_NUMBER + IdUtils.getGenerateNumber());

        //初始化订单步骤
        OrderStepParameter orderStepParameter = new OrderStepParameter();
        orderStepParameter.setOrderNo(entity.getApplyNo());

        entity = orderCancelRepository.saveAndFlush(entity);
        //服务商没签收，直接复审
        if (SysConstant.InstallOrder.CONFIG_VERIFY_N.equals(entity.getReceiveStatus()) || entity.getReceiveStatus().equals(SysConstant.InstallOrder.CONFIG_VERIFY_REFUSE)) {
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
        _scrmInfoService.submitPileOrder(null, orderCarEntity.getVinNo(), corpEntity.getCorpBrand(), SysConstant.Crm.CRM_CANCAL);
        return entity;
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
     * 更新
     *
     * @param entity
     * @return
     */

    @Override
    public OrderCancelEntity editInsatll(OrderCancelEntity entity) {
        // InstallOrderEntity installOrderEntity= installOrderService.findByNo(entity.getOrderNo());
        // installOrderEntity.setBeginCacle(SysConstant.IS_DEL_N);
        // installOrderService.edit(installOrderEntity);
        return edit(entity);
    }

    @Override
    public OrderCancelEntity editDelivery(OrderCancelEntity entity) {
        // DeliveryOrderEntity deliveryOrderEntity= deliveryOrderService.findByNo(entity.getOrderNo());
        // deliveryOrderEntity.setBeginCacle(SysConstant.IS_DEL_N);
        // deliveryOrderService.edit(deliveryOrderEntity);
        return edit(entity);
    }


    @Override
    @Transactional
    public OrderCancelEntity edit(OrderCancelEntity entity) {

        OrderCancelEntity oldEntity = findById(entity.getId());
        BeanCopyUtil.beanCopy(entity, oldEntity);
        orderCancelRepository.saveAndFlush(oldEntity);

        List<OrderStepEntity> orderStepEntityList = orderStepService.findByNo(oldEntity.getApplyNo());
        OrderStepEntity orderStep = new OrderStepEntity();
        if (orderStepEntityList != null && orderStepEntityList.size() > 0) {
            orderStep = orderStepEntityList.stream().filter(x -> SysConstant.InstallOrder.CONFIG_FLG_Y.equals(x.getCurrentStepFlg())).findFirst().orElse(null);
        }

        //是否审核（签收）
        if (SysConstant.Advice.RECEIVE_PASS.equals(entity.getVerifyStatus())) {
            //签收通过或非签收步骤
            OrderStepParameter orderStepParameter = new OrderStepParameter();
            orderStepParameter.setOrderNo(oldEntity.getApplyNo());
            orderStepParameter.setResultDesc(entity.getRemark());
            orderStepParameter.setFollowNo(orderStep.getFollowNo());

            OrderStepEntity orderStepEntity = orderStepService.orderStepAdd(orderStepParameter);
            if (SysConstant.OrderStep.LAST_STEP_Y.equals(orderStepEntity.getLastStep())) {

                OrderCarEntity orderCarEntity = orderCarService.findByNo(oldEntity.getOrderNo());
                CorpEntity corpEntity = getCorpEntity();
                logger.error("CRM推送（作废完成）：" + SysConstant.Crm.CRM_RETURN);
                _scrmInfoService.submitPileOrder(null, orderCarEntity.getVinNo(), corpEntity.getCorpBrand(), SysConstant.Crm.CRM_RETURN);
                orderCarEntity.setVinNo(orderCarEntity.getVinNo() + "(已作废)");
                orderCarService.edit(orderCarEntity);

            }

        } else {
            //签收不通过
            OrderStepEntity orderStepEntity = new OrderStepEntity();
            OrderStepParameter orderStepParameter = new OrderStepParameter();
            orderStepParameter.setOrderNo(oldEntity.getApplyNo());
            orderStepParameter.setResultDesc(entity.getRemark());
            orderStepParameter.setFollowNo(orderStep.getFollowNo());

            orderStepEntity = orderStepService.orderStepRejct(orderStepParameter);
            orderStepEntity.setCurrentStepFlg(SysConstant.IS_DEL_N);
            orderStepService.edit(orderStepEntity);

            //baimin 20190826 审核不通过，结束作废，安装订单beginCancel置N
            InstallOrderEntity installOrderEntity = installOrderService.findByNo(oldEntity.getOrderNo());
            if (installOrderEntity != null) {
                installOrderEntity.setBeginCacle(SysConstant.IS_DEL_N);
                installOrderService.edit(installOrderEntity);
            } else {
                DeliveryOrderEntity deliveryOrderEntity = deliveryOrderService.findByNo(oldEntity.getOrderNo());
                if (deliveryOrderEntity != null) {
                    deliveryOrderEntity.setBeginCacle(SysConstant.IS_DEL_N);
                    deliveryOrderService.saveOrderAndStep(deliveryOrderEntity);
                }
            }


            //CRM推送 审核不通过
            OrderCarEntity orderCarEntity = orderCarService.findByNo(oldEntity.getOrderNo());
            CorpEntity corpEntity = getCorpEntity();
            logger.error("CRM推送（作废审核不通过）：" + SysConstant.Crm.CRM_SCHEDULE);
            _scrmInfoService.submitPileOrder(null, orderCarEntity.getVinNo(), corpEntity.getCorpBrand(), SysConstant.Crm.CRM_SCHEDULE);
        }


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
        OrderCancelEntity entity = orderCancelRepository.findById(SysConstant.IS_DEL_N, id);
        if (entity == null) {
            throw new AppException(CustomMessage.NO_DATA_FIND);
        }
        //删除标记
        orderCancelRepository.delete(SysConstant.IS_DEL_Y, id);
        return true;
    }

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    @Override
    public OrderCancelEntity findById(String id) {
        OrderCancelEntity entity = orderCancelRepository.findById(SysConstant.IS_DEL_N, id);
        return entity;
    }

    /**
     * 根据id校验
     *
     * @param id
     * @return
     */
    @Override
    public OrderCancelEntity checkById(String id) {
        OrderCancelEntity entity = orderCancelRepository.findById(SysConstant.IS_DEL_N, id);
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
    public OrderCancelEntity findByNo(String no) {
        OrderCancelEntity entity = orderCancelRepository.findByNo(SysConstant.IS_DEL_N, no);
        return entity;
    }

    @Override
    public OrderCancelEntity findByApplyNo(String applyNo) {
        OrderCancelEntity entity = orderCancelRepository.findByapplyNo(SysConstant.IS_DEL_N, applyNo);
        InstallOrderEntity installOrderEntity=new InstallOrderEntity();
        installOrderEntity=installOrderService.findByNo(entity.getOrderNo());
        if (installOrderEntity!=null){
            entity.setInstallId(installOrderEntity.getId());
        }
        return entity;
    }


}

