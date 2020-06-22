package cn.backend.dao.primary.delivery;

import cn.backend.model.primary.delivery.DeliveryOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 *
 * @author sunkw
 * @date 2019/07/16
 */
@Repository("deliveryOrderRepository")
public interface DeliveryOrderRepository extends JpaRepository<DeliveryOrderEntity, String>, JpaSpecificationExecutor<DeliveryOrderEntity> {

    /**
     * 查找全部
     * @param deleteStatus
     * @return
     */
    @Query("select e from DeliveryOrderEntity e where e.isDel=:deleteStatus")
    List<DeliveryOrderEntity> findAll(@Param("deleteStatus") String deleteStatus);

    /**
     * 删除
     * @param deleteStatus
     * @param id
     * @return
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update DeliveryOrderEntity e set e.isDel=:deleteStatus where e.id=:id")
    int delete(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

    /**
     * 根据id查找
     * @param deleteStatus
     * @param id
     * @return
     */
    @Query("select e from DeliveryOrderEntity e where e.isDel=:deleteStatus and e.id=:id")
    DeliveryOrderEntity findById(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

    /**
     * 根据no查找
     * @param deleteStatus
     * @param orderNo
     * @return
     */
    @Query("select e from DeliveryOrderEntity e where e.isDel=:deleteStatus and e.orderNo=:orderNo")
    DeliveryOrderEntity findByNo(@Param("deleteStatus") String deleteStatus, @Param("orderNo") String orderNo);

    /**
     * 根据服务商NO查询
     * @param deleteStatus
     * @param supplierNo
     * @return
     */
    @Query("select e from DeliveryOrderEntity e where e.isDel=:deleteStatus and e.supplierNo=:supplierNo")
    List<DeliveryOrderEntity> findBySupplierNo(@Param("deleteStatus") String deleteStatus, @Param("supplierNo") String supplierNo);

    /**
     * 根据类型配置类型获取配置列表
     *
     * @param deleteStatus
     * @param pileCode
     * @param corpNo
     * @return
     */
    @Query(nativeQuery = true, value = "select * from t_delivery_order e where e.IS_DEL=?1 and e.PILE_CODE =?2 and e.CORP_NO =?3 ")
    DeliveryOrderEntity findByPileCodeAndCorp(String deleteStatus, String pileCode, String corpNo);

    /**
     * 获取回撤订单
     *
     * @param deleteStatus
     * @return
     */
    @Query(nativeQuery = true, value = "select * from t_delivery_order e1 left join t_order_car e2 on e1.ORDER_NO = e2.ORDER_NO where e1.IS_DEL=?1 and e2.IS_DEL=?1 and e1.CRM_ROLLBACK_FLG=?2 and e2.VIN_NO=?3 and e1.CORP_NO=?4 ")
    List<DeliveryOrderEntity> findRollBackOrderByVinAndCorpNo(String deleteStatus, String crmRollbackFlg, String vinNo, String corpNo);

}

