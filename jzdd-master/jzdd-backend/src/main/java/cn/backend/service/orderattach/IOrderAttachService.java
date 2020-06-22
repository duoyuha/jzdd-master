package cn.backend.service.orderattach;

import cn.backend.model.primary.orderattach.OrderAttachEntity;
import cn.backend.model.primary.orderattach.OrderAttachQuery;
import cn.backend.service.IBaseService;
import cn.zdwl.common.file.UploadFile;

import java.util.List;

/**
 * @author sunkw
 * @date 2019/07/23
 */
public interface IOrderAttachService extends IBaseService<OrderAttachEntity, OrderAttachQuery> {

    /**
     * 根据编号查找
     *
     * @param no
     * @return
     */
    OrderAttachEntity findByNo(String no);

    /**
     * 根据订单号查询
     * @param no
     * @return
     */
    List<OrderAttachEntity> findByOrderNo(String no);

    /**
     * 删除（批量逻辑删除）
     *
     * @param ids
     * @return
     */
    boolean delete(List<String> ids);

}

