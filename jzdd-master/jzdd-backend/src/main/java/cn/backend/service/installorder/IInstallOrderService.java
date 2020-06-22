package cn.backend.service.installorder;

import cn.backend.model.primary.installorder.InstallOrderEntity;
import cn.backend.model.primary.installorder.InstallOrderQuery;
import cn.backend.service.IBaseService;

import java.util.List;

/**
 * @author sunkw
 * @date 2019/07/17
 */
public interface IInstallOrderService extends IBaseService<InstallOrderEntity, InstallOrderQuery> {

    /**
     * 提交订单
     *
     * @param entity
     * @return
     */
    InstallOrderEntity submitOrder(InstallOrderEntity entity);

    /**
     * 订单工作流
     *
     * @param entity
     * @return
     */
    InstallOrderEntity orderWorkFollow(InstallOrderEntity entity);

    // /**
    //  * 订单工作流(重构)
    //  *
    //  * @param entity
    //  * @return
    //  */
    // InstallOrderEntity workFollow(InstallOrderEntity entity);

    /**
     * 根据编号查找
     *
     * @param no
     * @return
     */
    InstallOrderEntity findByNo(String no);

    /**
     * 保存
     *
     * @param entity
     * @return
     */
    InstallOrderEntity saveOrderAndStep(InstallOrderEntity entity);

    /**
     * 作废
     * @param query
     * @return
     */
    InstallOrderEntity cancel(InstallOrderQuery query);

    /**
     * 申请作废
     * @param query
     * @return
     */
    InstallOrderEntity applyCancel(InstallOrderQuery query);

    /**
     * 批量新增
     * @param list
     * @return
     */
    List<InstallOrderEntity> addList(List<InstallOrderEntity> list);

    /**
     * 提交订单
     *
     * @param entity
     * @return
     */
    InstallOrderEntity addInstall(InstallOrderEntity entity);

    /**
     * 编辑订单
     *
     * @param entity
     * @return
     */
    InstallOrderEntity editInstall(InstallOrderEntity entity);

    /**
     * 根据桩编码查找
     *
     * @param pileCode
     * @param corpNo
     * @return
     */
    InstallOrderEntity findByPileCodeAndCorp(String pileCode, String corpNo);


    InstallOrderEntity installReturn(String no, String remark);

    InstallOrderEntity findByVin(String vin, String corpNo);

    /**
     * 结算审核未通过，订单状态变为待服务商确认
     *
     * @param orderNo
     * @return
     */
    InstallOrderEntity settleVerifyFail(String orderNo, String remark);

    /**
     * 根据公司查找撤回订单
     *
     * @param crmRollbackFlg
     * @param corpNo
     * @return
     */
    List<InstallOrderEntity> findRollBackOrder(String crmRollbackFlg, String vinNo, String corpNo);

}

