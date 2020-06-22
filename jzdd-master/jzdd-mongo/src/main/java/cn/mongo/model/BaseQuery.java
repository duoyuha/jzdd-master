package cn.mongo.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * 通用查询父类
 */
@Getter
@Setter
public class BaseQuery {

    /**
     * 每页大小
     */
    private Integer pageSize;

    /**
     * 分页页码
     */
    private Integer pageNum;

    /**
     * 排序
     * 非json格式下的排序
     * 逗号分隔 + 正序 -倒序
     */
    private String sort;

    /**
     * 排序
     */
    private List<SortEntity> sortEntityList;


    /**
     * 开始时间
     */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date fromTime;

    /**
     * 结束时间
     */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date toTime;
}
