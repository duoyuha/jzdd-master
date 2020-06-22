package cn.backend.model.primary.supplier;

import cn.backend.model.BaseQuery;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 *
 * @author sunkw
 * @date 2019/07/08
 */
@Getter
@Setter
public class SupplierQuery extends BaseQuery {

    /**
     * 公司
     */
    private String corpNo;

    /**
     * 服务商名称
     */
    private String supplierName;

    private String supplierNo;

    private String orderType;

    private String orderNo;

    /**
     * 自动派单
     */
    private Map<String,Double> automaticMap;

    /**
     * 自动派单开关 Y开 N关
     */
    private String automaticCommand;

    private String supplierNos;

}
