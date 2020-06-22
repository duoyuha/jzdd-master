package cn.backend.model.primary.vehicle;

import cn.backend.config.swagger.DataType;
import cn.backend.model.BaseEntity;
import cn.backend.model.primary.vehicledetail.VehicleDetailEntity;
import cn.zdwl.common.validgroup.CreateGroup;
import cn.zdwl.common.validgroup.DeleteGroup;
import cn.zdwl.common.validgroup.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cache;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.List;

/**
 *
 * @author sunkw
 * @date 2019/07/08
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "m_vehicle")
@ApiModel(value = "m_vehicle", description = "车型")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "cn.backend.model.primary.vehicle")
public class VehicleEntity extends BaseEntity{

    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "cn.zdwl.common.util.IdGenerator")
    @NotEmpty(message = "主键ID不能为空",groups = {DeleteGroup.class,  UpdateGroup.class})
    @Column(name = "VEHICLE_ID")
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
     * 车型编码
     */
    @NotEmpty(message = "车型编码不能为空",groups = {UpdateGroup.class})
    @Column(name="VEHICLE_NO")
    @ApiModelProperty(value = "车型编码", dataType = DataType.STRING)
    private String vehicleNo;

    /**
     * 车型名称
     */
    @NotEmpty(message = "车型不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="VEHICLE_NAME")
    @ApiModelProperty(value = "车型名称", dataType = DataType.STRING)
    private String vehicleName;



    /**
     * 备注
     */
    // @NotEmpty(message = "备注不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="REMARK")
    @ApiModelProperty(value = "备注", dataType = DataType.STRING)
    private String remark;

    /**
     * 车型明细
     */
    @Transient
    private List<VehicleDetailEntity> vehicleDetailEntityList;


}

