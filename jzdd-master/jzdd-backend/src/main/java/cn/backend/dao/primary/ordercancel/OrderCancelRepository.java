package cn.backend.dao.primary.ordercancel;

import cn.backend.model.primary.ordercancel.OrderCancelEntity;
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
 * @date 2019/08/26
 */
@Repository("orderCancelRepository")
public interface OrderCancelRepository extends JpaRepository<OrderCancelEntity, String>, JpaSpecificationExecutor<OrderCancelEntity> {

    /**
     * 查找全部
     * @param deleteStatus
     * @return
     */
    @Query("select e from OrderCancelEntity e where e.isDel=:deleteStatus")
    List<OrderCancelEntity> findAll(@Param("deleteStatus") String deleteStatus);

    /**
     * 删除
     * @param deleteStatus
     * @param id
     * @return
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update OrderCancelEntity e set e.isDel=:deleteStatus where e.id=:id")
    int delete(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

    /**
     * 根据id查找
     * @param deleteStatus
     * @param id
     * @return
     */
    @Query("select e from OrderCancelEntity e where e.isDel=:deleteStatus and e.id=:id")
    OrderCancelEntity findById(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

    /**
     * 根据no查找
     * @param deleteStatus
     * @param no
     * @return
     */
    @Query("select e from OrderCancelEntity e where e.isDel=:deleteStatus and e.orderNo=:no")
    OrderCancelEntity findByNo(@Param("deleteStatus") String deleteStatus, @Param("no") String no);

    @Query("select e from OrderCancelEntity e where e.isDel=:deleteStatus and e.applyNo=:applyNo")
    OrderCancelEntity findByapplyNo(@Param("deleteStatus") String deleteStatus, @Param("applyNo") String applyNo);

}

