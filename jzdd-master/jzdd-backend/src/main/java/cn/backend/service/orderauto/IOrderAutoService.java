package cn.backend.service.orderauto;

import cn.backend.model.primary.orderauto.OrderAutoEntity;
import cn.backend.model.primary.orderauto.OrderAutoQuery;
import cn.backend.service.IBaseService;

import java.util.Set;

/**
 * @author sunkw
 * @date 2019/08/13
 */
public interface IOrderAutoService extends IBaseService<OrderAutoEntity, OrderAutoQuery> {

    /**
     * 根据编号查找
     *
     * @param no
     * @return
     */
    OrderAutoEntity findByNo(String no);


    String findAutoOrderSupplierNo(OrderAutoQuery query);

    void addOrderNumSupplierNo(OrderAutoQuery query);

    void editOrderNumSupplierNo(OrderAutoQuery query);

    void initAutoOrder(String corpNo);

    boolean deleteBatch(Set<String>ids);

}

