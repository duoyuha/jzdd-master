package cn.backend.dao.primary.workfollow;

import cn.backend.model.primary.workfollow.WorkFollowEntity;
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
@Repository("workFollowRepository")
public interface WorkFollowRepository extends JpaRepository<WorkFollowEntity, String>, JpaSpecificationExecutor<WorkFollowEntity> {

    /**
     * 查找全部
     * @param deleteStatus
     * @return
     */
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    @Query("select e from WorkFollowEntity e where e.isDel=:deleteStatus")
    List<WorkFollowEntity> findAll(@Param("deleteStatus") String deleteStatus);

    /**
     * 删除
     * @param deleteStatus
     * @param id
     * @return
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update WorkFollowEntity e set e.isDel=:deleteStatus where e.id=:id")
    int delete(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

    /**
     * 根据id查找
     * @param deleteStatus
     * @param id
     * @return
     */
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    @Query("select e from WorkFollowEntity e where e.isDel=:deleteStatus and e.id=:id")
    WorkFollowEntity findById(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

    /**
     * 根据no查找
     * @param deleteStatus
     * @param followNo
     * @return
     */
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    @Query("select e from WorkFollowEntity e where e.isDel=:deleteStatus and e.followNo=:followNo")
    WorkFollowEntity findByNo(@Param("deleteStatus") String deleteStatus, @Param("followNo") String followNo);

    /**
     * 根据code查找
     * @param deleteStatus
     * @param followCode
     * @return
     */
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    @Query("select e from WorkFollowEntity e where e.isDel=:deleteStatus and e.followCode=:followCode")
    WorkFollowEntity findByCode(@Param("deleteStatus") String deleteStatus, @Param("followCode") String followCode);

}

