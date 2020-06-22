package cn.backend.dao.primary.area;

import cn.backend.model.primary.area.AreaEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * @author sunkw
 * @date 2019/07/17
 */
@Repository("areaRepository")
public interface AreaRepository extends JpaRepository<AreaEntity, String>, JpaSpecificationExecutor<AreaEntity> {

    /**
     * 查找全部
     *
     * @param deleteStatus
     * @return
     */
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    @Query("select e from AreaEntity e where e.isDel=:deleteStatus")
    List<AreaEntity> findAll(@Param("deleteStatus") String deleteStatus);

    /**
     * 删除
     *
     * @param deleteStatus
     * @param id
     * @return
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update AreaEntity e set e.isDel=:deleteStatus where e.id=:id")
    int delete(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

    /**
     * 根据createdWhen查找
     *
     * @param createdWhen
     * @param createdWhen
     * @return
     */
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    @Query("select e from AreaEntity e where e.isDel=:deleteStatus and e.createdWhen=:createdWhen")
    AreaEntity findByCreatedWhen(@Param("deleteStatus") String deleteStatus, @Param("createdWhen") Date createdWhen);


    /**
     * 根据id查找
     *
     * @param deleteStatus
     * @param id
     * @return
     */
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    @Query("select e from AreaEntity e where e.isDel=:deleteStatus and e.id=:id")
    AreaEntity findById(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

    /**
     * 根据no查找
     *
     * @param deleteStatus
     * @param areaNo
     * @return
     */
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    @Query("select e from AreaEntity e where e.isDel=:deleteStatus and e.areaNo=:areaNo")
    AreaEntity findByNo(@Param("deleteStatus") String deleteStatus, @Param("areaNo") String areaNo);

}

