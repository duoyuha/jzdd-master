package cn.backend.dao.primary.supplierarea;

import cn.backend.model.primary.supplierarea.SupplierAreaEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

/**
 * @author sunkw
 * @date 2019/07/08
 */
@Repository("supplierAreaRepository")
public interface SupplierAreaRepository extends JpaRepository<SupplierAreaEntity, String>, JpaSpecificationExecutor<SupplierAreaEntity> {

    /**
     * 查找全部
     *
     * @param deleteStatus
     * @return
     */
    @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
    @Query("select e from SupplierAreaEntity e where e.isDel=:deleteStatus")
    List<SupplierAreaEntity> findAll(@Param("deleteStatus") String deleteStatus);

    /**
     * 删除
     *
     * @param deleteStatus
     * @param id
     * @return
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update SupplierAreaEntity e set e.isDel=:deleteStatus where e.id=:id")
    int delete(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

    /**
     * 根据id查找
     *
     * @param deleteStatus
     * @param id
     * @return
     */
    @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
    @Query("select e from SupplierAreaEntity e where e.isDel=:deleteStatus and e.id=:id")
    SupplierAreaEntity findById(@Param("deleteStatus") String deleteStatus, @Param("id") String id);


    /**
     * 根据no查找
     *
     * @param deleteStatus
     * @param no
     * @return
     */
    @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
    @Query("select e from SupplierAreaEntity e where e.isDel=:deleteStatus and e.supplierNo=:no")
    Set<SupplierAreaEntity> findBySupplierNo(@Param("deleteStatus") String deleteStatus, @Param("no") String no);

    /**
     * 批量删除
     *
     * @param deleteStatus
     * @param ids
     * @return
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update SupplierAreaEntity e set e.isDel =:deleteStatus where e.id in (:ids)")
    int deleteBySet(@Param("deleteStatus") String deleteStatus, @Param("ids") Set<String> ids);
}

