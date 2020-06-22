package cn.backend.service.cancel;

import cn.backend.config.constant.SysConstant;
import cn.backend.config.message.CustomMessage;
import cn.backend.dao.primary.viewcancel.ViewCancelRepository;
import cn.backend.model.primary.installorder.InstallOrderQuery;
import cn.backend.model.primary.ordercancel.OrderCancelEntity;
import cn.backend.model.primary.ordercancel.OrderCancelQuery;
import cn.backend.model.primary.orderstep.OrderStepEntity;
import cn.backend.model.primary.user.UserEntity;
import cn.backend.model.primary.viewcancel.ViewCancelEntity;
import cn.backend.model.primary.viewcancel.ViewCancelQuery;
import cn.backend.model.primary.viewdeliveryorder.ViewDeliveryOrderEntity;
import cn.backend.model.primary.viewdeliveryorder.ViewDeliveryOrderQuery;
import cn.backend.model.primary.viewinstallorder.ViewInstallOrderEntity;
import cn.backend.service.BaseService;
import cn.backend.service.config.IConfigService;
import cn.backend.service.delivery.IDeliveryOrderService;
import cn.backend.service.installorder.IInstallOrderService;
import cn.backend.service.installorderview.IInstallOrderViewService;
import cn.backend.service.ordercancel.IOrderCancelService;
import cn.backend.service.orderstep.IOrderStepService;
import cn.backend.service.user.IUserService;
import cn.backend.service.viewdeliveryorder.IViewDeliveryOrderService;
import cn.zdwl.common.context.BaseContextHandler;
import cn.zdwl.common.exception.AppException;
import org.apache.commons.lang.StringUtils;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author sunkw
 * @date 2019/07/16
 */
@Service(value = "cancelService")
public class CancelService extends BaseService implements ICancelService {

    @Resource
    private ViewCancelRepository viewCancelRepository;

    @Autowired
    private IUserService userService;

    @Autowired
    private IConfigService configService;

    @Autowired
    private IViewDeliveryOrderService viewDeliveryOrderService;

    @Autowired
    private IInstallOrderViewService installOrderViewService;

    @Autowired
    private IInstallOrderService installOrderService;

    @Autowired
    private IDeliveryOrderService deliveryOrderService;

    @Autowired
    private IOrderStepService orderStepService;

    @Autowired
    private IOrderCancelService orderCancelService;


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
     * 分页查询
     *@author  jinjianbo
     * @param viewCancelQuery
     * @return
     *
     * 修改了installOrderService和deliveryOrderService的订单逻辑，解决根据vin号查询出两个结果的问题
     */
    @Override
    public Page<ViewCancelEntity> findList(ViewCancelQuery viewCancelQuery) {
        Sort sort = buildSort(viewCancelQuery.getSort());
        Pageable pageable = new PageRequest(viewCancelQuery.getPageNum(), viewCancelQuery.getPageSize(), sort);
        Page<ViewCancelEntity> entityPage = viewCancelRepository.findAll(createSpecification(viewCancelQuery), pageable);
        for (ViewCancelEntity viewCancelEntity : entityPage) {
            if (installOrderService.findByNo(viewCancelEntity.getOrderNo()) != null) {
                viewCancelEntity.setOrderType(SysConstant.WorkFollow.CONFIG_WORKFOLLOW_INSTALL);
                viewCancelEntity.setInstallId(installOrderService.findByNo(viewCancelEntity.getOrderNo()).getId());
            }
            if (deliveryOrderService.findByNo(viewCancelEntity.getOrderNo()) != null) {
                viewCancelEntity.setOrderType(SysConstant.WorkFollow.CONFIG_WORKFOLLOW_DELIVERY);
            }
        }
        return entityPage;
    }

    /**
     * 列表查询
     *
     * @param viewCancelQuery
     * @return
     */
    @Override
    public List<ViewCancelEntity> findAll(ViewCancelQuery viewCancelQuery) {
        Sort sort = buildSort(viewCancelQuery.getSort());
        List<ViewCancelEntity> entityList = viewCancelRepository.findAll(createSpecification(viewCancelQuery), sort);
        return entityList;
    }

    /**
     * 创建查询条件
     *
     * @param viewCancelQuery
     * @return
     */
    private Specification createSpecification(ViewCancelQuery viewCancelQuery) {
        return new Specification<ViewCancelEntity>() {

            @Override
            public Predicate toPredicate(Root<ViewCancelEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();


                if (SysConstant.Common.LIST_ALL_FLG_NO.equals(viewCancelQuery.getAllOrderFlg())) {
                    if (!isEmpty(viewCancelQuery.getUserTypeWeb())) {
                        predicate.add(cb.like(root.get("positionCodes").as(String.class), "%" + viewCancelQuery.getUserTypeWeb() + "%"));
                    }
                    //是否完成
                    predicate.add(cb.equal(root.get("finishFlg").as(String.class), SysConstant.InstallOrder.CONFIG_FLG_N));
                }

                //职位
                if (SysConstant.Common.LIST_ALL_FLG_NO.equals(viewCancelQuery.getAllOrderFlg())) {
                    if (!StringUtils.isEmpty(viewCancelQuery.getPositionCode())) {
                        predicate.add(cb.like(root.get("positionCodes").as(String.class), "%" + viewCancelQuery.getPositionCode() + "%"));
                    }
                }

                //公司
                if (!isEmpty(viewCancelQuery.getCorpNo())) {
                    predicate.add(cb.equal(root.get("corpNo").as(String.class), viewCancelQuery.getCorpNo()));
                } else {
                    predicate.add(cb.equal(root.get("corpNo").as(String.class), BaseContextHandler.getCorpNo()));
                }

                //订单编号
                if (!StringUtils.isEmpty(viewCancelQuery.getOrderNo())) {
                    predicate.add(cb.equal(root.get("orderNo").as(String.class), viewCancelQuery.getOrderNo()));
                }

                if (!isEmpty(viewCancelQuery.getDealerName())) {
                    predicate.add(cb.like(root.get("dealerName").as(String.class), "%" + viewCancelQuery.getDealerName() + "%"));
                }

                if (!isEmpty(viewCancelQuery.getFinishFlg())) {
                    predicate.add(cb.equal(root.get("finishFlg").as(String.class), viewCancelQuery.getFinishFlg()));
                }

                //VIN车架号
                if (!StringUtils.isEmpty(viewCancelQuery.getVinNo())) {
                    predicate.add(cb.like(root.get("vinNo").as(String.class), "%" + viewCancelQuery.getVinNo() + "%"));
                }

                //作废时间
                if (!org.springframework.util.StringUtils.isEmpty(viewCancelQuery.getStartTime())) {
                    //大于或等于创建时间
                    predicate.add(cb.greaterThanOrEqualTo(root.get("createdWhen").as(Date.class), viewCancelQuery.getStartTime()));
                }
                if (!org.springframework.util.StringUtils.isEmpty(viewCancelQuery.getEndTime())) {
                    //小于或等于修改时间
                    predicate.add(cb.lessThanOrEqualTo(root.get("createdWhen").as(Date.class), viewCancelQuery.getEndTime()));
                }


                //服务商
                UserEntity user = userService.findById(BaseContextHandler.getUserId());
                if (!isEmpty(viewCancelQuery.getSupplierNo())) {
                    predicate.add(cb.equal(root.get("supplierNo").as(String.class), viewCancelQuery.getSupplierNo()));
                }

                if (!isEmpty(user.getSupplierNo())) {
                    predicate.add(cb.equal(root.get("supplierNo").as(String.class), user.getSupplierNo()));
                }

                //流程编号
                if (!StringUtils.isEmpty(viewCancelQuery.getFollowCode())) {
                    predicate.add(cb.equal(root.get("followCode").as(String.class), viewCancelQuery.getFollowCode()));
                }

                //流程编码
                if (!StringUtils.isEmpty(viewCancelQuery.getFollowNo())) {
                    predicate.add(cb.equal(root.get("followNo").as(String.class), viewCancelQuery.getFollowNo()));
                }

                //步骤矩阵
                if (!org.springframework.util.StringUtils.isEmpty(viewCancelQuery.getStepMatrix())) {
                    predicate.add(cb.equal(root.get("stepMatrix").as(String.class), viewCancelQuery.getStepMatrix()));
                }

                //是否是最新步骤
                if (!StringUtils.isEmpty(viewCancelQuery.getCurrentStepFlg())) {
                    predicate.add(cb.equal(root.get("currentStepFlg").as(String.class), viewCancelQuery.getCurrentStepFlg()));
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
    public ViewCancelEntity add(ViewCancelEntity entity) {
        configService.convert(entity);
        viewCancelRepository.saveAndFlush(entity);
        return entity;
    }

    /**
     * 更新
     *
     * @param entity
     * @return
     */
    @Override
    public ViewCancelEntity edit(ViewCancelEntity entity) {
        configService.convert(entity);
        viewCancelRepository.saveAndFlush(entity);
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
        ViewCancelEntity entity = viewCancelRepository.findById(SysConstant.IS_DEL_N, id);
        if (entity == null) {
            throw new AppException(CustomMessage.NO_DATA_FIND);
        }
        //删除标记
        viewCancelRepository.delete(SysConstant.IS_DEL_Y, id);
        return true;
    }

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    @Override
    public ViewCancelEntity findById(String id) {
        ViewCancelEntity entity = viewCancelRepository.findById(SysConstant.IS_DEL_N, id);
        return entity;
    }

    /**
     * 根据id校验
     *
     * @param id
     * @return
     */
    @Override
    public ViewCancelEntity checkById(String id) {
        ViewCancelEntity entity = viewCancelRepository.findById(SysConstant.IS_DEL_N, id);
        if (entity == null) {
            throw new AppException(CustomMessage.NO_DATA_FIND);
        }
        return entity;
    }

    @Override
    public List<OrderStepEntity> findByVin(String vin) {
//        ViewCancelQuery viewCancelQuery = new ViewCancelQuery();
//        viewCancelQuery.setVinNo(vin);
//        List<ViewCancelEntity> viewCancelEntityList = findAll(viewCancelQuery);
        List<OrderStepEntity> orderStepEntityList=new ArrayList<>();
        ViewDeliveryOrderQuery deliveryOrderQuery=new ViewDeliveryOrderQuery();
        deliveryOrderQuery.setVinNo(vin);
        InstallOrderQuery installOrderQuery=new InstallOrderQuery();
        installOrderQuery.setVinNo(vin);
        List<ViewDeliveryOrderEntity> viewDeliveryOrderEntityList=viewDeliveryOrderService.findAll(deliveryOrderQuery);
        List<ViewInstallOrderEntity> viewInstallOrderEntityList=installOrderViewService.findAll(installOrderQuery);
        if (viewDeliveryOrderEntityList.size()>0){
            for (ViewDeliveryOrderEntity item : viewDeliveryOrderEntityList) {
                OrderCancelQuery cancelQuery = new OrderCancelQuery();
                cancelQuery.setOrderNo(item.getOrderNo());
                List<OrderCancelEntity> cancelEntityList = orderCancelService.findAll(cancelQuery);
                for (OrderCancelEntity orderCancelEntity : cancelEntityList) {
                    orderStepEntityList = orderStepService.findByNo(orderCancelEntity.getApplyNo());
                    //viewCancelEntity.setOrderStepEntityList(orderStepEntityList);
                }

            }
        }else if (viewInstallOrderEntityList.size()>0){
            for (ViewInstallOrderEntity item : viewInstallOrderEntityList) {
                OrderCancelQuery cancelQuery = new OrderCancelQuery();
                cancelQuery.setOrderNo(item.getOrderNo());
                List<OrderCancelEntity> cancelEntityList = orderCancelService.findAll(cancelQuery);
                for (OrderCancelEntity orderCancelEntity : cancelEntityList) {
                    orderStepEntityList = orderStepService.findByNo(orderCancelEntity.getApplyNo());
                    //viewCancelEntity.setOrderStepEntityList(orderStepEntityList);
                }

            }
        }

        return orderStepEntityList;
    }



}

