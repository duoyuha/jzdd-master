package cn.backend.model.primary.viewdeliveryorder;

import cn.backend.config.constant.SysConstant;
import cn.backend.config.swagger.DataType;
import cn.backend.model.BaseEntity;
import cn.backend.model.primary.inspection.InspectionEntity;
import cn.backend.model.primary.orderattach.OrderAttachEntity;
import cn.zdwl.common.converters.dictconvert.Dict;
import cn.zdwl.common.validgroup.CreateGroup;
import cn.zdwl.common.validgroup.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author sunkw
 * @date 2019/07/17
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "v_delivery_order")
@ApiModel(value = "v_delivery_order", description = "配送订单视图")
public class ViewDeliveryOrderEntity extends BaseEntity {

    /**
     * 主键
     */
    @Id
    @Column(name = "ORDER_ID")
    @ApiModelProperty(value = "主键ID", dataType = DataType.STRING)
    private String id;

    /**
     * 公司编号
     */
    //@NotEmpty(message = "公司编号不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="CORP_NO")
    @ApiModelProperty(value = "公司编号", dataType = DataType.STRING)
    private String corpNo;

    /**
     * 结算回退备注
     */
    @Column(name = "SETTLE_REMARK")
    @ApiModelProperty(value = "结算回退备注", dataType = DataType.STRING)
    private String settleRemark;

    /**
     * 结算申请标志
     */
    @Column(name = "SETTLE_VERIFY_FLG")
    @ApiModelProperty(value = "结算申请标志", dataType = DataType.STRING)
    private String settleVerifyFlg= "N";

    /**
     * 订单编码
     */
    //@NotEmpty(message = "订单编码不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="ORDER_NO")
    @ApiModelProperty(value = "订单编码", dataType = DataType.STRING)
    private String orderNo;

    /**
     * 提报时间 yyyymmdd hhmmss
     */
    //@NotNull(message = "提报时间不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="ORDER_TIME",updatable = false)
    @ApiModelProperty(value = "提报时间 yyyymmdd hhmmss", dataType = DataType.STRING)
    private Date orderTime;

    /**
     * 联系人
     */
    //@NotEmpty(message = "联系人不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="INSTALL_CONTACT")
    @ApiModelProperty(value = "联系人", dataType = DataType.STRING)
    private String installContact;

    /**
     * 联系人电话
     */
    //@NotEmpty(message = "联系人电话不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="INSTALL_CONTACT_PHONE")
    @ApiModelProperty(value = "联系人电话", dataType = DataType.STRING)
    private String installContactPhone;

    /**
     * 安装省份
     */
    //@NotEmpty(message = "安装省份不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="INSTALL_PROVINCE")
    @ApiModelProperty(value = "安装省份", dataType = DataType.STRING)
    private String installProvince;

    /**
     * 安装城市
     */
    //@NotEmpty(message = "安装城市不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="INSTALL_CITY")
    @ApiModelProperty(value = "安装城市", dataType = DataType.STRING)
    private String installCity;

    /**
     * 安装区域
     */
    @NotEmpty(message = "安装区域不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="INSTALL_REGION")
    @ApiModelProperty(value = "安装区域", dataType = DataType.STRING)
    private String installRegion;

    /**
     * 安装地址
     */
    @NotEmpty(message = "安装地址不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="INSTALL_ADDRESS")
    @ApiModelProperty(value = "安装地址", dataType = DataType.STRING)
    private String installAddress;

    /**
     * 01 壁挂式 02 立柱式
     */
    //@NotEmpty(message = "01 壁挂式 02 立柱式不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="PILE_TYPE")
    @ApiModelProperty(value = "01 壁挂式 02 立柱式", dataType = DataType.STRING)
    private String pileType;

    /**
     * 01 壁挂式 02 立柱式
     */
    //@NotEmpty(message = "01 壁挂式 02 立柱式不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="PILE_TYPE_NAME")
    @ApiModelProperty(value = "01 壁挂式 02 立柱式", dataType = DataType.STRING)
    @Dict(sourceType = SysConstant.Delivery.PILE_TYPE, sourceField = "pileType")
    private String pileTypeName;

    /**
     * 充电桩编码
     */
    //@NotEmpty(message = "充电桩编码不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="PILE_CODE")
    @ApiModelProperty(value = "充电桩编码", dataType = DataType.STRING)
    private String pileCode;

    /**
     * 服务商编码
     */
    //@NotEmpty(message = "服务商编码不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="SUPPLIER_NO")
    @ApiModelProperty(value = "服务商编码", dataType = DataType.STRING)
    private String supplierNo;

    /**
     * 服务商名称
     */
    //@NotEmpty(message = "服务商名称不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="SUPPLIER_NAME")
    @ApiModelProperty(value = "服务商名称", dataType = DataType.STRING)
    private String supplierName;

    /**
     * 派单备注
     */
    //@NotEmpty(message = "派单备注不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="DISPATCH_REMARK")
    @ApiModelProperty(value = "派单备注", dataType = DataType.STRING)
    private String dispatchRemark;

    /**
     * 01 未签收 02 签收 03 拒绝
     */
    //@NotEmpty(message = "01 未签收 02 签收 03 拒绝不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="RECEIVE_STATUS")
    @ApiModelProperty(value = "01 未签收 02 签收 03 拒绝", dataType = DataType.STRING)
    private String receiveStatus;

    /**
     * 01 未签收 02 签收 03 拒绝
     */
    //@NotEmpty(message = "01 未签收 02 签收 03 拒绝不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="RECEIVE_STATUS_NAME")
    @ApiModelProperty(value = "01 未签收 02 签收 03 拒绝", dataType = DataType.STRING)
    @Dict(sourceType = SysConstant.Delivery.RECEIVE_STATUS, sourceField = "receiveStatus")
    private String receiveStatusName;

    /**
     * 派单备注
     */
    @NotEmpty(message = "签收备注不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="RECEIVE_REMARK")
    @ApiModelProperty(value = "签收备注", dataType = DataType.STRING)
    private String receiveRemark;

    /**
     * 联系客户时间 yyyymmdd hhmmss
     */
    //@NotNull(message = "联系客户时间 yyyymmdd hhmmss不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="CONTACT_CUST_TIME")
    @ApiModelProperty(value = "联系客户时间 yyyymmdd hhmmss", dataType = DataType.STRING)
    private Date contactCustTime;

    /**
     * 预约时间 yyyymmdd hhmmss
     */
    //@NotNull(message = "预约时间 yyyymmdd hhmmss不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="APPOINTMENT_TIME")
    @ApiModelProperty(value = "预约时间 yyyymmdd hhmmss", dataType = DataType.STRING)
    private Date appointmentTime;

    /**
     * 预约备注
     */
    @NotEmpty(message = "预约备注不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="APPOINTMENT_REMARK")
    @ApiModelProperty(value = "预约备注", dataType = DataType.STRING)
    private String appointmentRemark;


    /**
     * 派单时间 yyyymmdd hhmmss
     */
    // @NotNull(message = "审核时间不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "DISPATCH_TIME")
    // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "派单时间", dataType = DataType.STRING)
    private Date dispatchTime;

    /**
     * 配送类型：01 物流 02 自送
     */
    //@NotEmpty(message = "配送类型：01 物流 02 自送不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="DELIVERY_TYPE")
    @ApiModelProperty(value = "配送类型：01 物流 02 自送", dataType = DataType.STRING)
    private String deliveryType;

    /**
     * 配送类型：01 物流 02 自送
     */
    //@NotEmpty(message = "配送类型：01 物流 02 自送不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="DELIVERY_TYPE_NAME")
    @ApiModelProperty(value = "配送类型：01 物流 02 自送", dataType = DataType.STRING)
    @Dict(sourceType = SysConstant.Delivery.DELIVERY_TYPE, sourceField = "deliveryType")
    private String deliveryTypeName;

    /**
     * 物流公司
     */
    //@NotEmpty(message = "物流公司不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="DELIVERY_NAME")
    @ApiModelProperty(value = "物流公司", dataType = DataType.STRING)
    private String deliveryName;

    /**
     * 物流单号
     */
    //@NotEmpty(message = "物流单号不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="DELIVERY_NO")
    @ApiModelProperty(value = "物流单号", dataType = DataType.STRING)
    private String deliveryNo;

    /**
     * 物流单号
     */
    //@NotEmpty(message = "物流单号不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="DELIVERY_REMARK")
    @ApiModelProperty(value = "物流备注", dataType = DataType.STRING)
    private String deliveryRemark;

    /**
     * 物流单号附件
     */
    //@NotEmpty(message = "物流单号附件不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="DELIVERY_FILE")
    @ApiModelProperty(value = "物流单号附件", dataType = DataType.STRING)
    private String deliveryFile;

    /**
     * 服务商确认时间 yyyymmdd hhmmss
     */
    //@NotNull(message = "服务商确认时间 yyyymmdd hhmmss不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="SUPPLIER_CONFIRM_TIME")
    @ApiModelProperty(value = "服务商确认时间 yyyymmdd hhmmss", dataType = DataType.STRING)
    private Date supplierConfirmTime;

    /**
     * 是否确认 01 是 02 否
     */
    //@NotEmpty(message = "是否确认 01 是 02 否不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="SUPPLIER_CONFIRM_FLG")
    @ApiModelProperty(value = "是否确认 01 是 02 否", dataType = DataType.STRING)
    private String supplierConfirmFlg;

    /**
     * 是否确认 01 是 02 否
     */
    //@NotEmpty(message = "是否确认 01 是 02 否不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="SUPPLIER_CONFIRM_FLG_NAME")
    @ApiModelProperty(value = "是否确认 01 是 02 否", dataType = DataType.STRING)
    @Dict(sourceType = SysConstant.Delivery.YES_NO, sourceField = "supplierConfirmFlg")
    private String supplierConfirmFlgName;

    /**
     * 是否完成 Y 是 N 否
     */
    // @NotEmpty(message = "是否完成 Y 是 N 否不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "FINISH_FLG")
    // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "是否完成", dataType = DataType.STRING)
    private String finishFlg;

    /**
     * 车厂审核 01 未审核 02 审核通过 03 审核拒绝
     */
    //@NotEmpty(message = "车厂审核 01 未审核 02 审核通过 03 审核拒绝不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="VERIFY_FLG")
    @ApiModelProperty(value = "车厂审核 01 未审核 02 审核通过 03 审核拒绝", dataType = DataType.STRING)
    private String verifyFlg;

    /**
     * 车厂审核 01 未审核 02 审核通过 03 审核拒绝
     */
    //@NotEmpty(message = "车厂审核 01 未审核 02 审核通过 03 审核拒绝不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="VERIFY_FLG_NAME")
    @ApiModelProperty(value = "车厂审核 01 未审核 02 审核通过 03 审核拒绝", dataType = DataType.STRING)
    @Dict(sourceType = SysConstant.Delivery.VERIFY_FLG, sourceField = "verifyFlg")
    private String verifyFlgName;

    /**
     * 审核备注
     */
    //@NotEmpty(message = "审核备注不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="VERIFY_REMARK")
    @ApiModelProperty(value = "审核备注", dataType = DataType.STRING)
    private String verifyRemark;

    /**
     * 审核时间 yyyymmdd hhmmss
     */
    //@NotNull(message = "审核时间 yyyymmdd hhmmss不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="VERIFY_TIME")
    @ApiModelProperty(value = "审核时间 yyyymmdd hhmmss", dataType = DataType.STRING)
    private Date verifyTime;

    /***
     * 是否开始废弃动作
     */
    @Column(name="BEGIN_CACLE")
    @ApiModelProperty(value = "是否开始废弃动作", dataType = DataType.STRING)
    private String beginCacle;


    /**
     * 客户签收时间 yyyymmdd hhmmss
     */
    //@NotEmpty(message = "01 未签收 02 签收 03 拒绝不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="CUST_RECEIVE_TIME")
    @ApiModelProperty(value = "客户签收时间 yyyymmdd hhmmss", dataType = DataType.STRING)
    private Date custReceiveTime;

    /**
     * 步骤号
     */
    @Column(name = "FOLLOW_SEQ")
    @ApiModelProperty(value = "步骤号", dataType = DataType.INT)
    private Integer followSeq;

    /**
     * 步骤名
     */
    @Column(name = "STEP_NAME")
    @ApiModelProperty(value = "步骤名", dataType = DataType.STRING)
    private String stepName;

    /**
     * 职位
     */
    @Column(name = "POSITION_CODES")
    @ApiModelProperty(value = "职位", dataType = DataType.STRING)
    private String positionCodes;

    /**
     * 车主名称
     */
    @Column(name = "CAR_OWNER")
    @ApiModelProperty(value = "车主名称", dataType = DataType.STRING)
    private String carOwner;

    /**
     * 车主电话
     */
    @Column(name = "CAR_OWNER_PHONE")
    @ApiModelProperty(value = "车主电话", dataType = DataType.STRING)
    private String carOwnerPhone;

    /**
     * 经销商名称
     */
    @Column(name = "DEALER_NAME")
    @ApiModelProperty(value = "经销商名称", dataType = DataType.STRING)
    private String dealerName;

    /**
     * 经销商联系人
     */
    @Column(name = "DEALER_CONTACT")
    @ApiModelProperty(value = "经销商联系人", dataType = DataType.STRING)
    private String dealerContact;

    /**
     * 经销商电话
     */
    @Column(name = "DEALER_TEL")
    @ApiModelProperty(value = "经销商电话", dataType = DataType.STRING)
    private String dealerTel;

    /**
     * 销售时间
     */
    @Column(name = "SALE_DATE")
    @ApiModelProperty(value = "销售时间", dataType = DataType.STRING)
    private Date saleDate;

    /**
     * 车型
     */
    @Column(name = "CAR_VEHICLE")
    @ApiModelProperty(value = "车型", dataType = DataType.STRING)
    private String carVehicle;

    /**
     * 车架号
     */
    @Column(name = "VIN_NO")
    @ApiModelProperty(value = "车架号", dataType = DataType.STRING)
    private String vinNo;

    /**
     * 发动机编号
     */
    @Column(name = "ENGINE_NO")
    @ApiModelProperty(value = "发动机编号", dataType = DataType.STRING)
    private String engineNo;

    /**
     * 配送开始时间
     */
    @Column(name = "DELIVERY_TIME")
    @ApiModelProperty(value = "配送开始时间", dataType = DataType.STRING)
    private Date deliveryTime;

    /**
     * 配送完成时间
     */
    @Column(name = "DELIVERY_FINISH_TIME")
    @ApiModelProperty(value = "配送完成时间", dataType = DataType.STRING)
    private Date deliveryFinishTime;

    /**
     * 订单完成时间
     */
    @Column(name = "ORDER_FINISH_TIME")
    @ApiModelProperty(value = "订单完成时间", dataType = DataType.STRING)
    private Date orderFinishTime;

    /**
     * 流程编码
     */
    @Column(name = "FOLLOW_CODE")
    @ApiModelProperty(value = "流程编码", dataType = DataType.STRING)
    private String followCode;

    /**
     * 流程编号
     */
    @Column(name = "FOLLOW_NO")
    @ApiModelProperty(value = "流程编号", dataType = DataType.STRING)
    private String followNo;

    /**
     * 签收时间
     */
    @Column(name = "RECEIVE_TIME")
    @ApiModelProperty(value = "签收时间", dataType = DataType.STRING)
    private Date receiveTime;


    @Column(name="STEP_MATRIX")
    @ApiModelProperty(value = "步骤矩阵", dataType = DataType.STRING)
    private String stepMatrix;

    /**
     * 是否结算 Y 是 N 否
     */
    // @NotEmpty(message = "是否结算 Y 是 N 否不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "SETTLE_FLG")
    // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "是否结算 Y 是 N 否", dataType = DataType.STRING)
    private String settleFlg="N";

    /**
     * 图片
     */
    @Transient
    private List<OrderAttachEntity> orderAttachEntityList;

    /**
     * 巡检
     */
    @Transient
    private InspectionEntity inspectionEntity;

    /**
     * 提单备注
     */
    @Column(name = "ORDER_REMARK")
    @ApiModelProperty(value = "提单备注", dataType = DataType.STRING)
    private String orderRemark;

    /**
     * 充电桩型号
     */
    @Column(name = "PILE_MODEL")
    @ApiModelProperty(value = "充电桩型号", dataType = DataType.STRING)
    private String pileModel;

    /**
     * CRM退回备注
     */
    @Column(name = "CRM_ROLLBACK_REMARK")
    @ApiModelProperty(value = "CRM退回备注", dataType = DataType.STRING)
    private String crmRollbackRemark;

    /**
     * 满意度提交时间
     */
    @Column(name = "GRADE_SUBMIT_TIME")
    @ApiModelProperty(value = "满意度提交时间", dataType = DataType.STRING)
    private Date gradeSubmitTime;

    /**
     * 充电桩型号名称
     */
    @Column(name = "PILE_MODEL_NAME")
    @ApiModelProperty(value = "充电桩型号名称", dataType = DataType.STRING)
    private String pileModelName;

    //签收超时标志
    @Transient
    @ApiModelProperty(value = "签收超时标志Y 超时 N 不超时", dataType = DataType.STRING)
    private String  dispatchOverTimeFlg="N";

    //签收超时标志
    @Transient
    private int  dispatchOverTime=0;


    //预约超时标志
    @Transient
    @ApiModelProperty(value = "预约超时标志Y 超时 N 不超时", dataType = DataType.STRING)
    private String  contractOverTimeFlg="N";

//    /**
//     * 收费金额
//     */
//    @Column(name = "ACTUAL_PRICE")
//    @ApiModelProperty(value = "收费金额", dataType = DataType.STRING)
//    private Double actualPrice;

    /**
     * 车型明细
     */
    @Column(name = "VEHICLE_DETAIL")
    @ApiModelProperty(value = "车型明细", dataType = DataType.STRING)
    private String vehicleDetail;

    /**
     * 结算金额
     */
    @Column(name = "SETTLE_AMT")
    @ApiModelProperty(value = "结算金额", dataType = DataType.STRING)
    private Double settleAmt;

    /**
     * 配送时长
     */
    @Column(name = "DELIVERY_SPAN")
    @ApiModelProperty(value = "安装时长", dataType = DataType.STRING)
    private Long deliverySpan;

    @Transient
    private int contractOverTime=0;

    @Transient
    private String invoiceStutas;

    /**
     * 结算状态是否审核拒绝
     * 00 正常
     * 01 初审拒绝
     * 02 复审拒绝
     */
    @Transient
    private String settleRefuseFlg = "00";

}

