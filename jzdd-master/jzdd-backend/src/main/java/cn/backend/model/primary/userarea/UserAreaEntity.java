package cn.backend.model.primary.userarea;

import cn.backend.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.persistence.*;
import cn.zdwl.common.Logoutput.FiledLog;
import cn.backend.config.constant.SysConstant;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;
import cn.zdwl.common.validgroup.CreateGroup;
import cn.zdwl.common.validgroup.DeleteGroup;
import cn.zdwl.common.validgroup.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import cn.backend.config.swagger.DataType;
import org.hibernate.annotations.Cache;
/**
 *
 * @author sunkw
 * @date 2019/10/08
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "s_user_area")
@ApiModel(value = "s_user_area", description = "s_user_area")
public class UserAreaEntity extends BaseEntity{

    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "cn.zdwl.common.util.IdGenerator")
    @NotEmpty(message = "主键ID不能为空",groups = {DeleteGroup.class,  UpdateGroup.class})
    @Column(name = "AREA_ID")
    @ApiModelProperty(value = "主键ID", dataType = DataType.STRING)
    private String id;

    /**
     * 用户账号
     */
    @NotEmpty(message = "用户账号不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="USER_ACC")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "用户账号", dataType = DataType.STRING)
    private String userAcc;

    /**
     * 用户编号
     */
    @NotEmpty(message = "用户编号不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="USER_NO")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "用户编号", dataType = DataType.STRING)
    private String userNo;

    /**
     * 公司编号
     */
    @NotEmpty(message = "公司编号不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="CORP_NO")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "公司编号", dataType = DataType.STRING)
    private String corpNo;

    /**
     * 省份
     */
    @NotEmpty(message = "省份不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="USER_PROVINCE")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "省份", dataType = DataType.STRING)
    private String userProvince;

    /**
     * 城市
     */
    @NotEmpty(message = "城市不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="USER_CITY")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "城市", dataType = DataType.STRING)
    private String userCity;

    /**
     * 区域
     */
    // @NotEmpty(message = "区域不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="USER_RAGION")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "区域", dataType = DataType.STRING)
    private String userRagion;

    /**
     * 备注
     */
    // @NotEmpty(message = "备注不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="REMARK")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "备注", dataType = DataType.STRING)
    private String remark;


}

