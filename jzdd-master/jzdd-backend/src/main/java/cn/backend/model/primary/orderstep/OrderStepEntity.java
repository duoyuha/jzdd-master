package cn.backend.model.primary.orderstep;

import cn.backend.config.constant.SysConstant;
import cn.backend.config.swagger.DataType;
import cn.backend.model.BaseEntity;
import cn.backend.model.primary.viewdeliveryorder.ViewDeliveryOrderEntity;
import cn.backend.model.primary.viewinstallorder.ViewInstallOrderEntity;
import cn.zdwl.common.converters.dictconvert.Dict;
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
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 *
 * @author sunkw
 * @date 2019/07/16
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "t_order_step")
@ApiModel(value = "t_order_step", description = "订单步骤")
public class OrderStepEntity extends BaseEntity{

    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "cn.zdwl.common.util.IdGenerator")
    @NotEmpty(message = "主键ID不能为空",groups = {DeleteGroup.class,  UpdateGroup.class})
    @Column(name = "STEP_ID")
    @ApiModelProperty(value = "主键ID", dataType = DataType.STRING)
    private String id;

    /**
     * 流程编号
     */
    @NotEmpty(message = "流程编号不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="FOLLOW_NO")
    @ApiModelProperty(value = "流程编号", dataType = DataType.STRING)
    private String followNo;

    /**
     * 订单编码
     */
    @NotEmpty(message = "订单编码不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="ORDER_NO")
    @ApiModelProperty(value = "订单编码", dataType = DataType.STRING)
    private String orderNo;

    /**
     * 公司NO
     */
    //@NotEmpty(message = "{message.CustomMessage.RolecorpNoNotEmpty.body}",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="CORP_NO")
    @ApiModelProperty(value = "公司NO", dataType = DataType.STRING)
    private String corpNo;

    /**
     * 流程编码：01 安装订单 02 配送订单 03 投诉 04 报修 05 作废
     */
    @NotEmpty(message = "流程编码：01 安装订单 02 配送订单 03 投诉 04 报修 05 作废不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="FOLLOW_CODE")
    @ApiModelProperty(value = "流程编码：01 安装订单 02 配送订单 03 投诉 04 报修 05 作废", dataType = DataType.STRING)
    private String followCode;

    /**
     * 流程名称 01 安装订单 02 配送订单 03 投诉 04 报修 05 作废
     */
    @NotEmpty(message = "流程名称 01 安装订单 02 配送订单 03 投诉 04 报修 05 作废不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="FOLLOW_NAME")
    @Dict(sourceType = SysConstant.WorkFollow.CONFIG_TYPE_WORKFOLLOW, sourceField = "followCode")
    @ApiModelProperty(value = "流程名称 01 安装订单 02 配送订单 03 投诉 04 报修 05 作废", dataType = DataType.STRING)
    private String followName;

    /**
     * 本实体记录的唯一标识，产生规则为流水号
     */
    @NotEmpty(message = "本实体记录的唯一标识，产生规则为流水号不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="DETAIL_ID")
    @ApiModelProperty(value = "本实体记录的唯一标识，产生规则为流水号", dataType = DataType.STRING)
    private String detailId;

    /**
     * 流程明细编号
     */
    @NotEmpty(message = "流程明细编号不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="DETAIL_NO")
    @ApiModelProperty(value = "流程明细编号", dataType = DataType.STRING)
    private String detailNo;

    /**
     * 业务流程号
     */
    @NotNull(message = "业务流程号不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="STEP_SEQ")
    @ApiModelProperty(value = "业务流程号", dataType = DataType.INT)
    private Integer stepSeq;

    /**
     * 步骤号
     */
    @NotNull(message = "步骤号不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="FOLLOW_SEQ")
    @ApiModelProperty(value = "步骤号", dataType = DataType.INT)
    private Integer followSeq;

    /**
     * 步骤名称
     */
    @NotNull(message = "步骤名称不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="STEP_NAME")
    @ApiModelProperty(value = "步骤名称", dataType = DataType.STRING)
    private String stepName;

    /**
     * 是否允许作废:Y 是 N 否
     */
    @NotEmpty(message = "是否允许作废不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="CANCLE_FLG")
    @ApiModelProperty(value = "是否允许作废:Y 是 N 否", dataType = DataType.STRING)
    private String cancleFlg;

    /**
     * 是否审核:Y 是 N 否
     */
    @NotEmpty(message = "是否审核不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="CHECK_FLG")
    @ApiModelProperty(value = "是否允许作废:Y 是 N 否", dataType = DataType.STRING)
    private String checkFlg;

    /**
     * 审批职位：01 充电经理  02 整车事业部经理  03 安装服务商负责人 04 勘察人员 05 安装人员
     */
    @NotEmpty(message = "审批职位：01 充电经理  02 整车事业部经理  03 安装服务商负责人 04 勘察人员 05 安装人员 不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="POSITION_CODES")
    @ApiModelProperty(value = "审批职位：01 充电经理  02 整车事业部经理  03 安装服务商负责人 04 勘察人员 05 安装人员 ", dataType = DataType.STRING)
    private String positionCodes;

    /**
     * 是否当前步骤:Y 是 N 否
     */
    @NotEmpty(message = "是否当前步骤:Y 是 N 否不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="CURRENT_STEP_FLG")
    @ApiModelProperty(value = "是否当前步骤:Y 是 N 否", dataType = DataType.STRING)
    private String currentStepFlg;

    /**
     * 操作时间
     */
  //  @NotNull(message = "操作时间不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="STEP_TIME")
    @ApiModelProperty(value = "操作时间", dataType = DataType.STRING)
    private Date stepTime;

    /**
     * 操作人
     */
    @NotEmpty(message = "操作人不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="STEP_USER_NO")
    @ApiModelProperty(value = "操作人", dataType = DataType.STRING)
    private String stepUserNo;

    /**
     * 操作人
     */
    @NotEmpty(message = "操作人不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="STEP_USER_NAME")
    @ApiModelProperty(value = "操作人", dataType = DataType.STRING)
    private String stepUserName;

    /**
     * 操作日志
     */
    @NotEmpty(message = "操作日志不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="STEP_REMARK")
    @ApiModelProperty(value = "操作日志", dataType = DataType.STRING)
    private String stepRemark;

    /**
     * 审批结果：01 同意 02 拒绝
     */
    @NotEmpty(message = "审批结果：01 同意 02 拒绝不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="RESULT_CODE")
    @ApiModelProperty(value = "审批结果：01 同意 02 拒绝", dataType = DataType.STRING)
    private String resultCode;

    /**
     * 审批结果 01 同意 02 拒绝
     */
    @NotEmpty(message = "审批结果 01 同意 02 拒绝不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="RESULT_NAME")
    @Dict(sourceType = SysConstant.OrderStep.CONFIG_TYPE_CHECK, sourceField = "resultCode")
    @ApiModelProperty(value = "审批结果 01 同意 02 拒绝", dataType = DataType.STRING)
    private String resultName;

    /**
     * 超时状态：01 超时 02 未超时
     */
    @NotEmpty(message = "超时状态：01 超时 02 未超时不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="TIMEOUT_STATUS")
    @ApiModelProperty(value = "超时状态：01 超时 02 未超时", dataType = DataType.STRING)
    private String timeoutStatus;

    /**
     * 超时状态：01 超时 02 未超时
     */
    @NotEmpty(message = "超时状态：01 超时 02 未超时不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="TIMEOUT_STATUS_NAME")
    @Dict(sourceType = SysConstant.OrderStep.CONFIG_TYPE_TIMEOUT, sourceField = "timeoutStatus")
    @ApiModelProperty(value = "超时状态：01 超时 02 未超时", dataType = DataType.STRING)
    private String timeoutStatusName;

    /**
     * 基准时间
     */
    @NotEmpty(message = "基准时间不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="BASE_TIME")
    @ApiModelProperty(value = "基准时间", dataType = DataType.STRING)
    private Date baseTime;

    /**
     * 是否检查超时:Y 是 N 否
     */
    @NotEmpty(message = "是否检查超时:Y 是 N 否不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="TIMEOUT_FLG")
    @ApiModelProperty(value = "是否检查超时:Y 是 N 否", dataType = DataType.STRING)
    private String timeOutFlg;

    /**
     * 超时时间，单位：小时
     */
    @NotEmpty(message = "超时时间，单位：小时不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="TIMEOUT_RADIO")
    @ApiModelProperty(value = "超时时间，单位：小时", dataType = DataType.STRING)
    private Integer timeOutRadio;

    /**
     * 超时时间
     */
    @NotEmpty(message = "超时时间不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="OUTTIME")
    @ApiModelProperty(value = "超时时间", dataType = DataType.INT)
    private Integer outTime;

    /**
     * 审批备注
     */
    @NotEmpty(message = "审批备注不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="RESULT_DESC")
    @ApiModelProperty(value = "审批备注", dataType = DataType.STRING)
    private String resultDesc;

    /**
     * 是否服务商确认:Y 是 N 否
     */
    @NotEmpty(message = "是否服务商确认不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="CONFIRM_STEP")
    @ApiModelProperty(value = "是否服务商确认:Y 是 N 否", dataType = DataType.STRING)
    private String confirmStep;

    /**
     * 是否最后一步:Y 最后一步 N 不是最后一步
     */
    @NotEmpty(message = "是否最后一步:Y 最后一步 N 不是最后一步不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="LAST_STEP")
    @ApiModelProperty(value = "是否最后一步:Y 最后一步 N 不是最后一步", dataType = DataType.STRING)
    private String lastStep;


    /**
     * 是否回退:Y 是 N 否
     */
    @NotEmpty(message = "是否回退:Y 是 N 否不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="ROLLBACK_FLG")
    @ApiModelProperty(value = "是否回退:Y 是 N 否", dataType = DataType.STRING)
    private String rollbackFlg;

    /**
     * 回退步骤
     */
    @NotNull(message = "回退步骤不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="ROLLBACK_SEQ")
    @ApiModelProperty(value = "回退步骤", dataType = DataType.STRING)
    private Integer rollbackSeq;

    /**
     * 上级步骤
     */
    @NotNull(message = "上级步骤不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="PARENT_SEQ")
    @ApiModelProperty(value = "上级步骤", dataType = DataType.STRING)
    private Integer parentSeq;

    /**
     * 步骤矩阵
     */
    @NotEmpty(message = "步骤矩阵",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="STEP_MATRIX")
    @ApiModelProperty(value = "步骤矩阵", dataType = DataType.STRING)
    private String stepMatrix;


    @Transient
    private ViewDeliveryOrderEntity deliveryOrderEntity;

    @Transient
    private ViewInstallOrderEntity installOrderEntity;

    /**
     * 提报时间
     * 孙魁伟 add 为了仪表盘显示
     */
    @Transient
    private  Date orderTime;


    /**
     * 业务id
     * 孙魁伟 add 为了仪表盘显示
     */
    @Transient
    private  String orderId;










}

