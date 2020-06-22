package cn.backend.model.primary.settledetail;

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
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 *
 * @author James
 * @date 2019/07/29
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "t_settle_detail")
@ApiModel(value = "t_settle_detail", description = "t_settle_detail")
public class SettleDetailEntity extends BaseEntity {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "cn.zdwl.common.util.IdGenerator")
    @NotEmpty(message = "{message.CustomMessage.detailIdNotEmpty.body}",groups = {DeleteGroup.class,  UpdateGroup.class})
    @Column(name = "DETAIL_ID")
    @ApiModelProperty(value = "主键ID", dataType = DataType.STRING)
    private String id;

    /**
     * 公司编号
     */
    @NotEmpty(message = "{message.CustomMessage.SettleDetailcorpNoNotEmpty.body}",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="CORP_NO")
    @ApiModelProperty(value = "公司编号", dataType = DataType.STRING)
    private String corpNo;

    /**
     * 结算订单编码
     */
    @NotEmpty(message = "{message.CustomMessage.SettleDetailsettleNoNotEmpty.body}",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="SETTLE_NO")
    @ApiModelProperty(value = "结算订单编码", dataType = DataType.STRING)
    private String settleNo;

    /**
     * 安装订单编码
     */
    @NotEmpty(message = "{message.CustomMessage.SettleDetailorderNoNotEmpty.body}",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="ORDER_NO")
    @ApiModelProperty(value = "安装订单编码", dataType = DataType.STRING)
    private String orderNo;

    /**
     * 结算金额
     */
    @NotNull(message = "{message.CustomMessage.SettleDetailsettleAmtNotEmpty.body}",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="SETTLE_AMT")
    @ApiModelProperty(value = "结算金额", dataType = DataType.STRING)
    private BigDecimal settleAmt;

    /**
     * 服务商编码
     */
    @NotEmpty(message = "{message.CustomMessage.SettleDetailsupplierNoNotEmpty.body}",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="SUPPLIER_NO")
    @ApiModelProperty(value = "服务商编码", dataType = DataType.STRING)
    private String supplierNo;

    /**
     * 服务商名称
     */
    @NotEmpty(message = "{message.CustomMessage.SettleDetailsupplierNameNotEmpty.body}",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="SUPPLIER_NAME")
    @ApiModelProperty(value = "服务商名称", dataType = DataType.STRING)
    private String supplierName;

}

