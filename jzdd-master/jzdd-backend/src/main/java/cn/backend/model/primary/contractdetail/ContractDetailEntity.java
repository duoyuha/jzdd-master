package cn.backend.model.primary.contractdetail;

import cn.backend.config.swagger.DataType;
import cn.backend.model.BaseEntity;
import cn.backend.model.primary.contractarea.ContractareaEntity;
import cn.backend.model.primary.contractfee.ContractfeeEntity;
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
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 *
 * @author sunkw
 * @date 2019/07/09
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "m_contract_detail")
@ApiModel(value = "m_contract_detail", description = "m_contract_detail")
// @Cacheable
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "cn.backend.model.primary.contractdetail")
public class ContractDetailEntity extends BaseEntity{

    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "cn.zdwl.common.util.IdGenerator")
    @NotEmpty(message = "主键ID不能为空",groups = {DeleteGroup.class,  UpdateGroup.class})
    @Column(name = "DETAIL_ID")
    @ApiModelProperty(value = "主键ID", dataType = DataType.STRING)
    private String id;

    /**
     * 公司编号
     */
 //   @NotEmpty(message = "公司编号不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="CORP_NO")
    @ApiModelProperty(value = "公司编号", dataType = DataType.STRING)
    private String corpNo;

    /**
     * 合同编码
     */
  //  @NotEmpty(message = "合同编码不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="CONTRACT_NO")
    @ApiModelProperty(value = "合同编码", dataType = DataType.STRING)
    private String contractNo;

    @Column(name="DETAIL_NO")
    @ApiModelProperty(value = "合同明细编码", dataType = DataType.STRING)
    private String detailNo;

    /**
     * 车型编码编码
     */
    @NotEmpty(message = "车型编码编码不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="VEHICLE_NOS")
    @ApiModelProperty(value = "车型编码编码", dataType = DataType.STRING)
    private String vehicleNos;

    /**
     * 充电桩型号
     */
    @NotEmpty(message = "充电桩型号不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="PILE_MODELS")
    @ApiModelProperty(value = "充电桩型号", dataType = DataType.STRING)
    private String pileModels;

    /**
     * 车型明细编码
     */
    @NotEmpty(message = "车型明细编码不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="DETAIL_NOS")
    @ApiModelProperty(value = "车型明细编码", dataType = DataType.STRING)
    private String detailNos;

    // /**
    //  * 策略开始日期
    //  */
    // @NotNull(message = "策略开始日期不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    // @Column(name="DETAIL_BEGIN_DATE")
    // @ApiModelProperty(value = "策略开始日期", dataType = DataType.STRING)
    // private Date detailBeginDate;

    // /**
    //  * 策略结束日期
    //  */
    // @NotNull(message = "策略结束日期不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    // @Column(name="DETAIL_END_DATE")
    // @ApiModelProperty(value = "策略结束日期", dataType = DataType.STRING)
    // private Date detailEndDate;

    /**
     * 备注
     */
   // @NotEmpty(message = "备注不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="REMARK")
    @ApiModelProperty(value = "备注", dataType = DataType.STRING)
    private String remark;


    /**
     * 计价区域
     */
    @Transient
    private List<ContractareaEntity> contractareaEntityList;

    /**
     * 费用类型
     */
    @Transient
    private List<ContractfeeEntity> contractfeeEntityList;

    /**
     * 区域信息-接收
     */
//    @Transient
//    @NotEmpty(message = "区域信息不能为空",groups = {CreateGroup.class, UpdateGroup.class})
//    private String area;

    /**
     * 充电桩名称
     */
    @Transient
    private String pilenames;

    /**
     * 车型名称
     */
    @Transient
    private String carsNames;

    /**
     * 配置名称
     */
    @Transient
    private String vehicleDetailNames;

}

