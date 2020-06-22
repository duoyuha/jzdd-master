package cn.backend.dao.primary.menu;

import cn.backend.model.primary.menu.MenuEntity;
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
@Repository("menuRepository")
public interface MenuRepository extends JpaRepository<MenuEntity, String>, JpaSpecificationExecutor<MenuEntity> {

    /**
     * 查找全部
     * @param deleteStatus
     * @return
     */
    @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
    @Query("select e from MenuEntity e where e.isDel=:deleteStatus")
    List<MenuEntity> findAll(@Param("deleteStatus") String deleteStatus);

    /**
     * 删除
     * @param deleteStatus
     * @param id
     * @return
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update MenuEntity e set e.isDel=:deleteStatus where e.id=:id")
    int delete(@Param("deleteStatus") String deleteStatus, @Param("id") String id);

    /**
     * 根据id查找
     * @param deleteStatus
     * @param id
     * @return
     */
    @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })
    @Query("select e from MenuEntity e where e.isDel=:deleteStatus and e.id=:id")
    MenuEntity findById(@Param("deleteStatus") String deleteStatus, @Param("id") String id);


    /**
     * 统计查询菜单数
     * @param ids
     * @return
     */
    @Query("select count(e) from MenuEntity e where e.menuId in (:ids)")
    int getMenuCountBySet(@Param("ids") Set<String> ids);

    /**
     * 根据角色查找菜单列表
     * @param roleId
     * @return
     */
    @Query(value = "SELECT " +
            "m.* " +
            "FROM " +
            "s_menu m, " +
            "s_role_menu rm " +
            "WHERE " +
            "m.MENU_ID = rm.MENU_ID " +
            "AND m.IS_DEL = ?1 " +
            "AND rm.IS_DEL = ?1 " +
            "AND rm.ROLE_ID = ?2",nativeQuery = true)
    List<MenuEntity> findByRoleId(@Param("deleteStatus") String deleteStatus, @Param("roleId") String roleId);

}

