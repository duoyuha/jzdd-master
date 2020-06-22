package cn.backend.model.primary.report;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

/**
 * @author sunkw
 * @date 2019/07/08
 */
@Getter
@Setter
public class
OrderReportQuery {



    /**
     * 车型
     */

    private String carVehicle;


    /**
     * 订单类型
     */
    @NotEmpty(message = "订单类型不能为空")
    private String orderType;


    /**
     * 安装服务商
     */
    private String supplierNo;

    /**
     * 结束时间
     */
    @NotEmpty(message = "开始时间不能为空")
    private Date orderTimeStart;

    /**
     * 开始时间
     */
    @NotEmpty(message = "结束时间不能为空")
    private Date orderTimeEnd;


    /**
     * 省份
     */

    private String installProvince;


    /**
     * 城市
     */

    private String installCity;

    /**
     * 服务商筛选情况判断
     */
    private String type;







}
