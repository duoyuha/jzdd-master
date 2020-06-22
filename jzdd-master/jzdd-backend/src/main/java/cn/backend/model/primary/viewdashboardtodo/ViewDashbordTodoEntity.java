package cn.backend.model.primary.viewdashboardtodo;

import cn.backend.config.swagger.DataType;
import cn.backend.model.BaseEntity;
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
@Table(name = "v_dashboard_todo")
@ApiModel(value = "v_dashboard_todo", description = "待办事项的视图")
public class ViewDashbordTodoEntity extends BaseEntity {

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
    @Column(name = "ORDER_NO")
    @ApiModelProperty(value = "订单编号", dataType = DataType.STRING)
    private String orderNo;

    /**
     * 公司编号
     */
    @Column(name = "CORP_NO")
    @ApiModelProperty(value = "公司编号", dataType = DataType.STRING)
    private String corpNo;

    /**
     * 订单时间 yyyymmdd hhmmss
     */
    @Column(name = "ORDER_TIME")
    @ApiModelProperty(value = "订单时间", dataType = DataType.STRING)
    private Date orderTime;


    /**
     * 流程编码：01 安装订单 02 配送订单 03 投诉 04 报修 05 作废
     */
    @Column(name = "FOLLOW_CODE")
    @ApiModelProperty(value = "流程编码：01 安装订单 02 配送订单 03 投诉 04 报修 05 作废", dataType = DataType.STRING)
    private String followCode;

    /**
     * 流程名称 01 安装订单 02 配送订单 03 投诉 04 报修 05 作废
     */
    @Column(name = "FOLLOW_NAME")
    @ApiModelProperty(value = "流程名称 01 安装订单 02 配送订单 03 投诉 04 报修 05 作废", dataType = DataType.STRING)
    private String followName;

    /**
     * 步骤号
     */
    @Column(name = "FOLLOW_SEQ")
    @ApiModelProperty(value = "步骤号", dataType = DataType.INT)
    private Integer followSeq;

    /**
     * 步骤名称
     */
    @Column(name = "STEP_NAME")
    @ApiModelProperty(value = "步骤名称", dataType = DataType.STRING)
    private String stepName;

    /**
     * 服务商编码
     */
    @Column(name = "SUPPLIER_NO")
    @ApiModelProperty(value = "服务商编号", dataType = DataType.STRING)
    private String supplierNo;

    /**
     * 服务商名称
     */
    @Column(name = "SUPPLIER_NAME")
    @ApiModelProperty(value = "服务商编号", dataType = DataType.STRING)
    private String supplierName;

    /**
     * 职位
     */
    @Column(name = "POSITION_CODES")
    @ApiModelProperty(value = "职位", dataType = DataType.STRING)
    private String positionCodes;

    /**
     * 是否完成
     */
    @Column(name = "FINISH_FLG")
    @ApiModelProperty(value = "是否完成", dataType = DataType.STRING)
    private String finishFlg;


}
