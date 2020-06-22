package cn.backend.model.primary.settle;

import cn.backend.config.constant.SysConstant;
import cn.backend.config.swagger.DataType;
import cn.backend.model.BaseEntity;
import cn.backend.model.primary.settledetail.SettleDetailEntity;
import cn.zdwl.common.Logoutput.FiledLog;
import cn.zdwl.common.converters.dictconvert.Dict;
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
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author James
 * @date 2019/07/29
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "t_settle")
@ApiModel(value = "t_settle", description = "t_settle")
public class SettleEntity extends BaseEntity {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "cn.zdwl.common.util.IdGenerator")
    @NotEmpty(message = "{message.CustomMessage.settleIdNotEmpty.body}",groups = {DeleteGroup.class,  UpdateGroup.class})
    @Column(name = "SETTLE_ID")
    @ApiModelProperty(value = "主键ID", dataType = DataType.STRING)
    private String id;

    /**
     * 公司编号
     */
   // @NotEmpty(message = "{message.CustomMessage.SettlecorpNoNotEmpty.body}",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="CORP_NO")
    @ApiModelProperty(value = "公司编号", dataType = DataType.STRING)
    private String corpNo;

    @Column(name="ORDER_NO")
    @ApiModelProperty(value = "订单编码", dataType = DataType.STRING)
    private String orderNo;

    /**
     * 结算单编号
     */
    @Column(name="SETTLE_NO")
    @ApiModelProperty(value = "订单编码", dataType = DataType.STRING)
    private String settleNo;

    /**
     * 结算单状态：01 有效 02 无效
     */
    @Column(name="SETTLE_STATUS")
    @ApiModelProperty(value = "结算单状态", dataType = DataType.STRING)
    private String settleStatus;



    @Column(name="FINISH_TIME")
    @ApiModelProperty(value = "完成时间", dataType = DataType.LOCAL_DATE)
    private Date finishTime;

    /**
     * 车厂审核 01 未审核 02 审核通过 03 审核拒绝
     */
    // @NotEmpty(message = "车厂审核不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "FIRST_VERIFY_FLG")
    // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "车厂审核 01 未审核 02 审核通过 03 审核拒绝", dataType = DataType.STRING)
    private String firstVerifyFlg;

    /**
     * 车厂审核 01 未审核 02 审核通过 03 审核拒绝
     */
    // @NotEmpty(message = "车厂审核 01 未审核 02 审核通过 03 审核拒绝不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "FIRST_VERIFY_FLG_NAME")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @Dict(sourceType = SysConstant.InstallOrder.CONFIG_VERIFY, sourceField = "verifyFlg")
    @ApiModelProperty(value = "车厂审核", dataType = DataType.STRING)
    private String firstVerifyFlgFlgName;

    /**
     * 审核备注
     */
    // @NotEmpty(message = "审核备注不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "FIRST_VERIFY_REMARK")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "审核备注", dataType = DataType.STRING)
    private String firstVerifyRemark;

    /**
     * 车厂审核 01 未审核 02 审核通过 03 审核拒绝
     */
    // @NotEmpty(message = "车厂审核不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "SECOND_VERIFY_FLG")
    // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "车厂审核 01 未审核 02 审核通过 03 审核拒绝", dataType = DataType.STRING)
    private String secondVerifyFlg;

    /**
     * 车厂审核 01 未审核 02 审核通过 03 审核拒绝
     */
    // @NotEmpty(message = "车厂审核 01 未审核 02 审核通过 03 审核拒绝不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "SECOND_VERIFY_FLG_NAME")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @Dict(sourceType = SysConstant.InstallOrder.CONFIG_VERIFY, sourceField = "verifyFlg")
    @ApiModelProperty(value = "车厂审核", dataType = DataType.STRING)
    private String secondVerifyFlgFlgName;

    /**
     * 审核备注
     */
    // @NotEmpty(message = "审核备注不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "SECOND_VERIFY_REMARK")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "审核备注", dataType = DataType.STRING)
    private String secondVerifyRemark;


    /**
     * 订单状态：01 有效 02 无效
     */
    @Column(name="SETTLE_STATUS_NAME")
    @Dict(sourceType = SysConstant.Settle.SETTLE_STATUS,sourceField = "settleStatus")
    @ApiModelProperty(value = "结算单状态", dataType = DataType.STRING)
    private String settleStatusName;

    /**
     * 结算总金额
     */
    @Column(name="TTL_AMT")
    @ApiModelProperty(value = "结算总金额", dataType = DataType.STRING)
    private BigDecimal ttlAmt = BigDecimal.ZERO;

    /**
     * 服务商编码
     */
   // @NotEmpty(message = "{message.CustomMessage.SettlesupplierNoNotEmpty.body}",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="SUPPLIER_NO")
    @ApiModelProperty(value = "服务商编码", dataType = DataType.STRING)
    private String supplierNo;

    @Column(name="SETTLE_FILE")
    @ApiModelProperty(value = "结算附件", dataType = DataType.STRING)
    private String settleFile;

    /**
     * 服务商名称
     */
   // @NotEmpty(message = "{message.CustomMessage.SettlesupplierNameNotEmpty.body}",groups = {UpdateGroup.class})
    @Column(name="SUPPLIER_NAME")
    @ApiModelProperty(value = "服务商名称", dataType = DataType.STRING)
    private String supplierName;


    /**
     * 开票状态
     */
    // @NotEmpty(message = "{message.CustomMessage.SettlesupplierNoNotEmpty.body}",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="INVOICE_STATUS")
    @ApiModelProperty(value = "开票状态", dataType = DataType.STRING)
    private String invoiceStatus;

    /**
     * 开票状态
     */
    // @NotEmpty(message = "{message.CustomMessage.SettlesupplierNameNotEmpty.body}",groups = {UpdateGroup.class})
    @Column(name="INVOICE_STATUS_NAME")
    @ApiModelProperty(value = "开票状态名称", dataType = DataType.STRING)
    @Dict(sourceType = SysConstant.InstallOrder.CONFIG_YORN, sourceField = "invoiceStatus")
    private String invoiceStatusName;

    /**
     * 订单编码
     */
    //@NotEmpty(message = "订单编码不能为空",groups = {CreateGroup.class, UpdateGroup.class})
//    @Column(name="ORDER_NO")
//    @ApiModelProperty(value = "订单编码", dataType = DataType.STRING)
//    private String orderNo;

    @Transient
    private List<SettleDetailEntity> settleDetailEntityList;


    /**
     * 审核流程
     * 1:初审
     * 2:复审
     */
    @Transient
    private String stepName;


    /**
     * 审批状态
     * 01:通过
     * 02:拒绝
     */
    @Transient

    private String verifyStatus;

    /**
     * 审批评论

     */

    @Transient

    private String verifyRemark;

    /**
     * 安装订单编号,多个编号之间已逗号分隔
     */
    @Transient
    private String installOrderNos;

    /**
     * 配送订单编号,多个编号之间已逗号分隔
     */
    @Transient
    private String deliveryOrderNos;


    @Transient
    @JsonIgnore
    private  String userType;


    /**
     * 是否完成 Y 是 N 否
     */
    // @NotEmpty(message = "是否完成 Y 是 N 否不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "FINISH_FLG")
    // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "是否完成 Y 是 N 否", dataType = DataType.STRING)
    private String finishFlg="N";

    @Column(name="ORDER_TYPE")
    @ApiModelProperty(value = "订单类型 01 安装 02 配送", dataType = DataType.STRING)
    private String orderType;



    @Column(name="ORDER_TYPE_NAME")
    @ApiModelProperty(value = "订单类型 01 安装 02 配送", dataType = DataType.STRING)
    private String orderTypeName;

    @Transient
    private String type;
}

