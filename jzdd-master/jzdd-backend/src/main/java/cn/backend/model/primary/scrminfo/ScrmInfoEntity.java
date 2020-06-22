package cn.backend.model.primary.scrminfo;

import cn.backend.config.constant.SysConstant;
import cn.backend.config.swagger.DataType;
import cn.backend.model.BaseEntity;
import cn.zdwl.common.Logoutput.FiledLog;
import cn.zdwl.common.converters.dictconvert.Dict;
import cn.zdwl.common.validgroup.CreateGroup;
import cn.zdwl.common.validgroup.DeleteGroup;
import cn.zdwl.common.validgroup.UpdateGroup;
import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Date;

/**
 *
 * @author sunkw
 * @date 2019/07/17
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "t_scrm_info")
@ApiModel(value = "t_scrm_info", description = "t_scrm_info")
public class ScrmInfoEntity extends BaseEntity{

    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "cn.zdwl.common.util.IdGenerator")
    @NotEmpty(message = "主键ID不能为空",groups = {DeleteGroup.class,  UpdateGroup.class})
    @Column(name = "SCRM_ID")
    @ApiModelProperty(value = "主键ID", dataType = DataType.STRING)
    private String id;

    /**
     * SCRM ID
     */
    @NotEmpty(message = "SCRM ID不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="CASE_ID")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "SCRM ID", dataType = DataType.STRING)
    private String caseId;

    /**
     * 公司编号
     */
    @NotEmpty(message = "公司编号不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="CORP_NO")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "公司编号", dataType = DataType.STRING)
    private String corpNo;

    /**
     * 编号
     */
    @NotEmpty(message = "编号不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="SCRM_NO")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "编号", dataType = DataType.STRING)
    private String scrmNo;

    /**
     * 订单状态
     */
    @NotEmpty(message = "订单状态不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="CASE_STATUS")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "订单状态", dataType = DataType.STRING)
    private String caseStatus;

    /**
     * 车主姓名
     */
    @NotEmpty(message = "车主姓名不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="CUST_NAME")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "车主姓名", dataType = DataType.STRING)
    private String custName;

    /**
     * 车主电话
     */
    @NotEmpty(message = "车主电话不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="CUST_TEL")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "车主电话", dataType = DataType.STRING)
    private String custTel;

    /**
     * 工单类型(1.欧拉销售咨询2.欧拉售后咨询3.欧拉售后投诉4.WEY销售咨询5.WEY售后咨询6.WEY售后投诉)
     */
    @NotEmpty(message = "工单类型：1.欧拉售后咨询2.欧拉售后投诉3.WEY售后咨询4.WEY售后投诉不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="TYPE_CODE")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "工单类型：1.欧拉售后咨询2.欧拉售后投诉3.WEY售后咨询4.WEY售后投诉", dataType = DataType.STRING)
    private String typeCode;

    /**
     * 工单类型(1.欧拉销售咨询2.欧拉售后咨询3.欧拉售后投诉4.WEY销售咨询5.WEY售后咨询6.WEY售后投诉)
     */
    @NotEmpty(message = "工单类型：1.欧拉售后咨询2.欧拉售后投诉3.WEY售后咨询4.WEY售后投诉不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="TYPE_CODE_NAME")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "工单类型：1.欧拉售后咨询2.欧拉售后投诉3.WEY售后咨询4.WEY售后投诉", dataType = DataType.STRING)
    @Dict(sourceType = SysConstant.Scrm.CASE_TYPE, sourceField = "typeCode")
    private String typeCodeName;

    /**
     * VIN
     */
    @NotEmpty(message = "VIN不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="VIN")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "VIN", dataType = DataType.STRING)
    private String vin;

    /**
     * 车型名称
     */
    @NotEmpty(message = "车型名称不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="VEHICLE_TYPE_NAME")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "车型名称", dataType = DataType.STRING)
    @JSONField(name="VehicleTypeName")
    private String vehicleTypeName;

    /**
     * 行驶里程
     */
    @NotEmpty(message = "行驶里程不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="MILLEAGE")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "行驶里程", dataType = DataType.STRING)
    @JSONField(name="Mileage")
    private String milleage;

    /**
     * 车牌号
     */
    @NotEmpty(message = "车牌号不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="PLATE_NUMBER")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "车牌号", dataType = DataType.STRING)
    @JSONField(name="PlateNumber")
    private String plateNumber;

    /**
     * 主题
     */
    @NotEmpty(message = "主题不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="SUBJECT")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "主题", dataType = DataType.STRING)
    @JSONField(name="Subject")
    private String subject;

    /**
     * 一级分类
     */
    @NotEmpty(message = "一级分类不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="CATEGORY_1")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "一级分类", dataType = DataType.STRING)
    @JSONField(name="Category1")
    private String category1;

    /**
     * 二级分类
     */
    @NotEmpty(message = "二级分类不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="CATEGORY_2")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "二级分类", dataType = DataType.STRING)
    @JSONField(name="SecondCategory1")
    private String category2;

    /**
     * 三级分类
     */
    @NotEmpty(message = "三级分类不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="CATEGORY_3")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "三级分类", dataType = DataType.STRING)
    @JSONField(name="ThirdCategory1")
    private String category3;

    /**
     * 投诉描述
     */
    @NotEmpty(message = "投诉描述不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="CONTENT_DESC")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "投诉描述", dataType = DataType.STRING)
    private String contentDesc;

    /**
     * 1咨询
     * 2投诉
     */
    @Transient
    private Integer adviceOrComplain;

    /**
     * 创建时间
     */
    @Transient
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 签名
     */
    @Transient
    private String sign;

    @Transient
    private boolean isNew;
}

