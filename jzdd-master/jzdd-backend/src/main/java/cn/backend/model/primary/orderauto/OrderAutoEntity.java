package cn.backend.model.primary.orderauto;

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
/**
 *
 * @author sunkw
 * @date 2019/08/13
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "t_order_auto")
@ApiModel(value = "t_order_auto", description = "t_order_auto")
public class OrderAutoEntity extends BaseEntity{

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
   // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "公司编码", dataType = DataType.STRING)
    private String corpNo;

    /**
     * 自动派单编码
     */
    @NotEmpty(message = "自动派单编码不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="ORDER_AUTO_NO")
    // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "自动派单编码", dataType = DataType.STRING)
    private String orderAutoNo;

    /**
     * 流程编码：01 安装订单 02 配送订单 03 投诉 04 报修 05 作废
     */
   // @NotEmpty(message = "流程编码：01 安装订单 02 配送订单 03 投诉 04 报修 05 作废不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="ORDER_TYPE")
  // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "流程编码：01 安装订单 02 配送订单 03 投诉 04 报修 05 作废", dataType = DataType.STRING)
    private String orderType;

    /**
     * 流程名称 01 安装订单 02 配送订单 03 投诉 04 报修 05 作废
     */
  //  @NotEmpty(message = "流程名称 01 安装订单 02 配送订单 03 投诉 04 报修 05 作废不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="ORDER_TYPE_NAME")
   // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "流程名称 01 安装订单 02 配送订单 03 投诉 04 报修 05 作废", dataType = DataType.STRING)
    private String orderTypeName;

    /**
     * 安装服务商编码
     */
   // @NotEmpty(message = "安装服务商编码不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="SUPPLIER_NO")
   // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "安装服务商编码", dataType = DataType.STRING)
    private String supplierNo;

    /**
     * 安装服务商名称
     */
  //  @NotEmpty(message = "安装服务商名称不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="SUPPLIER_NAME")
  //  @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "安装服务商名称", dataType = DataType.STRING)
    private String supplierName;

    /**
     * 订单数量
     */
  //  @NotNull(message = "订单数量不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="ORDER_NUM")
   // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "订单数量", dataType = DataType.STRING)
    private Integer orderNum;

    /**
     * 城市名称
     */
  //  @NotEmpty(message = "城市名称不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="CITY_NAME")
  //  @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "城市名称", dataType = DataType.STRING)
    private String cityName;

    /**
     * 城市名称
     */
    //  @NotEmpty(message = "城市名称不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="PROVINCE_NAME")
    //  @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "城市名称", dataType = DataType.STRING)
    private String provinceName;

    /**
     * 备注
     */
  //  @NotEmpty(message = "备注不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="REMARK")
   // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "备注", dataType = DataType.STRING)
    private String remark;


}

