package cn.backend.model.primary.report;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

/**
 * @author james
 * @date 2019/8/1
 **/
@Getter
@Setter
@ToString
public class SupplierReport {

    /**
     * 服务商编号
     */
    private String supplierNo;

    /**
     * 服务商名称
     */
    private String supplierName;

    /**
     * 总订单数量
     */
    private long allOrderNum;

    /**
     * 已完成订单量
     */
    private long finishOrderNum;

    /**
     * 投诉单量
     */
    private long complainOrderNum;

    /**
     * 被投诉的问题
     */
    private Map<String, Double> complainDesc;

    /**
     * 投诉率
     */
    private double complainPer;

    /**
     * 评分
     */
    private double score;

    /**
     * 平均超期时长
     */
    private double outTimeAvg;

    /**
     * 平均安装时长
     */
    private double installTimeAvg;

    /**
     * 满意度平均分
     */
    private double gradeScore;

}
