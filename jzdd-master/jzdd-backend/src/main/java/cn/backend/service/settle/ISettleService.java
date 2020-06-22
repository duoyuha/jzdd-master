package cn.backend.service.settle;


import cn.backend.model.primary.settle.SettleEntity;
import cn.backend.model.primary.settle.SettleQuery;
import cn.backend.model.primary.viewdeliveryorder.ViewDeliveryOrderEntity;
import cn.backend.model.primary.viewinstallorder.ViewInstallOrderEntity;
import cn.backend.service.IBaseService;

import java.util.List;

/**
 * @author James
 * @date 2019/07/29
 */
public interface ISettleService extends IBaseService<SettleEntity, SettleQuery> {



    /**
     * 根据结算单编号查找
     *
     * @param no 结算单编号
     * @return 结算单
     */
    SettleEntity findByNo(String no);

    /**
     * 安装结算单导出
     * @param query 查询条件
     * @return 查询结果
     */
    List<ViewInstallOrderEntity> export(SettleQuery query);

    /**
     * 配送结算导出
     * @param query
     * @return
     */
    List<ViewDeliveryOrderEntity> delExport(SettleQuery query);

    boolean back(SettleQuery settleQuery);

    SettleEntity submit(SettleEntity entity);





}

