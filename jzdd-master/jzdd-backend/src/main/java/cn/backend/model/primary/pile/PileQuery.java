package cn.backend.model.primary.pile;

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
public class PileQuery extends BaseQuery{

    /**
     * 公司
     */
    private String corpNo;

    /**
     * 型号名称
     */
    private String pileName;

    private  String pileNos;

    private String supplierNo;

}
