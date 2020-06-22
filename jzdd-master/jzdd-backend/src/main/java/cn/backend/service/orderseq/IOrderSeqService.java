package cn.backend.service.orderseq;

import cn.backend.model.primary.orderseq.OrderSeqEntity;
import cn.backend.model.primary.orderseq.OrderSeqQuery;
import cn.backend.service.IBaseService;

/**
 *
 * @author sunkw
 * @date 2019/07/23
 */
public interface IOrderSeqService extends IBaseService<OrderSeqEntity, OrderSeqQuery>{



    String getOrderNo(OrderSeqQuery orderSeqQuery);

}

