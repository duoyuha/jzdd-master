package cn.backend.service.orderfee;

import cn.backend.config.constant.SysConstant;
import cn.backend.config.message.CustomMessage;
import cn.backend.dao.primary.orderfee.OrderFeeRepository;
import cn.backend.model.primary.contract.ContractEntity;
import cn.backend.model.primary.contract.ContractQuery;
import cn.backend.model.primary.contract.QContractEntity;
import cn.backend.model.primary.contractdetail.ContractDetailEntity;
import cn.backend.model.primary.contractdetail.QContractDetailEntity;
import cn.backend.model.primary.contractfee.ContractfeeEntity;
import cn.backend.model.primary.contractfee.QContractfeeEntity;
import cn.backend.model.primary.installorder.InstallOrderEntity;
import cn.backend.model.primary.ordercar.OrderCarEntity;
import cn.backend.model.primary.orderfee.OrderFeeEntity;
import cn.backend.model.primary.orderfee.OrderFeeQuery;
import cn.backend.model.primary.vehicle.QVehicleEntity;
import cn.backend.model.primary.vehicle.VehicleEntity;
import cn.backend.model.primary.vehicledetail.QVehicleDetailEntity;
import cn.backend.model.primary.vehicledetail.VehicleDetailEntity;
import cn.backend.model.primary.vehicledetail.VehicleDetailQuery;
import cn.backend.service.BaseService;
import cn.backend.service.config.IConfigService;
import cn.backend.service.contract.IContractService;
import cn.backend.service.contractarea.IContractareaService;
import cn.backend.service.contractdetail.IContractDetailService;
import cn.backend.service.contractfee.IContractfeeService;
import cn.backend.service.delivery.IDeliveryOrderService;
import cn.backend.service.installorder.IInstallOrderService;
import cn.backend.service.ordercar.IOrderCarService;
import cn.backend.service.vehicle.IVehicleService;
import cn.backend.service.vehicledetail.IVehicleDetailService;
import cn.zdwl.common.context.BaseContextHandler;
import cn.zdwl.common.exception.AppException;
import com.querydsl.jpa.impl.JPAQueryFactory;
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

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author sunkw
 * @date 2019/08/06
 */
@Service(value = "orderFeeService")
public class OrderFeeService extends BaseService implements IOrderFeeService {

    protected final Log logger = LogFactory.getLog(getClass());

    @Resource
    private OrderFeeRepository orderFeeRepository;

    @Autowired
    private IConfigService configService;

    @Autowired
    private IVehicleService vehicleService;

    @Autowired
    private IVehicleDetailService vehicleDetailService;

    @Autowired
    private IContractService contractService;

    @Autowired
    private IContractDetailService contractDetailService;

    @Autowired
    private IContractareaService contractareaService;

    @Autowired
    private IContractfeeService contractfeeService;

    @Autowired
    private IOrderCarService orderCarService;

    @Autowired
    private IInstallOrderService installOrderService;

    @Autowired
    private IDeliveryOrderService deliveryOrderService;

    @Autowired
    private EntityManager entityManager;

    //查询工厂实体
    private JPAQueryFactory queryFactory;

    //实例化控制器完成后执行该方法实例化JPAQueryFactory
    @PostConstruct
    public void initFactory() {

        queryFactory = new JPAQueryFactory(entityManager);
    }

    /**
     * 分页查询
     *
     * @param orderFeeQuery
     * @return
     */
    @Override
    public Page<OrderFeeEntity> findList(OrderFeeQuery orderFeeQuery) {
        Sort sort = buildSort(orderFeeQuery.getSort());
        Pageable pageable = new PageRequest(orderFeeQuery.getPageNum(), orderFeeQuery.getPageSize(), sort);
        Page<OrderFeeEntity> entityPage = orderFeeRepository.findAll(createSpecification(orderFeeQuery), pageable);
        return entityPage;
    }

    /**
     * 列表查询
     *
     * @param orderFeeQuery
     * @return
     */
    @Override
    public List<OrderFeeEntity> findAll(OrderFeeQuery orderFeeQuery) {
        Sort sort = buildSort(orderFeeQuery.getSort());
        List<OrderFeeEntity> entityList = orderFeeRepository.findAll(createSpecification(orderFeeQuery), sort);
        return entityList;
    }

    /**
     * 创建查询条件
     *
     * @param orderFeeQuery
     * @return
     */
    private Specification createSpecification(OrderFeeQuery orderFeeQuery) {
        return new Specification<OrderFeeEntity>() {

            @Override
            public Predicate toPredicate(Root<OrderFeeEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();

                //订单编号
                if (!isEmpty(orderFeeQuery.getOrderNo())) {
                    predicate.add(cb.equal(root.get("orderNo").as(String.class), orderFeeQuery.getOrderNo()));
                }

                //公司
                if (!isEmpty(orderFeeQuery.getCorpNo())) {
                    predicate.add(cb.equal(root.get("corpNo").as(String.class), orderFeeQuery.getCorpNo()));
                } else {
                    predicate.add(cb.equal(root.get("corpNo").as(String.class), BaseContextHandler.getCorpNo()));
                }

                //根据订单号查询
                if (!StringUtils.isEmpty(orderFeeQuery.getOrderNos())) {

                    Expression<String> exp = root.<String>get("orderNo");
                    String str = orderFeeQuery.getOrderNos();
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
    public OrderFeeEntity add(OrderFeeEntity entity) {
        configService.convert(entity);
        orderFeeRepository.saveAndFlush(entity);
        return entity;
    }

    /**
     * 更新
     *
     * @param entity
     * @return
     */
    @Override
    public OrderFeeEntity edit(OrderFeeEntity entity) {
        configService.convert(entity);
        orderFeeRepository.saveAndFlush(entity);
        return entity;
    }

    /**
     * 根据安装订单生成资费
     *
     * @param entity
     * @return
     */
    @Override
    public OrderFeeEntity addByInstallOrder(InstallOrderEntity entity) {
        //之前如果有订单资费，统一删除
        OrderFeeQuery orderFeeQuery = new OrderFeeQuery();
        orderFeeQuery.setOrderNo(entity.getOrderNo());
        List<OrderFeeEntity> orderFeeEntityList = findAll(orderFeeQuery);
        Set<String> ids = orderFeeEntityList.stream().map(OrderFeeEntity::getId).collect(Collectors.toSet());
        if (ids.size() > 0) {
            deleteBatch(ids);
        }
        //获取车型
        OrderCarEntity orderCarEntity = orderCarService.findByNo(entity.getOrderNo());
        if (orderCarEntity == null) {
            throw new AppException(CustomMessage.NO_DATA_FIND);
        }
        String vehicleName = orderCarEntity.getCarVehicle();
        VehicleEntity vehicleEntity = vehicleService.findByNameAndCorp(vehicleName, entity.getCorpNo());
        if (vehicleEntity == null) {
            throw new AppException(CustomMessage.NO_DATA_FIND);
        }

        String vehicleDetailName = orderCarEntity.getVehicleDetail();

        if (isEmpty(vehicleDetailName)) {
            throw new AppException(CustomMessage.NO_DATA_FIND);
        }

        VehicleDetailQuery vehicleDetailQuery = new VehicleDetailQuery();
        vehicleDetailQuery.setVehicleNo(vehicleEntity.getVehicleNo());
        vehicleDetailQuery.setDetailName(vehicleDetailName);
        List<VehicleDetailEntity> vehicleDetailEntityList = vehicleDetailService.findAll(vehicleDetailQuery);

        if (vehicleDetailEntityList == null || vehicleDetailEntityList.size() > 0) {
            throw new AppException(CustomMessage.NO_DATA_FIND);
        }

        //获取合同
        ContractQuery contractQuery = new ContractQuery();

        contractQuery.setSupplierNo(entity.getSupplierNo());
        contractQuery.setEffectiveFlg(SysConstant.InstallOrder.CONFIG_FLG_Y);

        List<ContractEntity> contractEntityList = contractService.findAll(contractQuery);

        if (contractEntityList == null || contractEntityList.size() == 0) {
            throw new AppException(CustomMessage.NO_DATA_FIND);
        }

        ContractEntity contractEntity = contractEntityList.stream().findFirst().orElse(null);

        //baimin
        //根据合同号获取合同详细
        List<ContractDetailEntity> contractDetailEntityList = contractDetailService.findByNo(contractEntity.getContractNo());
        if (contractDetailEntityList == null || contractDetailEntityList.size() == 0) {
            throw new AppException(CustomMessage.NO_DATA_FIND);
        }
        //根据车型明细名称筛选出合同明细
        //TODO 车型明细建议改名，ContractDetailEntity中detailNos（车型明细编码）和detailNo（合同明细编码）容易混淆，且车型明细没有no只有名字
        ContractDetailEntity contractDetailEntity = contractDetailEntityList.stream()
                .filter(e -> e.getDetailNos().contains(vehicleDetailName)).findFirst().orElse(null);
        //获取计价策略
        // ContractEntity contractEntity = contractService.findByVehicleAndPile(vehicleDetailEntity.getDetailNo(), entity.getPileModel());
        // if (contractEntity == null) {
        //     throw new AppException(CustomMessage.NO_DATA_FIND);
        // }
        //生成订单资费
        OrderFeeEntity orderFeeEntity = new OrderFeeEntity();
        orderFeeEntity.setCorpNo(entity.getCorpNo());
        orderFeeEntity.setOrderNo(entity.getOrderNo());
        orderFeeEntity.setSupplierNo(entity.getSupplierNo());
        orderFeeEntity.setSupplierName(entity.getSupplierName());
        //检查是否在有效期内
        Date beginDate = contractEntity.getBeginDate();
        Date EndDate = contractEntity.getEndDate();
        Date nowDate = new Date();
        if (nowDate.before(beginDate) || nowDate.after(EndDate)) {
            orderFeeEntity.setSettleAmt(new BigDecimal(0));
        } else {
            //计算资费
            // ContractDetailEntity contractDetailEntity = contractEntity.getDetailEntityList().stream().findFirst().orElse(null);
            if (contractDetailEntity == null) {
                throw new AppException(CustomMessage.NO_DATA_FIND);
            }
            // List<ContractfeeEntity> contractfeeEntityList = contractDetailEntity.getContractfeeEntityList();
            //根据合同明细名称查找资费
            List<ContractfeeEntity> contractfeeEntityList = contractfeeService.findByNo(contractDetailEntity.getDetailNo());
            BigDecimal orderFee = new BigDecimal(0);
            for (ContractfeeEntity item : contractfeeEntityList) {
                orderFee = orderFee.add(item.getFeeAmt());
            }
            orderFeeEntity.setSettleAmt(orderFee);
        }
        return add(orderFeeEntity);
    }

    /**
     * 批量保存
     *
     * @param ids
     * @return
     */
    @Override
    public boolean deleteBatch(Set<String> ids) {
        orderFeeRepository.deleteBatch(SysConstant.IS_DEL_Y, ids);
        return true;
    }

    /**
     * 删除（逻辑删除）
     *
     * @param id
     * @return
     */
    @Override
    public boolean delete(String id) {
        OrderFeeEntity entity = orderFeeRepository.findById(SysConstant.IS_DEL_N, id);
        if (entity == null) {
            throw new AppException(CustomMessage.NO_DATA_FIND);
        }
        //删除标记
        orderFeeRepository.delete(SysConstant.IS_DEL_Y, id);
        return true;
    }

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    @Override
    public OrderFeeEntity findById(String id) {
        OrderFeeEntity entity = orderFeeRepository.findById(SysConstant.IS_DEL_N, id);
        return entity;
    }

    /**
     * 根据id校验
     *
     * @param id
     * @return
     */
    @Override
    public OrderFeeEntity checkById(String id) {
        OrderFeeEntity entity = orderFeeRepository.findById(SysConstant.IS_DEL_N, id);
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
    public OrderFeeEntity findByNo(String no) {
        OrderFeeEntity entity = orderFeeRepository.findByOrderNo(SysConstant.IS_DEL_N, no);
        return entity;
    }

    /**
     * 根据安装订单生成资费
     *
     * @param query
     * @return
     */
    @Override
    public OrderFeeEntity addOrderFee(OrderFeeQuery query) {
        List<OrderFeeEntity> orderFeeEntityList = findAll(query);
        Set<String> ids = orderFeeEntityList.stream().map(OrderFeeEntity::getId).collect(Collectors.toSet());
        if (ids.size() > 0) {
            deleteBatch(ids);
        }
        OrderCarEntity orderCarEntity = orderCarService.findByNo(query.getOrderNo());
        if (orderCarEntity == null) {
            logger.error("orderCarEntity为空");
            throw new AppException(CustomMessage.NO_DATA_FIND);
        }
        logger.info("orderCarEntity: " + orderCarEntity);
        //获取车型
        QVehicleEntity qVehicleEntity = QVehicleEntity.vehicleEntity;
        QVehicleDetailEntity qVehicleDetailEntity = QVehicleDetailEntity.vehicleDetailEntity;
        List<VehicleDetailEntity> vehicleDetailEntityList = queryFactory
                .select(qVehicleDetailEntity)
                //查询积分总数
                .from(qVehicleEntity, qVehicleDetailEntity)
                .where(qVehicleEntity.vehicleNo.eq(qVehicleDetailEntity.vehicleNo)
                        .and(
                                qVehicleEntity.corpNo.eq(query.getCorpNo())
                        )
                        .and(
                                qVehicleEntity.vehicleName.eq(orderCarEntity.getCarVehicle())
                        )
                        .and(
                                qVehicleDetailEntity.detailName.eq(orderCarEntity.getVehicleDetail())
                        )
                        .and(
                                qVehicleEntity.isDel.eq(SysConstant.IS_DEL_N)
                        )
                )
                .fetch();
        if (vehicleDetailEntityList == null || vehicleDetailEntityList.size() == 0) {
            logger.error("vehicleDetailEntityList为空");
            throw new AppException(CustomMessage.VEHICLE_DETAIL_NOT_FOUND);
        }
        VehicleDetailEntity vehicleDetailEntity = vehicleDetailEntityList.stream().findFirst().orElse(null);
        logger.info("vehicleDetailEntity: " + vehicleDetailEntity);
        //获取合同数据
        QContractEntity qContractEntity = QContractEntity.contractEntity;
        QContractDetailEntity qContractDetailEntity = QContractDetailEntity.contractDetailEntity;
        // QContractareaEntity qContractareaEntity = QContractareaEntity.contractareaEntity;
        QContractfeeEntity qContractfeeEntity = QContractfeeEntity.contractfeeEntity;
        List<ContractfeeEntity> contractfeeEntityList = queryFactory
                .select(qContractfeeEntity
                )
                //查询积分总数
                .from(qContractEntity, qContractDetailEntity, qContractfeeEntity)
                .where(qContractEntity.contractNo.eq(qContractDetailEntity.contractNo)
                        .and(
                                qContractEntity.corpNo.eq(query.getCorpNo())
                        )
                        .and(
                                qContractDetailEntity.detailNo.eq(qContractfeeEntity.detailNo)
                        )

                        .and(
                                qContractEntity.isDel.eq(SysConstant.IS_DEL_N)
                        )
                        .and(
                                qContractDetailEntity.isDel.eq(SysConstant.IS_DEL_N)
                        )
                        .and(
                                qContractfeeEntity.isDel.eq(SysConstant.IS_DEL_N)
                        )
                        .and(
                                qContractDetailEntity.detailNos.contains(vehicleDetailEntity.getDetailNo())
                        )
                        .and(
                                qContractEntity.beginDate.before(query.getOrderTime())
                        )
                        .and(
                                qContractEntity.endDate.after(query.getOrderTime())
                        )
                        //20190916 baimin 计价策略加入日期，同样筛选
                        // .and(
                        //         qContractDetailEntity.detailBeginDate.before(query.getOrderTime())
                        // )
                        // .and(
                        //         qContractDetailEntity.detailEndDate.after(query.getOrderTime())
                        // )
                        .and(
                                qContractfeeEntity.feeType.eq(query.getOrderType())
                        )
                        .and(
                                qContractEntity.supplierNo.eq(query.getSupplierNo())
                        )
                        .and(
                                qContractDetailEntity.pileModels.contains(query.getPileModel())
                        )
                )
                .fetch();
        if (contractfeeEntityList == null || contractfeeEntityList.size() == 0) {
            logger.error("contractfeeEntityList为空");
            throw new AppException(CustomMessage.CONTRACT_FEE_NOT_FOUND);
        }
        //判断安装是否需要立桩费
        boolean needPost = false;
        switch (query.getOrderType()) {
            case SysConstant.WorkFollow.CONFIG_WORKFOLLOW_INSTALL:
                InstallOrderEntity installOrderEntity = installOrderService.findByNo(query.getOrderNo());
                if (installOrderEntity == null) {
                    logger.error("installOrderEntity为空");
                    throw new AppException(CustomMessage.NO_DATA_FIND);
                }
                if (SysConstant.InstallOrder.PILE_TYPE_POST.equals(installOrderEntity.getPileType())) {
                    needPost = true;
                }
                break;
            // case SysConstant.WorkFollow.CONFIG_WORKFOLLOW_DELIVERY:
            //     DeliveryOrderEntity deliveryOrderEntity = deliveryOrderService.findByNo(query.getOrderNo());
            //     if (deliveryOrderEntity == null) {
            //         throw new AppException(CustomMessage.NO_DATA_FIND);
            //     }
            //     pileType = deliveryOrderEntity.getPileType();
            //     break;
        }
        if (!needPost) {
            contractfeeEntityList = contractfeeEntityList.stream()
                    .filter(e -> !SysConstant.Contract.FEE_CODE_POST.equals(e.getFeeCode())).collect(Collectors.toList());
        }
        OrderFeeEntity orderFeeEntity = new OrderFeeEntity();
        orderFeeEntity.setCorpNo(query.getCorpNo());
        orderFeeEntity.setOrderNo(query.getOrderNo());
        orderFeeEntity.setSupplierNo(query.getSupplierNo());
        orderFeeEntity.setSupplierName(query.getSupplierName());
        BigDecimal orderFee = new BigDecimal(0);
        for (ContractfeeEntity item : contractfeeEntityList) {
            logger.info("ContractfeeEntity: " + item);
            orderFee = orderFee.add(item.getFeeAmt());
        }
        orderFeeEntity.setSettleAmt(orderFee);
        return add(orderFeeEntity);
    }


}

