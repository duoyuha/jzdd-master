package cn.backend.model.primary.workfollowdetail;

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
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Cache;

/**
 *
 * @author sunkw
 * @date 2019/07/16
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "m_work_follow_detail")
@ApiModel(value = "m_work_follow_detail", description = "工作流详细")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "cn.backend.model.primary.workfollowdetail")
public class WorkFollowDetailEntity extends BaseEntity{

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
     * 流程明细编号
     */
    @NotEmpty(message = "流程明细编号不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="DETAIL_NO")
    @ApiModelProperty(value = "流程明细编号", dataType = DataType.STRING)
    private String detailNo;

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
     * 审批步骤
     */
    @NotNull(message = "审批步骤不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="FOLLOW_SEQ")
    @ApiModelProperty(value = "审批步骤", dataType = DataType.STRING)
    private Integer followSeq;

    /**
     * 流程名称
     */
    @NotEmpty(message = "流程名称不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="FOLLOW_NAME")
    @ApiModelProperty(value = "流程名称", dataType = DataType.STRING)
    private String followName;

    /**
     * 审批职位：01 充电经理  02 整车事业部经理  03 安装服务商负责人 04 勘察人员 05 安装人员
     */
    @NotEmpty(message = "审批职位：01 充电经理  02 整车事业部经理  03 安装服务商负责人 04 勘察人员 05 安装人员不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="POSITION_CODES")
    @ApiModelProperty(value = "审批职位：01 充电经理  02 整车事业部经理  03 安装服务商负责人 04 勘察人员 05 安装人员", dataType = DataType.STRING)
    private String positionCodes;

    /**
     * 审批职位：01 充电经理  02 整车事业部经理  03 安装服务商负责人 04 勘察人员 05 安装人员
     */
    @NotEmpty(message = "审批职位：01 充电经理  02 整车事业部经理  03 安装服务商负责人 04 勘察人员 05 安装人员不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="POSITION_NAME")
    @ApiModelProperty(value = "审批职位：01 充电经理  02 整车事业部经理  03 安装服务商负责人 04 勘察人员 05 安装人员", dataType = DataType.STRING)
    private String positionName;

    /**
     * 审批名称
     */
    @NotEmpty(message = "审批名称不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="STEP_NAME")
    @ApiModelProperty(value = "审批名称", dataType = DataType.STRING)
    private String stepName;

    /**
     * 回退步骤
     */
    @NotNull(message = "回退步骤不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="ROLLBACK_SEQ")
    @ApiModelProperty(value = "回退步骤", dataType = DataType.STRING)
    private Integer rollbackSeq;

    /**
     * 上级步骤
     */
    @NotNull(message = "上级步骤不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="PARENT_SEQ")
    @ApiModelProperty(value = "上级步骤", dataType = DataType.STRING)
    private Integer parentSeq;

    /**
     * 是否回退:Y 是 N 否
     */
    @NotEmpty(message = "是否回退:Y 是 N 否不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="ROLLBACK_FLG")
    @ApiModelProperty(value = "是否回退:Y 是 N 否", dataType = DataType.STRING)
    private String rollbackFlg;

    //
    // @Column(name="DISPATCH_KANCHA_FLG")
    // @ApiModelProperty(value = "是否为派单勘察:Y 是 N 否", dataType = DataType.STRING)
    // private String dispatchKanChaFlg;
    //
    // @Column(name="KANCHA_FLG")
    // @ApiModelProperty(value = "是否为勘察:Y 是 N 否", dataType = DataType.STRING)
    // private String kanChaFlg;
    //
    // @Column(name="DISPATCH_INSTALL_FLG")
    // @ApiModelProperty(value = "是否为分配安装:Y 是 N 否", dataType = DataType.STRING)
    // private String dispatchInstallFlg;
    //
    // @Column(name="SUP_CONFIRM_FLG")
    // @ApiModelProperty(value = "是否为服务商确认:Y 是 N 否", dataType = DataType.STRING)
    // private String supConfirmFlg;
    //
    // @Column(name="CAR_VERIFY_FLG")
    // @ApiModelProperty(value = "是否为审核:Y 是 N 否", dataType = DataType.STRING)
    // private String carVerifyFlg;


    /**
     * 是否允许作废:Y 是 N 否
     */
    @NotEmpty(message = "是否允许作废:Y 是 N 否不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="CANCLE_FLG")
    @ApiModelProperty(value = "是否允许作废:Y 是 N 否", dataType = DataType.STRING)
    private String cancleFlg;

    /**
     * 是否审核:Y 是 N 否
     */
    @NotEmpty(message = "是否审核:Y 是 N 否不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="CHECK_FLG")
    @ApiModelProperty(value = "是否允许作废:Y 是 N 否", dataType = DataType.STRING)
    private String checkFlg;

    /**
     * 是否重新生成:Y 是 N 否
     */
    @NotEmpty(message = "是否重新生成:Y 是 N 否不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="CHECK_REBUILD_FLG")
    @ApiModelProperty(value = "是否重新生成:Y 是 N 否", dataType = DataType.STRING)
    private String checkRebuildFlg;

    /**
     * 是否检查超时:Y 是 N 否
     */
    @NotEmpty(message = "是否检查超时:Y 是 N 否不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="TIMEOUT_FLG")
    @ApiModelProperty(value = "是否检查超时:Y 是 N 否", dataType = DataType.STRING)
    private String timeOutFlg;

    /**
     * 是否服务商确认:Y 是 N 否
     */
    @NotEmpty(message = "是否服务商确认不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="CONFIRM_STEP")
    @ApiModelProperty(value = "是否服务商确认:Y 是 N 否", dataType = DataType.STRING)
    private String confirmStep;

    /**
     * 步骤矩阵
     */
    @NotEmpty(message = "步骤矩阵",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="STEP_MATRIX")
    @ApiModelProperty(value = "步骤矩阵", dataType = DataType.STRING)
    private String stepMatrix;


    /**
     * 超时时间，单位：小时
     */
    @NotNull(message = "超时时间，单位：小时不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="TIMEOUT_RADIO")
    @ApiModelProperty(value = "超时时间，单位：小时", dataType = DataType.STRING)
    private Integer timeOutRadio;

    // /**
    //  * 是否安装步骤:Y 是 N 否
    //  */
    // @NotEmpty(message = "是否安装步骤:Y 是 N 否",groups = {CreateGroup.class, UpdateGroup.class})
    // @Column(name="INSTALL_FLG")
    // @ApiModelProperty(value = "是否安装步骤:Y 是 N 否", dataType = DataType.STRING)
    // private String installFlg;

    /**
     * 是否签收步骤:Y 是 N 否
     */
    // @NotEmpty(message = "是否签收步骤:Y 是 N 否",groups = {CreateGroup.class, UpdateGroup.class})
    // @Column(name="RECEIVE_FLG")
    // @ApiModelProperty(value = "是否签收步骤:Y 是 N 否", dataType = DataType.STRING)
    // private String receiveFlg;

    /**
     * 超时类型
     */
     @Column(name="TIMEOUT_TYPE")
     @ApiModelProperty(value = "超时类型 01 安装签收超时 02 安装联系客户超时 03 安装勘察超时 04 安装超时", dataType = DataType.STRING)
     private String timeoutType;

    /**
     * 是否最后一步:Y 最后一步 N 不是最后一步
     */
    @NotEmpty(message = "是否最后一步:Y 最后一步 N 不是最后一步不能为空",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="LAST_STEP")
    @ApiModelProperty(value = "是否最后一步:Y 最后一步 N 不是最后一步", dataType = DataType.STRING)
    private String lastStep;


    // @Column(name="DISPATCH_FLG")
    // @ApiModelProperty(value = "是否为派单:Y 是 N 否", dataType = DataType.STRING)
    // private String dispatchFlg;

}

