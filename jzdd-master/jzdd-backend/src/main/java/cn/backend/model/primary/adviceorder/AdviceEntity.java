package cn.backend.model.primary.adviceorder;

import cn.backend.config.constant.SysConstant;
import cn.backend.config.swagger.DataType;
import cn.backend.model.BaseEntity;
import cn.zdwl.common.Logoutput.FiledLog;
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
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Date;

/**
 * @author sunkw
 * @date 2019/07/17
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "t_advice_order")
@ApiModel(value = "t_advice_order", description = "t_advice_order")
public class AdviceEntity extends BaseEntity {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "cn.zdwl.common.util.IdGenerator")
    @NotEmpty(message = "主键ID不能为空", groups = {DeleteGroup.class, UpdateGroup.class})
    @Column(name = "ADVICE_ID")
    @ApiModelProperty(value = "主键ID", dataType = DataType.STRING)
    private String id;

    /**
     * 公司编号
     */
    //@NotEmpty(message = "公司编号不能为空", groups = {UpdateGroup.class})
    @Column(name = "CORP_NO")
    // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "公司编号", dataType = DataType.STRING)
    private String corpNo;

    /**
     * 签收状态 01 未签收 02 签收 03 拒绝
     */
    // @NotEmpty(message = "签收状态不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "RECEIVE_STATUS")
    // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "签收状态 01 未签收 02 签收 03 拒绝", dataType = DataType.STRING)
    private String receiveStatus="01";

    /**
     * 签收状态 01 未签收 02 签收 03 拒绝
     */
    // @NotEmpty(message = "01 未签收 02 签收 03 拒绝不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "RECEIVE_STATUS_NAME")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @Dict(sourceType = SysConstant.InstallOrder.CONFIG_RECEIVESTATUS, sourceField = "receiveStatus")
    @ApiModelProperty(value = "签收状态", dataType = DataType.STRING)
    private String receiveStatusName;

    /**
     * 订单编码
     */
    @NotEmpty(message = "订单编码不能为空", groups = {UpdateGroup.class})
    @Column(name = "ADVICE_NO")
    // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "订单编码", dataType = DataType.STRING)
    private String adviceNo;

    /**
     * 安装订单编码
     */
    @NotEmpty(message = "安装订单编码不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "ORDER_NO")
    // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "安装订单编码", dataType = DataType.STRING)
    private String orderNo;

    /**
     * 联系客户时间
     */
    // @NotNull(message = "联系客户时间不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "CONTACT_CUST_TIME")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "联系客户时间", dataType = DataType.STRING)
    private Date contactCustTime;

    /**
     * 服务商编码
     */
    @NotEmpty(message = "服务商编码不能为空", groups = {UpdateGroup.class})
    @Column(name = "SUPPLIER_NO")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "服务商编码", dataType = DataType.STRING)
    private String supplierNo;

    /**
     * 服务商名称
     */
    @NotEmpty(message = "服务商名称不能为空", groups = {UpdateGroup.class})
    @Column(name = "SUPPLIER_NAME")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "服务商名称", dataType = DataType.STRING)
    private String supplierName;

    /**
     * 报修类别 01咨询 02报修
     */
    @NotEmpty(message = "报修类别不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "ADVICE_TYPE")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "报修类别 01咨询 02报修", dataType = DataType.STRING)
    private String adviceType;

    /**
     * 报修类别 01咨询 02报修
     */
    // @NotEmpty(message = "报修类别不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "ADVICE_TYPE_NAME")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @Dict(sourceType = SysConstant.Advice.CONFIG_TYPE_COMPLAINDESC, sourceField = "adviceType")
    @ApiModelProperty(value = "报修类别 01咨询 02报修", dataType = DataType.STRING)
    private String adviceTypeName;

    /**
     * 投诉内容
     */
    // @NotEmpty(message = "投诉内容不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "ADVICE_DESC")
    // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "投诉内容", dataType = DataType.STRING)
    private String adviceDesc;

    /**
     * 投诉备注
     */
    // @NotEmpty(message = "投诉备注不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "ADVICE_REMARK")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "投诉备注", dataType = DataType.STRING)
    private String adviceRemark;

    /**
     * 咨询时间 yyyymmdd hhmmss
     */
    // @NotNull(message = "咨询时间 yyyymmdd hhmmss不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "ADVICE_TIME")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "咨询时间 yyyymmdd hhmmss", dataType = DataType.STRING)
    private Date adviceTime;

    /**
     * 签收时间/拒绝时间 yyyymmdd hhmmss
     */
    // @NotNull(message = "签收时间/拒绝时间 yyyymmdd hhmmss不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "RECEIVE_TIME")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "签收时间/拒绝时间 yyyymmdd hhmmss", dataType = DataType.STRING)
    private Date receiveTime;

    /**
     * 解决时间 yyyymmdd hhmmss
     */
    // @NotNull(message = "解决时间 yyyymmdd hhmmss不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "SOLUTION_TIME")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "解决时间 yyyymmdd hhmmss", dataType = DataType.STRING)
    private Date solutionTime;

    /**
     * 签收备注或拒绝备注
     */
    // @NotEmpty(message = "签收备注或拒绝备注不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "RECEIVE_DESC")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "签收备注或拒绝备注", dataType = DataType.STRING)
    private String receiveDesc;

    /**
     * 解决方案
     */
    // @NotEmpty(message = "解决方案不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "SOLUTION_DESC")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "解决方案", dataType = DataType.STRING)
    private String solutionDesc;

    @Column(name = "SOLUTION_SUPPLIER_NO")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "解决服务商编码", dataType = DataType.STRING)
    private String solutionSupplierNo;

    @Column(name = "SOLUTION_SUPPLIER_NAME")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "解决服务商名称", dataType = DataType.STRING)
    private String solutionSupplierName;

    /**
     * 备注
     */
    // @NotEmpty(message = "备注不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "SOLUTION_REMARK")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "备注", dataType = DataType.STRING)
    private String solutionRemark;

    /**
     * 车主名称
     */
    //@NotEmpty(message = "车主名称不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "CAR_OWNER")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "车主名称", dataType = DataType.STRING)
    private String carOwner;

    /**
     * 车主电话
     */
    // @NotEmpty(message = "车主电话不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "CAR_OWNER_PHONE")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "车主电话", dataType = DataType.STRING)
    private String carOwnerPhone;

    /**
     * 车架号
     */
    // @NotEmpty(message = "车架号不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "VIN_NO")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "车架号", dataType = DataType.STRING)
    private String vinNo;

    /**
     * 是否完成 Y 是 N 否
     */
    // @NotEmpty(message = "是否完成 Y 是 N 否不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "FINISH_FLG")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "是否完成 Y 是 N 否", dataType = DataType.STRING)
    private String finishFlg;

    /**
     * 安装完成时间
     */
    @Column(name = "FINISH_TIME")
    @ApiModelProperty(value = "安装完成时间", dataType = DataType.LOCAL_DATE_TIME)
    private Date finishTime;

    /**
     * 编号
     */
    // @NotEmpty(message = "编号不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "SCRM_NO")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "编号", dataType = DataType.STRING)
    private String scrmNo;

    /**
     * 当前步骤
     */
    @Transient
    private Integer currentStep;

    /**
     * 是否撤回
     */
    @Transient
    private String rollBackFlg;

    /**
     * 回撤备注
     */
    @Transient
    private String remark;

    // /**
    //  * 超时开始时间
    //  */
    // @Transient
    // private Date timeoutStartTime;
    //
    // /**
    //  * 超时开始时间
    //  */
    // @Transient
    // private Date timeoutEndTime;

    /**
     * 签收状态 01通过 02拒绝
     */
//    @Transient
//    @JsonIgnore
//    private String receiveStatus = "01";

    @Transient
    @JsonIgnore
    private Date baseTime;

    @Transient
    @JsonIgnore
    private Date EndTime;


}

