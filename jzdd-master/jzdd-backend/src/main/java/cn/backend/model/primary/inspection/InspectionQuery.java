package cn.backend.model.primary.inspection;

import cn.backend.model.BaseQuery;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author sunkw
 * @date 2019/07/29
 */
@Getter
@Setter
public class InspectionQuery extends BaseQuery{
    /**
     * 是否完成
     */
    private String finishFlg;

    /**
     * 订单编号
     */
    private String orderNo;
    /**
     * 巡检状态
     */
    private String inspectionStatus;


}
