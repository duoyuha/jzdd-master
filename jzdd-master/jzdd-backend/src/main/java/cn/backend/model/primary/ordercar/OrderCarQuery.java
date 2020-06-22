package cn.backend.model.primary.ordercar;

import cn.backend.model.BaseQuery;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author sunkw
 * @date 2019/07/17
 */
@Getter
@Setter
public class OrderCarQuery extends BaseQuery{

    /**
     * VIN
     */
    private String vin;

    private String corpNo;

}
