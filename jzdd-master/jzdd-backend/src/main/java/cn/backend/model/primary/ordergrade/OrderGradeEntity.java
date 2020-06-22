package cn.backend.model.primary.ordergrade;

import cn.backend.config.constant.SysConstant;
import cn.backend.config.swagger.DataType;
import cn.backend.model.BaseEntity;
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
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
/**
 *
 * @author sunkw
 * @date 2019/07/23
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "t_order_grade")
@ApiModel(value = "t_order_grade", description = "t_order_grade")
public class OrderGradeEntity extends BaseEntity{

    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "cn.zdwl.common.util.IdGenerator")
    @NotEmpty(message = "主键ID不能为空",groups = {DeleteGroup.class,  UpdateGroup.class})
    @Column(name = "GRADE_ID")
    @ApiModelProperty(value = "主键ID", dataType = DataType.STRING)
    private String id;

    /**
     * 公司编号
     */
    //@NotEmpty(message = "公司编号不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="CORP_NO")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "公司编号", dataType = DataType.STRING)
    private String corpNo;

    /**
     * 满意度编码
     */
    //@NotEmpty(message = "满意度编码不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="GRADE_NO")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "满意度编码", dataType = DataType.STRING)
    private String gradeNo;

    /**
     * 订单编码
     */
    //@NotEmpty(message = "订单编码不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="ORDER_NO")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "订单编码", dataType = DataType.STRING)
    private String orderNo;

    /**
     * 订单类型：01 安装订单 02 配送订单
     */
    //@NotEmpty(message = "订单类型：01 安装订单 02 配送订单不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="ORDER_TYPE")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "订单类型：01 安装订单 02 配送订单", dataType = DataType.STRING)
    private String orderType;

    /**
     * 订单类型 01 安装订单 02 配送订单
     */
    //@NotEmpty(message = "订单类型 01 安装订单 02 配送订单不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="ORDER_TYPE_NAME")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @Dict(sourceType = SysConstant.orderGrade.CONFIG_ORDER_TYPE, sourceField = "orderType")
    @ApiModelProperty(value = "订单类型 01 安装订单 02 配送订单", dataType = DataType.STRING)
    private String orderTypeName;

    /**
     * 安装工人是否按时到达 Y 是 N 否
     */
    //@NotEmpty(message = "安装工人是否按时到达 Y 是 N 否不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="ONTIME_FLG")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "安装工人是否按时到达 Y 是 N 否", dataType = DataType.STRING)
    private String ontimeFlg;

    /**
     * 是否乱收费 Y 是 N 否
     */
    //@NotEmpty(message = "是否乱收费 Y 是 N 否不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="ILLEGAL_CHARGE_FLG")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "是否乱收费 Y 是 N 否", dataType = DataType.STRING)
    private String illegalChargeFlg;

    /**
     * 是否乱收费 Y 是 N 否
     */
    //@NotEmpty(message = "是否乱收费 Y 是 N 否不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="TRAIN_FLG")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "是否乱收费 Y 是 N 否", dataType = DataType.STRING)
    private String trainFlg;

    /**
     * 安装服务商评分 1非常不满意 2不满意 3一般 4满意 5非常满意
     */
    //@NotNull(message = "安装服务商评分 1非常不满意 2不满意 3一般 4满意 5非常满意不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="SUPPLIER_GRADE")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "安装服务商评分 1非常不满意 2不满意 3一般 4满意 5非常满意", dataType = DataType.STRING)
    private Integer supplierGrade;

    /**
     * 经销商评分 1非常不满意 2不满意 3一般 4满意 5非常满意
     */
    // @NotNull(message = "经销商评分 1非常不满意 2不满意 3一般 4满意 5非常满意不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="DEALER_GRADE")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "经销商评分 1非常不满意 2不满意 3一般 4满意 5非常满意", dataType = DataType.STRING)
    private Integer dealerGrade;

    /**
     * 意见和建议
     */
    //@NotEmpty(message = "意见和建议不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="REMARK")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "意见和建议", dataType = DataType.STRING)
    private String remark;


}

