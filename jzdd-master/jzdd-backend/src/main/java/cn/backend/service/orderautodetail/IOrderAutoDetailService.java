package cn.backend.service.orderautodetail;

import cn.backend.model.primary.orderautodetail.OrderAutoDetailEntity;
import cn.backend.model.primary.orderautodetail.OrderAutoDetailQuery;
import cn.backend.service.IBaseService;

/**
 * @author sunkw
 * @date 2019/08/14
 */
public interface IOrderAutoDetailService extends IBaseService<OrderAutoDetailEntity, OrderAutoDetailQuery> {

    /**
     * 根据编号查找
     *
     * @param orderNo
     * @return
     */
    OrderAutoDetailEntity findByOrderNo(String orderNo);

}

