package cn.backend.service.cancel;

import cn.backend.model.primary.orderstep.OrderStepEntity;
import cn.backend.model.primary.viewcancel.ViewCancelEntity;
import cn.backend.model.primary.viewcancel.ViewCancelQuery;
import cn.backend.service.IBaseService;

import java.util.List;

/**
 * @author sunkw
 * @date 2019/07/16
 */
public interface ICancelService extends IBaseService<ViewCancelEntity, ViewCancelQuery> {

    List<OrderStepEntity> findByVin(String vin);

}

