package cn.backend.model.primary.workfollowdetail;

import cn.backend.model.BaseQuery;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author sunkw
 * @date 2019/07/16
 */
@Getter
@Setter
public class WorkFollowDetailQuery extends BaseQuery{

    //流程编号
    private String followNo;

    //审批步骤
    private Integer followSeq;

}
