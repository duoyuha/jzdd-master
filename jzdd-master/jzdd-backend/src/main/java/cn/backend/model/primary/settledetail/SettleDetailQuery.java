package cn.backend.model.primary.settledetail;

import cn.backend.model.BaseQuery;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author James
 * @date 2019/07/29
 */
@Getter
@Setter
@ToString
public class SettleDetailQuery extends BaseQuery {

    private String settleNo;

    private String corpNo;

    private String orderNo;

}
