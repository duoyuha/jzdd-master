package cn.backend.service.ordergrade;

import cn.backend.config.constant.SysConstant;
import cn.backend.config.message.CustomMessage;
import cn.backend.dao.primary.ordergrade.OrderGradeRepository;
import cn.backend.model.primary.ordergrade.OrderGradeEntity;
import cn.backend.model.primary.ordergrade.OrderGradeQuery;
import cn.backend.service.BaseService;
import cn.backend.service.config.IConfigService;
import cn.zdwl.common.context.BaseContextHandler;
import cn.zdwl.common.exception.AppException;
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
import java.util.ArrayList;
import java.util.List;

/**
 * @author sunkw
 * @date 2019/07/23
 */
@Service(value = "orderGradeService")
public class OrderGradeService extends BaseService implements IOrderGradeService {

    @Resource
    private OrderGradeRepository orderGradeRepository;

    @Autowired
    private IConfigService configService;

    /**
     * 分页查询
     *
     * @param orderGradeQuery
     * @return
     */
    @Override
    public Page<OrderGradeEntity> findList(OrderGradeQuery orderGradeQuery) {
        Sort sort = buildSort(orderGradeQuery.getSort());
        Pageable pageable = new PageRequest(orderGradeQuery.getPageNum(), orderGradeQuery.getPageSize(), sort);
        Page<OrderGradeEntity> entityPage = orderGradeRepository.findAll(createSpecification(orderGradeQuery), pageable);
        return entityPage;
    }

    /**
     * 列表查询
     *
     * @param orderGradeQuery
     * @return
     */
    @Override
    public List<OrderGradeEntity> findAll(OrderGradeQuery orderGradeQuery) {
        Sort sort = buildSort(orderGradeQuery.getSort());
        List<OrderGradeEntity> entityList = orderGradeRepository.findAll(createSpecification(orderGradeQuery), sort);
        return entityList;
    }

    /**
     * 创建查询条件
     *
     * @param orderGradeQuery
     * @return
     */
    private Specification createSpecification(OrderGradeQuery orderGradeQuery) {
        return new Specification<OrderGradeEntity>() {

            @Override
            public Predicate toPredicate(Root<OrderGradeEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();

                if (!isEmpty(orderGradeQuery.getOrderNo())) {
                    predicate.add(cb.equal(root.get("orderNo").as(String.class), orderGradeQuery.getOrderNo()));
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
    public OrderGradeEntity add(OrderGradeEntity entity) {
        //满意度编码
        entity.setGradeNo(SysConstant.orderGrade.PREFIX_NUMBER + IdUtils.getGenerateNumber());
        entity.setCorpNo(BaseContextHandler.getCorpNo());
        configService.convert(entity);
        orderGradeRepository.saveAndFlush(entity);
        return entity;
    }

    /**
     * 更新
     *
     * @param entity
     * @return
     */
    @Override
    public OrderGradeEntity edit(OrderGradeEntity entity) {
        configService.convert(entity);
        orderGradeRepository.saveAndFlush(entity);
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
        OrderGradeEntity entity = orderGradeRepository.findById(SysConstant.IS_DEL_N, id);
        if (entity == null) {
            throw new AppException(CustomMessage.NO_DATA_FIND);
        }
        //删除标记
        orderGradeRepository.delete(SysConstant.IS_DEL_Y, id);
        return true;
    }

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    @Override
    public OrderGradeEntity findById(String id) {
        OrderGradeEntity entity = orderGradeRepository.findById(SysConstant.IS_DEL_N, id);
        return entity;
    }

    /**
     * 根据id校验
     *
     * @param id
     * @return
     */
    @Override
    public OrderGradeEntity checkById(String id) {
        OrderGradeEntity entity = orderGradeRepository.findById(SysConstant.IS_DEL_N, id);
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
    public OrderGradeEntity findByNo(String no) {
        OrderGradeEntity entity = orderGradeRepository.findByNo(SysConstant.IS_DEL_N, no);
        return entity;
    }

    /**
     * 根据订单编号查找
     *
     * @param no
     * @return
     */
    @Override
    public OrderGradeEntity findByOrderNo(String no) {
        OrderGradeEntity entity = orderGradeRepository.findByOrderNo(SysConstant.IS_DEL_N, no);
        return entity;
    }


}

