package cn.backend.model.primary.dept;

import cn.backend.config.constant.SysConstant;
import cn.backend.model.BaseEntity;
import cn.zdwl.common.Logoutput.FiledLog;
import cn.zdwl.common.validgroup.CreateGroup;
import cn.zdwl.common.validgroup.DeleteGroup;
import cn.zdwl.common.validgroup.UpdateGroup;
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
 * @author xsj
 * @date 2019/03/14
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "s_dept")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "cn.backend.model.primary.dept")
public class DeptEntity extends BaseEntity{

    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "cn.zdwl.common.util.IdGenerator")
    @NotEmpty(message = "{message.CustomMessage.DeptdeptIdNotEmpty.body}",groups = {DeleteGroup.class,  UpdateGroup.class})
    @Column(name = "DEPT_ID")
    private String id;

    /**
     * 部门编号
     */
    //@NotEmpty(message = "{message.CustomMessage.DeptdeptNoNotEmpty.body}",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="DEPT_NO")
    private String deptNo;

    /**
     * 部门名称
     */
    @NotEmpty(message = "{message.CustomMessage.DeptdeptNameNotEmpty.body}",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="DEPT_NAME")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    private String deptName;

    /**
     * 公司编号
     */
    //@NotEmpty(message = "{message.CustomMessage.DeptcorpNoNotEmpty.body}",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="CORP_NO")
    private String corpNo;

    /**
     * 部门负责人
     */
    //@NotEmpty(message = "{message.CustomMessage.DeptdeptChargerNoNotEmpty.body}",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="DEPT_CHARGER_NO")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    private String deptChargerNo;

    /**
     * 部门负责人名称
     */
    //@NotEmpty(message = "{message.CustomMessage.DeptdeptChargerNameNotEmpty.body}",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="DEPT_CHARGER_NAME")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    private String deptChargerName;

    /**
     * 部门负责人电话
     */
    //@NotEmpty(message = "{message.CustomMessage.DeptdeptChargerPhoneNotEmpty.body}",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="DEPT_CHARGER_PHONE")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    private String deptChargerPhone;

    /**
     * 部门负责人邮箱
     */
    //@NotEmpty(message = "{message.CustomMessage.DeptdeptChargerMailNotEmpty.body}",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="DEPT_CHARGER_MAIL")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    private String deptChargerMail;

    /**
     * 部门类型
     */
    @Column(name="DEPT_TYPE_NOS")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    private String deptTypeNos;

    /**
     * 部门类型名称
     */
    @Column(name="DEPT_TYPE_NAMES")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    private String deptTypeNames;

    /**
     * 部门人数
     */
    //@NotNull(message = "{message.CustomMessage.DeptdeptStaffNumNotEmpty.body}",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="DEPT_STAFF_NUM")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    private Integer deptStaffNum;

    

    /**
     * 备注
     */
    //@NotEmpty(message = "{message.CustomMessage.DeptremarkNotEmpty.body}",groups = {CreateGroup.class, UpdateGroup.class})
    @Column(name="REMARK")
    @FiledLog(SysConstant.Common.OUTPUT_LOG)
    private String remark;


}

