package cn.backend.model.primary.role;

import cn.backend.model.BaseQuery;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author xsj
 * @date 2019/03/13
 */
@Getter
@Setter
public class RoleQuery extends BaseQuery{

    /**
     * 用户名
     */
    private String roleName;



}
