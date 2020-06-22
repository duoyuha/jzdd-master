package cn.backend.service.viewsettle;

import cn.backend.model.primary.settle.SettleQuery;
import cn.backend.model.primary.viewsettle.SettleViewEntity;
import cn.backend.service.IBaseService;

/**
 * @author James
 * @date 2019/07/30
 */
public interface ISettleViewService extends IBaseService<SettleViewEntity, SettleQuery> {

    SettleViewEntity findBySettleNo(String settleNo);

}
