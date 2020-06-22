package cn.backend.model.primary.vehicledetail;

import cn.backend.model.BaseQuery;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author sunkw
 * @date 2019/08/17
 */
@Getter
@Setter
public class VehicleDetailQuery extends BaseQuery{

    private String vehicleNo;

    private  String detailName;

}
