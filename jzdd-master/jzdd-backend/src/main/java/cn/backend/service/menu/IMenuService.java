package cn.backend.service.menu;

import cn.backend.model.primary.menu.MenuEntity;
import cn.backend.model.primary.menu.MenuQuery;
import cn.backend.service.IBaseService;

import java.util.List;
import java.util.Set;

/**
 *
 * @author xsj
 * @date 2019/03/13
 */
public interface IMenuService extends IBaseService<MenuEntity,MenuQuery>{


    /**
     * 根据判断ids是否存在
     * @param menuIds
     * @return
     */
    boolean checkMenuExistsByIds(Set<String> menuIds);

    /**
     * 根据角色获取菜单
     * @param roleId
     * @return
     */
    List<MenuEntity> findByRoleEntity(String roleId);

}

