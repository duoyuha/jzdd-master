package cn.backend.model.primary.contract;

import cn.backend.config.swagger.DataType;
import cn.backend.model.BaseEntity;
import cn.backend.model.primary.contractdetail.ContractDetailEntity;
import cn.zdwl.common.file.UploadFile;
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
import java.util.Date;
import java.util.List;

/**
 *
 * @author sunkw
 * @date 2019/07/09
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "m_contract")
@ApiModel(value = "m_contract", description = "m_contract")
// @Cacheable
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "cn.backend.model.primary.contract")
public class ContractEntity extends BaseEntity{

    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "cn.zdwl.common.util.IdGenerator")
    @NotEmpty(message = "主键ID不能为空",groups = {DeleteGroup.class,  UpdateGroup.class})
    @Column(name = "CONTRACT_ID")
    @ApiModelProperty(value = "主键ID", dataType = DataType.STRING)
    private String id;

    /**
     * 公司编号
     */
   // @NotEmpty(message = "公司编号不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="CORP_NO")
    @ApiModelProperty(value = "公司编号", dataType = DataType.STRING)
    private String corpNo;

    /**
     * 安装服务商编码
     */
    @NotEmpty(message = "安装服务商编码不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="SUPPLIER_NO")
    @ApiModelProperty(value = "安装服务商编码", dataType = DataType.STRING)
    private String supplierNo;

    /**
     * 合同编码
     */
  //  @NotEmpty(message = "合同编码不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="CONTRACT_NO")
    @ApiModelProperty(value = "合同编码", dataType = DataType.STRING)
    private String contractNo;

    /**
     * 合同号
     */
  //  @NotEmpty(message = "合同号不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="CONTRACT_CODE")
    @ApiModelProperty(value = "合同号", dataType = DataType.STRING)
    private String contractCode;

    /**
     * 合同开始日期
     */
  //  @NotNull(message = "合同开始日期不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="BEGIN_DATE")
    @ApiModelProperty(value = "合同开始日期", dataType = DataType.STRING)
    private Date beginDate;

    /**
     * 合同结束日期
     */
  //  @NotNull(message = "合同结束日期不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="END_DATE")
    @ApiModelProperty(value = "合同结束日期", dataType = DataType.STRING)
    private Date endDate;

    /**
     * 合同签订日期
     */
   // @NotNull(message = "合同签订日期不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="SIGN_DATE")
    @ApiModelProperty(value = "合同签订日期", dataType = DataType.STRING)
    private Date signDate;

    /**
     * 是否含税
     */
    @Column(name="TAX_FLG")
    @ApiModelProperty(value = "是否含税", dataType = DataType.STRING)
    private String taxFlg = "N";

    /**
     * 合同附件
     */
   // @NotEmpty(message = "合同附件不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="CONTRACT_FILE")
    @ApiModelProperty(value = "合同附件", dataType = DataType.STRING)
    private String contractFile;
    /**
     * 合同附件名称
     */
    @Column(name="CONTRACT_FILE_NAME")
    @ApiModelProperty(value = "合同附件名称", dataType = DataType.STRING)
    private String contractFileName;

    /**
     * 备注
     */
  //  @NotEmpty(message = "备注不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="REMARK")
    @ApiModelProperty(value = "备注", dataType = DataType.STRING)
    private String remark;

    @Transient
    private UploadFile contract;

    @Transient
    private String supplierName;

    /**
     * 计价
     */
    @Transient
    private List<ContractDetailEntity> detailEntityList;



}

