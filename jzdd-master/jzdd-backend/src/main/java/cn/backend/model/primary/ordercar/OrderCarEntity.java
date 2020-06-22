package cn.backend.model.primary.ordercar;

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
 * @date 2019/07/17
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "t_order_car")
@ApiModel(value = "t_order_car", description = "t_order_car")
public class OrderCarEntity extends BaseEntity{

    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "cn.zdwl.common.util.IdGenerator")
    @NotEmpty(message = "主键ID不能为空",groups = {DeleteGroup.class,  UpdateGroup.class})
    @Column(name = "CAR_ID")
    @ApiModelProperty(value = "主键ID", dataType = DataType.STRING)
    private String id;

    /**
     * 公司编号
     */
    @NotEmpty(message = "公司编号不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="CORP_NO")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "公司编号", dataType = DataType.STRING)
    private String corpNo;

    /**
     * 订单编码
     */
    @NotEmpty(message = "订单编码不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="ORDER_NO")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "订单编码", dataType = DataType.STRING)
    private String orderNo;

    /**
     * 车主名称
     */
    @NotEmpty(message = "车主名称不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="CAR_OWNER")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "车主名称", dataType = DataType.STRING)
    private String carOwner;

    /**
     * 车主电话
     */
    @NotEmpty(message = "车主电话不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="CAR_OWNER_PHONE")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "车主电话", dataType = DataType.STRING)
    private String carOwnerPhone;

    /**
     * 经销商名称
     */
    @NotEmpty(message = "经销商名称不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="DEALER_NAME")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "经销商名称", dataType = DataType.STRING)
    private String dealerName;

    /**
     * 经销商联系人
     */
    @NotEmpty(message = "经销商联系人不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="DEALER_CONTACT")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "经销商联系人", dataType = DataType.STRING)
    private String dealerContact;

    /**
     * 经销商电话
     */
    @NotEmpty(message = "经销商电话不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="DEALER_TEL")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "经销商电话", dataType = DataType.STRING)
    private String dealerTel;

    /**
     * 车型
     */
    @NotEmpty(message = "车型不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="CAR_VEHICLE")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "车型", dataType = DataType.STRING)
    private String carVehicle;

    /**
     * 车型明细
     */
    @NotEmpty(message = "车型明细不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="VEHICLE_DETAIL")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "车型明细", dataType = DataType.STRING)
    private String vehicleDetail;

    /**
     * 车架号
     */
    @NotEmpty(message = "车架号不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="VIN_NO")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "车架号", dataType = DataType.STRING)
    private String vinNo;

    /**
     * 发动机编号
     */
    @NotEmpty(message = "发动机编号不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="ENGINE_NO")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "发动机编号", dataType = DataType.STRING)
    private String engineNo;

    /**
     * 销售日期 yyyymmdd
     */
    @NotNull(message = "销售日期 yyyymmdd不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="SALE_DATE")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "销售日期 yyyymmdd", dataType = DataType.STRING)
    private Date saleDate;

    /**
     * 备注
     */
    @NotEmpty(message = "备注不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="REMARK")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "备注", dataType = DataType.STRING)
    private String remark;


}

