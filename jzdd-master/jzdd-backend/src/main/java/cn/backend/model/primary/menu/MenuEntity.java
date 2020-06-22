package cn.backend.model.primary.menu;

import cn.backend.config.swagger.DataType;
import cn.zdwl.common.validgroup.DeleteGroup;
import cn.zdwl.common.validgroup.UpdateGroup;
import cn.backend.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@Table(name = "s_menu")
@ApiModel(value = "menu", description = "菜单对象")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "cn.backend.model.primary.menu")
public class MenuEntity extends BaseEntity{

    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "cn.zdwl.common.util.IdGenerator")
    @NotEmpty(message = "{message.CustomMessage.MenumenuIdNotEmpty.body}",groups = {DeleteGroup.class,  UpdateGroup.class})
    @Column(name = "MENU_ID")
    @ApiModelProperty(value = "主键ID", dataType = DataType.STRING)
    private String menuId;

    /**
     * 菜单名
     */
    //@NotEmpty(message = "{message.CustomMessage.MenumenuNameNotEmpty.body}",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="MENU_NAME")
    @ApiModelProperty(value = "菜单名", dataType = DataType.STRING)
    private String menuName;

    /**
     * 路径
     */
    //@NotEmpty(message = "{message.CustomMessage.MenutargetUrlNotEmpty.body}",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="TARGET_URL")
    @ApiModelProperty(value = "路径", dataType = DataType.STRING)
    private String targetUrl;

    /**
     * 菜单类名
     */
    //@NotEmpty(message = "{message.CustomMessage.MenumenuClassNotEmpty.body}",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="MENU_CLASS")
    @ApiModelProperty(value = "菜单类名", dataType = DataType.STRING)
    private String menuClass;

    /**
     * 菜单层级
     */
    //@NotNull(message = "{message.CustomMessage.MenumenuLevelNotEmpty.body}",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="MENU_LEVEL")
    @ApiModelProperty(value = "菜单层级", dataType = DataType.INT)
    private Integer menuLevel;

    /**
     * 排序
     */
    //@NotNull(message = "{message.CustomMessage.MenusortNotEmpty.body}",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="SORT")
    @ApiModelProperty(value = "排序", dataType = DataType.INT)
    private Integer sort;

    /**
     * 父级ID
     */
    //@NotEmpty(message = "{message.CustomMessage.MenuparentIdNotEmpty.body}",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="PARENT_ID")
    @ApiModelProperty(value = "父级ID", dataType = DataType.STRING)
    private String parentId;

    /**
     * 菜单类型: 01 菜单 02 按钮
     */
    //@NotEmpty(message = "{message.CustomMessage.MenumenuTypeNotEmpty.body}",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="MENU_TYPE")
    @ApiModelProperty(value = "菜单类型 01 菜单 02 按钮", dataType = DataType.STRING)
    private String menuType;

    /**
     * 菜单类型: 01 菜单 02 按钮
     */
    //@NotEmpty(message = "{message.CustomMessage.MenumenuTypeNameNotEmpty.body}",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="MENU_TYPE_NAME")
    @ApiModelProperty(value = "菜单类型名称 01 菜单 02 按钮", dataType = DataType.STRING)
    private String menuTypeName;


}

