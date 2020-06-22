package cn.backend.model.primary.rolemenu;

import cn.zdwl.common.validgroup.DeleteGroup;
import cn.zdwl.common.validgroup.UpdateGroup;
import cn.backend.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.annotations.Cache;

import javax.persistence.*;

/**
 *
 * @author xsj
 * @date 2019/03/13
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "s_role_menu")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "cn.backend.model.primary.rolemenu")
public class RoleMenuEntity extends BaseEntity{

    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "cn.zdwl.common.util.IdGenerator")
    @NotEmpty(message = "{message.CustomMessage.RoleMenuroleMenuIdNotEmpty.body}",groups = {DeleteGroup.class,  UpdateGroup.class})
    @Column(name = "ROLE_MENU_ID")
    private String roleMenuId;

    /**
     * 菜单ID
     */
    //@NotEmpty(message = "{message.CustomMessage.RoleMenumenuIdNotEmpty.body}",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="MENU_ID")
    private String menuId;

    /**
     * 角色ID
     */
    //@NotEmpty(message = "{message.CustomMessage.RoleMenuroleIdNotEmpty.body}",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="ROLE_ID")
    private String roleId;


}

