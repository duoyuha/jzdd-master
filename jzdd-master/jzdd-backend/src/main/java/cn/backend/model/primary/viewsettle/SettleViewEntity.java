package cn.backend.model.primary.viewsettle;

import cn.backend.config.constant.SysConstant;
import cn.backend.config.swagger.DataType;
import cn.backend.model.BaseEntity;
import cn.zdwl.common.converters.dictconvert.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author James
 * @date 2019/07/30
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "v_settle")
@ApiModel(value = "v_settle", description = "结算单视图")
public class SettleViewEntity extends BaseEntity {

    /**
     * 主键
     */
    @Id
    @Column(name = "SETTLE_ID")
    @ApiModelProperty(value = "主键ID", dataType = DataType.STRING)
    private String id;

    /**
     * 公司编号
     */
    @Column(name = "CORP_NO")
    @ApiModelProperty(value = "公司编号", dataType = DataType.STRING)
    private String corpNo;

//    @Column(name="ORDER_NO")
//    @ApiModelProperty(value = "订单编码", dataType = DataType.STRING)
//    private String orderNo;

    /**
     * 步骤矩阵
     */
    @Column(name="STEP_MATRIX")
    @ApiModelProperty(value = "步骤矩阵", dataType = DataType.STRING)
    private String stepMatrix;

    /**
     * 订单编码
     */
    @Column(name = "SETTLE_NO")
    @ApiModelProperty(value = "结算单编号", dataType = DataType.STRING)
    private String settleNo;

    /**
     * 订单状态：01 有效 02 无效
     */
    @Column(name = "SETTLE_STATUS")
    @ApiModelProperty(value = "结算单状态", dataType = DataType.STRING)
    private String settleStatus;

    /**
     * 订单状态：01 有效 02 无效
     */
    @Column(name = "SETTLE_STATUS_NAME")
    @ApiModelProperty(value = "结算单状态", dataType = DataType.STRING)
    private String settleStatusName;

    /**
     * 结算总金额
     */
    @Column(name = "TTL_AMT")
    @ApiModelProperty(value = "结算金额", dataType = DataType.STRING)
    private BigDecimal ttlAmt;

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
    @ApiModelProperty(value = "服务商名称", dataType = DataType.STRING)
    private String supplierName;

    /**
     * 步骤号
     */
    @Column(name = "FOLLOW_SEQ")
    @ApiModelProperty(value = "步骤号", dataType = DataType.STRING)
    private Integer followSeq;

    /**
     * 审批职位：01 充电经理  02 整车事业部经理  03 安装服务商负责人 04 勘察人员 05 安装人员
     */
    @Column(name = "POSITION_CODES")
    @ApiModelProperty(value = "审批职位", dataType = DataType.STRING)
    private String positionCodes;

    /**
     * 审批步骤名称
     */
    @Column(name = "STEP_NAME")
    @ApiModelProperty(value = "审批步骤名称", dataType = DataType.STRING)
    private String stepName;

    /**
     * 是否完成 Y 是 N 否
     */
    // @NotEmpty(message = "是否完成 Y 是 N 否不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "FINISH_FLG")
    // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "是否完成 Y 是 N 否", dataType = DataType.STRING)
    private String finishFlg="N";



    /**
     * 订单类型
     */
    @Column(name = "ORDER_TYPE")
    @ApiModelProperty(value = "订单类型", dataType = DataType.STRING)
    private String orderType;


    /**
     * 订单类型
     */
    @Column(name = "ORDER_TYPE_NAME")
    @ApiModelProperty(value = "订单类型", dataType = DataType.STRING)
    private String orderTypeName;

    /**
     * 初审结果
     */
    @Column(name = "FIRST_VERIFY_FLG")
    @ApiModelProperty(value = "初审结果", dataType = DataType.STRING)
    private String firstVerifyFlg;

    /**
     * 复审结果
     */
    @Column(name = "SECOND_VERIFY_FLG")
    @ApiModelProperty(value = "初审结果", dataType = DataType.STRING)
    private String secondVerifyFlg;

    /**
     * 完成时间
     */
    @Column(name="FINISH_TIME")
    @ApiModelProperty(value = "完成时间", dataType = DataType.LOCAL_DATE)
    private Date finishTime;

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
     * 车架号
     */
//    @Column(name="VIN_NO")
//    @ApiModelProperty(value = "车架号", dataType = DataType.STRING)
//    private String vinNo;

    /**
     * 01 壁挂式 02 立柱式
     */
    //@NotEmpty(message = "01 壁挂式 02 立柱式不能为空",groups = {CreateGroup.class, UpdateGroup.class})
//    @Column(name="PILE_TYPE")
//    @ApiModelProperty(value = "01 壁挂式 02 立柱式", dataType = DataType.STRING)
//    private String pileType;

    /**
     * 01 壁挂式 02 立柱式
     */
    //@NotEmpty(message = "01 壁挂式 02 立柱式不能为空",groups = {CreateGroup.class, UpdateGroup.class})
//    @Column(name="PILE_TYPE_NAME")
//    @ApiModelProperty(value = "01 壁挂式 02 立柱式", dataType = DataType.STRING)
//    @Dict(sourceType = SysConstant.Delivery.PILE_TYPE, sourceField = "pileType")
//    private String pileTypeName;



}

