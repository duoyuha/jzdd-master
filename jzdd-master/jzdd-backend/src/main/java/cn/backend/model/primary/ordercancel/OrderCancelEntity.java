package cn.backend.model.primary.ordercancel;

import cn.backend.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.persistence.*;
import cn.zdwl.common.Logoutput.FiledLog;
import cn.backend.config.constant.SysConstant;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;
import cn.zdwl.common.validgroup.CreateGroup;
import cn.zdwl.common.validgroup.DeleteGroup;
import cn.zdwl.common.validgroup.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import cn.backend.config.swagger.DataType;
import org.hibernate.annotations.Cache;

import java.util.Date;

/**
 *
 * @author sunkw
 * @date 2019/08/26
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "t_order_cancel")
@ApiModel(value = "t_order_cancel", description = "t_order_cancel")
public class OrderCancelEntity extends BaseEntity{

    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "cn.zdwl.common.util.IdGenerator")
    @NotEmpty(message = "主键ID不能为空",groups = {DeleteGroup.class,  UpdateGroup.class})
    @Column(name = "ID")
    @ApiModelProperty(value = "主键ID", dataType = DataType.STRING)
    private String id;

    /**
     * 公司编码
     */
    @NotEmpty(message = "公司编码不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="CORP_NO")

    @ApiModelProperty(value = "公司编码", dataType = DataType.STRING)
    private String corpNo;

    /**
     * 订单号
     */
   // @NotEmpty(message = "申请号不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="APPLY_NO")

    @ApiModelProperty(value = "申请号", dataType = DataType.STRING)
    private String applyNo;


    /**
     * 订单号
     */
    @NotEmpty(message = "订单号不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="ORDER_NO")

    @ApiModelProperty(value = "订单号", dataType = DataType.STRING)
    private String orderNo;

    /**
     * 流程编码：01 安装订单 02 配送订单 03 投诉 04 报修 05 作废
     */
  //  @NotEmpty(message = "流程编码：01 安装订单 02 配送订单 03 投诉 04 报修 05 作废不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="ORDER_TYPE")

    @ApiModelProperty(value = "流程编码：01 安装订单 02 配送订单 03 投诉 04 报修 05 作废", dataType = DataType.STRING)
    private String orderType;

    /**
     * 流程名称 01 安装订单 02 配送订单 03 投诉 04 报修 05 作废
     */
  //  @NotEmpty(message = "流程名称 01 安装订单 02 配送订单 03 投诉 04 报修 05 作废不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="ORDER_TYPE_NAME")

    @ApiModelProperty(value = "流程名称 01 安装订单 02 配送订单 03 投诉 04 报修 05 作废", dataType = DataType.STRING)
    private String orderTypeName;

    /**
     * 安装服务商编码
     */
   // @NotEmpty(message = "安装服务商编码不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="SUPPLIER_NO")

    @ApiModelProperty(value = "安装服务商编码", dataType = DataType.STRING)
    private String supplierNo;

    /**
     * 安装服务商名称
     */
   // @NotEmpty(message = "安装服务商名称不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="SUPPLIER_NAME")
  //  @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "安装服务商名称", dataType = DataType.STRING)
    private String supplierName;

    /**
     * 申请时间
     */
  //  @NotNull(message = "申请时间不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="APPLY_DATE")
  //  @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "申请时间", dataType = DataType.STRING)
    private Date applyDate;

    /**
     * 申请原因
     */
   // @NotEmpty(message = "申请原因不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="APPLY_REASON")
   // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "申请原因", dataType = DataType.STRING)
    private String applyReason;

    /**
     * 初审时间
     */
   // @NotNull(message = "初审时间不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="FIRST_VERIFY_DATE")
   // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "初审时间", dataType = DataType.STRING)
    private Date firstVerifyDate;

    /**
     * 初审备注
     */
  //  @NotEmpty(message = "初审备注不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="FIRST_VERIFY_REMARK")
 //   @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "初审备注", dataType = DataType.STRING)
    private String firstVerifyRemark;

    /**
     * 复审时间
     */
  //  @NotNull(message = "复审时间不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="SECOND_VERIFY_DATE")
  //  @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "复审时间", dataType = DataType.STRING)
    private Date secondVerifyDate;

    /**
     * 复审备注
     */
  //  @NotEmpty(message = "复审备注不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="SECOND_VERIFY_REMARK")
  //  @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "复审备注", dataType = DataType.STRING)
    private String secondVerifyRemark;

    /**
     * 备注
     */
  //  @NotEmpty(message = "备注不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="REMARK")
   // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "备注", dataType = DataType.STRING)
    private String remark;

    @Transient
    private  String receiveStatus;

    @Transient
    @JsonIgnore
    private String verifyStatus = "01";

    @Transient
    private String installId;


}

