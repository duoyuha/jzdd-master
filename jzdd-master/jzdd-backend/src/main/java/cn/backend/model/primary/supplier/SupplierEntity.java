package cn.backend.model.primary.supplier;

import cn.backend.config.swagger.DataType;
import cn.backend.model.BaseEntity;
import cn.backend.model.primary.supplierarea.SupplierAreaEntity;
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
import java.util.Map;
import java.util.Set;

/**
 * @author sunkw
 * @date 2019/07/08
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "m_supplier")
@ApiModel(value = "m_supplier", description = "安装服务商")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "cn.backend.model.primary.supplier")
public class SupplierEntity extends BaseEntity {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "cn.zdwl.common.util.IdGenerator")
    @NotEmpty(message = "主键ID不能为空", groups = {DeleteGroup.class, UpdateGroup.class})
    @Column(name = "SUPPLIER_ID")
    @ApiModelProperty(value = "主键ID", dataType = DataType.STRING)
    private String id;

    /**
     * 公司编号
     */
    @NotEmpty(message = "公司编号不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "CORP_NO")
    @ApiModelProperty(value = "公司编号", dataType = DataType.STRING)
    private String corpNo;

    /**
     * 安装服务商编号
     */
    @NotEmpty(message = "公司编号不能为空", groups = {UpdateGroup.class})
    @Column(name = "SUPPLIER_NO")
    @ApiModelProperty(value = "公司编号", dataType = DataType.STRING)
    private String supplierNo;

    /**
     * 安装服务商名称
     */
    @NotEmpty(message = "安装服务商名称不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "SUPPLIER_NAME")
    @ApiModelProperty(value = "安装服务商名称", dataType = DataType.STRING)
    private String supplierName;

    /**
     * 安装服务商简称
     */
    // @NotEmpty(message = "安装服务商简称不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "SUPPLIER_ABBR")
    @ApiModelProperty(value = "安装服务商简称", dataType = DataType.STRING)
    private String supplierAbbr;

    /**
     * 负责人
     */
    @NotEmpty(message = "负责人不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "CHARGE_PERSON")
    @ApiModelProperty(value = "负责人", dataType = DataType.STRING)
    private String chargePerson;

    /**
     * 负责人联系方式
     */
    @NotEmpty(message = "负责人联系方式不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "CHARGE_PERSON_PHONE")
    @ApiModelProperty(value = "负责人联系方式", dataType = DataType.STRING)
    private String chargePersonPhone;

    /**
     * 负责人联系邮箱
     */
    //@NotEmpty(message = "负责人联系邮箱不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "CHARGE_PERSON_MAIL")
    @ApiModelProperty(value = "负责人联系邮箱", dataType = DataType.STRING)
    private String chargePersonMail;

    /**
     * 负责人联系传真
     */
    //@NotEmpty(message = "负责人联系传真不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "CHARGE_PERSON_FAX")
    @ApiModelProperty(value = "负责人联系传真", dataType = DataType.STRING)
    private String chargePersonFax;

    /**
     * 负责人联系传真
     */
    //@NotEmpty(message = "地址不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "CHARGE_PERSON_ADDR")
    @ApiModelProperty(value = "负责人联系地址", dataType = DataType.STRING)
    private String chargePersonAddr;

    /**
     * 三证合一
     */
    //@NotEmpty(message = "三证合一不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "CERTIFICATE_FILE")
    @ApiModelProperty(value = "三证合一", dataType = DataType.STRING)
    private String certificateFile;

    /**
     * 自动派单比例
     */
    @Column(name = "AUTO_ORDER_PROP")
    @ApiModelProperty(value = "备注", dataType = DataType.STRING)
    private Integer autoOrderProp;

    /**
     * 备注
     */
    // @NotEmpty(message = "备注不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "REMARK")
    @ApiModelProperty(value = "备注", dataType = DataType.STRING)
    private String remark;


    // @NotEmpty(message = "备注不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "PILE_NOS")
    @ApiModelProperty(value = "充电桩编号多选", dataType = DataType.STRING)
    private String pileNos;

    /**
     * 电工证图片名
     */
    @Column(name = "ELE_FILENAME")
    @ApiModelProperty(value = "电工证图片名", dataType = DataType.STRING)
    private String eleFileName;
    /**
     * 区域信息
     */
    @Transient
    private Set<SupplierAreaEntity> supplierAreaEntities;

    /**
     * 区域信息-接收
     */
    @Transient
    @NotEmpty(message = "区域信息不能为空", groups = {CreateGroup.class, UpdateGroup.class})
    private String area;

    @Transient
    private int allMonth;

    @Transient
    private Map<String, Integer> numMap;

    @Transient
    private String city;


}

