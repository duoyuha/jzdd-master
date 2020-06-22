package cn.backend.model.primary.contractfee;

import cn.backend.config.constant.SysConstant;
import cn.backend.config.swagger.DataType;
import cn.backend.model.BaseEntity;
import cn.zdwl.common.converters.dictconvert.Dict;
import cn.zdwl.common.validgroup.CreateGroup;
import cn.zdwl.common.validgroup.DeleteGroup;
import cn.zdwl.common.validgroup.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 *
 * @author sunkw
 * @date 2019/07/09
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "m_contract_detail_fee")
@ApiModel(value = "m_contract_detail_fee", description = "m_contract_detail_fee")
// @Cacheable
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "cn.backend.model.primary.contractfee")
public class ContractfeeEntity extends BaseEntity{

    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "cn.zdwl.common.util.IdGenerator")
    @NotEmpty(message = "主键ID不能为空",groups = {DeleteGroup.class,  UpdateGroup.class})
    @Column(name = "FEE_ID")
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
     * 合同编码
     */
    //@NotEmpty(message = "合同编码不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="DETAIL_NO")
    @ApiModelProperty(value = "合同编码", dataType = DataType.STRING)
    private String detailNo;

    /**
     * 费用代码
     */
    //@NotEmpty(message = "费用代码不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="FEE_CODE")
    @ApiModelProperty(value = "费用代码", dataType = DataType.STRING)
    private String feeCode;

    /**
     * 费用代码 01 安装 02 配送
     */
    //@NotEmpty(message = "费用代码不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="FEE_TYPE")
    @ApiModelProperty(value = "费用类型", dataType = DataType.STRING)
    private String feeType;

    /**
     * 费用代码 01 安装 02 配送
     */
    //@NotEmpty(message = "费用代码不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="FEE_TYPE_NAME")
    @Dict(sourceType = SysConstant.Contract.FEE_TYPE, sourceField = "feeType")
    @ApiModelProperty(value = "费用类型名称", dataType = DataType.STRING)
    private String feeTypeName;

    /**
     * 费用名称
     */
    //@NotEmpty(message = "费用名称不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="FEE_NAME")
    @Dict(sourceType = SysConstant.Contract.FEE_NAME, sourceField = "feeCode")
    @ApiModelProperty(value = "费用名称", dataType = DataType.STRING)
    private String feeName;

    /**
     * 费用金额
     */
    //@NotEmpty(message = "费用金额不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="FEE_AMT")
    @ApiModelProperty(value = "费用金额", dataType = DataType.STRING)
    private BigDecimal feeAmt;


}

