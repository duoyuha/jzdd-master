package cn.backend.dao.primary.viewcancel;

import cn.backend.model.primary.viewcancel.ViewCancelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 *
 * @author sunkw
 * @date 2019/07/17
 */
@Repository("viewCancelRepository")
public interface ViewCancelRepository extends JpaRepository<ViewCancelEntity, String>, JpaSpecificationExecutor<ViewCancelEntity> {

    /**
     * 根据id查找
     * @param deleteStatus
     * @param id
     * @return
     */
    @Query("select e from ViewCancelEntity e where e.isDel=:deleteStatus and e.id=:id")
    ViewCancelEntity findById(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

    /**
     * 删除
     * @param deleteStatus
     * @param id
     * @return
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update ViewCancelEntity e set e.isDel=:deleteStatus where e.id=:id")
    int delete(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

    // @Query("SELECT e FROM ViewCancelEntity u WHERE e.isDel=:deleteStatus and e.vin LIKE CONCAT('%',:vin,'%')")
    // List<ViewCancelEntity> findByVin(@Param("vin") String vin);

}

