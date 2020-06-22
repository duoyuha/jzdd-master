package cn.backend.model.primary.vin;

import cn.backend.model.BaseQuery;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 *
 * @author sunkw
 * @date 2019/07/08
 */
@Getter
@Setter
public class VinQuery extends BaseQuery{

    /**
     * 公司
     */
    private String corpNo;

    /**
     * vin编码
     */
    private String vinCode;
    /**
     * 开始时间
     */
    private Date fromTime;
    /**
     * 结束时间
     */
    private Date toTime;

    /**
     * 不送桩原因
     */
    private String remark;

}
