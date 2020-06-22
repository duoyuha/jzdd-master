package cn.backend.model.primary.viewcomplainorder;

import cn.backend.config.constant.SysConstant;
import cn.backend.config.swagger.DataType;
import cn.backend.model.BaseEntity;
import cn.backend.model.primary.scrminfo.ScrmInfoEntity;
import cn.zdwl.common.Logoutput.FiledLog;
import cn.zdwl.common.converters.dictconvert.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

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
@Table(name = "v_complain_order")
@ApiModel(value = "v_complain_order", description = "投诉订单的视图")
public class ViewComplainEntity extends BaseEntity {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "cn.zdwl.common.util.IdGenerator")
    @Column(name = "COMPLAIN_ID")
    @ApiModelProperty(value = "主键ID", dataType = DataType.STRING)
    private String id;

    /**
     * 公司编号
     */
    @Column(name = "CORP_NO")
    @ApiModelProperty(value = "公司编号", dataType = DataType.STRING)
    private String corpNo;

    /**
     * 订单编码
     */
    @Column(name = "COMPLAIN_NO")
    @ApiModelProperty(value = "订单编码", dataType = DataType.STRING)
    private String complainNo;

    /**
     * 安装订单编码
     */
    @Column(name = "ORDER_NO")
    @ApiModelProperty(value = "安装订单编码", dataType = DataType.STRING)
    private String orderNo;

    /**
     * 联系客户时间
     */
    @Column(name = "CONTACT_CUST_TIME")
    @ApiModelProperty(value = "联系客户时间", dataType = DataType.STRING)
    private Date contactCustTime;

    /**
     * scrm投诉描述
     */
    @Column(name="SCRM_CONTENT_DESC")
    @ApiModelProperty(value = "scrm投诉描述", dataType = DataType.STRING)
    private String scrmContentDesc;

    /**
     * 服务商编码
     */
    @Column(name = "SUPPLIER_NO")
    @ApiModelProperty(value = "服务商编码", dataType = DataType.STRING)
    private String supplierNo;

    /**
     * 服务商名称
     */
    @Column(name = "SUPPLIER_NAME")
    @ApiModelProperty(value = "服务商名称", dataType = DataType.STRING)
    private String supplierName;

    /**
     * 投诉内容
     */
    @Column(name="COMPLAIN_DESC")
    @ApiModelProperty(value = "投诉内容", dataType = DataType.STRING)
    private String complainDesc;

    @Column(name="COMPLAIN_DESC_NAME")
    @ApiModelProperty(value = "投诉内容", dataType = DataType.STRING)
    // @Dict(sourceType = SysConstant.Complain.CONFIG_TYPE_COMPLAINDESC, sourceField = "complainDesc")
    private String complainDescName;

    /**
     * 投诉备注
     */
    @Column(name = "COMPLAIN_REMARK")
    @ApiModelProperty(value = "投诉备注", dataType = DataType.STRING)
    private String complainRemark;

    /**
     * 咨询时间 yyyymmdd hhmmss
     */
    @Column(name = "COMPLAIN_TIME")
    @ApiModelProperty(value = "咨询时间 yyyymmdd hhmmss", dataType = DataType.STRING)
    private Date complainTime;

    /**
     * 签收时间/拒绝时间 yyyymmdd hhmmss
     */
    @Column(name = "RECEIVE_TIME")
    @ApiModelProperty(value = "签收时间/拒绝时间 yyyymmdd hhmmss", dataType = DataType.STRING)
    private Date receiveTime;

    /**
     * 解决时间 yyyymmdd hhmmss
     */
    @Column(name = "SOLUTION_TIME")
    @ApiModelProperty(value = "解决时间 yyyymmdd hhmmss", dataType = DataType.STRING)
    private Date solutionTime;

    /**
     * 签收备注或拒绝备注
     */
    @Column(name = "RECEIVE_DESC")
    @ApiModelProperty(value = "签收备注或拒绝备注", dataType = DataType.STRING)
    private String receiveDesc;

    /**
     * 解决方案
     */
    @Column(name = "SOLUTION_DESC")
    @ApiModelProperty(value = "解决方案", dataType = DataType.STRING)
    private String solutionDesc;

    @Column(name = "SOLUTION_SUPPLIER_NO")
    @ApiModelProperty(value = "解决服务商编码", dataType = DataType.STRING)
    private String solutionSupplierNo;

    @Column(name = "SOLUTION_SUPPLIER_NAME")
    @ApiModelProperty(value = "解决服务商名称", dataType = DataType.STRING)
    private String solutionSupplierName;

    /**
     * 备注
     */
    @Column(name = "SOLUTION_REMARK")
    @ApiModelProperty(value = "备注", dataType = DataType.STRING)
    private String solutionRemark;

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
     * 车架号
     */
    @Column(name = "VIN_NO")
    @ApiModelProperty(value = "车架号", dataType = DataType.STRING)
    private String vinNo;

    /**
     * 是否完成 Y 是 N 否
     */
    @Column(name = "FINISH_FLG")
    @ApiModelProperty(value = "是否完成 Y 是 N 否", dataType = DataType.STRING)
    private String finishFlg;

    /**
     * 编号
     */
    @Column(name = "SCRM_NO")
    @ApiModelProperty(value = "编号", dataType = DataType.STRING)
    private String scrmNo;

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
     * 发动机编号
     */
    @Column(name = "ENGINE_NO")
    @ApiModelProperty(value = "发动机编号", dataType = DataType.STRING)
    private String engineNo;


    /**
     * 矩阵
     */
    @Column(name = "STEP_MATRIX")
    @ApiModelProperty(value = "矩阵", dataType = DataType.STRING)
    private String stepMatrix;

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
     * 安装完成时间
     */
    @Column(name = "FINISH_TIME")
    @ApiModelProperty(value = "安装完成时间", dataType = DataType.LOCAL_DATE_TIME)
    private Date finishTime;

    @Transient
    private ScrmInfoEntity scrmInfoEntity;


    /**
     * scrm是否关闭
     */
    @Column(name = "SCRM_FINISH_FLG")
    @ApiModelProperty(value = "scrm是否关闭", dataType = DataType.STRING)
    private String scrmFinishFlg;

    /**
     * 联系客户超时标志
     */
    @Transient
    private int contactOverTime = 0;

    /**
     * 联系客户超时标志
     */
    @Transient
    @ApiModelProperty(value = "预约超时标志Y 超时 N 不超时", dataType = DataType.STRING)
    private String contactOverTimeFlg = "N";




}

