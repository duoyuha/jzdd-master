package cn.backend.model.primary.vehicledetail;

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
 * @date 2019/08/17
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "m_vehicle_detail")
@ApiModel(value = "m_vehicle_detail", description = "m_vehicle_detail")
public class VehicleDetailEntity extends BaseEntity{

    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "cn.zdwl.common.util.IdGenerator")
    @NotEmpty(message = "主键ID不能为空",groups = {DeleteGroup.class,  UpdateGroup.class})
    @Column(name = "DETAIL_ID")
    @ApiModelProperty(value = "主键ID", dataType = DataType.STRING)
    private String id;

    /**
     * 公司编号
     */
  //  @NotEmpty(message = "公司编号不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="CORP_NO")
  //  @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "公司编号", dataType = DataType.STRING)
    private String corpNo;

    /**
     * 车型编码
     */
 //   @NotEmpty(message = "车型编码不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="VEHICLE_NO")
  //  @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "车型编码", dataType = DataType.STRING)
    private String vehicleNo;

    /**
     * 明细编码
     */
   // @NotEmpty(message = "明细编码不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="DETAIL_NO")
  //  @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "明细编码", dataType = DataType.STRING)
    private String detailNo;

    /**
     * 车型明细名称
     */
  //  @NotEmpty(message = "车型明细名称不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="DETAIL_NAME")
  //  @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "车型明细名称", dataType = DataType.STRING)
    private String detailName;

    /**
     * 是否二次安装:Y /N
     */
    // @NotEmpty(message = "是否二次安装:Y /N不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="SECOND_INSTALL")
    @ApiModelProperty(value = "是否二次安装:Y /N", dataType = DataType.STRING)
    private String secondInstall;

    // /**
    //  * 是否二次配送:Y /N
    //  */
    // // @NotEmpty(message = "是否二次配送:Y /N不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    // @Column(name="SECOND_DISTRIBUTION")
    // @ApiModelProperty(value = "是否二次配送:Y /N", dataType = DataType.STRING)
    // private String secondDistribution;

    /**
     * 备注
     */
   // @NotEmpty(message = "备注不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="REMARK")
  //  @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "备注", dataType = DataType.STRING)
    private String remark;


}

