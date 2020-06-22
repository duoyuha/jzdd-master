package cn.backend.model.primary.ordercancel;

import cn.backend.model.BaseQuery;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author sunkw
 * @date 2019/08/26
 */
@Getter
@Setter
public class OrderCancelQuery extends BaseQuery{

    private String orderNo;

}
