package cn.backend.model.primary.user;

import cn.backend.config.constant.SysConstant;
import cn.backend.config.swagger.DataType;
import cn.backend.model.BaseEntity;
import cn.backend.model.primary.userarea.UserAreaEntity;
import cn.zdwl.common.converters.dictconvert.Dict;
import cn.zdwl.common.validgroup.CreateGroup;
import cn.zdwl.common.validgroup.DeleteGroup;
import cn.zdwl.common.validgroup.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.List;

/**
 * @author sunkw
 * @date 2019/03/13
 */
@Getter
@Setter
@ToString
@Entity
@Where(clause = "IS_DEL='N'")
@Table(name = "s_user")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "cn.backend.model.primary.user")
@ApiModel(value = "user", description = "用户对象")
public class UserEntity extends BaseEntity {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "cn.zdwl.common.util.IdGenerator")
    @NotEmpty(message = "{message.CustomMessage.UseruserIdNotEmpty.body}", groups = {DeleteGroup.class, UpdateGroup.class})
    @Column(name = "USER_ID")
    @ApiModelProperty(value = "主键ID", dataType = DataType.STRING)
    private String userId;

    /**
     * 用户账号
     */
    @NotEmpty(message = "用户账号不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "USER_ACC")
    @ApiModelProperty(value = "用户账号", dataType = DataType.STRING)
    private String userAcc;

    /**
     * 用户编号
     */
    // @NotEmpty(message = "用户编号不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "USER_NO")
    @ApiModelProperty(value = "用户编号", dataType = DataType.STRING)
    private String userNo;

    /**
     * 用户名
     */
   // @NotEmpty(message = "用户名不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "USER_NAME")
    @ApiModelProperty(value = "用户名", dataType = DataType.STRING)
    private String userName;

    /**
     * 密码
     */
   // @NotEmpty(message = "密码不能为空", groups = {CreateGroup.class})
    @Column(name = "USER_PASSWORD")
    @ApiModelProperty(value = "密码", dataType = DataType.STRING)
    private String userPassword;

    /**
     * 用户邮箱
     */
    // @NotEmpty(message = "用户邮箱不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "USER_MAIL")
    @ApiModelProperty(value = "用户邮箱", dataType = DataType.STRING)
    private String userMail;

    /**
     * 用户手机号
     */
    //@NotEmpty(message = "用户手机号不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "USER_PHONE")
    @ApiModelProperty(value = "用户手机号", dataType = DataType.STRING)
    private String userPhone;

    /**
     * 公司编号
     */
    // @NotEmpty(message = "公司编号不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "CORP_NO")
    @ApiModelProperty(value = "公司编号", dataType = DataType.STRING)
    private String corpNo;

    /**
     * 角色ID
     */
   // @NotEmpty(message = "角色不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "ROLE_ID")
    @ApiModelProperty(value = "角色ID", dataType = DataType.STRING)
    private String roleId;

    /**
     * 角色名称
     */
    // @NotEmpty(message = "角色名称不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "ROLE_NAME")
    @ApiModelProperty(value = "角色名称", dataType = DataType.STRING)
    private String roleName;

    /**
     * 是否为管理员:Y /N
     */
  //  @NotEmpty(message = "是否为管理员不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "DATA_CHECK")
    @ApiModelProperty(value = "是否为管理员:Y /N", dataType = DataType.STRING)
    private String dataCheck;

    /**
     * 用户类型 01 充电经理 02 整车事业部经理 03 安装服务商
     */
   // @NotEmpty(message = "用户类型不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "USER_TYPE")
    @ApiModelProperty(value = "用户类型 01 充电经理 02 整车事业部经理 03 安装服务商", dataType = DataType.STRING)
    private String userType;

    /**
     * 用户类型 01 充电经理 02 整车事业部经理 03 安装服务商
     */
    // @NotEmpty(message = "用户类型 01 充电经理 02 整车事业部经理 03 安装服务商不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "USER_TYPE_NAME")
    @Dict(sourceType = SysConstant.User.CONFIG_USERPOSITION, sourceField = "userType")
    @ApiModelProperty(value = "用户类型 01 充电经理 02 整车事业部经理 03 安装服务商", dataType = DataType.STRING)
    private String userTypeName;

    /**
     * 服务商岗位 01 充电经理 02 整车事业部经理 03 安装服务商
     */
    //@NotEmpty(message = "服务商岗位不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "USER_POSITION")
    @ApiModelProperty(value = "服务商岗位 01 充电经理 02 整车事业部经理 03 安装服务商", dataType = DataType.STRING)
    private String userPosition;

    /**
     * 服务商岗位 01 充电经理 02 整车事业部经理 03 安装服务商
     */
    // @NotEmpty(message = "服务商岗位 01 充电经理 02 整车事业部经理 03 安装服务商不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "USER_POSITION_NAME")
    // @Dict(sourceType = SysConstant.User.CONFIG_USERPOSITION, sourceField = "userPosition")
    @ApiModelProperty(value = "服务商岗位 01 充电经理 02 整车事业部经理 03 安装服务商", dataType = DataType.STRING)
    private String userPositionName;

    /**
     * 安装服务商编号
     */
    @Column(name = "SUPPLER_NO")
    @ApiModelProperty(value = "安装服务商编号", dataType = DataType.STRING)
    private String supplierNo;

    /**
     * 证书路径
     */
    @Column(name = "CERTIFICATE_FILE")
    @ApiModelProperty(value = "证书路径", dataType = DataType.STRING)
    private String certificateFile;

    /**
     * 重置密码
     */
    @Transient
    @ApiModelProperty(value = "重置密码", dataType = DataType.STRING)
    private String restPassword;

    @Transient
    private String dealerID;

    /**
     * 区域信息-接收
     */
    @Transient
    private String area;

    /**
     * 施工部门
     */
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @Where(clause="IS_DEL='N'")
    @JoinColumn(name = "USER_NO", referencedColumnName = "USER_NO",insertable = false,updatable = false)
    private List<UserAreaEntity> areas;


}