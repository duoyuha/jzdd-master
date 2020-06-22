package cn.backend.model.primary.inspection;

import cn.backend.config.swagger.DataType;
import cn.backend.model.BaseEntity;
import cn.backend.model.primary.installorder.InstallOrderEntity;
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
 * @date 2019/07/29
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "t_order_inspection")
@ApiModel(value = "t_order_inspection", description = "t_order_inspection")
public class InspectionEntity extends BaseEntity{

    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "cn.zdwl.common.util.IdGenerator")
    @NotEmpty(message = "主键ID不能为空",groups = {DeleteGroup.class,  UpdateGroup.class})
    @Column(name = "INSPECTION_ID")
    @ApiModelProperty(value = "主键ID", dataType = DataType.STRING)

    private String id;

    /**
     * 附件编码
     */
  //  @NotEmpty(message = "附件编码不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="INSPECTION_NO")
    @ApiModelProperty(value = "附件编码", dataType = DataType.STRING)

    private String inspectionNo;

    /**
     * 路径
     */
  //  @NotEmpty(message = "路径不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="ATTACH_PATH")
    @ApiModelProperty(value = "路径", dataType = DataType.STRING)

    private String attachPath;

    /**
     * 文件名称
     */
 //   @NotEmpty(message = "文件名称不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="FILE_NAME")
    @ApiModelProperty(value = "文件名称", dataType = DataType.STRING)

    private String fileName;

    /**
     * 订单编号
     */
    //@NotEmpty(message = "订单编号不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="ORDER_NO")
    @ApiModelProperty(value = "订单编号", dataType = DataType.STRING)
    private String orderNo;

    /**
     * 是否完成
     */
    @Column(name="FINISH_FLG")
    @ApiModelProperty(value = "是否完成", dataType = DataType.STRING)
    private String finishFlg;

    /**
     * 服务商编码
     */
    @Column(name = "SUPPLIER_NO")
    @ApiModelProperty(value = "服务商编码", dataType = DataType.STRING)
    private String supplierNo;

    /**
     * 服务商名称
     */
    @Column(name = "SUPPLIER_NAME")
    @ApiModelProperty(value = "服务商名称", dataType = DataType.STRING)
    private String supplierName;

    /**
     * 巡检时间
     */
    //@NotNull(message = "巡检时间不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="INSPECTION_TIME")
    @ApiModelProperty(value = "巡检时间", dataType = DataType.STRING)

    private Date inspectionTime;

    /**
     * 巡检人
     */
 //   @NotEmpty(message = "巡检人不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="INSPECTION_PERSON")
    @ApiModelProperty(value = "巡检人", dataType = DataType.STRING)
    private String inspectionPerson;

    /**
     * 巡检描述
     */
  //  @NotEmpty(message = "巡检描述不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="INSPECTION_DESC")
    @ApiModelProperty(value = "巡检描述", dataType = DataType.STRING)
    private String inspectionDesc;

    @Transient
    private String orderNos;

    @Transient
    private List<String> orderNoList;


    @Transient
    private InstallOrderEntity installOrderEntity;

    /**
     * vin
     */
    @Transient
    private String vin;

    /**
     * 巡检结果
     */
    @Column(name="INSPECTION_RESULT")
    @ApiModelProperty(value = "巡检结果", dataType = DataType.STRING)
    private String inspectionResult;

    /**
     * 巡检状态
     */
    @Column(name="INSPECTION_STATUS")
    @ApiModelProperty(value = "巡检状态", dataType = DataType.STRING)
    private String inspectionStatus;

}

