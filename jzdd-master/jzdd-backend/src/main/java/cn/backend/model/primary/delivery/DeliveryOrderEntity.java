package cn.backend.model.primary.delivery;

import cn.backend.config.constant.SysConstant;
import cn.backend.config.swagger.DataType;
import cn.backend.model.BaseEntity;
import cn.backend.model.primary.orderattach.OrderAttachEntity;
import cn.backend.model.primary.ordercar.OrderCarEntity;
import cn.backend.model.primary.ordergrade.OrderGradeEntity;
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
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 *
 * @author sunkw
 * @date 2019/07/16
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "t_delivery_order")
@ApiModel(value = "t_delivery_order", description = "t_delivery_order")
public class DeliveryOrderEntity extends BaseEntity{

    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "cn.zdwl.common.util.IdGenerator")
    @NotEmpty(message = "主键ID不能为空",groups = {DeleteGroup.class,  UpdateGroup.class})
    @Column(name = "ORDER_ID")
    @ApiModelProperty(value = "主键ID", dataType = DataType.STRING)
    private String id;

    /**
     * 公司编号
     */
    @NotEmpty(message = "公司编号不能为空",groups = {CreateGroup.class, UpdateGroup.class})
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
    private String settleVerifyFlg="N";

    /**
     * 订单编码
     */
    @NotEmpty(message = "订单编码不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="ORDER_NO")
    @ApiModelProperty(value = "订单编码", dataType = DataType.STRING)
    private String orderNo;

    /**
     * 提报时间 yyyymmdd hhmmss
     */
   // @NotNull(message = "提报时间不能为空",groups = {CreateGroup.class, UpdateGroup.class})
   //修改orderTime为可随重新提报修改
    @Column(name="ORDER_TIME",updatable = true)
    @ApiModelProperty(value = "提报时间 yyyymmdd hhmmss", dataType = DataType.STRING)
    private Date orderTime;


    /**
     * 派单时间 yyyymmdd hhmmss
     */
    // @NotNull(message = "审核时间不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "DISPATCH_TIME")
    // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "派单时间", dataType = DataType.STRING)
    private Date dispatchTime;

    /**
     * 充电桩型号
     */
    @Column(name = "PILE_MODEL")
    @ApiModelProperty(value = "充电桩型号", dataType = DataType.STRING)
    private String pileModel;

    /**
     * 充电桩型号名称
     */
    @Column(name = "PILE_MODEL_NAME")
    @ApiModelProperty(value = "充电桩型号名称", dataType = DataType.STRING)
    private String pileModelName;


    /**
     * 联系人
     */
   // @NotEmpty(message = "联系人不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="INSTALL_CONTACT")
    @ApiModelProperty(value = "联系人", dataType = DataType.STRING)
    private String installContact;

    /**
     * 联系人电话
     */
   // @NotEmpty(message = "联系人电话不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="INSTALL_CONTACT_PHONE")
    @ApiModelProperty(value = "联系人电话", dataType = DataType.STRING)
    private String installContactPhone;

    /**
     * 安装省份
     */
   // @NotEmpty(message = "安装省份不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="INSTALL_PROVINCE")
    @ApiModelProperty(value = "安装省份", dataType = DataType.STRING)
    private String installProvince;

    /**
     * 安装城市
     */
  //  @NotEmpty(message = "安装城市不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="INSTALL_CITY")
    @ApiModelProperty(value = "安装城市", dataType = DataType.STRING)
    private String installCity;

    /**
     * 安装区域
     */
   // @NotEmpty(message = "安装区域不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="INSTALL_REGION")
    @ApiModelProperty(value = "安装区域", dataType = DataType.STRING)
    private String installRegion;

    /**
     * 安装地址
     */
   // @NotEmpty(message = "安装地址不能为空",groups = {CreateGroup.class, UpdateGroup.class})
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
     *签收时间 yyyymmdd hhmmss
     */
    // @NotNull(message = "联系客户时间不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "RECEIVE_TIME")

    @ApiModelProperty(value = "签收时间", dataType = DataType.STRING)
    private Date receiveTime;

    /**
     *配送开始时间 yyyymmdd hhmmss
     */
    // @NotNull(message = "联系客户时间不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "DELIVERY_TIME")
    @ApiModelProperty(value = "配送开始时间", dataType = DataType.STRING)
    private Date deliveryTime;

    /**
     * 配送结束时间 yyyymmdd hhmmss
     */
    // @NotNull(message = "联系客户时间不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "DELIVERY_FINISH_TIME")
    @ApiModelProperty(value = "配送结束时间", dataType = DataType.STRING)
    private Date deliveryFinishTime;



    /**
     * 01 未签收 02 签收 03 拒绝
     */
    //@NotEmpty(message = "01 未签收 02 签收 03 拒绝不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="RECEIVE_STATUS_NAME")
    @ApiModelProperty(value = "01 未签收 02 签收 03 拒绝", dataType = DataType.STRING)
    @Dict(sourceType = SysConstant.Delivery.RECEIVE_STATUS, sourceField = "receiveStatus")
    private String receiveStatusName;

    /**
     * 客户签收时间 yyyymmdd hhmmss
     */
    //@NotEmpty(message = "01 未签收 02 签收 03 拒绝不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="CUST_RECEIVE_TIME")
    @ApiModelProperty(value = "客户签收时间 yyyymmdd hhmmss", dataType = DataType.STRING)
    private Date custReceiveTime;

    /**
     * 派单备注
     */
    //@NotEmpty(message = "签收备注不能为空",groups = {CreateGroup.class, UpdateGroup.class})
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
   // @NotEmpty(message = "预约备注不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="APPOINTMENT_REMARK")
    @ApiModelProperty(value = "预约备注", dataType = DataType.STRING)
    private String appointmentRemark;

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
    private String finishFlg="N";

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

    /**
     * 提单备注
     */
    // @NotEmpty(message = "提单备注不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "ORDER_REMARK")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "提单备注", dataType = DataType.STRING)
    private String orderRemark;

    /***
     * 是否开始废弃动作
     */
    @Column(name="BEGIN_CACLE")
    @ApiModelProperty(value = "是否开始废弃动作", dataType = DataType.STRING)
    private String beginCacle;

    /**
     * vin 用于创建订单时查询车主信息
     */
    @Transient
    @NotEmpty(message = "VIN不能为空", groups = {CreateGroup.class})
    private String vin;

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

    // /**
    //  * 超时开始时间
    //  */
    // @Transient
    // private Date timeoutStartTime;

    /**
     * 步骤备注
     */
    @Transient
    private String remark;

    /**
     * 保存还是提交 01保存 02提交
     */
    @Transient
    private String saveOrSubmit;

    /**
     * 需要删除的图片
     */
    @Transient
    private String delAttachNos;

    /**
     * 全国订单数
     */
    @Transient
    private Integer CountryNum;

    /**
     * 地区订单数
     */
    @Transient
    private Integer cityNum;

    /**
     * 登陆者NO
     */
    @Transient
    private String userNo;

    /**
     * 登陆者名称
     */
    @Transient
    private String userName;

    /**
     * 步骤
     */
//    @Transient
//    private Integer step;

    /**
     * 签收附件地址
     */
    @Transient
    private String signFile;

    /**
     * 车辆信息
     */
    @Transient
    private OrderCarEntity orderCarEntity;

    /**
     * crm参数
     */
    @Transient
    private String carOwner;

    @Transient
    private String carOwnerPhone;

    @Transient
    private String dealerName;

    @Transient
    private String dealerContact;

    @Transient
    private String dealerTel;

    @Transient
    private String carVehicle;

    @Transient
    private String vehicleDetail;

    @Transient
    private String vinNo;

    @Transient
    private String engineNo;

    @Transient
    private Date saleDate;

    @Transient
    private List<OrderAttachEntity> orderAttachEntityList;

    /**
     * 满意度
     */
    @Transient
    private OrderGradeEntity orderGradeEntity;

    @Transient
    @JsonIgnore
    private String verifyStatus = "01";

    @Transient
    @JsonIgnore
    private Date baseTime;

    @Transient
    @JsonIgnore
    private Date endTime;

    /**
     * 安装完成时间
     */
    @Column(name = "FINISH_TIME")
    @ApiModelProperty(value = "安装完成时间", dataType = DataType.LOCAL_DATE_TIME)
    private Date finishTime;


    /**
     * crm 测试标志
     */
    @Transient
    private  String crmTestFlg = "N";


    /**
     * token 用于创建订单时查询车主信息
     */
    @Transient
    @NotEmpty(message = "token不能为空", groups = {CreateGroup.class})
    private String token;

    /**
     * brand 用于创建订单时查询车主信息
     */
    @Transient
    @NotNull(message = "brand不能为空", groups = {CreateGroup.class})
    private Integer brand;


    /**
     * 是否结算 Y 是 N 否
     */
    // @NotEmpty(message = "是否结算 Y 是 N 否不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "SETTLE_FLG")
    // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "是否结算 Y 是 N 否", dataType = DataType.STRING)
    private String settleFlg="N";

    /**
     * 是否CRM撤回
     */
    @Column(name = "CRM_ROLLBACK_FLG")
    @ApiModelProperty(value = "是否CRM撤回", dataType = DataType.STRING)
    private String crmRollbackFlg = SysConstant.InstallOrder.CONFIG_FLG_N;

    /**
     * CRM退回备注
     */
    @Column(name = "CRM_ROLLBACK_REMARK")
    @ApiModelProperty(value = "CRM退回备注", dataType = DataType.STRING)
    private String crmRollbackRemark;

    /**
     * 满意度提交时间
     */
    // @NotNull(message = "满意度提交时间不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "GRADE_SUBMIT_TIME")
    // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "满意度提交时间", dataType = DataType.STRING)
    private Date gradeSubmitTime;

    /**
     * 是否二次安装:Y /N
     */
    @Transient
    private String secondInstall;


}

