package cn.backend.service.orderattach;

import cn.backend.config.constant.SysConstant;
import cn.backend.config.message.CustomMessage;
import cn.backend.dao.primary.orderattach.OrderAttachRepository;
import cn.backend.model.primary.orderattach.OrderAttachEntity;
import cn.backend.model.primary.orderattach.OrderAttachQuery;
import cn.backend.service.BaseService;
import cn.backend.service.config.IConfigService;
import cn.zdwl.common.exception.AppException;
import cn.zdwl.common.file.UploadFile;
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
@Service(value = "orderAttachService")
public class OrderAttachService extends BaseService implements IOrderAttachService {

    @Resource
    private OrderAttachRepository orderAttachRepository;

    @Autowired
    private IConfigService configService;

    /**
     * 分页查询
     *
     * @param orderAttachQuery
     * @return
     */
    @Override
    public Page<OrderAttachEntity> findList(OrderAttachQuery orderAttachQuery) {
        Sort sort = buildSort(orderAttachQuery.getSort());
        Pageable pageable = new PageRequest(orderAttachQuery.getPageNum(), orderAttachQuery.getPageSize(), sort);
        Page<OrderAttachEntity> entityPage = orderAttachRepository.findAll(createSpecification(orderAttachQuery), pageable);
        return entityPage;
    }

    /**
     * 列表查询
     *
     * @param orderAttachQuery
     * @return
     */
    @Override
    public List<OrderAttachEntity> findAll(OrderAttachQuery orderAttachQuery) {
        Sort sort = buildSort(orderAttachQuery.getSort());
        List<OrderAttachEntity> entityList = orderAttachRepository.findAll(createSpecification(orderAttachQuery), sort);
        return entityList;
    }

    /**
     * 创建查询条件
     *
     * @param orderAttachQuery
     * @return
     */
    private Specification createSpecification(OrderAttachQuery orderAttachQuery) {
        return new Specification<OrderAttachEntity>() {

            @Override
            public Predicate toPredicate(Root<OrderAttachEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();

                //订单号
                if (!isEmpty(orderAttachQuery.getOrderNo())) {
                    predicate.add(cb.equal(root.get("orderNo").as(String.class), orderAttachQuery.getOrderNo()));
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
    public OrderAttachEntity add(OrderAttachEntity entity) {
        entity.setAttachNo(SysConstant.orderAttach.PREFIX_NUMBER + IdUtils.getGenerateNumber());
        configService.convert(entity);
        orderAttachRepository.saveAndFlush(entity);
        return entity;
    }

    /**
     * 更新
     *
     * @param entity
     * @return
     */
    @Override
    public OrderAttachEntity edit(OrderAttachEntity entity) {
        configService.convert(entity);
        orderAttachRepository.saveAndFlush(entity);
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
        OrderAttachEntity entity = orderAttachRepository.findById(SysConstant.IS_DEL_N, id);
        if (entity == null) {
            throw new AppException(CustomMessage.NO_DATA_FIND);
        }
        //删除标记
        orderAttachRepository.delete(SysConstant.IS_DEL_Y, id);
        return true;
    }

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    @Override
    public OrderAttachEntity findById(String id) {
        OrderAttachEntity entity = orderAttachRepository.findById(SysConstant.IS_DEL_N, id);
        return entity;
    }

    /**
     * 根据id校验
     *
     * @param id
     * @return
     */
    @Override
    public OrderAttachEntity checkById(String id) {
        OrderAttachEntity entity = orderAttachRepository.findById(SysConstant.IS_DEL_N, id);
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
    public OrderAttachEntity findByNo(String no) {
        OrderAttachEntity entity = orderAttachRepository.findByNo(SysConstant.IS_DEL_N, no);
        return entity;
    }

    /**
     * 根据订单编号查找
     *
     * @param no
     * @return
     */
    @Override
    public List<OrderAttachEntity> findByOrderNo(String no) {
        return orderAttachRepository.findByOrderNo(SysConstant.IS_DEL_N, no);
    }

    /**
     * 删除（批量逻辑删除）
     *
     * @param nos
     * @return
     */
    @Override
    public boolean delete(List<String> nos) {
        orderAttachRepository.batchDelete(SysConstant.IS_DEL_Y, nos);
        return true;
    }


}

