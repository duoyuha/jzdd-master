package cn.backend.model.primary.contractdetail;

import cn.backend.model.BaseQuery;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author sunkw
 * @date 2019/07/09
 */
@Getter
@Setter
public class ContractDetailQuery extends BaseQuery{

    /**
     * 车型
     */
    private String vehicleNo;

    /**
     * 充电桩
     */
    private String pileNo;

    /**
     * 公司
     */
    private String corpNo;

}
