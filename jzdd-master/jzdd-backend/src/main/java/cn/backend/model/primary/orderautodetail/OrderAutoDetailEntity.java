package cn.backend.model.primary.orderautodetail;

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
 * @date 2019/08/14
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "t_order_auto_detail")
@ApiModel(value = "t_order_auto_detail", description = "t_order_auto_detail")
public class OrderAutoDetailEntity extends BaseEntity{

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
     * 自动派单编码
     */
    @NotEmpty(message = "自动派单编码不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="ORDER_AUTO_NO")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "自动派单编码", dataType = DataType.STRING)
    private String orderAutoNo;

    /**
     * 安装服务商编码
     */
    @NotEmpty(message = "安装服务商编码不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="SUPPLIER_NO")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "安装服务商编码", dataType = DataType.STRING)
    private String supplierNo;

    /**
     * 订单编号
     */
    @NotEmpty(message = "订单编号不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="ORDER_NO")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "订单编号", dataType = DataType.STRING)
    private String orderNo;


}

