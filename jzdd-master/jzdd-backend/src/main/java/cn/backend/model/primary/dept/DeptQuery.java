package cn.backend.model.primary.dept;

import cn.backend.model.BaseQuery;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author xsj
 * @date 2019/03/14
 */
@Getter
@Setter
public class DeptQuery extends BaseQuery{
    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 公司编码
     */
    private String corpNo;

    /**
     * 部门类型 01 抢修 02 生产
     */
    private String deptTypeNos;

}
