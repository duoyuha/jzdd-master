package cn.backend.service.viewdeliveryorder;

import cn.backend.model.primary.viewdeliveryorder.ViewDeliveryOrderEntity;
import cn.backend.model.primary.viewdeliveryorder.ViewDeliveryOrderQuery;
import cn.backend.service.IBaseService;

/**
 *
 * @author sunkw
 * @date 2019/07/23
 */
public interface IViewDeliveryOrderService extends IBaseService<ViewDeliveryOrderEntity,ViewDeliveryOrderQuery>{

    /**
     * 根据编号查找
     *
     * @param no
     * @return
     */
    ViewDeliveryOrderEntity findByNo(String no);

}

