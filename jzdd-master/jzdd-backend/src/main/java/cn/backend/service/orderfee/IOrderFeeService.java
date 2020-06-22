package cn.backend.service.orderfee;

import cn.backend.model.primary.installorder.InstallOrderEntity;
import cn.backend.model.primary.orderfee.OrderFeeEntity;
import cn.backend.model.primary.orderfee.OrderFeeQuery;
import cn.backend.service.IBaseService;

import java.util.List;
import java.util.Set;

/**
 * @author sunkw
 * @date 2019/08/06
 */
public interface IOrderFeeService extends IBaseService<OrderFeeEntity, OrderFeeQuery> {

    /**
     * 根据编号查找
     *
     * @param no
     * @return
     */
    OrderFeeEntity findByNo(String no);

    /**
     * 根据安装订单生成资费
     *
     * @param entity
     * @return
     */
    OrderFeeEntity addOrderFee(OrderFeeQuery query);


    OrderFeeEntity addByInstallOrder(InstallOrderEntity entity);

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    boolean deleteBatch(Set<String> ids);

}

