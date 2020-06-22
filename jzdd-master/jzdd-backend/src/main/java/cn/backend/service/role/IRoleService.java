package cn.backend.service.role;

import cn.backend.model.primary.role.RoleEntity;
import cn.backend.model.primary.role.RoleQuery;
import cn.backend.service.IBaseService;

/**
 *
 * @author xsj
 * @date 2019/03/13
 */
public interface IRoleService extends IBaseService<RoleEntity,RoleQuery>{


    /**
     * 根据id获取，附带菜单
     * @param id
     * @return
     */
    RoleEntity findByIdWithMenus(String id);

}

