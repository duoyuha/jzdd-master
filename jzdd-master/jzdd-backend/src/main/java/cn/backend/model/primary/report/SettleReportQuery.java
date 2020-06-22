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
public class SettleReportQuery {



    /**
     * 车型
     */

    private String carVehicle;





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


    private String type;









}
