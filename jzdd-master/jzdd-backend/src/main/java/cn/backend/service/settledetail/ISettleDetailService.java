package cn.backend.service.settledetail;

import cn.backend.model.primary.settledetail.SettleDetailEntity;
import cn.backend.model.primary.settledetail.SettleDetailQuery;
import cn.backend.service.IBaseService;

import java.util.List;

/**
 * @author James
 * @date 2019/07/29
 */
public interface ISettleDetailService extends IBaseService<SettleDetailEntity, SettleDetailQuery> {

        List<SettleDetailEntity> findByNo(String no);
}

