package cn.backend.dao.primary.workfollowdetail;

import cn.backend.model.primary.workfollowdetail.WorkFollowDetailEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

/**
 *
 * @author sunkw
 * @date 2019/07/16
 */
@Repository("workFollowDetailRepository")
public interface WorkFollowDetailRepository extends JpaRepository<WorkFollowDetailEntity, String>, JpaSpecificationExecutor<WorkFollowDetailEntity> {

    /**
     * 查找全部
     * @param deleteStatus
     * @return
     */
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    @Query("select e from WorkFollowDetailEntity e where e.isDel=:deleteStatus")
    List<WorkFollowDetailEntity> findAll(@Param("deleteStatus") String deleteStatus);

    /**
     * 删除
     * @param deleteStatus
     * @param id
     * @return
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update WorkFollowDetailEntity e set e.isDel=:deleteStatus where e.id=:id")
    int delete(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

    /**
     * 根据id查找
     * @param deleteStatus
     * @param id
     * @return
     */
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    @Query("select e from WorkFollowDetailEntity e where e.isDel=:deleteStatus and e.id=:id")
    WorkFollowDetailEntity findById(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

    /**
     * 根据no查找
     * @param deleteStatus
     * @param followNo
     * @return
     */
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    @Query("select e from WorkFollowDetailEntity e where e.isDel=:deleteStatus and e.followNo=:followNo")
    WorkFollowDetailEntity findByNo(@Param("deleteStatus") String deleteStatus, @Param("followNo") String followNo);

    /**
     * 根据no查找
     * @param deleteStatus
     * @param followNo
     * @return
     */
    //@QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    @Query(value = "select FOLLOW_SEQ from m_work_follow_detail where IS_DEL=?1 and FOLLOW_NO =?2", nativeQuery = true)
    Set<Integer> findStepsByFollowNo(@Param("deleteStatus") String deleteStatus, @Param("followNo") String followNo);

    /**
     * 根据no查找
     * @param deleteStatus
     * @param followNo
     * @return
     */
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    @Query("select e from WorkFollowDetailEntity e where e.isDel=:deleteStatus and e.followNo=:followNo and e.followSeq=:followSeq")
    WorkFollowDetailEntity findByFollowNoAndFollowSeq(@Param("deleteStatus") String deleteStatus, @Param("followNo") String followNo, @Param("followSeq") Integer followSeq);

    /**
     * 查找最小步骤
     * @param deleteStatus
     * @param followNo
     * @return
     */
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    @Query(value = "select * from m_work_follow_detail e1 where e1.IS_DEL = ?1 and e1.FOLLOW_NO = ?2 and e1.FOLLOW_SEQ = (select MIN(e2.FOLLOW_SEQ) from m_work_follow_detail e2 where e2.IS_DEL = ?1 and e2.FOLLOW_NO = ?2)", nativeQuery = true)
    WorkFollowDetailEntity findMinFollowSeq(@Param("deleteStatus") String deleteStatus, @Param("followNo") String followNo);

    /**
     * 查找最大步骤
     * @param deleteStatus
     * @param followNo
     * @return
     */
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    @Query(value = "select * from m_work_follow_detail e1 where e1.IS_DEL = ?1 and e1.FOLLOW_NO = ?2 and e1.FOLLOW_SEQ = (select MAX(e2.FOLLOW_SEQ) from m_work_follow_detail e2 where e2.IS_DEL = ?1 and e2.FOLLOW_NO = ?2)", nativeQuery = true)
    WorkFollowDetailEntity findMaxFollowSeq(@Param("deleteStatus") String deleteStatus, @Param("followNo") String followNo);

    /**
     * 查找下一步
     * @param deleteStatus
     * @param followNo
     * @param followSeq
     * @return
     */
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    @Query("select e from WorkFollowDetailEntity e where e.isDel=:deleteStatus and e.followNo=:followNo and e.parentSeq=:followSeq")
    WorkFollowDetailEntity findNextFollowSeq(@Param("deleteStatus") String deleteStatus, @Param("followNo") String followNo, @Param("followSeq") Integer followSeq);

    /**
     * 查找待确认步骤
     * @param deleteStatus
     * @param followNo
     * @return
     */
    @QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
    @Query("select e from WorkFollowDetailEntity e where e.isDel=:deleteStatus and e.followNo=:followNo and e.confirmStep='Y'")
    WorkFollowDetailEntity findConfirmSeq(@Param("deleteStatus") String deleteStatus, @Param("followNo") String followNo);

}

