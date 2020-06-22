package cn.backend.dao.primary.orderstep;

import cn.backend.model.primary.orderstep.OrderStepEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import javax.transaction.Transactional;
import java.util.List;

/**
 *
 * @author sunkw
 * @date 2019/07/16
 */
@Repository("orderStepRepository")
public interface OrderStepRepository extends JpaRepository<OrderStepEntity, String>, JpaSpecificationExecutor<OrderStepEntity> {

    /**
     * 查找全部
     * @param deleteStatus
     * @return
     */
    @Query("select e from OrderStepEntity e where e.isDel=:deleteStatus")
    List<OrderStepEntity> findAll(@Param("deleteStatus") String deleteStatus);

    /**
     * 删除
     * @param deleteStatus
     * @param id
     * @return
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update OrderStepEntity e set e.isDel=:deleteStatus where e.id=:id")
    int delete(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

    /**
     * 根据id查找
     * @param deleteStatus
     * @param id
     * @return
     */
    @Query("select e from OrderStepEntity e where e.isDel=:deleteStatus and e.id=:id")
    OrderStepEntity findById(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

    /**
     * 根据no查找
     * @param deleteStatus
     * @param orderNo
     * @return
     */
    @Query("select e from OrderStepEntity e where e.isDel=:deleteStatus and e.orderNo=:orderNo")
    List<OrderStepEntity> findByNo(@Param("deleteStatus") String deleteStatus, @Param("orderNo") String orderNo);


    /**
     * 根据no查找
     * @param deleteStatus
     * @param orderNo
     * @return
     */
    @Query("select e from OrderStepEntity e where e.isDel=:deleteStatus and e.orderNo=:orderNo and e.followNo=:followNo")
    List<OrderStepEntity> findByOrderNoAndFollowNo(@Param("deleteStatus") String deleteStatus, @Param("orderNo") String orderNo, @Param("followNo") String followNo);

    /**
     * 根据订单编号查找最后一步
     * @param deleteStatus
     * @param orderNo
     * @return
     */
    @Query("select e from OrderStepEntity e where e.isDel=:deleteStatus and e.orderNo=:orderNo and e.currentStepFlg='Y' and e.followCode=:followCode")
    OrderStepEntity findLastByOrderNo(@Param("deleteStatus") String deleteStatus, @Param("orderNo") String orderNo, @Param("followCode") String followCode);

    /**
     * 根据订单编号和步骤号查找
     * @param deleteStatus
     * @param orderNo
     * @param followSeq
     * @return
     */
    @Query("select e from OrderStepEntity e where e.isDel=:deleteStatus and e.orderNo=:orderNo and e.followSeq=:followSeq")
    List<OrderStepEntity> findByOrderNoAndFollowSeq(@Param("deleteStatus") String deleteStatus, @Param("orderNo") String orderNo, @Param("followSeq") int followSeq);

}

