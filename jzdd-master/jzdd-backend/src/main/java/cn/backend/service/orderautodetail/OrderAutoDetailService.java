package cn.backend.service.orderautodetail;

import cn.backend.config.constant.SysConstant;
import cn.backend.dao.primary.orderautodetail.OrderAutoDetailRepository;
import cn.backend.model.primary.orderautodetail.OrderAutoDetailEntity;
import cn.backend.model.primary.orderautodetail.OrderAutoDetailQuery;
import cn.zdwl.common.exception.AppException;
import cn.backend.config.message.CustomMessage;
import cn.backend.service.BaseService;
import cn.backend.service.config.IConfigService;
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

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author sunkw
 * @date 2019/08/14
 */
@Service(value = "orderAutoDetailService")
public class OrderAutoDetailService extends BaseService implements IOrderAutoDetailService {

    @Resource
    private OrderAutoDetailRepository orderAutoDetailRepository;

    @Autowired
    private IConfigService configService;

    /**
     * 分页查询
     *
     * @param orderAutoDetailQuery
     * @return
     */
    @Override
    public Page<OrderAutoDetailEntity> findList(OrderAutoDetailQuery orderAutoDetailQuery) {
        Sort sort = buildSort(orderAutoDetailQuery.getSort());
        Pageable pageable = new PageRequest(orderAutoDetailQuery.getPageNum(), orderAutoDetailQuery.getPageSize(), sort);
        Page<OrderAutoDetailEntity> entityPage = orderAutoDetailRepository.findAll(createSpecification(orderAutoDetailQuery), pageable);
        return entityPage;
    }

    /**
     * 列表查询
     *
     * @param orderAutoDetailQuery
     * @return
     */
    @Override
    public List<OrderAutoDetailEntity> findAll(OrderAutoDetailQuery orderAutoDetailQuery) {
        Sort sort = buildSort(orderAutoDetailQuery.getSort());
        List<OrderAutoDetailEntity> entityList = orderAutoDetailRepository.findAll(createSpecification(orderAutoDetailQuery), sort);
        return entityList;
    }

    /**
     * 创建查询条件
     *
     * @param orderAutoDetailQuery
     * @return
     */
    private Specification createSpecification(OrderAutoDetailQuery orderAutoDetailQuery) {
        return new Specification<OrderAutoDetailEntity>() {

            @Override
            public Predicate toPredicate(Root<OrderAutoDetailEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();

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
    public OrderAutoDetailEntity add(OrderAutoDetailEntity entity) {
        orderAutoDetailRepository.saveAndFlush(entity);
        return entity;
    }

    /**
     * 更新
     *
     * @param entity
     * @return
     */
    @Override
    public OrderAutoDetailEntity edit(OrderAutoDetailEntity entity) {
        orderAutoDetailRepository.saveAndFlush(entity);
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
        OrderAutoDetailEntity entity = orderAutoDetailRepository.findById(SysConstant.IS_DEL_N, id);
        if (entity == null) {
            throw new AppException(CustomMessage.NO_DATA_FIND);
        }
        //删除标记
        orderAutoDetailRepository.delete(SysConstant.IS_DEL_Y, id);
        return true;
    }

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    @Override
    public OrderAutoDetailEntity findById(String id) {
        OrderAutoDetailEntity entity = orderAutoDetailRepository.findById(SysConstant.IS_DEL_N, id);
        return entity;
    }

    /**
     * 根据id校验
     *
     * @param id
     * @return
     */
    @Override
    public OrderAutoDetailEntity checkById(String id) {
        OrderAutoDetailEntity entity = orderAutoDetailRepository.findById(SysConstant.IS_DEL_N, id);
        if (entity == null) {
            throw new AppException(CustomMessage.NO_DATA_FIND);
        }
        return entity;
    }

    /**
     * 根据编号查找
     *
     * @param orderNo
     * @return
     */
    @Override
    public OrderAutoDetailEntity findByOrderNo(String orderNo) {
        OrderAutoDetailEntity entity = orderAutoDetailRepository.findByOrderNo(SysConstant.IS_DEL_N, orderNo);
        return entity;
    }


}

