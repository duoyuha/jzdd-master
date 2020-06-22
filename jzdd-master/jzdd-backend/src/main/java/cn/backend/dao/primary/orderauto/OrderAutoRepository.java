package cn.backend.dao.primary.orderauto;

import cn.backend.model.primary.orderauto.OrderAutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

/**
 *
 * @author sunkw
 * @date 2019/08/13
 */
@Repository("orderAutoRepository")
public interface OrderAutoRepository extends JpaRepository<OrderAutoEntity, String>, JpaSpecificationExecutor<OrderAutoEntity> {

    /**
     * 查找全部
     * @param deleteStatus
     * @return
     */
    @Query("select e from OrderAutoEntity e where e.isDel=:deleteStatus")
    List<OrderAutoEntity> findAll(@Param("deleteStatus") String deleteStatus);

    /**
     * 删除
     * @param deleteStatus
     * @param id
     * @return
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update OrderAutoEntity e set e.isDel=:deleteStatus where e.id=:id")
    int delete(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

    /**
     * 批量删除
     * @param deleteStatus
     * @param ids
     * @return
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update OrderAutoEntity e set e.isDel=:deleteStatus where e.id in (:ids)")
    int deleteBatch(@Param("deleteStatus") String deleteStatus, @Param("id") Set<String> ids);

    /**
     * 根据id查找
     * @param deleteStatus
     * @param id
     * @return
     */
    @Query("select e from OrderAutoEntity e where e.isDel=:deleteStatus and e.id=:id")
    OrderAutoEntity findById(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

    /**
     * 根据no查找
     * @param deleteStatus
     * @param no
     * @return
     */
    @Query("select e from OrderAutoEntity e where e.isDel=:deleteStatus and e.orderAutoNo=:no")
    OrderAutoEntity findByNo(@Param("deleteStatus") String deleteStatus, @Param("no") String no);

}

