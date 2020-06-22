package cn.backend.model.primary.orderattach;

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
/**
 *
 * @author sunkw
 * @date 2019/07/23
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "t_order_attach")
@ApiModel(value = "t_order_attach", description = "t_order_attach")
public class OrderAttachEntity extends BaseEntity{

    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "cn.zdwl.common.util.IdGenerator")
    @NotEmpty(message = "主键ID不能为空",groups = {DeleteGroup.class,  UpdateGroup.class})
    @Column(name = "ATTACH_ID")
    @ApiModelProperty(value = "主键ID", dataType = DataType.STRING)
    private String id;

    /**
     * 附件编码
     */
    @NotEmpty(message = "附件编码不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="ATTACH_NO")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "附件编码", dataType = DataType.STRING)
    private String attachNo;

    /**
     * 01 安装方案 02 安装勘察其他附件 03 人桩合影 04 安装确认书 05 安装其他附件 06 配送附件 07 签收证明
     */
    @NotEmpty(message = "01 安装方案 02 安装勘察其他附件 03 人桩合影 04 安装确认书 05 安装其他附件 06 配送附件 07 签收证明不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="ATTACH_TYPE")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "01 安装方案 02 安装勘察其他附件 03 人桩合影 04 安装确认书 05 安装其他附件 06 配送附件 07 签收证明", dataType = DataType.STRING)
    private String attachType;

    /**
     * 路径
     */
    @NotEmpty(message = "路径不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="ATTACH_PATH")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "路径", dataType = DataType.STRING)
    private String attachPath;

    /**
     * 订单类型：01 安装订单 02 配送订单
     */
    @NotEmpty(message = "订单类型：01 安装订单 02 配送订单不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="ORDER_TYPE")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "订单类型：01 安装订单 02 配送订单", dataType = DataType.STRING)
    private String orderType;

    /**
     * 订单类型：01 安装订单 02 配送订单
     */
    @NotEmpty(message = "订单类型：01 安装订单 02 配送订单不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="ORDER_TYPE_NAME")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @Dict(sourceType = SysConstant.WorkFollow.CONFIG_TYPE_WORKFOLLOW, sourceField = "orderType")
    @ApiModelProperty(value = "订单类型：01 安装订单 02 配送订单", dataType = DataType.STRING)
    private String orderTypeName;

    /**
     * 文件名称
     */
    @NotEmpty(message = "文件名称不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="FILE_NAME")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "文件名称", dataType = DataType.STRING)
    private String fileName;

    /**
     * 附件顺序号
     */
    // @NotEmpty(message = "附件顺序号不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="ATTACH_DISP")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "附件顺序号", dataType = DataType.STRING)
    private Integer attachDisp;



    /**
     * 订单编号
     */
    @NotEmpty(message = "订单编号不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="ORDER_NO")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    @ApiModelProperty(value = "订单编号", dataType = DataType.STRING)
    private String orderNo;


}

