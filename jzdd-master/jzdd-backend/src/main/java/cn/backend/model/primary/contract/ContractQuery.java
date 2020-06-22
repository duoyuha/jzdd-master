package cn.backend.model.primary.contract;

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
public class ContractQuery extends BaseQuery{

    /**
     * 合同编号
     */
    private String contractCode;

    /**
     * 公司
     */
    private String corpNo;

    private  String supplierNo;

    private  String effectiveFlg;




}
