package cn.backend.model.primary.dashbord;

import cn.backend.model.BaseQuery;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author sunkw
 * @date 2019/07/17
 */
@Getter
@Setter
public class DashbordQuery extends BaseQuery {

    private String positionCode;

    private String supplierNo;

    private Date startDate;

    private Date endDate;

    private String corpNo;

}
