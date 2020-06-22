package cn.backend.service.ordercar;

import cn.backend.model.primary.ordercar.OrderCarEntity;
import cn.backend.model.primary.ordercar.OrderCarQuery;
import cn.backend.service.IBaseService;

import java.util.List;

/**
 * @author sunkw
 * @date 2019/07/17
 */
public interface IOrderCarService extends IBaseService<OrderCarEntity, OrderCarQuery> {

    /**
     * 根据编号查找
     *
     * @param no
     * @return
     */
    OrderCarEntity findByNo(String no);

    // /**
    //  * 根据vinNo查找
    //  *
    //  * @param vinNo
    //  * @return
    //  */
    // OrderCarEntity findByVinNo(String vinNo);

    List<OrderCarEntity> findListByVinNo(String vinNo);

    OrderCarEntity findByVinNoAndCorp(String vinNo,String corpNo);


    /**
     * 从外部接口获取信息
     *
     * @param orderCarQuery
     * @return
     */
    OrderCarEntity getOrderCarInfo(OrderCarQuery orderCarQuery);

}

