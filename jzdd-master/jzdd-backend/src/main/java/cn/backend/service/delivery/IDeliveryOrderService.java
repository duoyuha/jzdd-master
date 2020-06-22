package cn.backend.service.delivery;

import cn.backend.model.primary.delivery.DeliveryOrderEntity;
import cn.backend.model.primary.delivery.DeliveryOrderQuery;
import cn.backend.service.IBaseService;

import java.util.List;

/**
 *
 * @author sunkw
 * @date 2019/07/16
 */
public interface IDeliveryOrderService extends IBaseService<DeliveryOrderEntity,DeliveryOrderQuery>{

    /**
     * 根据编号查找
     *
     * @param no
     * @return
     */
    DeliveryOrderEntity findByNo(String no);

    /**
     * 根据服务商NO查询
     * @param supplierNo
     * @return
     */
    List<DeliveryOrderEntity> findBySupplierNo(String supplierNo);


    /**
     * 作废
     * @param query
     * @return
     */
    DeliveryOrderEntity cancel(DeliveryOrderQuery query);


    /**
     * 订单工作流
     * @param entity
     * @return
     */
    DeliveryOrderEntity orderWorkFollow(DeliveryOrderEntity entity);

    /**
     * 保存
     * @param entity
     * @return
     */
    DeliveryOrderEntity saveOrderAndStep(DeliveryOrderEntity entity);

    /**
     * 申请作废
     * @param query
     * @return
     */
    DeliveryOrderEntity applyCancel(DeliveryOrderQuery query);

    /**
     * crm撤回
     *
     * @param no
     * @return
     */
    DeliveryOrderEntity deliveryReturn(String no, String remark);

    /**
     * 根据桩编码查找
     *
     * @param pileCode
     * @param corpNo
     * @return
     */
    DeliveryOrderEntity findByPileCodeAndCorp(String pileCode, String corpNo);

    /**
     * 根据车架号查找
     *
     * @param vin
     * @param corpNo
     * @return
     */
    DeliveryOrderEntity findByVin(String vin, String corpNo);

    /**
     * 结算审核未通过，订单状态变为待服务商确认
     *
     * @return
     */
    DeliveryOrderEntity settleVerifyFail(String orderNo, String remark);

    DeliveryOrderEntity save(DeliveryOrderEntity entity);

    /**
     * 根据公司查找撤回订单
     *
     * @param crmRollbackFlg
     * @param vinNo
     * @param corpNo
     * @return
     */
    List<DeliveryOrderEntity> findRollBackOrder(String crmRollbackFlg, String vinNo, String corpNo);

}

