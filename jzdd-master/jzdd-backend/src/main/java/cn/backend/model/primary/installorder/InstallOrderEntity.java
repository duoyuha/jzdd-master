package cn.backend.model.primary.installorder;

import cn.backend.config.constant.SysConstant;
import cn.backend.config.swagger.DataType;
import cn.backend.model.BaseEntity;
import cn.backend.model.primary.orderattach.OrderAttachEntity;
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
 * @author sunkw
 * @date 2019/07/17
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "t_install_order")
@ApiModel(value = "t_install_order", description = "t_install_order")
public class InstallOrderEntity extends BaseEntity {

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
     * 公司编号
     */
    //@NotEmpty(message = "公司编号不能为空", groups = {UpdateGroup.class})
    @Column(name = "CORP_NO")
    // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "公司编号", dataType = DataType.STRING, hidden = true)
    private String corpNo;

    /**
     * 订单编码
     */
    @NotEmpty(message = "订单编码不能为空", groups = {UpdateGroup.class})
    @Column(name = "ORDER_NO")
    // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "订单编码", dataType = DataType.STRING)
    private String orderNo;

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
     * 提报时间 yyyymmdd hhmmss
     */
    //@NotNull(message = "提报时间 yyyymmdd hhmmss不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "ORDER_TIME")
    // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "提报时间 yyyymmdd hhmmss", dataType = DataType.STRING)
    private Date orderTime;

    /**
     * 巡检编码
     */
    //@NotEmpty(message = "巡检编码不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "CHECK_NO")
    // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "巡检编码", dataType = DataType.STRING)
    private String checkNo;

    /**
     * 联系人
     */
    @NotEmpty(message = "联系人不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "INSTALL_CONTACT")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "联系人", dataType = DataType.STRING)
    private String installContact;

    /**
     * 联系人电话
     */
    @NotEmpty(message = "联系人电话不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "INSTALL_CONTACT_PHONE")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "联系人电话", dataType = DataType.STRING)
    private String installContactPhone;

    /**
     * 安装省份
     */
    @NotEmpty(message = "安装省份不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "INSTALL_PROVINCE")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "安装省份", dataType = DataType.STRING)
    private String installProvince;

    /**
     * 安装城市
     */
    @NotEmpty(message = "安装城市不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "INSTALL_CITY")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "安装城市", dataType = DataType.STRING)
    private String installCity;

    /**
     * 安装区域
     */
    // @NotEmpty(message = "安装区域不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "INSTALL_REGION")
    // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "安装区域", dataType = DataType.STRING)
    private String installRegion;

    /**
     * 安装地址
     */
    @NotEmpty(message = "安装地址不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "INSTALL_ADDRESS")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "安装地址", dataType = DataType.STRING)
    private String installAddress;

    /**
     * 提单备注
     */
    // @NotEmpty(message = "提单备注不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "ORDER_REMARK")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "提单备注", dataType = DataType.STRING)
    private String orderRemark;

    /**
     * 充电桩编码
     */
    // @NotEmpty(message = "充电桩编码不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "PILE_CODE")
    // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "充电桩编码", dataType = DataType.STRING)
    private String pileCode;

    /**
     * 充电桩型号
     */
    // @NotEmpty(message = "充电桩编码不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "PILE_MODEL")
    // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "充电桩型号", dataType = DataType.STRING)
    private String pileModel;

    /**
     * 充电桩型号名称
     */
    // @NotEmpty(message = "充电桩编码不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "PILE_MODEL_NAME")
    // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "充电桩型号名称", dataType = DataType.STRING)
    private String pileModelName;

    /**
     * 充电桩类型 01 壁挂式 02 立柱式
     */
    // @NotEmpty(message = "充电桩类型不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "PILE_TYPE")
    // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "充电桩类型 01 壁挂式 02 立柱式", dataType = DataType.STRING)
    private String pileType;

    /**
     * 充电桩类型 01 壁挂式 02 立柱式
     */
    // @NotEmpty(message = "充电桩类型不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "PILE_TYPE_NAME")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @Dict(sourceType = SysConstant.InstallOrder.CONFIG_PILETYPE, sourceField = "pileType")
    @ApiModelProperty(value = "充电桩类型", dataType = DataType.STRING)
    private String pileTypeName;

    /**
     * 服务商编码
     */
    @NotEmpty(message = "服务商不能为空", groups = {UpdateGroup.class})
    @Column(name = "SUPPLIER_NO")
    // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "服务商编号", dataType = DataType.STRING)
    private String supplierNo;

    /**
     * 服务商名称
     */
    // @NotEmpty(message = "服务商名称不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "SUPPLIER_NAME")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "服务商", dataType = DataType.STRING)
    private String supplierName;

    /**
     * 派单备注
     */
    // @NotEmpty(message = "派单备注不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "DISPATCH_REMARK")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "派单备注", dataType = DataType.STRING)
    private String dispatchRemark;

    /**
     * 签收状态 01 未签收 02 签收 03 拒绝
     */
    // @NotEmpty(message = "签收状态不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "RECEIVE_STATUS")
    // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "签收状态 01 未签收 02 签收 03 拒绝", dataType = DataType.STRING)
    private String receiveStatus;

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
     * 派单备注
     */
    // @NotEmpty(message = "派单备注不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "RECEIVE_REMARK")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "派单备注", dataType = DataType.STRING)
    private String receiveRemark;

    /**
     * 联系客户时间 yyyymmdd hhmmss
     */
    // @NotNull(message = "联系客户时间不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "CONTACT_CUST_TIME")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "联系客户时间", dataType = DataType.STRING)
    private Date contactCustTime;

    /**
     *签收时间 yyyymmdd hhmmss
     */
    // @NotNull(message = "联系客户时间不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "RECEIVE_TIME")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "签收时间", dataType = DataType.STRING)
    private Date receiveTime;





    /**
     * 预约时间 yyyymmdd hhmmss
     */
    // @NotNull(message = "预约时间不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "APPOINTMENT_TIME")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "预约时间", dataType = DataType.STRING)
    private Date appointmentTime;

    /**
     * 预约备注
     */
    // @NotEmpty(message = "预约备注不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "APPOINTMENT_REMARK")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "预约备注", dataType = DataType.STRING)
    private String appointmentRemark;

    /**
     * 勘察人员编码
     */
    // @NotEmpty(message = "勘察人员不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "CHECK_USER_NO")
    // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "勘察人员编码", dataType = DataType.STRING)
    private String checkUserNo;

    /**
     * 勘察人员名称
     */
    // @NotEmpty(message = "勘察人员不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "CHECK_USER_NAME")
    // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "勘察人员名称", dataType = DataType.STRING)
    private String checkUserName;

    /**
     * 勘察人员电话
     */
    // @NotEmpty(message = "勘察人员不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "CHECK_USER_PHONE")
    // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "勘察人员电话", dataType = DataType.STRING)
    private String checkUserPhone;


    /**
     * 安装完成时间
     */
    @Column(name = "FINISH_TIME")
    @ApiModelProperty(value = "安装完成时间", dataType = DataType.LOCAL_DATE_TIME)
    private Date finishTime;
    /**
     * 派单勘察备注
     */
    // @NotEmpty(message = "派单勘察备注不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "ASSIGN_CHECK_REMARK")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "派单勘察备注", dataType = DataType.STRING)
    private String assignCheckRemark;

    /**
     * 勘察时间 yyyymmdd hhmmss
     */
    // @NotNull(message = "勘察时间不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "CHECK_TIME")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "勘察时间", dataType = DataType.STRING)
    private Date checkTime;

    /**
     * 勘察结束时间 yyyymmdd hhmmss
     */
    // @NotNull(message = "勘察时间不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "CHECK_FINISH_TIME")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "勘察结束时间", dataType = DataType.STRING)
    private Date checkFinishTime;

    /**
     * 布线距离
     */
    // @NotNull(message = "布线距离不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "WIRE_LENGTH")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "布线距离", dataType = DataType.STRING)
    private Integer wireLength;

    /**
     * 是否报装 01 是 02 否
     */
    // @NotEmpty(message = "是否报装不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "INSTALL_FLG")
    // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "是否报装", dataType = DataType.STRING)
    private String installFlg;

    @Column(name = "CRM_ROLLBACK_FLG")
    @ApiModelProperty(value = "是否CRM撤回", dataType = DataType.STRING)
    private String crmRollbackFlg = SysConstant.InstallOrder.CONFIG_FLG_N;



    /**
     * 是否报装 01 是 02 否
     */
    // @NotEmpty(message = "是否报装 01 是 02 否不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "INSTALL_FLG_NAME")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @Dict(sourceType = SysConstant.InstallOrder.CONFIG_YORN, sourceField = "installFlg")
    @ApiModelProperty(value = "是否报装", dataType = DataType.STRING)
    private String installFlgName;

    /**
     * 预计价格
     */
    // @NotNull(message = "预计价格不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "ESTIMATE_PRICE")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "预计价格", dataType = DataType.STRING)
    private Double estimatePrice;

    /**
     * 用电类型 01 物业电 02 自家电 03 国网电
     */
    // @NotEmpty(message = "用电类型不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "ELEC_TYPE")
    // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "用电类型 01 物业电 02 自家电 03 国网电", dataType = DataType.STRING)
    private String elecType;

    /**
     * 用电类型 01 物业电 02 自家电 03 国网电
     */
    // @NotEmpty(message = "用电类型不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "ELEC_TYPE_NAME")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @Dict(sourceType = SysConstant.InstallOrder.CONFIG_ELECTYPE, sourceField = "elecType")
    @ApiModelProperty(value = "用电类型", dataType = DataType.STRING)
    private String elecTypeName;

    /**
     * 安装方案
     */
    // @NotEmpty(message = "安装方案不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "INSTALL_METHOD_FILE")
    // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "安装方案", dataType = DataType.STRING)
    private String installMethodFile;

    /**
     * 其他附件
     */
    // @NotEmpty(message = "其他附件不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "OTHER_FILE")
    // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "其他附件", dataType = DataType.STRING)
    private String otherFile;

    /**
     * 勘察备注
     */
    // @NotEmpty(message = "勘察备注不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "CHECK_REMARK")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "勘察备注", dataType = DataType.STRING)
    private String checkRemark;

    /**
     * 预约安装时间 yyyymmdd hhmmss
     */
    // @NotNull(message = "预约安装时间不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "APPOINTMENT_INSTALL_TIME")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
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
    // @NotEmpty(message = "预约安装备注不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "APPOINTMENT_INSTALL_REMARK")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "预约安装备注", dataType = DataType.STRING)
    private String appointmentInstallRemark;

    /**
     * 安装人员编码
     */
    // @NotEmpty(message = "安装人员不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "INSTALL_USER_NO")
    // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "安装人员编码", dataType = DataType.STRING)
    private String installUserNo;

    /**
     * 安装人员名称
     */
    // @NotEmpty(message = "安装人员不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "INSTALL_USER_NAME")
    // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "安装人员名称", dataType = DataType.STRING)
    private String installUserName;

    /**
     * 安装人员电话
     */
    // @NotEmpty(message = "勘察人员不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "INSTALL_USER_PHONE")
    // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "安装人员电话", dataType = DataType.STRING)
    private String installUserPhone;


    /**
     * 派单勘察备注
     */
    // @NotEmpty(message = "派单勘察备注不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "ASSIGN_INSTALL_REMARK")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "派单勘察备注", dataType = DataType.STRING)
    private String assignInstallRemark;

    /**
     * 安装时间
     */
    @Column(name = "INSTALL_TIME")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "安装时间", dataType = DataType.STRING)
    private Date installTime;


    /**
     * 安装完成时间
     */
    @Column(name = "INSTALL_FINISH_TIME")
    @ApiModelProperty(value = "安装完成时间", dataType = DataType.STRING)
    private Date installFinishTime;


    /**
     * 铺设方式 01 穿管 02 桥架 03 地沟 04 暗孵 05 其他
     */
    // @NotEmpty(message = "铺设方式不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "INSTALL_TYPE")
    // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "铺设方式 01 穿管 02 桥架 03 地沟 04 暗孵 05 其他", dataType = DataType.STRING)
    private String installType;

    /**
     * 铺设方式 01 穿管 02 桥架 03 地沟 04 暗孵 05 其他
     */
    // @NotEmpty(message = "铺设方式 01 穿管 02 桥架 03 地沟 04 暗孵 05 其他不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "INSTALL_TYPE_NAME")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @Dict(sourceType = SysConstant.InstallOrder.CONFIG_INSTALLTYPE, sourceField = "installType")
    @ApiModelProperty(value = "铺设方式", dataType = DataType.STRING)
    private String installTypeName;

    /**
     * 收费金额
     */
    // @NotNull(message = "收费金额不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "ACTUAL_PRICE")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "收费金额", dataType = DataType.STRING)
    private Double actualPrice;

    /**
     * 是否联网 01 是 02 否
     */
    // @NotEmpty(message = "是否联网不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "CONNECT_FLG")
    // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "是否联网 01 是 02 否", dataType = DataType.STRING)
    private String connectFlg;

    /**
     * 是否联网 01 是 02 否
     */
    // @NotEmpty(message = "是否联网 01 是 02 否不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "CONNECT_FLG_NAME")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @Dict(sourceType = SysConstant.InstallOrder.CONFIG_YORN, sourceField = "connectFlg")
    @ApiModelProperty(value = "是否联网", dataType = DataType.STRING)
    private String connectFlgName;

    /**
     * GPS经度
     */
    // @NotEmpty(message = "GPS经度不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "GPS_LON")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "GPS经度", dataType = DataType.STRING)
    private String gpsLon;

    /**
     * GPS维度
     */
    // @NotEmpty(message = "GPS维度不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "GPS_LAT")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "GPS维度", dataType = DataType.STRING)
    private String gpsLat;

    /**
     * 人桩合影
     */
    // @NotEmpty(message = "人桩合影不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "PERSON_PILE_PIC")
    // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "人桩合影", dataType = DataType.STRING)
    private String personPilePic;

    /**
     * 安装确认书
     */
    // @NotEmpty(message = "安装确认书不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "INSTALL_CONFIRM_FILE")
    // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "安装确认书", dataType = DataType.STRING)
    private String installConfirmFile;

    /**
     * 其他安装附件
     */
    // @NotEmpty(message = "安装确认书不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "OTHER_INSTALL_FILE")
    // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "其他安装附件", dataType = DataType.STRING)
    private String otherInstallFile;

    /**
     * 安装备注
     */
    // @NotEmpty(message = "安装备注不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "INSTALL_REMARK")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "安装备注", dataType = DataType.STRING)
    private String installRemark;

    /**
     * 服务商确认时间 yyyymmdd hhmmss
     */
    // @NotNull(message = "服务商确认时间不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "SUPPLIER_CONFIRM_TIME")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "服务商确认时间", dataType = DataType.STRING)
    private Date supplierConfirmTime;

    /**
     * 是否确认 01 是 02 否
     */
    // @NotEmpty(message = "是否确认不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "SUPPLIER_CONFIRM_FLG")
    // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "是否确认 01 是 02 否", dataType = DataType.STRING)
    private String supplierConfirmFlg;

    /**
     * 是否确认 01 是 02 否
     */
    // @NotEmpty(message = "是否确认 01 是 02 否不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "SUPPLIER_CONFIRM_FLG_NAME")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @Dict(sourceType = SysConstant.InstallOrder.CONFIG_YORN, sourceField = "supplierConfirmFlg")
    @ApiModelProperty(value = "是否确认", dataType = DataType.STRING)
    private String supplierConfirmFlgName;

    /**
     * 车厂审核 01 未审核 02 审核通过 03 审核拒绝
     */
    // @NotEmpty(message = "车厂审核不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "VERIFY_FLG")
    // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "车厂审核 01 未审核 02 审核通过 03 审核拒绝", dataType = DataType.STRING)
    private String verifyFlg;

    /**
     * 车厂审核 01 未审核 02 审核通过 03 审核拒绝
     */
    // @NotEmpty(message = "车厂审核 01 未审核 02 审核通过 03 审核拒绝不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "VERIFY_FLG_NAME")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @Dict(sourceType = SysConstant.InstallOrder.CONFIG_VERIFY, sourceField = "verifyFlg")
    @ApiModelProperty(value = "车厂审核", dataType = DataType.STRING)
    private String verifyFlgName;

    /**
     * 审核备注
     */
    // @NotEmpty(message = "审核备注不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "VERIFY_REMARK")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "审核备注", dataType = DataType.STRING)
    private String verifyRemark;

    /**
     * 审核时间 yyyymmdd hhmmss
     */
    // @NotNull(message = "审核时间不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "VERIFY_TIME")
    // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "审核时间", dataType = DataType.STRING)
    private Date verifyTime;

    /**
     * 派单时间 yyyymmdd hhmmss
     */
    // @NotNull(message = "审核时间不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "DISPATCH_TIME")
    // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "派单时间", dataType = DataType.STRING)
    private Date dispatchTime;

    /**
     * CRM退回备注
     */
    // @NotNull(message = "审核时间不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "CRM_ROLLBACK_REMARK")
    // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "CRM退回备注", dataType = DataType.STRING)
    private String crmRollbackRemark;

    /**
     * 派单勘察提交时间
     */
    // @NotNull(message = "派单勘察提交时间不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "DISPATCH_CHECK_SUBMIT_TIME")
    // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "派单勘察提交时间", dataType = DataType.STRING)
    private Date dispatchCheckSubmitTime;

    /**
     * 派单安装提交时间
     */
    // @NotNull(message = "派单安装提交时间不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "DISPATCH_INSTALL_SUBMIT_TIME")
    // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "派单安装提交时间", dataType = DataType.STRING)
    private Date dispatchInstallSubmitTime;

    /**
     * 满意度提交时间
     */
    // @NotNull(message = "满意度提交时间不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "GRADE_SUBMIT_TIME")
    // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "满意度提交时间", dataType = DataType.STRING)
    private Date gradeSubmitTime;

    /**
     * 是否开始做废动作 Y 是 N 否
     */
    // @NotEmpty(message = "是否开始做废动作不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "BEGIN_CACLE")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "是否开始做废动作 Y 是 N 否", dataType = DataType.STRING)
    private String beginCacle;

    /**
     * 是否结算 Y 是 N 否
     */
    // @NotEmpty(message = "是否结算 Y 是 N 否不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "SETTLE_FLG")
    // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "是否结算 Y 是 N 否", dataType = DataType.STRING)
    private String settleFlg;

    /**
     * 是否完成 Y 是 N 否
     */
    // @NotEmpty(message = "是否完成 Y 是 N 否不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "FINISH_FLG")
    // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "是否完成 Y 是 N 否", dataType = DataType.STRING)
    private String finishFlg;

    /**
     * 是否巡检 Y 是 N 否
     */
    // @NotEmpty(message = "是否巡检 Y 是 N 否", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "INSPECTION_TOUR_FLG")
    // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "是否巡检 Y 是 N 否", dataType = DataType.STRING)
    private String inspectionTourFlg;

    /**
     * vin 用于创建订单时查询车主信息
     */
    @Transient
    @NotEmpty(message = "VIN不能为空", groups = {CreateGroup.class})
    private String vin;

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
     * 超时开始时间
     */
    @Transient
    private Date timeoutStartTime;

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
     * 满意度
     */
    @Transient
    private OrderGradeEntity orderGradeEntity;

    /**
     * 审批状态 01通过 02拒绝
     */
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
     * crm参数
     */
    @Transient
    private String crmTestFlg = "N";

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

    // /**
    //  * 是否二次安装:Y /N
    //  */
    // @Transient
    // private String secondInstall;


}

