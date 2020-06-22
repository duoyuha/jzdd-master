package cn.backend.model.primary.report;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.minidev.json.annotate.JsonIgnore;

/**
 * @author james
 * @date 2019/8/1
 **/
@Getter
@Setter
@ToString
public class AnalyseReport {

    /**
     * 市
     */
    private String city;

    /**
     * 服务商编号
     */
    @JsonIgnore
    private String supplierNo;

    /**
     * 服务商名称
     */
    private String supplierName;

    /**
     * 订单总数
     */
    @JsonIgnore
    private long orderNum;

    /**
     * 接单量
     */
    private Double orderNumPercent;


    /**
     * 已完成订单量
     */
    @JsonIgnore
    private long finishedOrderSize;

    /**
     * 完成率
     */
    @JsonIgnore
    private Double finishRate;


    /**
     * 投诉单量
     */
    private long complainOrderNum;



    /**
     * 安装时效
     */
    private double averageInstallTime;

    /**
     * 满意度
     */
    private double grade;



    /**
     * 满意度
     */
    @JsonIgnore
    private int gradeTtl;

    /**
     * 超时单量
     */
    private long overtimeOrderNum;

    /**
     * 超时时长(小时)
     */
    private double overtimeHours;

    /**
     * 投诉率
     */
    private double complainPer;


    @JsonIgnore
    private  long installSpan;


}
