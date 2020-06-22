package cn.backend.model.primary.orderstep;

import cn.backend.model.primary.ordergrade.OrderGradeEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Transient;
import java.util.Date;

/**
 *
 * @author sunkw
 * @date 2019/07/16
 */
@Getter
@Setter
public class OrderStepParameter{

    /**
     * 订单编码
     */
    private String orderNo;

    /**
     * 流程编号
     */
    private String followNo;


    /**
     * 操作日志
     */
    private String remark;

    /**
     * 基准时间
     */
    private Date baseTime;

    /**
     * 结束时间
     */
    private Date endTime = new Date();


    /**
     * 审批备注
     */
    private String resultDesc;

    /**
     * 超时计算类型
     * 01 安装、配送,限定工作日
     * 02 报修咨询、投诉，限定工作日和工时
     */
    private String TimeOutType;

    /**
     * 满意度
     */
    private OrderGradeEntity orderGradeEntity;

    /**
     * 重新生成订单类型 01 安装订单 02 配送订单
     */
    private String rebuildOrderType;



}
