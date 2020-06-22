package cn.backend.model.primary.orderfee;

import cn.backend.model.BaseQuery;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 *
 * @author sunkw
 * @date 2019/08/06
 */
@Getter
@Setter
public class OrderFeeQuery extends BaseQuery{

    /**
     * 订单编号
     */
    private String orderNo;
    /**
     * 01 安装 02 配送
     */
    private String orderType;

    /**
     * 公司编号
     */
    private String corpNo;

    /**
     * 服务商编码
     */
    private String supplierNo;

    private  String pileModel;
    /**
     * 提报时间
     */

    private Date orderTime;
    /**
     * 服务商编码
     */
    private String supplierName;




    private  String orderNos;

}
