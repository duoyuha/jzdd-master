package cn.backend.dao.primary.installorder;

import cn.backend.model.primary.installorder.InstallOrderEntity;
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
 * @date 2019/07/17
 */
@Repository("installOrderRepository")
public interface InstallOrderRepository extends JpaRepository<InstallOrderEntity, String>, JpaSpecificationExecutor<InstallOrderEntity> {

    /**
     * 查找全部
     * @param deleteStatus
     * @return
     */
    @Query("select e from InstallOrderEntity e where e.isDel=:deleteStatus")
    List<InstallOrderEntity> findAll(@Param("deleteStatus") String deleteStatus);

    /**
     * 删除
     * @param deleteStatus
     * @param id
     * @return
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update InstallOrderEntity e set e.isDel=:deleteStatus where e.id=:id")
    int delete(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

    /**
     * 根据id查找
     * @param deleteStatus
     * @param id
     * @return
     */
    @Query("select e from InstallOrderEntity e where e.isDel=:deleteStatus and e.id=:id")
    InstallOrderEntity findById(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

    /**
     * 根据no查找
     * @param deleteStatus
     * @param orderNo
     * @return
     */
    @Query("select e from InstallOrderEntity e where e.isDel=:deleteStatus and e.orderNo=:orderNo")
    InstallOrderEntity findByNo(@Param("deleteStatus") String deleteStatus, @Param("orderNo") String orderNo);

    /**
     * 根据桩编码查找
     * @param deleteStatus
     * @param pile
     * @param corpNo
     * @return
     */
//    @Query("select e from InstallOrderEntity e where e.isDel=:deleteStatus and e.pileCode=:pile and e.corpNo=:corpNo and e.pileCode<>null")
//    List<InstallOrderEntity> findByPileCodeAndCorp(@Param("deleteStatus") String deleteStatus, @Param("pile") String pile, @Param("corpNo") String corpNo);

    /**
     * 根据类型配置类型获取配置列表
     *
     * @param deleteStatus
     * @param
     * @return
     */
    // @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    @Query(nativeQuery = true, value = "select * from t_install_order e where e.IS_DEL=?1 and e.PILE_CODE =?2 and e.CORP_NO =?3 ")
    InstallOrderEntity findByPileCodeAndCorp(String deleteStatus, String pile,String corpNo);

    /**
     * 获取回撤订单
     *
     * @param deleteStatus
     * @return
     */
    @Query(nativeQuery = true, value = "select * from t_install_order e1 left join t_order_car e2 on e1.ORDER_NO = e2.ORDER_NO where e1.IS_DEL=?1 and e2.IS_DEL=?1 and e1.CRM_ROLLBACK_FLG=?2 and e2.VIN_NO=?3 and e1.CORP_NO=?4 ")
    List<InstallOrderEntity> findRollBackOrderByVinAndCorpNo(String deleteStatus, String crmRollbackFlg, String vinNo, String corpNo);


}

