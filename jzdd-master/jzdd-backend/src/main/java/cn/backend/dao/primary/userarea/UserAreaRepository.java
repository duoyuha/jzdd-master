package cn.backend.dao.primary.userarea;

import cn.backend.model.primary.userarea.UserAreaEntity;
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
 * @author sunkw
 * @date 2019/10/08
 */
@Repository("userAreaRepository")
public interface UserAreaRepository extends JpaRepository<UserAreaEntity, String>, JpaSpecificationExecutor<UserAreaEntity> {

    /**
     * 查找全部
     *
     * @param deleteStatus
     * @return
     */
    @Query("select e from UserAreaEntity e where e.isDel=:deleteStatus")
    List<UserAreaEntity> findAll(@Param("deleteStatus") String deleteStatus);

    /**
     * 删除
     *
     * @param deleteStatus
     * @param id
     * @return
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update UserAreaEntity e set e.isDel=:deleteStatus where e.id=:id")
    int delete(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

    /**
     * 批量删除
     *
     * @param deleteStatus
     * @param ids
     * @return
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update UserAreaEntity e set e.isDel=:deleteStatus where e.id in :ids")
    int deleteBatch(@Param("deleteStatus") String deleteStatus, @Param("ids") Set<String> ids);

    /**
     * 根据id查找
     *
     * @param deleteStatus
     * @param id
     * @return
     */
    @Query("select e from UserAreaEntity e where e.isDel=:deleteStatus and e.id=:id")
    UserAreaEntity findById(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

    /**
     * 根据no查找
     *
     * @param deleteStatus
     * @param userNo
     * @return
     */
    @Query("select e from UserAreaEntity e where e.isDel=:deleteStatus and e.userNo=:userNo")
    List<UserAreaEntity> findByUserNo(@Param("deleteStatus") String deleteStatus, @Param("userNo") String userNo);

}

