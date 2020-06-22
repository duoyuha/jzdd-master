package cn.backend.model.primary.pile;

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
@Table(name = "m_pile")
@ApiModel(value = "m_pile", description = "充电桩型号")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "cn.backend.model.primary.pile")
public class PileEntity extends BaseEntity{

    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "cn.zdwl.common.util.IdGenerator")
    @NotEmpty(message = "主键ID不能为空",groups = {DeleteGroup.class,  UpdateGroup.class})
    @Column(name = "PILE_ID")
    @ApiModelProperty(value = "主键ID", dataType = DataType.STRING)
    private String id;

    /**
     * 公司编号
     */
    @NotEmpty(message = "公司编号不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="CORP_NO")
    @ApiModelProperty(value = "公司编号", dataType = DataType.STRING)
    private String corpNo;

    /**
     * 桩编码
     */
    @NotEmpty(message = "桩编码不能为空",groups = {UpdateGroup.class})
    @Column(name="PILE_NO")
    @ApiModelProperty(value = "桩编码", dataType = DataType.STRING)
    private String pileNo;

    /**
     * 桩名称
     */
    @NotEmpty(message = "桩名称不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="PILE_NAME")
    @ApiModelProperty(value = "桩名称", dataType = DataType.STRING)
    private String pileName;

    /**
     * 正面照片
     */
    // @NotEmpty(message = "正面照片不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="PILE_PIC_ONE")
    @ApiModelProperty(value = "正面照片", dataType = DataType.STRING)
    private String pilePicOne;

    /**
     * 背面照片
     */
    // @NotEmpty(message = "背面照片不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="PILE_PIC_SECOND")
    @ApiModelProperty(value = "背面照片", dataType = DataType.STRING)
    private String pilePicSecond;

    /**
     * 侧面照片
     */
    // @NotEmpty(message = "侧面照片不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="PILE_PIC_THIRD")
    @ApiModelProperty(value = "侧面照片", dataType = DataType.STRING)
    private String pilePicThird;

    /**
     * 说明书
     */
    // @NotEmpty(message = "说明书不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="PILE_USE")
    @ApiModelProperty(value = "说明书", dataType = DataType.STRING)
    private String pileUse;

    /**
     * 备注
     */
    // @NotEmpty(message = "备注不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="REMARK")
    @ApiModelProperty(value = "备注", dataType = DataType.STRING)
    private String remark;

    /**
     * 服务商编码
     */
    // @NotEmpty(message = "{message.CustomMessage.SettlesupplierNoNotEmpty.body}",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="SUPPLIER_NO")
    @ApiModelProperty(value = "服务商编码", dataType = DataType.STRING)
    private String supplierNo;

    /**
     * 服务商名称
     */
    // @NotEmpty(message = "{message.CustomMessage.SettlesupplierNameNotEmpty.body}",groups = {UpdateGroup.class})
    @Column(name="SUPPLIER_NAME")
    @ApiModelProperty(value = "服务商名称", dataType = DataType.STRING)
    private String supplierName;


}

