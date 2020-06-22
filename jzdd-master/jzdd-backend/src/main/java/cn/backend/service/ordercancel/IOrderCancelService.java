package cn.backend.service.ordercancel;

import cn.backend.model.primary.ordercancel.OrderCancelEntity;
import cn.backend.model.primary.ordercancel.OrderCancelQuery;
import cn.backend.service.IBaseService;

/**
 *
 * @author sunkw
 * @date 2019/08/26
 */
public interface IOrderCancelService extends IBaseService<OrderCancelEntity, OrderCancelQuery>{

    /**
     * 根据编号查找
     *
     * @param no
     * @return
     */
    OrderCancelEntity findByNo(String no);

    OrderCancelEntity addInstall(OrderCancelEntity entity);

    OrderCancelEntity editInsatll(OrderCancelEntity entity);

    OrderCancelEntity editDelivery(OrderCancelEntity entity);

    OrderCancelEntity addDelivery(OrderCancelEntity entity);

    OrderCancelEntity findByApplyNo(String applyNo);

}

