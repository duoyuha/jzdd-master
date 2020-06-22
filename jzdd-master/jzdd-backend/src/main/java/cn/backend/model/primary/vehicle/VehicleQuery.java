package cn.backend.model.primary.vehicle;

import cn.backend.model.BaseQuery;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author sunkw
 * @date 2019/07/08
 */
@Getter
@Setter
public class VehicleQuery extends BaseQuery{

    /**
     * 公司
     */
    private String corpNo;

    /**
     * 车型名称
     */
    private String vehicleName;

}
