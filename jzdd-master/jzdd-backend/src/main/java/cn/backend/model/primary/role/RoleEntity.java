package cn.backend.model.primary.role;

import cn.backend.config.swagger.DataType;
import cn.zdwl.common.validgroup.CreateGroup;
import cn.zdwl.common.validgroup.DeleteGroup;
import cn.zdwl.common.validgroup.UpdateGroup;
import cn.backend.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Set;

/**
 *
 * @author xsj
 * @date 2019/03/13
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "s_role")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "cn.backend.model.primary.role")
@ApiModel(value = "role", description = "角色对象")
public class RoleEntity extends BaseEntity{

    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "cn.zdwl.common.util.IdGenerator")
    @NotEmpty(message = "{message.CustomMessage.RoleroleIdNotEmpty.body}",groups = {DeleteGroup.class,  UpdateGroup.class})
    @Column(name = "ROLE_ID")
    @ApiModelProperty(value = "主键ID", dataType = DataType.STRING)
    private String roleId;

    /**
     * 角色名
     */
    @NotEmpty(message = "{message.CustomMessage.RoleroleNameNotEmpty.body}",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="ROLE_NAME")
    @ApiModelProperty(value = "角色名", dataType = DataType.STRING)
    private String roleName;

    /**
     * 公司NO
     */
    //@NotEmpty(message = "{message.CustomMessage.RolecorpNoNotEmpty.body}",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="CORP_NO")
    @ApiModelProperty(value = "公司NO", dataType = DataType.STRING)
    private String corpNo;

    /**
     * 菜单ids
     */
    @Transient
    private Set<String> menuIds;

    /**
     * 菜单关系是否变化（修改角色菜单关系时）
     */
    @Transient
    @ApiModelProperty(value = "菜单ID拼接以逗号做分割", dataType = DataType.STRING)
    private Boolean menuIsChange;


}

