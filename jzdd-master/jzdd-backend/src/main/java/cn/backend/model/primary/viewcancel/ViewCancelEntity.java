package cn.backend.model.primary.viewcancel;

import cn.backend.config.constant.SysConstant;
import cn.backend.config.swagger.DataType;
import cn.backend.model.BaseEntity;
import cn.backend.model.primary.orderstep.OrderStepEntity;
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
@Table(name = "v_order_cancel")
@ApiModel(value = "v_order_cancel", description = "作废视图")
public class ViewCancelEntity extends BaseEntity {

    /**
     * 主键
     */
    @Id
    @Column(name = "STEP_ID")
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
     * 职位
     */
    @Column(name = "POSITION_CODES")
    @ApiModelProperty(value = "职位", dataType = DataType.STRING)
    private String positionCodes;

    /**
     * 步骤名
     */
    @Column(name = "STEP_NAME")
    @ApiModelProperty(value = "步骤名", dataType = DataType.STRING)
    private String stepName;

    /**
     * 步骤号
     */
    @Column(name = "FOLLOW_SEQ")
    @ApiModelProperty(value = "步骤号", dataType = DataType.INT)
    private Integer followSeq;

    /**
     * 步骤矩阵
     */
    @NotEmpty(message = "步骤矩阵", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "STEP_MATRIX")
    @ApiModelProperty(value = "步骤矩阵", dataType = DataType.STRING)
    private String stepMatrix;

    /**
     * 订单编码
     */
    @Column(name = "ORDER_NO")
    @ApiModelProperty(value = "订单编码", dataType = DataType.STRING)
    private String orderNo;

    /**
     * 操作时间
     */
    //  @NotNull(message = "操作时间不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="STEP_TIME")
    @ApiModelProperty(value = "操作时间", dataType = DataType.STRING)
    private Date stepTime;

    /**
     * 流程编码：01 安装订单 02 配送订单 03 投诉 04 报修 05 作废
     */
    @NotEmpty(message = "流程编码：01 安装订单 02 配送订单 03 投诉 04 报修 05 作废不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="FOLLOW_CODE")
    @ApiModelProperty(value = "流程编码：01 安装订单 02 配送订单 03 投诉 04 报修 05 作废", dataType = DataType.STRING)
    private String followCode;

    /**
     * 流程名称 01 安装订单 02 配送订单 03 投诉 04 报修 05 作废
     */
    @NotEmpty(message = "流程名称 01 安装订单 02 配送订单 03 投诉 04 报修 05 作废不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="FOLLOW_NAME")
    @Dict(sourceType = SysConstant.WorkFollow.CONFIG_TYPE_WORKFOLLOW, sourceField = "followCode")
    @ApiModelProperty(value = "流程名称 01 安装订单 02 配送订单 03 投诉 04 报修 05 作废", dataType = DataType.STRING)
    private String followName;

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
     * 安装地址
     */
    @Column(name = "INSTALL_ADDRESS")
    @ApiModelProperty(value = "安装地址", dataType = DataType.STRING)
    private String installAddress;

    /**
     * 提报时间 yyyymmdd hhmmss
     */
    //@NotNull(message = "提报时间不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="ORDER_TIME",updatable = false)
    @ApiModelProperty(value = "提报时间 yyyymmdd hhmmss", dataType = DataType.STRING)
    private Date orderTime;

    /**
     * 审批结果：01 同意 02 拒绝
     */
    @NotEmpty(message = "审批结果：01 同意 02 拒绝不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="RESULT_CODE")
    @ApiModelProperty(value = "审批结果：01 同意 02 拒绝", dataType = DataType.STRING)
    private String resultCode;

    /**
     * 车架号
     */
    @Column(name = "VIN_NO")
    @ApiModelProperty(value = "车架号", dataType = DataType.STRING)
    private String vinNo;

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

    @Column(name = "FINISH_FLG")
    private String finishFlg;

    @Column(name = "ORDER_TYPE")
    private String orderType;

    @Column(name = "APPLY_NO")
    private String applyNo;

    @Transient
    private String installId;

    @Transient
    private List<OrderStepEntity> orderStepEntityList;


}

