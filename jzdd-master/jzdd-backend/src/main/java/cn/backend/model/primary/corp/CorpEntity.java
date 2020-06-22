package cn.backend.model.primary.corp;


import cn.backend.config.constant.SysConstant;
import cn.backend.config.swagger.DataType;
import cn.backend.model.BaseEntity;
import cn.zdwl.common.Logoutput.FiledLog;
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
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * @author xsj
 * @date 2019/03/13
 */
@Getter
@Setter
@ToString
@Entity
@Where(clause = "IS_DEL='N'")
@Table(name = "s_corp")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "cn.backend.model.primary.corp")
@ApiModel(value = "corp", description = "公司对象")
public class CorpEntity extends BaseEntity {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "cn.zdwl.common.util.IdGenerator")
    @NotEmpty(message = "{message.CustomMessage.CorpcorpIdNotEmpty.body}", groups = {DeleteGroup.class, UpdateGroup.class})
    @Column(name = "CORP_ID")
    @ApiModelProperty(value = "主键ID", dataType = DataType.STRING)
    private String id;

    /**
     * 公司编号
     */
    //@NotEmpty(message = "{message.CustomMessage.CorpcorpNoNotEmpty.body}",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "CORP_NO")
    @ApiModelProperty(value = "公司编号", dataType = DataType.STRING)
    @FiledLog
    private String corpNo;


    /**
     * 公司名称
     */
    @NotEmpty(message = "{message.CustomMessage.CorpcorpNameNotEmpty.body}", groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "CORP_NAME")
    @ApiModelProperty(value = "公司名称", dataType = DataType.STRING, required = true)
    private String corpName;


    /**
     * 负责人
     */
    // @NotEmpty(message = "负责人不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "CHARGE_PERSON")
    @ApiModelProperty(value = "负责人", dataType = DataType.STRING)
    private String chargePerson;

    /**
     * 负责人联系方式
     */
    // @NotEmpty(message = "负责人联系方式不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "CHARGE_PERSON_PHONE")
    @ApiModelProperty(value = "负责人联系方式", dataType = DataType.STRING)
    private String chargePersonPhone;

    /**
     * 负责人联系邮箱
     */
    //@NotEmpty(message = "负责人联系邮箱不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "CHARGE_PERSON_MAIL")
    @ApiModelProperty(value = "负责人联系邮箱", dataType = DataType.STRING)
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
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
    //@NotEmpty(message = "负责人联系传真不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "CHARGE_PERSON_ADDR")
    @ApiModelProperty(value = "负责人联系传真", dataType = DataType.STRING)
    private String chargePersonAddr;

    /**
     * 备注
     */
    //@NotEmpty(message = "{message.CustomMessage.CorpremarkNotEmpty.body}",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "REMARK")
    @ApiModelProperty(value = "备注", dataType = DataType.STRING)
    private String remark;


    /**
     * 品牌
     */
    //@NotEmpty(message = "负责人联系传真不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name = "CORP_BRAND")
    @ApiModelProperty(value = "品牌", dataType = DataType.INT)
    private int corpBrand;


}

