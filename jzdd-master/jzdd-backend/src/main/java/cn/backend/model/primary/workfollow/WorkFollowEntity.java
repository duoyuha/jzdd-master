package cn.backend.model.primary.workfollow;

import cn.backend.config.swagger.DataType;
import cn.backend.model.BaseEntity;
import cn.backend.model.primary.workfollowdetail.WorkFollowDetailEntity;
import cn.zdwl.common.validgroup.CreateGroup;
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
import java.util.List;

/**
 *
 * @author sunkw
 * @date 2019/07/16
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "m_work_follow")
@ApiModel(value = "m_work_follow", description = "工作流")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "cn.backend.model.primary.workfollow")
public class WorkFollowEntity extends BaseEntity{

    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "cn.zdwl.common.util.IdGenerator")
    @NotEmpty(message = "主键ID不能为空",groups = {DeleteGroup.class,  UpdateGroup.class})
    @Column(name = "FOLLOW_ID")
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
     * 流程编号
     */
    @NotEmpty(message = "流程编号不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="FOLLOW_NO")
    @ApiModelProperty(value = "流程编号", dataType = DataType.STRING)
    private String followNo;

    /**
     * 流程编码：01 安装订单 02 配送订单 03 投诉 04 报修 05 作废
     */
    @NotEmpty(message = "流程编码：01 安装订单 02 配送订单 03 投诉 04 报修 05 作废不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="FOLLOW_CODE")
    @ApiModelProperty(value = "流程编码：01 安装订单 02 配送订单 03 投诉 04 报修 05 作废", dataType = DataType.STRING)
    private String followCode;

    /**
     * 流程名称 01 安装订单 02 配送订单 03 投诉 04 报修 05 作废
     */
    @NotEmpty(message = "流程名称 01 安装订单 02 配送订单 03 投诉 04 报修 05 作废不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="FOLLOW_NAME")
    @ApiModelProperty(value = "流程名称 01 安装订单 02 配送订单 03 投诉 04 报修 05 作废", dataType = DataType.STRING)
    private String followName;

    @Transient
    private List<WorkFollowDetailEntity> workFollowDetailEntityList;


}

