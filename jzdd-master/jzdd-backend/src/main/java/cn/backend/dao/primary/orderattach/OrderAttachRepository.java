package cn.backend.dao.primary.orderattach;

import cn.backend.model.primary.orderattach.OrderAttachEntity;
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
@Repository("orderAttachRepository")
public interface OrderAttachRepository extends JpaRepository<OrderAttachEntity, String>, JpaSpecificationExecutor<OrderAttachEntity> {

    /**
     * 查找全部
     * @param deleteStatus
     * @return
     */
    @Query("select e from OrderAttachEntity e where e.isDel=:deleteStatus")
    List<OrderAttachEntity> findAll(@Param("deleteStatus") String deleteStatus);

    /**
     * 删除
     * @param deleteStatus
     * @param id
     * @return
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update OrderAttachEntity e set e.isDel=:deleteStatus where e.id=:id")
    int delete(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

    /**
     * 删除
     * @param deleteStatus
     * @param nos
     * @return
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update OrderAttachEntity e set e.isDel=:deleteStatus where e.attachNo in:nos")
    int batchDelete(@Param("deleteStatus") String deleteStatus, @Param("nos") List<String> nos);

    /**
     * 根据id查找
     * @param deleteStatus
     * @param id
     * @return
     */
    @Query("select e from OrderAttachEntity e where e.isDel=:deleteStatus and e.id=:id")
    OrderAttachEntity findById(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

    /**
     * 根据no查找
     * @param deleteStatus
     * @param no
     * @return
     */
    @Query("select e from OrderAttachEntity e where e.isDel=:deleteStatus and e.attachNo=:no")
    OrderAttachEntity findByNo(@Param("deleteStatus") String deleteStatus, @Param("no") String no);

    /**
     * 根据no查找
     * @param deleteStatus
     * @param orderNo
     * @return
     */
    @Query("select e from OrderAttachEntity e where e.isDel=:deleteStatus and e.orderNo=:orderNo")
    List<OrderAttachEntity> findByOrderNo(@Param("deleteStatus") String deleteStatus, @Param("orderNo") String orderNo);

}

