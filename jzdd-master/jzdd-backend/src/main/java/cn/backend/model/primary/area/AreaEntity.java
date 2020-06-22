package cn.backend.model.primary.area;

import cn.backend.config.constant.SysConstant;
import cn.backend.config.swagger.DataType;
import cn.backend.model.BaseEntity;
import cn.zdwl.common.Logoutput.FiledLog;
import cn.zdwl.common.validgroup.CreateGroup;
import cn.zdwl.common.validgroup.DeleteGroup;
import cn.zdwl.common.validgroup.UpdateGroup;
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

/**
 * @author sunkw
 * @date 2019/07/17
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "m_area")
@ApiModel(value = "m_area", description = "m_area")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "cn.backend.model.primary.area")
public class AreaEntity extends BaseEntity {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "cn.zdwl.common.util.IdGenerator")
    @NotEmpty(message = "主键ID不能为空", groups = {DeleteGroup.class, UpdateGroup.class})
    @Column(name = "AREA_ID")
    @ApiModelProperty(value = "主键ID", dataType = DataType.STRING)
    private String id;

    /**
     * 地区编号
     */
    @NotEmpty(message = "地区编号不能为空", groups = {UpdateGroup.class})
    @Column(name = "AREA_NO")
    // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "地区编号", dataType = DataType.STRING)
    private String areaNo;

    /**
     * 地区名称
     */
    @NotEmpty(message = "地区名称不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "AREA_NAME")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "地区名称", dataType = DataType.STRING)
    private String areaName;

    /**
     * 邮政编码
     */
    @NotEmpty(message = "邮政编码不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "POST_CODE")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "邮政编码", dataType = DataType.STRING)
    private String postCode;

    /**
     * 上级邮政编码
     */
    @NotEmpty(message = "上级邮政编码不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "PARENT_POST_CODE")
    // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "上级邮政编码", dataType = DataType.STRING)
    private String parentPostCode;

    /**
     * 上级名称
     */
    @NotEmpty(message = "上级名称不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "PARENT_AREA_NAME")
    // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "上级名称", dataType = DataType.STRING)
    private String parentAreaName;

    /**
     * 地区等级 1省 2市 3区
     */
    @NotEmpty(message = "地区等级不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "AREA_LEVEL")
    // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "地区等级", dataType = DataType.STRING)
    private String areaLevel;

    /**
     * 地区区域 华中 华南 华北
     */
    @NotEmpty(message = "地区区域不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "AREA_ZONE")
    // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "地区区域", dataType = DataType.STRING)
    private String areaZone;


}

