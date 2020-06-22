package cn.backend.model.primary.orderauto;

import cn.backend.model.BaseQuery;
import cn.zdwl.common.validgroup.CreateGroup;
import cn.zdwl.common.validgroup.UpdateGroup;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author sunkw
 * @date 2019/08/13
 */
@Getter
@Setter
public class OrderAutoQuery extends BaseQuery{

    @NotEmpty(message = "城市不能为空")
    private String cityName;

    @NotEmpty(message = "省份不能为空")
    private String provinceName;


     private String supplierNos;

    @NotEmpty(message = "订单类型不能为空")
    private String orderType;

    @NotEmpty(message = "公司编码不能为空")
    private String corpNo;

    private  int orderNum;

    private String supplierNo;

    private String orderNo;

    private String orderAutoNo;

}
