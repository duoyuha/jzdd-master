package cn.backend.model.primary.config;

import cn.backend.config.swagger.DataType;
import cn.backend.model.BaseEntity;
import cn.zdwl.common.validgroup.DeleteGroup;
import cn.zdwl.common.validgroup.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.annotations.Cache;

import javax.persistence.*;


/**
 *
 * @author xsj
 * @date 2019/03/13
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "s_config")
@ApiModel(value = "s_config", description = "系统配置")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "cn.backend.model.primary.config")
public class ConfigEntity extends BaseEntity{

    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "cn.zdwl.common.util.IdGenerator")
    @NotEmpty(message = "{message.CustomMessage.ConfigconfigIdNotEmpty.body}",groups = {DeleteGroup.class,  UpdateGroup.class})
    @Column(name = "CONFIG_ID")
    @ApiModelProperty(value = "主键ID", dataType = DataType.STRING)
    private String configId;

    /**
     * 代码分类标识
     */
    //@NotEmpty(message = "{message.CustomMessage.ConfigconfigTypeNotEmpty.body}",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="CONFIG_TYPE")
    @ApiModelProperty(value = "代码分类标识", dataType = DataType.STRING)
    private String configType;

    /**
     * 代码名称
     */
    //@NotEmpty(message = "{message.CustomMessage.ConfigconfigTypeNameNotEmpty.body}",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="CONFIG_TYPE_NAME")
    @ApiModelProperty(value = "代码名称", dataType = DataType.STRING)
    private String configTypeName;

    /**
     * 代码所属单位
     */
    //@NotEmpty(message = "{message.CustomMessage.ConfigconfigOrgNotEmpty.body}",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="CONFIG_ORG")
    @ApiModelProperty(value = "代码所属单位", dataType = DataType.STRING)
    private String configOrg;

    /**
     * 代码值
     */
    //@NotEmpty(message = "{message.CustomMessage.ConfigconfigValNotEmpty.body}",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="CONFIG_VAL")
    @ApiModelProperty(value = "代码值", dataType = DataType.STRING)
    private String configVal;

    /**
     * 代码值的名称
     */
    //@NotEmpty(message = "{message.CustomMessage.ConfigconfigNameNotEmpty.body}",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="CONFIG_NAME")
    @ApiModelProperty(value = "代码值的名称", dataType = DataType.STRING)
    private String configName;

    /**
     * 代码显示顺序号
     */
    //@NotNull(message = "{message.CustomMessage.ConfigconfigDispNotEmpty.body}",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="CONFIG_DISP")
    @ApiModelProperty(value = "代码显示顺序号", dataType = DataType.STRING)
    private Double configDisp;

    /**
     * 代码的内容1
     */
    //@NotEmpty(message = "{message.CustomMessage.ConfigconfigCont1NotEmpty.body}",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="CONFIG_CONT1")
    @ApiModelProperty(value = "代码的内容1", dataType = DataType.STRING)
    private String configCont1;

    /**
     * 代码的内容2
     */
    //@NotEmpty(message = "{message.CustomMessage.ConfigconfigCont2NotEmpty.body}",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="CONFIG_CONT2")
    @ApiModelProperty(value = "代码的内容2", dataType = DataType.STRING)
    private String configCont2;

    /**
     * 代码的内容3
     */
    //@NotEmpty(message = "{message.CustomMessage.ConfigconfigCont3NotEmpty.body}",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="CONFIG_CONT3")
    @ApiModelProperty(value = "代码的内容3", dataType = DataType.STRING)
    private String configCont3;

    /**
     * 代码的内容4
     */
    //@NotEmpty(message = "{message.CustomMessage.ConfigconfigCont4NotEmpty.body}",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="CONFIG_CONT4")
    @ApiModelProperty(value = "代码的内容4", dataType = DataType.STRING)
    private String configCont4;

    /**
     * 代码的内容5
     */
    //@NotEmpty(message = "{message.CustomMessage.ConfigconfigCont5NotEmpty.body}",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="CONFIG_CONT5")
    @ApiModelProperty(value = "代码的内容5", dataType = DataType.STRING)
    private String configCont5;


}

