package cn.backend.model.primary.viewdeliveryorder;

import cn.backend.config.constant.SysConstant;
import cn.backend.model.BaseQuery;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 *
 * @author sunkw
 * @date 2019/07/23
 */
@Getter
@Setter
public class ViewDeliveryOrderQuery extends BaseQuery{

    /**
     * 省
     */
    private String province;

    private  String userType;

    private String allOrderFlg = SysConstant.Common.LIST_ALL_FLG_NO;

    private String userTypeWeb;

    private String noNeedStepMatrix;

    private  String appSearch = SysConstant.InstallOrder.CONFIG_FLG_N;

    private String pileType;



    /**
     * 公司
     */
    private String corpNo;

    private String beginCacle;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 市
     */
    private String city;

    /**
     * 超时状态
     */
    private String afterStatus;

    /**
     * 订单开始时间
     */
    private Date orderStartTime;

    /**
     * 订单结束时间
     */
    private Date orderOverTime;

    /**
     * 配送开始时间
     */
    private Date deliveryStartTime;

    /**
     * 配送结束时间
     */
    private Date deliveryOverTime;

    /**
     * 服务商确认时间
     */
    private Date supplierConfirmTimeStart;

    private Date supplierConfirmTimeEnd;


    private  String dealerName;

    private String vehicleDetail;


    /**
     * 完成开始时间
     */
    private Date finishStartTime;

    /**
     * 完成结束时间
     */
    private Date finishOverTime;

    /**
     * 销售时间开始
     */
    private Date saleDateStart;

    /**
     * 销售时间结束
     */
    private Date saleDateEnd;

    /**
     * 多条件查询
     */
    private String multipleQuery;

    /**
     * 服务商
     */
    private String supplierNo;

    /**
     * 步骤号
     */
    private Integer followSeq;

    /**
     * 是否完成
     */
    private String finishFlg;

    /**\
     * 区别APP和WEB
     * 01 app 02 web
     */
    private String show;


    private String useSteps;

    /**
     * 车型
     */
    private String carVehicle;

    /**
     * 01 未签收 02 签收 03 拒绝
     */
    private String receiveStatus;

    /**
     * 职位
     */
    // private String userPosition;

    private String followCode;


    private  String stepMatrix;

    /**
     * 是否结算
     */
    private String settleFlg;

    private String vinNo;

    private String type;

    /**
     * 派单开始时间
     */
    private Date dispatchStartTime;

    /**
     * 派单结束时间
     */
    private Date dispatchOverTime;


}
