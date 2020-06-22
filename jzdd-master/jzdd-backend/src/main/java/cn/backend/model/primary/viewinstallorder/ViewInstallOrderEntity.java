package cn.backend.model.primary.viewinstallorder;

import cn.backend.config.constant.SysConstant;
import cn.backend.config.swagger.DataType;
import cn.backend.model.BaseEntity;
import cn.backend.model.primary.inspection.InspectionEntity;
import cn.backend.model.primary.orderattach.OrderAttachEntity;
import cn.zdwl.common.Logoutput.FiledLog;
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
@Table(name = "v_install_order")
@ApiModel(value = "v_install_order", description = "安装订单视图")
public class ViewInstallOrderEntity extends BaseEntity {

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
    @Column(name = "CORP_NO")
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
    @Column(name = "ORDER_NO")
    @ApiModelProperty(value = "订单编码", dataType = DataType.STRING)
    private String orderNo;

    /**
     * 提报时间 yyyymmdd hhmmss
     */
    @Column(name = "ORDER_TIME")
    @ApiModelProperty(value = "提报时间 yyyymmdd hhmmss", dataType = DataType.STRING)
    private Date orderTime;

    /**
     * 巡检编码
     */
    @Column(name = "CHECK_NO")
    @ApiModelProperty(value = "巡检编码", dataType = DataType.STRING)
    private String checkNo;

    /**
     * 联系人
     */
    @Column(name = "INSTALL_CONTACT")
    @ApiModelProperty(value = "联系人", dataType = DataType.STRING)
    private String installContact;

    /**
     * 联系人电话
     */
    @Column(name = "INSTALL_CONTACT_PHONE")
    @ApiModelProperty(value = "联系人电话", dataType = DataType.STRING)
    private String installContactPhone;

    /**
     * 安装省份
     */
    @Column(name = "INSTALL_PROVINCE")
    @ApiModelProperty(value = "安装省份", dataType = DataType.STRING)
    private String installProvince;

    /**
     * 安装城市
     */
    @Column(name = "INSTALL_CITY")
    @ApiModelProperty(value = "安装城市", dataType = DataType.STRING)
    private String installCity;

    /**
     * 安装区域
     */
    @Column(name = "INSTALL_REGION")
    @ApiModelProperty(value = "安装区域", dataType = DataType.STRING)
    private String installRegion;

    /**
     * 安装地址
     */
    @Column(name = "INSTALL_ADDRESS")
    @ApiModelProperty(value = "安装地址", dataType = DataType.STRING)
    private String installAddress;

    /**
     * 提单备注
     */
    @Column(name = "ORDER_REMARK")
    @ApiModelProperty(value = "提单备注", dataType = DataType.STRING)
    private String orderRemark;

    /**
     * 充电桩编码
     */
    @Column(name = "PILE_CODE")
    @ApiModelProperty(value = "充电桩编码", dataType = DataType.STRING)
    private String pileCode;

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
     * 充电桩类型 01 壁挂式 02 立柱式
     */
    @Column(name = "PILE_TYPE")
    @ApiModelProperty(value = "充电桩类型 01 壁挂式 02 立柱式", dataType = DataType.STRING)
    private String pileType;

    /**
     * 充电桩类型 01 壁挂式 02 立柱式
     */
    @Column(name = "PILE_TYPE_NAME")
    // @Dict(sourceType = SysConstant.InstallOrder.CONFIG_PILETYPE, sourceField = "pileType")
    @ApiModelProperty(value = "充电桩类型", dataType = DataType.STRING)
    private String pileTypeName;

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
    @ApiModelProperty(value = "服务商", dataType = DataType.STRING)
    private String supplierName;

    /**
     * 派单备注
     */
    @Column(name = "DISPATCH_REMARK")
    @ApiModelProperty(value = "派单备注", dataType = DataType.STRING)
    private String dispatchRemark;

    /**
     * 签收状态 01 未签收 02 签收 03 拒绝
     */
    @Column(name = "RECEIVE_STATUS")
    @ApiModelProperty(value = "签收状态 01 未签收 02 签收 03 拒绝", dataType = DataType.STRING)
    private String receiveStatus;

    /**
     * 签收状态 01 未签收 02 签收 03 拒绝
     */
    @Column(name = "RECEIVE_STATUS_NAME")
    // @Dict(sourceType = SysConstant.InstallOrder.CONFIG_RECEIVESTATUS, sourceField = "receiveStatus")
    @ApiModelProperty(value = "签收状态", dataType = DataType.STRING)
    private String receiveStatusName;

    /**
     * 派单备注
     */
    @Column(name = "RECEIVE_REMARK")
    @ApiModelProperty(value = "派单备注", dataType = DataType.STRING)
    private String receiveRemark;

    /**
     * 联系客户时间 yyyymmdd hhmmss
     */
    @Column(name = "CONTACT_CUST_TIME")
    @ApiModelProperty(value = "联系客户时间", dataType = DataType.STRING)
    private Date contactCustTime;

    /**
     * 预约时间 yyyymmdd hhmmss
     */
    @Column(name = "APPOINTMENT_TIME")
    @ApiModelProperty(value = "预约时间", dataType = DataType.STRING)
    private Date appointmentTime;

    /**
     * 预约备注
     */
    @Column(name = "APPOINTMENT_REMARK")
    @ApiModelProperty(value = "预约备注", dataType = DataType.STRING)
    private String appointmentRemark;

    /**
     * 勘察人员编码
     */
    @Column(name = "CHECK_USER_NO")
    @ApiModelProperty(value = "勘察人员编码", dataType = DataType.STRING)
    private String checkUserNo;

    /**
     * 用于安装人员查询 sunKW 20191012 新增
     */
    @Column(name = "CHECK_USER_QUERY")
    @ApiModelProperty(value = "勘察人员查询", dataType = DataType.STRING)
    private String checkUserQuery;
    //

    /**
     * 勘察人员名称
     */
    @Column(name = "CHECK_USER_NAME")
    @ApiModelProperty(value = "勘察人员名称", dataType = DataType.STRING)
    private String checkUserName;

    /**
     * 勘察人员电话
     */
    @Column(name = "CHECK_USER_PHONE")
    @ApiModelProperty(value = "勘察人员电话", dataType = DataType.STRING)
    private String checkUserPhone;

    /**
     * 派单勘察备注
     */
    @Column(name = "ASSIGN_CHECK_REMARK")
    @ApiModelProperty(value = "派单勘察备注", dataType = DataType.STRING)
    private String assignCheckRemark;

    /**
     * 勘察时间 yyyymmdd hhmmss
     */
    @Column(name = "CHECK_TIME")
    @ApiModelProperty(value = "勘察时间", dataType = DataType.STRING)
    private Date checkTime;

    /**
     * 勘察结束时间 yyyymmdd hhmmss
     */
    @Column(name = "CHECK_FINISH_TIME")
    @ApiModelProperty(value = "勘察结束时间", dataType = DataType.STRING)
    private Date checkFinishTime;

    /**
     * 派单时间 yyyymmdd hhmmss
     */
    @Column(name = "DISPATCH_TIME")
    @ApiModelProperty(value = "派单时间", dataType = DataType.STRING)
    private Date dispatchTime;

    /**
     * 布线距离
     */
    @Column(name = "WIRE_LENGTH")
    @ApiModelProperty(value = "布线距离", dataType = DataType.STRING)
    private Integer wireLength;

    /**
     * 是否报装 01 是 02 否
     */
    @Column(name = "INSTALL_FLG")
    @ApiModelProperty(value = "是否报装", dataType = DataType.STRING)
    private String installFlg;

    /**
     * 是否报装 01 是 02 否
     */
    @Column(name = "INSTALL_FLG_NAME")
    // @Dict(sourceType = SysConstant.InstallOrder.CONFIG_YORN, sourceField = "installFlg")
    @ApiModelProperty(value = "是否报装", dataType = DataType.STRING)
    private String installFlgName;

    /**
     * 预计价格
     */
    @Column(name = "ESTIMATE_PRICE")
    @ApiModelProperty(value = "预计价格", dataType = DataType.STRING)
    private Double estimatePrice;

    /**
     * 用电类型 01 物业电 02 自家电 03 国网电
     */
    @Column(name = "ELEC_TYPE")
    @ApiModelProperty(value = "用电类型 01 物业电 02 自家电 03 国网电", dataType = DataType.STRING)
    private String elecType;

    /**
     * 用电类型 01 物业电 02 自家电 03 国网电
     */
    @Column(name = "ELEC_TYPE_NAME")
    // @Dict(sourceType = SysConstant.InstallOrder.CONFIG_ELECTYPE, sourceField = "elecType")
    @ApiModelProperty(value = "用电类型", dataType = DataType.STRING)
    private String elecTypeName;

    /**
     * 签收时间
     */
    @Column(name = "RECEIVE_TIME")
    @ApiModelProperty(value = "签收时间", dataType = DataType.STRING)
    private Date receiveTime;

    /**
     * 安装方案
     */
    @Column(name = "INSTALL_METHOD_FILE")
    @ApiModelProperty(value = "安装方案", dataType = DataType.STRING)
    private String installMethodFile;

    /**
     * 其他附件
     */
    @Column(name = "OTHER_FILE")
    @ApiModelProperty(value = "其他附件", dataType = DataType.STRING)
    private String otherFile;

    /**
     * 勘察备注
     */
    @Column(name = "CHECK_REMARK")
    @ApiModelProperty(value = "勘察备注", dataType = DataType.STRING)
    private String checkRemark;

    /**
     * 预约安装时间 yyyymmdd hhmmss
     */
    @Column(name = "APPOINTMENT_INSTALL_TIME")
    @ApiModelProperty(value = "预约安装时间", dataType = DataType.STRING)
    private Date appointmentInstallTime;

    /**
     * 安装联系客户时间
     */
    @Column(name = "INSTALL_CONTACT_CUST_TIME")
    @ApiModelProperty(value = "安装联系客户时间", dataType = DataType.STRING)
    private Date installContactCustTime;

    /**
     * 预约安装备注
     */
    @Column(name = "APPOINTMENT_INSTALL_REMARK")
    @ApiModelProperty(value = "预约安装备注", dataType = DataType.STRING)
    private String appointmentInstallRemark;

    /**
     * 安装人员编码
     */
    @Column(name = "INSTALL_USER_NO")
    @ApiModelProperty(value = "安装人员编码", dataType = DataType.STRING)
    private String installUserNo;

    /**
     * 安装人员名称
     */
    @Column(name = "INSTALL_USER_NAME")
    @ApiModelProperty(value = "安装人员名称", dataType = DataType.STRING)
    private String installUserName;

    /**
     * 安装人员电话
     */
    @Column(name = "INSTALL_USER_PHONE")
    @ApiModelProperty(value = "安装人员电话", dataType = DataType.STRING)
    private String installUserPhone;


    /**
     * 派单勘察备注
     */
    @Column(name = "ASSIGN_INSTALL_REMARK")
    @ApiModelProperty(value = "派单勘察备注", dataType = DataType.STRING)
    private String assignInstallRemark;

    /**
     * 安装完成时间
     */
    @Column(name = "INSTALL_FINISH_TIME")
    @ApiModelProperty(value = "安装完成时间", dataType = DataType.STRING)
    private Date installFinishTime;

    /**
     * 铺设方式 01 穿管 02 桥架 03 地沟 04 暗孵 05 其他
     */
    @Column(name = "INSTALL_TYPE")
    @ApiModelProperty(value = "铺设方式 01 穿管 02 桥架 03 地沟 04 暗孵 05 其他", dataType = DataType.STRING)
    private String installType;

    /**
     * 铺设方式 01 穿管 02 桥架 03 地沟 04 暗孵 05 其他
     */
    @Column(name = "INSTALL_TYPE_NAME")
    // @Dict(sourceType = SysConstant.InstallOrder.CONFIG_INSTALLTYPE, sourceField = "installType")
    @ApiModelProperty(value = "铺设方式", dataType = DataType.STRING)
    private String installTypeName;

    /**
     * 收费金额
     */
    @Column(name = "ACTUAL_PRICE")
    @ApiModelProperty(value = "收费金额", dataType = DataType.STRING)
    private Double actualPrice;

    /**
     * 是否联网 01 是 02 否
     */
    @Column(name = "CONNECT_FLG")
    @ApiModelProperty(value = "是否联网 01 是 02 否", dataType = DataType.STRING)
    private String connectFlg;

    /**
     * 是否联网 01 是 02 否
     */
    @Column(name = "CONNECT_FLG_NAME")
    // @Dict(sourceType = SysConstant.InstallOrder.CONFIG_YORN, sourceField = "connectFlg")
    @ApiModelProperty(value = "是否联网", dataType = DataType.STRING)
    private String connectFlgName;

    /**
     * GPS经度
     */
    @Column(name = "GPS_LON")
    @ApiModelProperty(value = "GPS经度", dataType = DataType.STRING)
    private String gpsLon;

    /**
     * GPS维度
     */
    @Column(name = "GPS_LAT")
    @ApiModelProperty(value = "GPS维度", dataType = DataType.STRING)
    private String gpsLat;

    /**
     * 人桩合影
     */
    @Column(name = "PERSON_PILE_PIC")
    @ApiModelProperty(value = "人桩合影", dataType = DataType.STRING)
    private String personPilePic;

    /**
     * 安装时长
     */
    @Column(name = "INSTALL_SPAN")
    @ApiModelProperty(value = "安装时长", dataType = DataType.STRING)
    private Long installSpan;



    /**
     * 安装确认书
     */
    @Column(name = "INSTALL_CONFIRM_FILE")
    @ApiModelProperty(value = "安装确认书", dataType = DataType.STRING)
    private String installConfirmFile;

    /**
     * 其他安装附件
     */
    @Column(name = "OTHER_INSTALL_FILE")
    @ApiModelProperty(value = "其他安装附件", dataType = DataType.STRING)
    private String otherInstallFile;

    /**
     * 安装备注
     */
    @Column(name = "INSTALL_REMARK")
    @ApiModelProperty(value = "安装备注", dataType = DataType.STRING)
    private String installRemark;

    /**
     * 服务商确认时间 yyyymmdd hhmmss
     */
    @Column(name = "SUPPLIER_CONFIRM_TIME")
    @ApiModelProperty(value = "服务商确认时间", dataType = DataType.STRING)
    private Date supplierConfirmTime;

    /**
     * 是否确认 01 是 02 否
     */
    @Column(name = "SUPPLIER_CONFIRM_FLG")
    @ApiModelProperty(value = "是否确认 01 是 02 否", dataType = DataType.STRING)
    private String supplierConfirmFlg;

    /**
     * 是否确认 01 是 02 否
     */
    @Column(name = "SUPPLIER_CONFIRM_FLG_NAME")
    // @Dict(sourceType = SysConstant.InstallOrder.CONFIG_YORN, sourceField = "supplierConfirmFlg")
    @ApiModelProperty(value = "是否确认", dataType = DataType.STRING)
    private String supplierConfirmFlgName;

    /**
     * 车厂审核 01 未审核 02 审核通过 03 审核拒绝
     */
    @Column(name = "VERIFY_FLG")
    @ApiModelProperty(value = "车厂审核 01 未审核 02 审核通过 03 审核拒绝", dataType = DataType.STRING)
    private String verifyFlg;

    /**
     * 车厂审核 01 未审核 02 审核通过 03 审核拒绝
     */
    @Column(name = "VERIFY_FLG_NAME")
    // @Dict(sourceType = SysConstant.InstallOrder.CONFIG_VERIFY, sourceField = "verifyFlg")
    @ApiModelProperty(value = "车厂审核", dataType = DataType.STRING)
    private String verifyFlgName;

    /**
     * 审核备注
     */
    @Column(name = "VERIFY_REMARK")
    @ApiModelProperty(value = "审核备注", dataType = DataType.STRING)
    private String verifyRemark;

    /**
     * 审核时间 yyyymmdd hhmmss
     */
    @Column(name = "VERIFY_TIME")
    @ApiModelProperty(value = "审核时间", dataType = DataType.STRING)
    private Date verifyTime;

    /**
     * 是否开始做废动作 Y 是 N 否
     */
    @Column(name = "BEGIN_CACLE")
    @ApiModelProperty(value = "是否开始做废动作", dataType = DataType.STRING)
    private String beginCacle;

    /**
     * 是否结算 Y 是 N 否
     */
    @Column(name = "SETTLE_FLG")
    @ApiModelProperty(value = "是否结算", dataType = DataType.STRING)
    private String settleFlg;

    /**
     * 是否完成 Y 是 N 否
     */
    @Column(name = "FINISH_FLG")
    @ApiModelProperty(value = "是否完成", dataType = DataType.STRING)
    private String finishFlg;

    /**
     * 订单完成时间
     */
    @Column(name = "ORDER_FINISH_TIME")
    @ApiModelProperty(value = "订单完成时间", dataType = DataType.STRING)
    private Date orderFinishTime;

    /**
     * 是否巡检 Y 是 N 否
     */
    @Column(name = "INSPECTION_TOUR_FLG")
    @ApiModelProperty(value = "是否巡检", dataType = DataType.STRING)
    private String inspectionTourFlg;

    /**
     * CRM退回备注
     */
    @Column(name = "CRM_ROLLBACK_REMARK")
    @ApiModelProperty(value = "CRM退回备注", dataType = DataType.STRING)
    private String crmRollbackRemark;

    /**
     * 派单勘察提交时间
     */
    @Column(name = "DISPATCH_CHECK_SUBMIT_TIME")
    @ApiModelProperty(value = "派单勘察提交时间", dataType = DataType.STRING)
    private Date dispatchCheckSubmitTime;

    /**
     * 派单安装提交时间
     */
    @Column(name = "DISPATCH_INSTALL_SUBMIT_TIME")
    @ApiModelProperty(value = "派单安装提交时间", dataType = DataType.STRING)
    private Date dispatchInstallSubmitTime;

    /**
     * 满意度提交时间
     */
    @Column(name = "GRADE_SUBMIT_TIME")
    @ApiModelProperty(value = "满意度提交时间", dataType = DataType.STRING)
    private Date gradeSubmitTime;

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
     * 职位
     */
    @Column(name = "POSITION_CODES")
    @ApiModelProperty(value = "职位", dataType = DataType.STRING)
    private String positionCodes;

    /**
     * 步骤矩阵
     */
    @NotEmpty(message = "步骤矩阵", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "STEP_MATRIX")
    @ApiModelProperty(value = "步骤矩阵", dataType = DataType.STRING)
    private String stepMatrix;

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

    // /**
    //  * 是否超时
    //  */
    // @Column(name = "TIMEOUT_FLG")
    // @ApiModelProperty(value = "是否超时", dataType = DataType.STRING)
    // private String timeoutFlg;

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
     * 签收超时标志
     */
    @Transient
    @ApiModelProperty(value = "签收超时标志Y 超时 N 不超时", dataType = DataType.STRING)
    private String dispatchOverTimeFlg = "N";

    @Transient
    private int dispatchOverTime = 0;

    /**
     * 预约超时标志
     */
    @Transient
    @ApiModelProperty(value = "预约超时标志Y 超时 N 不超时", dataType = DataType.STRING)
    private String contractOverTimeFlg = "N";

    @Transient
    private int contractOverTime = 0;

    /**
     * 勘察超时标志
     */
    @Transient
    @ApiModelProperty(value = "勘察超时标志Y 超时 N 不超时", dataType = DataType.STRING)
    private String checkOverTimeFlg = "N";

    @Transient
    private int checkOverTime = 0;

    /**
     * 安装超时标志
     */
    @Transient
    @ApiModelProperty(value = "安装超时标志Y 超时 N 不超时", dataType = DataType.STRING)
    private String installOverFlg = "N";

    @Transient
    private int installkOverTime = 0;

    /**
     * 安装时间
     */
    @Column(name = "INSTALL_TIME")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "安装时间", dataType = DataType.LOCAL_DATE)
    private Date installTime;

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

