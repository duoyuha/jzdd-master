package cn.backend.dao.primary.viewdeliveryorder;

import cn.backend.model.primary.viewdeliveryorder.ViewDeliveryOrderEntity;
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
@Repository("viewDeliveryOrderRepository")
public interface ViewDeliveryOrderRepository extends JpaRepository<ViewDeliveryOrderEntity, String>, JpaSpecificationExecutor<ViewDeliveryOrderEntity> {

    /**
     * 查找全部
     * @param deleteStatus
     * @return
     */
    @Query("select e from ViewDeliveryOrderEntity e where e.isDel=:deleteStatus")
    List<ViewDeliveryOrderEntity> findAll(@Param("deleteStatus") String deleteStatus);

    /**
     * 删除
     * @param deleteStatus
     * @param id
     * @return
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update ViewDeliveryOrderEntity e set e.isDel=:deleteStatus where e.id=:id")
    int delete(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

    /**
     * 根据id查找
     * @param deleteStatus
     * @param id
     * @return
     */
    @Query("select e from ViewDeliveryOrderEntity e where e.isDel=:deleteStatus and e.id=:id")
    ViewDeliveryOrderEntity findById(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

    /**
     * 根据no查找
     * @param deleteStatus
     * @param no
     * @return
     */
    @Query("select e from ViewDeliveryOrderEntity e where e.isDel=:deleteStatus and e.orderNo=:no")
    ViewDeliveryOrderEntity findByNo(@Param("deleteStatus") String deleteStatus, @Param("no") String no);

}

