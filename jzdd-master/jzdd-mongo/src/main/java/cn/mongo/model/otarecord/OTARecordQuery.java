package cn.mongo.model.otarecord;

import cn.mongo.model.BaseQuery;
import lombok.Getter;
import lombok.Setter;

/**
 * create by dnc at 2019年06月11日
 */
@Getter
@Setter
public class OTARecordQuery extends BaseQuery {

    /**
     * 公司编号
     */
    private String corpNo;

    /**
     * 设备编号
     */
    private String tmnlNo;

    /**
     * 设备标识
     */
    private String tmnlCode;

}
