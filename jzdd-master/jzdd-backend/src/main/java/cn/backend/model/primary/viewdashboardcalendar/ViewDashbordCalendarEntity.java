package cn.backend.model.primary.viewdashboardcalendar;

import cn.backend.config.swagger.DataType;
import cn.backend.model.BaseEntity;
import cn.zdwl.common.validgroup.CreateGroup;
import cn.zdwl.common.validgroup.DeleteGroup;
import cn.zdwl.common.validgroup.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@Table(name = "v_dashboard_calendar")
@ApiModel(value = "v_dashboard_calendar", description = "任务日历的视图")
public class ViewDashbordCalendarEntity extends BaseEntity {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "cn.zdwl.common.util.IdGenerator")
    @NotEmpty(message = "主键ID不能为空", groups = {DeleteGroup.class, UpdateGroup.class})
    @Column(name = "ORDER_ID")
    @ApiModelProperty(value = "主键ID", dataType = DataType.STRING)
    private String id;

    /**
     * 订单编号
     */
    @NotEmpty(message = "订单编号不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "ORDER_NO")
    // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "订单编号", dataType = DataType.STRING)
    private String orderNo;

    /**
     * 公司编号
     */
    @Column(name = "CORP_NO")
    @ApiModelProperty(value = "公司编号", dataType = DataType.STRING)
    private String corpNo;

    /**
     * 预约时间 yyyymmdd hhmmss
     */
    @Column(name = "APPOINTMENT_TIME")
    @ApiModelProperty(value = "预约时间", dataType = DataType.STRING)
    private Date appointmentTime;


    /**
     * 预约时间 yyyymmdd hhmmss
     */
    @Column(name = "APPOINTMENT_DATE")
    @ApiModelProperty(value = "预约时间", dataType = DataType.STRING)
    private Date appointmentDate;

    /**
     * 步骤名称
     */
    @NotEmpty(message = "步骤名称不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "STEP_NAME")
    @ApiModelProperty(value = "步骤名称", dataType = DataType.STRING)
    private String stepName;

    /**
     * 流程编码
     */
    @NotEmpty(message = "流程编码不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "FOLLOW_CODE")
    @ApiModelProperty(value = "流程编码", dataType = DataType.STRING)
    private String followCode;

    /**
     * 步骤矩阵
     */
    @NotEmpty(message = "步骤矩阵", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "STEP_MATRIX")
    @ApiModelProperty(value = "步骤矩阵", dataType = DataType.STRING)
    private String stepMatrix;

    /**
     * 服务商编码
     */
    @Column(name = "SUPPLIER_NO")
    @ApiModelProperty(value = "服务商编号", dataType = DataType.STRING)
    private String supplierNo;

    /**
     * 职位
     */
    @Column(name = "POSITION_CODES")
    @ApiModelProperty(value = "职位", dataType = DataType.STRING)
    private String positionCodes;


}
