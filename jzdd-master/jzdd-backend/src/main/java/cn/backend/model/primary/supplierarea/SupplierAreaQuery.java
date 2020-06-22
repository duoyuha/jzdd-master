package cn.backend.model.primary.supplierarea;

import cn.backend.model.BaseQuery;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author sunkw
 * @date 2019/07/08
 */
@Getter
@Setter
public class SupplierAreaQuery extends BaseQuery{


    private String cityName;


    private String provinceName;

    private  String corpNo;

    private String supplierNo;


}
