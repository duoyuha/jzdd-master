package cn.backend.dao.primary.orderautodetail;

import cn.backend.model.primary.orderautodetail.OrderAutoDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author sunkw
 * @date 2019/08/14
 */
@Repository("orderAutoDetailRepository")
public interface OrderAutoDetailRepository extends JpaRepository<OrderAutoDetailEntity, String>, JpaSpecificationExecutor<OrderAutoDetailEntity> {

    /**
     * 查找全部
     *
     * @param deleteStatus
     * @return
     */
    @Query("select e from OrderAutoDetailEntity e where e.isDel=:deleteStatus")
    List<OrderAutoDetailEntity> findAll(@Param("deleteStatus") String deleteStatus);

    /**
     * 删除
     *
     * @param deleteStatus
     * @param id
     * @return
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update OrderAutoDetailEntity e set e.isDel=:deleteStatus where e.id=:id")
    int delete(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

    /**
     * 根据id查找
     *
     * @param deleteStatus
     * @param id
     * @return
     */
    @Query("select e from OrderAutoDetailEntity e where e.isDel=:deleteStatus and e.id=:id")
    OrderAutoDetailEntity findById(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

    /**
     * 根据no查找
     *
     * @param deleteStatus
     * @param orderNo
     * @return
     */
    @Query("select e from OrderAutoDetailEntity e where e.isDel=:deleteStatus and e.orderNo=:orderNo")
    OrderAutoDetailEntity findByOrderNo(@Param("deleteStatus") String deleteStatus, @Param("orderNo") String orderNo);

}

