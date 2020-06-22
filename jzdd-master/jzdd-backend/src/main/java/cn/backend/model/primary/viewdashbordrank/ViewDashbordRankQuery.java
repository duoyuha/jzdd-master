package cn.backend.model.primary.viewdashbordrank;

import cn.backend.model.BaseQuery;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author sunkw
 * @date 2019/07/23
 */
@Getter
@Setter
public class ViewDashbordRankQuery extends BaseQuery{


    private  String supplierNo;

    private  String carVehicle;

    private  String finishFlg;

}
