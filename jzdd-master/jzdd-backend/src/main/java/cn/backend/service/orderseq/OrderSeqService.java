package cn.backend.service.orderseq;

import cn.backend.config.constant.SysConstant;
import cn.backend.config.message.CustomMessage;
import cn.backend.dao.primary.orderseq.OrderSeqRepository;
import cn.backend.model.primary.orderseq.OrderSeqEntity;
import cn.backend.model.primary.orderseq.OrderSeqQuery;
import cn.backend.service.BaseService;
import cn.zdwl.common.context.BaseContextHandler;
import cn.zdwl.common.exception.AppException;
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
 * create by keven at 2019年04月08日
 */
@Service(value = "docBasicSeqService")
public class OrderSeqService extends BaseService implements IOrderSeqService{

    @Resource
    private OrderSeqRepository orderSeqRepository;



    /**
     * 分页查询
     * @param orderSeqQuery
     * @return
     */
    @Override
    public Page<OrderSeqEntity> findList(OrderSeqQuery orderSeqQuery) {
        Sort sort=buildSort(orderSeqQuery.getSort());
        Pageable pageable = new PageRequest(orderSeqQuery.getPageNum(), orderSeqQuery.getPageSize(),sort);
        Page<OrderSeqEntity> entityPage = orderSeqRepository.findAll(createSpecification(orderSeqQuery),pageable);
        return entityPage;
    }

    /**
     * 列表查询
     * @param orderSeqQuery
     * @return
     */
    @Override
    public List<OrderSeqEntity> findAll(OrderSeqQuery orderSeqQuery) {
        Sort sort=buildSort(orderSeqQuery.getSort());
        List<OrderSeqEntity> entityList = orderSeqRepository.findAll(createSpecification(orderSeqQuery),sort);
        return entityList;
    }

    /**
     * 创建查询条件
     * @param orderSeqQuery
     * @return
     */
    private Specification createSpecification(OrderSeqQuery orderSeqQuery){
        return new Specification<OrderSeqEntity>(){

            @Override
            public Predicate toPredicate(Root<OrderSeqEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();

                predicate.add(cb.equal(root.get("occurDate").as(String.class), orderSeqQuery.getOccurDate()));
                predicate.add(cb.equal(root.get("corpNo").as(String.class), BaseContextHandler.getCorpNo()));
                predicate.add(cb.equal(root.get("orderType").as(String.class), orderSeqQuery.getOrderType()));
                //筛选已删除
                predicate.add(cb.equal(root.get("isDel").as(String.class),SysConstant.IS_DEL_N));

                Predicate[] p = new Predicate[predicate.size()];
                return cb.and(predicate.toArray(p));
            }

        };
    }

    /**
     * 新建
     * @param entity
     * @return
     */
    @Override
    public OrderSeqEntity add(OrderSeqEntity entity) {
        orderSeqRepository.saveAndFlush(entity);
        return entity;
    }

    /**
     * 更新
     * @param entity
     * @return
     */
    @Override
    public OrderSeqEntity edit(OrderSeqEntity entity) {
        orderSeqRepository.saveAndFlush(entity);
        return entity;
    }

    /**
     * 删除（逻辑删除）
     * @param id
     * @return
     */
    @Override
    public boolean delete(String id) {
        OrderSeqEntity entity=orderSeqRepository.findById(SysConstant.IS_DEL_N,id);
        if (entity ==null) {
            throw new AppException(CustomMessage.NO_DATA_FIND);
        }
        //删除标记
        orderSeqRepository.delete(SysConstant.IS_DEL_Y,id);
        return true;
    }

    /**
     * 根据id获取
     * @param id
     * @return
     */
    @Override
    public OrderSeqEntity findById(String id) {
        OrderSeqEntity entity=orderSeqRepository.findById(SysConstant.IS_DEL_N,id);
        return entity;
    }

    /**
     * 根据id校验
     * @param id
     * @return
     */
    @Override
    public OrderSeqEntity checkById(String id) {
        OrderSeqEntity entity=orderSeqRepository.findById(SysConstant.IS_DEL_N,id);
        if (entity ==null) {
            throw new AppException(CustomMessage.NO_DATA_FIND);
        }
        return entity;
    }


    @Override
    public String getOrderNo(OrderSeqQuery orderSeqQuery) {

        String strOrderNo ="";
        String lastName="";
        String firstName="";

        int seq=1;

        switch (orderSeqQuery.getOrderType()) {
            case SysConstant.WorkFollow.CONFIG_WORKFOLLOW_INSTALL:

                lastName = SysConstant.InstallOrder.PREFIX_NUMBER;
                break;
            case SysConstant.WorkFollow.CONFIG_WORKFOLLOW_DELIVERY:

                lastName = SysConstant.Delivery.PREFIX_NUMBER;
                break;

        }

        switch (orderSeqQuery.getCorpNo()) {
            case SysConstant.Delivery.CORP_ORA:
                firstName = SysConstant.Delivery.ORA;
                break;
            case SysConstant.Delivery.CORP_WEIPA:
                firstName = SysConstant.Delivery.WEY;
                break;

        }



        List<OrderSeqEntity> list = findAll(orderSeqQuery);

        if(list==null || list.size()==0){
            OrderSeqEntity orderSeqEntity = new OrderSeqEntity();
            orderSeqEntity.setCorpNo(orderSeqQuery.getCorpNo());
            orderSeqEntity.setOccurDate(orderSeqQuery.getOccurDate());
            orderSeqEntity.setOrderSeq(seq);
            orderSeqEntity.setOrderType(orderSeqQuery.getOrderType());

            add(orderSeqEntity);


        }else{

            OrderSeqEntity orderSeqEntity = list.stream().findFirst().orElse(null);

            seq = orderSeqEntity.getOrderSeq()+1;

            orderSeqEntity.setOrderSeq(seq);

            edit(orderSeqEntity);

        }


        strOrderNo=firstName+ orderSeqQuery.getOccurDate() + String.format("%05d", seq)+lastName;


        return strOrderNo;



    }
}
