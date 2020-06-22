package cn.backend.service.orderauto;

import cn.backend.config.constant.SysConstant;
import cn.backend.dao.primary.orderauto.OrderAutoRepository;
import cn.backend.model.primary.orderauto.OrderAutoEntity;
import cn.backend.model.primary.orderauto.OrderAutoQuery;
import cn.backend.model.primary.orderautodetail.OrderAutoDetailEntity;
import cn.backend.model.primary.supplier.SupplierEntity;
import cn.backend.model.primary.supplier.SupplierQuery;
import cn.backend.model.primary.supplierarea.SupplierAreaEntity;
import cn.backend.model.primary.supplierarea.SupplierAreaQuery;
import cn.backend.service.orderautodetail.IOrderAutoDetailService;
import cn.backend.service.supplier.ISupplierService;
import cn.backend.service.supplierarea.ISupplierAreaService;
import cn.zdwl.common.exception.AppException;
import cn.backend.config.message.CustomMessage;
import cn.backend.service.BaseService;
import cn.backend.service.config.IConfigService;
import cn.zdwl.common.util.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author sunkw
 * @date 2019/08/13
 */
@Service(value = "orderAutoService")
public class OrderAutoService extends BaseService implements IOrderAutoService {

    @Resource
    private OrderAutoRepository orderAutoRepository;

    @Autowired
    private IConfigService configService;

    @Autowired
    private ISupplierAreaService supplierAreaService;

    @Autowired
    private ISupplierService supplierService;

    @Autowired
    private IOrderAutoDetailService orderAutoDetailService;

    /**
     * 分页查询
     *
     * @param orderAutoQuery
     * @return
     */
    @Override
    public Page<OrderAutoEntity> findList(OrderAutoQuery orderAutoQuery) {
        Sort sort = buildSort(orderAutoQuery.getSort());
        Pageable pageable = new PageRequest(orderAutoQuery.getPageNum(), orderAutoQuery.getPageSize(), sort);
        Page<OrderAutoEntity> entityPage = orderAutoRepository.findAll(createSpecification(orderAutoQuery), pageable);
        return entityPage;
    }

    /**
     * 列表查询
     *
     * @param orderAutoQuery
     * @return
     */
    @Override
    public List<OrderAutoEntity> findAll(OrderAutoQuery orderAutoQuery) {
        Sort sort = buildSort(orderAutoQuery.getSort());
        List<OrderAutoEntity> entityList = orderAutoRepository.findAll(createSpecification(orderAutoQuery), sort);
        return entityList;
    }

    /**
     * 创建查询条件
     *
     * @param orderAutoQuery
     * @return
     */
    private Specification createSpecification(OrderAutoQuery orderAutoQuery) {
        return new Specification<OrderAutoEntity>() {

            @Override
            public Predicate toPredicate(Root<OrderAutoEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();

                predicate.add(cb.equal(root.get("corpNo").as(String.class), orderAutoQuery.getCorpNo()));


                predicate.add(cb.equal(root.get("orderType").as(String.class), orderAutoQuery.getOrderType()));

                predicate.add(cb.equal(root.get("cityName").as(String.class), orderAutoQuery.getCityName()));

                //  predicate.add(cb.equal(root.get("supplierNo").as(String.class),orderAutoQuery.getSupplierNo()));
                if (!StringUtils.isEmpty(orderAutoQuery.getSupplierNos())) {

                    Expression<String> exp = root.<String>get("supplierNo");
                    String str = orderAutoQuery.getSupplierNos();
                    String[] strarr = str.split(",");
                    Set<String> stringList = new HashSet<>();
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
    public OrderAutoEntity add(OrderAutoEntity entity) {
        //编号
        entity.setOrderAutoNo(SysConstant.User.PREFIX_NUMBER + IdUtils.getGenerateNumber());
        orderAutoRepository.saveAndFlush(entity);
        return entity;
    }

    /**
     * 更新
     *
     * @param entity
     * @return
     */
    @Override
    public OrderAutoEntity edit(OrderAutoEntity entity) {
        orderAutoRepository.saveAndFlush(entity);
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
        OrderAutoEntity entity = orderAutoRepository.findById(SysConstant.IS_DEL_N, id);
        if (entity == null) {
            throw new AppException(CustomMessage.NO_DATA_FIND);
        }
        //删除标记
        orderAutoRepository.delete(SysConstant.IS_DEL_Y, id);
        return true;
    }

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    @Override
    public OrderAutoEntity findById(String id) {
        OrderAutoEntity entity = orderAutoRepository.findById(SysConstant.IS_DEL_N, id);
        return entity;
    }

    /**
     * 根据id校验
     *
     * @param id
     * @return
     */
    @Override
    public OrderAutoEntity checkById(String id) {
        OrderAutoEntity entity = orderAutoRepository.findById(SysConstant.IS_DEL_N, id);
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
    public OrderAutoEntity findByNo(String no) {
        OrderAutoEntity entity = orderAutoRepository.findByNo(SysConstant.IS_DEL_N, no);
        return entity;
    }

    @Override
    public String findAutoOrderSupplierNo(OrderAutoQuery query) {
        String strSupplier = "";
        SupplierAreaQuery supplierAreaQuery = new SupplierAreaQuery();
        supplierAreaQuery.setCityName(query.getCityName());
        supplierAreaQuery.setCorpNo(query.getCorpNo());
        supplierAreaQuery.setProvinceName(query.getProvinceName());
        List<SupplierAreaEntity> supplierAreaEntities = supplierAreaService.findAll(supplierAreaQuery);
        String suppliers = "";
        if (supplierAreaEntities != null && supplierAreaEntities.size() > 0) {
            //拼接字符串
            for (SupplierAreaEntity supplierAreaEntity : supplierAreaEntities) {
                Map<String, String> checkMap = new HashMap<>();
                if (!checkMap.containsKey(supplierAreaEntity.getSupplierNo())) {
                    suppliers += supplierAreaEntity.getSupplierNo() + ",";
                    checkMap.put(supplierAreaEntity.getSupplierNo(), "");
                }
            }
        }
        //获取安装服务商的订单数量
        OrderAutoQuery orderAutoQuery = new OrderAutoQuery();
        // orderAutoQuery.setProvinceName(query.getProvinceName());
        // orderAutoQuery.setCityName(query.getCityName());
        orderAutoQuery.setCorpNo(query.getCorpNo());
        orderAutoQuery.setSupplierNos(suppliers);
        orderAutoQuery.setOrderType(query.getOrderType());
        List<OrderAutoEntity> orderAutoEntityList = findAll(orderAutoQuery);
        OrderAutoEntity orderAutoEntity;
        if (orderAutoEntityList == null || orderAutoEntityList.size() == 0) {
            SupplierAreaEntity supplierAreaEntity = supplierAreaEntities.stream().findFirst().orElse(null);
            if (supplierAreaEntity != null) {
                strSupplier = supplierAreaEntity.getSupplierNo();
                //新增一条数据
                query.setOrderNum(1);
                query.setSupplierNo(strSupplier);
                orderAutoEntity = createEntity(query);
                add(orderAutoEntity);
                //新增自动派单明细
                query.setOrderAutoNo(orderAutoEntity.getOrderAutoNo());
                createOrderAutoDetail(query);
            }
        } else {
            //获取数量最小的OrderAutoEntity
            // Optional<OrderAutoEntity> ordreAutoOpt = orderAutoEntityList.stream().min(Comparator.comparingInt(OrderAutoEntity::getOrderNum));
            // orderAutoEntity = ordreAutoOpt.get();
            // 20190909 baimin 自动派单修改为按全国比例派单
            SupplierQuery supplierQuery = new SupplierQuery();
            supplierQuery.setSupplierNos(suppliers);
            List<SupplierEntity> supplierEntityList = supplierService.findAll(supplierQuery);
            //派单总数
            int allOrderNum = orderAutoEntityList.stream().mapToInt(OrderAutoEntity::getOrderNum).sum() + 1;
            String supplierNo = "";
            double differProp = 0;
            for (SupplierEntity supplierEntity : supplierEntityList) {
                //比较实际数量与比例相差
                double prop = supplierEntity.getAutoOrderProp();
                int supplierOrderNum = 0;
                OrderAutoEntity orderAutoNumEntity = orderAutoEntityList.stream().filter(e -> supplierEntity.equals(e.getSupplierNo())).findFirst().orElse(null);
                if (orderAutoNumEntity != null) {
                    supplierOrderNum = orderAutoNumEntity.getOrderNum();
                }
                //实际比例
                double actualProp = new BigDecimal(supplierOrderNum)
                        .divide(new BigDecimal(allOrderNum), 2, BigDecimal.ROUND_HALF_UP)
                        .multiply(new BigDecimal(100))
                        .doubleValue();
                //比例相差
                double differ = prop - actualProp;
                if (differ > differProp) {
                    supplierNo = supplierEntity.getSupplierNo();
                    differProp = differ;
                }
            }
            //如果找不到（比例为0），不自动派单
            if(isEmpty(supplierNo)) {
                return supplierNo;
            }
            strSupplier = supplierNo;
            final String finalSupplierNo = supplierNo;
            //新增一条数据
            orderAutoEntity = orderAutoEntityList.stream()
                    .filter(e -> finalSupplierNo.equals(e.getSupplierNo())).findFirst().orElse(null);
            //如果没找到则新建一条
            if (orderAutoEntity != null) {
                orderAutoEntity.setOrderNum(orderAutoEntity.getOrderNum() + 1);
                edit(orderAutoEntity);
            } else {
                query.setOrderNum(1);
                query.setSupplierNo(strSupplier);
                orderAutoEntity = createEntity(query);
                add(orderAutoEntity);
            }
            //新增自动派单明细
            query.setOrderAutoNo(orderAutoEntity.getOrderAutoNo());
            createOrderAutoDetail(query);
        }
        return strSupplier;
    }

    @Override
    @Transactional
    public void addOrderNumSupplierNo(OrderAutoQuery query) {
        //获取安装服务商的订单数量
        OrderAutoQuery orderAutoQuery = new OrderAutoQuery();
        orderAutoQuery.setProvinceName(query.getProvinceName());
        orderAutoQuery.setCityName(query.getCityName());
        orderAutoQuery.setCorpNo(query.getCorpNo());
        orderAutoQuery.setSupplierNos(query.getSupplierNo());
        orderAutoQuery.setOrderType(query.getOrderType());
        List<OrderAutoEntity> orderAutoEntityList = findAll(orderAutoQuery);
        OrderAutoEntity orderAutoEntity;
        if (orderAutoEntityList == null || orderAutoEntityList.size() == 0) {
            //新增一条数据
            query.setOrderNum(1);
            query.setSupplierNo(query.getSupplierNo());
            orderAutoEntity = createEntity(query);
            add(orderAutoEntity);
        } else {
            orderAutoEntity = orderAutoEntityList.stream().findFirst().orElse(null);
            //新增一条数据
            orderAutoEntity.setOrderNum(orderAutoEntity.getOrderNum() + 1);
            edit(orderAutoEntity);
        }
        //新增自动派单明细
        query.setOrderAutoNo(orderAutoEntity.getOrderAutoNo());
        createOrderAutoDetail(query);
    }

    private OrderAutoDetailEntity createOrderAutoDetail(OrderAutoQuery query) {
        OrderAutoDetailEntity orderAutoDetailEntity = new OrderAutoDetailEntity();
        orderAutoDetailEntity.setOrderAutoNo(query.getOrderAutoNo());
        orderAutoDetailEntity.setOrderNo(query.getOrderNo());
        orderAutoDetailEntity.setSupplierNo(query.getSupplierNo());
        return orderAutoDetailService.add(orderAutoDetailEntity);
    }

    @Override
    @Transactional
    public void editOrderNumSupplierNo(OrderAutoQuery query) {
        //检查是否已有记录
        OrderAutoDetailEntity orderAutoDetailEntity = orderAutoDetailService.findByOrderNo(query.getOrderNo());
        if (orderAutoDetailEntity == null) {
            //该订单没有记录
            addOrderNumSupplierNo(query);
        } else {
            //校验服务商是否一致
            if (!query.getSupplierNo().equals(orderAutoDetailEntity.getSupplierNo())) {
                //服务商不一致，需要改变
                //原来的先删除
                orderAutoDetailService.delete(orderAutoDetailEntity.getId());
                //记录减一
                OrderAutoEntity orderAutoEntity = findByNo(orderAutoDetailEntity.getOrderAutoNo());
                orderAutoEntity.setOrderNum(orderAutoEntity.getOrderNum() - 1);
                edit(orderAutoEntity);
                //对应服务商数量加一
                addOrderNumSupplierNo(query);
            }
        }
    }

    @Override
    public void initAutoOrder(String corpNo) {
        OrderAutoQuery query = new OrderAutoQuery();
        query.setCorpNo(corpNo);
        List<OrderAutoEntity> orderAutoEntityList = findAll(query);
        Set<String> ids = orderAutoEntityList.stream().map(OrderAutoEntity::getId).collect(Collectors.toSet());
        deleteBatch(ids);
    }

    @Override
    public boolean deleteBatch(Set<String> ids) {
        orderAutoRepository.deleteBatch(SysConstant.IS_DEL_Y, ids);
        return true;
    }

    private OrderAutoEntity createEntity(OrderAutoQuery query) {
        OrderAutoEntity orderAutoEntity = new OrderAutoEntity();
        orderAutoEntity.setCityName(query.getCityName());
        orderAutoEntity.setProvinceName(query.getProvinceName());
        orderAutoEntity.setOrderNum(query.getOrderNum());
        orderAutoEntity.setCorpNo(query.getCorpNo());
        orderAutoEntity.setSupplierNo(query.getSupplierNo());
        orderAutoEntity.setOrderType(query.getOrderType());
        return orderAutoEntity;
    }

    /**
     * 检查是否需要改变 true需要，false不需要
     *
     * @param query
     * @return
     */
    private boolean checkOrderNumSupplierNo(OrderAutoQuery query) {
        boolean result;
        //检查是否已有记录
        OrderAutoDetailEntity orderAutoDetailEntity = orderAutoDetailService.findByOrderNo(query.getOrderNo());
        if (orderAutoDetailEntity == null) {
            //该订单没有记录
            throw new AppException(CustomMessage.NO_DATA_FIND);
        } else {
            //校验服务商是否一致
            if (query.getSupplierNo().equals(orderAutoDetailEntity.getSupplierNo())) {
                //服务商一致，不需要改变
                result = false;
            } else {
                //服务商不一致，需要改变
                result = true;
            }
        }
        return result;
    }


}

