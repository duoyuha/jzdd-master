package cn.backend.model.primary.orderseq;

import cn.backend.model.BaseQuery;
import cn.zdwl.common.util.DateUtils;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

/**
 *
 * @author sunkw
 * @date 2019/08/06
 */
@Getter
@Setter
public class OrderSeqQuery extends BaseQuery{

    /**
     * 订单编号
     */
    @NotEmpty(message = "订单类型不能为空")
    private String orderType;

    /**
     * 公司编号
     */
    @NotEmpty(message = "公司不能为空")
    private String corpNo;

    @NotEmpty(message = "日期不能为空")
    private  String occurDate= DateUtils.dateToStr(new Date(), "yyyyMMdd");

}
