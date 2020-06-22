package cn.backend.service.rolemenu;

import cn.backend.model.primary.rolemenu.RoleMenuEntity;
import cn.backend.model.primary.rolemenu.RoleMenuQuery;
import cn.backend.service.IBaseService;

import java.util.List;
import java.util.Set;

/**
 *
 * @author xsj
 * @date 2019/03/13
 */
public interface IRoleMenuService extends IBaseService<RoleMenuEntity,RoleMenuQuery>{


    /**
     * 根据角色id查询菜单ids
     *
     * @param roleId
     * @return
     */
    Set<String> findMenuIdsByRoleId(String roleId);

    /**
     * 批量删除
     *
     * @param deleteSet
     * @param roleId
     * @return
     */
    boolean deleteBySet(Set<String> deleteSet, String roleId);

    /**
     * 批量更新
     * @param entityList
     * @return
     */
    List<RoleMenuEntity> save(List<RoleMenuEntity> entityList);

}

