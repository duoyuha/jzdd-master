package cn.backend.dao.primary.rolemenu;

import cn.backend.model.primary.rolemenu.RoleMenuEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

/**
 *
 * @author xsj
 * @date 2019/03/13
 */
@Repository("roleMenuRepository")
public interface RoleMenuRepository extends JpaRepository<RoleMenuEntity, String>, JpaSpecificationExecutor<RoleMenuEntity> {

    /**
     * 查找全部
     * @param deleteStatus
     * @return
     */
    @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
    @Query("select e from RoleMenuEntity e where e.isDel=:deleteStatus")
    List<RoleMenuEntity> findAll(@Param("deleteStatus") String deleteStatus);

    /**
     * 删除
     * @param deleteStatus
     * @param id
     * @return
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update RoleMenuEntity e set e.isDel=:deleteStatus where e.id=:id")
    int delete(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

    /**
     * 根据id查找
     * @param deleteStatus
     * @param id
     * @return
     */
    @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
    @Query("select e from RoleMenuEntity e where e.isDel=:deleteStatus and e.id=:id")
    RoleMenuEntity findById(@Param("deleteStatus") String deleteStatus, @Param("id") String id);


    /**
     * 根据角色id查询菜单ids
     * @param deleteStatus
     * @param roleId
     * @return
     */
    @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
    @Query("select e.menuId from RoleMenuEntity e where e.isDel=:deleteStatus and e.roleId=:roleId")
    Set<String> findMenuIdsByRoleId(@Param("deleteStatus") String deleteStatus, @Param("roleId") String roleId);

    /**
     * 批量删除
     * @param deleteStatus
     * @param ids
     * @return
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update RoleMenuEntity e set e.isDel =:deleteStatus where e.menuId in (:ids) and e.roleId=:roleId")
    int deleteBySet(@Param("deleteStatus") String deleteStatus, @Param("ids") Set<String> ids , @Param("roleId")String roleId);

}

