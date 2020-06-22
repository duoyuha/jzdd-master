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
SupplierReportQuery {

    /**
     * 安装服务商
     */
    private String supplierNo;

    /**
     * 开始时间
     */
    private Date queryTimeStart;

    /**
     * 结束时间
     */
    private Date queryTimeEnd;


}
