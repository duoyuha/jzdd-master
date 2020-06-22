package cn.backend.model.primary.viewdashbordrank;

import cn.backend.config.swagger.DataType;
import cn.backend.model.BaseEntity;
import cn.zdwl.common.validgroup.CreateGroup;
import cn.zdwl.common.validgroup.DeleteGroup;
import cn.zdwl.common.validgroup.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "v_dashboard_rank")
@ApiModel(value = "v_dashboard_rank", description = "订单数量的视图")
public class ViewDashbordRankEntity extends BaseEntity {

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
     * 省份
     */
    //@NotEmpty(message = "省份不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="INSTALL_PROVINCE")
    // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "省份", dataType = DataType.STRING)
    private String installProvince;

    /**
     * 车型名称
     */
    // @NotEmpty(message = "车型名称",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="CAR_VEHICLE")
    // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "车型名称", dataType = DataType.STRING)
    private String carVehicle;

    /**
     * 结束标志
     */
    //  @NotEmpty(message = "流程名称 01 安装订单 02 配送订单 03 投诉 04 报修 05 作废不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="FINISH_FLG")
    // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "结束标志", dataType = DataType.STRING)
    private String finishFlg;

    /**
     * 订单数量
     */
    // @NotEmpty(message = "安装服务商编码不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="ORDER_NUM")
    // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "订单数量", dataType = DataType.INT)
    private Integer orderNum;

    /**
     * 服务商编码
     */
    @NotEmpty(message = "服务商不能为空", groups = {UpdateGroup.class})
    @Column(name = "SUPPLIER_NO")
    // @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "服务商编号", dataType = DataType.STRING)
    private String supplierNo;


}
