package cn.backend.model.primary.vin;

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
/**
 *
 * @author sunkw
 * @date 2019/07/08
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "m_vin")
@ApiModel(value = "m_vin", description = "VIN")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "cn.backend.model.primary.vin")
public class VinEntity extends BaseEntity{

    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "cn.zdwl.common.util.IdGenerator")
    @NotEmpty(message = "主键ID不能为空",groups = {DeleteGroup.class,  UpdateGroup.class})
    @Column(name = "VIN_ID")
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
     * 车架编码
     */
    @NotEmpty(message = "车架编码不能为空",groups = {UpdateGroup.class})
    @Column(name="VIN_NO")
    @ApiModelProperty(value = "车架编码", dataType = DataType.STRING)
    private String vinNo;

    /**
     * 车架号
     */
    @NotEmpty(message = "车架号不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="VIN_CODE")
    @ApiModelProperty(value = "车架号", dataType = DataType.STRING)
    private String vinCode;

    /**
     * 是否提供桩 01 提供 02 不提供
     */
    // @NotEmpty(message = "是否提供桩 01 提供 02 不提供不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="GIVE_PILE")
    @ApiModelProperty(value = "是否提供桩 01 提供 02 不提供", dataType = DataType.STRING)
    private String givePile;

    /**
     * 是否提供桩 01 提供 02 不提供
     */
    // @NotEmpty(message = "是否提供桩 01 提供 02 不提供不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="GIVE_PILE_NMAE")
    @Dict(sourceType = SysConstant.Vin.CONFIG_GIVEPILE, sourceField = "givePile")
    @ApiModelProperty(value = "是否提供桩 01 提供 02 不提供", dataType = DataType.STRING)
    private String givePileName;

    /**
     * 备注
     */
    // @NotEmpty(message = "备注不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="REMARK")
    @ApiModelProperty(value = "备注", dataType = DataType.STRING)
    private String remark;


}

