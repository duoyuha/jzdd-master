package cn.backend.dao.primary.role;

import cn.backend.model.primary.role.RoleEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import javax.transaction.Transactional;
import java.util.List;

/**
 *
 * @author xsj
 * @date 2019/03/13
 */
@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<RoleEntity, String>, JpaSpecificationExecutor<RoleEntity> {

    /**
     * 查找全部
     * @param deleteStatus
     * @return
     */
    @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
    @Query("select e from RoleEntity e where e.isDel=:deleteStatus")
    List<RoleEntity> findAll(@Param("deleteStatus") String deleteStatus);

    /**
     * 删除
     * @param deleteStatus
     * @param id
     * @return
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update RoleEntity e set e.isDel=:deleteStatus where e.id=:id")
    int delete(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

    /**
     * 根据id查找
     * @param deleteStatus
     * @param id
     * @return
     */
    @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
    @Query("select e from RoleEntity e where e.isDel=:deleteStatus and e.id=:id")
    RoleEntity findById(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

    /**
     * 根据角色名查询
     *
     * @param roleName
     * @param corpNo
     * @param deleteStatus
     * @return
     */
    @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
    @Query("select e from RoleEntity e where e.isDel=:deleteStatus and e.roleName=:roleName and e.corpNo=:corpNo")
    RoleEntity findByNameAndCorp(@Param("deleteStatus") String deleteStatus,@Param("roleName") String roleName, @Param("corpNo") String corpNo);


}

