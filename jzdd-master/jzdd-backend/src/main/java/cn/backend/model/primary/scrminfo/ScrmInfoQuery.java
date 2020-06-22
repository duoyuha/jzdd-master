package cn.backend.model.primary.scrminfo;

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
public class ScrmInfoQuery extends BaseQuery{

    private String token;

    private String vin;

    private Integer brand;

    private Integer state;

}
