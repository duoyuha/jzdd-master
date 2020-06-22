package cn.backend.dao.primary.ordergrade;

import cn.backend.model.primary.ordergrade.OrderGradeEntity;
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
 * @date 2019/07/23
 */
@Repository("orderGradeRepository")
public interface OrderGradeRepository extends JpaRepository<OrderGradeEntity, String>, JpaSpecificationExecutor<OrderGradeEntity> {

    /**
     * 查找全部
     * @param deleteStatus
     * @return
     */
    @Query("select e from OrderGradeEntity e where e.isDel=:deleteStatus")
    List<OrderGradeEntity> findAll(@Param("deleteStatus") String deleteStatus);

    /**
     * 删除
     * @param deleteStatus
     * @param id
     * @return
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update OrderGradeEntity e set e.isDel=:deleteStatus where e.id=:id")
    int delete(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

    /**
     * 根据id查找
     * @param deleteStatus
     * @param id
     * @return
     */
    @Query("select e from OrderGradeEntity e where e.isDel=:deleteStatus and e.id=:id")
    OrderGradeEntity findById(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

    /**
     * 根据no查找
     * @param deleteStatus
     * @param no
     * @return
     */
    @Query("select e from OrderGradeEntity e where e.isDel=:deleteStatus and e.gradeNo=:no")
    OrderGradeEntity findByNo(@Param("deleteStatus") String deleteStatus, @Param("no") String no);

    /**
     * 根据订单no查找
     * @param deleteStatus
     * @param orderNo
     * @return
     */
    @Query("select e from OrderGradeEntity e where e.isDel=:deleteStatus and e.orderNo=:orderNo")
    OrderGradeEntity findByOrderNo(@Param("deleteStatus") String deleteStatus, @Param("orderNo") String orderNo);

}

