package cn.backend.model.primary.viewdashboardtodo;

import cn.backend.model.BaseQuery;
import lombok.Getter;
import lombok.Setter;

/**
 * @author sunkw
 * @date 2019/07/23
 */
@Getter
@Setter
public class ViewDashbordTodoQuery extends BaseQuery {

    private String corpNo;

    private String supplierNo;

    private String userType;

    private String finishFlg;

}
