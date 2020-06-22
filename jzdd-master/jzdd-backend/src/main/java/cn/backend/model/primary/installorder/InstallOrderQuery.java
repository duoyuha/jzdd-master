package cn.backend.model.primary.installorder;

import cn.backend.config.constant.SysConstant;
import cn.backend.model.BaseQuery;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 *
 * @author sunkw
 * @date 2019/07/17
 */
@Getter
@Setter
public class InstallOrderQuery extends BaseQuery{

    /**
     * 省
     */
    private String installProvince;

    /**
     * 市
     */
    private String installCity;


    /**
     * 市
     */
    private String pileCode;

    /**
     * 充电桩样式
     */
    private String pileType;

    /**
     * 服务商
     */
    private String supplierNo;

    /**
     * 订单创建日期开始
     */
    private Date orderTimeStart;

    /**
     * 订单创建日期结束
     */
    private Date orderTimeEnd;

     /**
      * 超时状态
      */
     private String timeoutFlg;

    /**
     * 预约勘察日期开始
     */
    private Date appointmentTimeStart;

    /**
     * 预约勘察日期结束
     */
    private Date appointmentTimeEnd;

    /**
     * 勘察完成日期开始
     */
    private Date checkTimeStart;

    /**
     * 勘察完成日期结束
     */
    private Date checkTimeEnd;

    /**
     * 预约安装日期开始
     */
    private Date appointmentInstallTimeStart;

    /**
     * 预约安装日期结束
     */
    private Date appointmentInstallTimeEnd;

    // /**
    //  * 安装开始日期开始
    //  */
    // private Date installTimeStart;
    //
    // /**
    //  * 安装开始日期结束
    //  */
    // private Date installTimeEnd;

    /**
     * 安装完成日期开始
     */
    private Date installFinishTimeStart;

    /**
     * 安装完成日期结束
     */
    private Date installFinishTimeEnd;

    /**
     * 订单完成日期开始
     */
    private Date orderFinishTimeStart;

    /**
     * 订单完成日期结束
     */
    private Date orderFinishTimeEnd;

    /**
     * 销售时间开始
     */
    private Date saleDateStart;

    /**
     * 销售时间结束
     */
    private Date saleDateEnd;

    // /**
    //  * 订单步骤
    //  */
    // private Integer orderStep;

    /**
     * 多条件查询
     */
    private String multipleQuery;

    /**
     * 公司
     */
    private String corpNo;

    // /**
    //  * 不需要的步骤
    //  */
    // private String noUseSteps;
    //
    // /**
    //  * 需要的步骤
    //  */
    // private String useSteps;

    /**
     * 是否开始做废动作 Y 是 N 否
     */
    private String beginCacle;

    /**
     * 是否结算 Y 是 N 否
     */
    private String settleFlg;

    /**
     * 是否完成 Y 是 N 否
     */
    private String finishFlg;

    /**
     * 是否巡检 Y 是 N 否
     */
    private String inspectionTourFlg;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 作废：初审 Y通过，N没通过
     */
    private String cancelVerify;


    /**
     * 作废：复审 Y通过，N没通过
     */
    private String cancelVerifySecond;

    /**
     * 作废备注
     */
    private String remark;

    /**
     * 职位
     */
    // private String userPosition;


    /**
     * 车型
     */

    private String carVehicle;

    private String vehicleDetail;

    private  String receiveStatus;


    private String show;

    private String followCode;

    private  String stepMatrix;

    private  String appSearch = SysConstant.InstallOrder.CONFIG_FLG_N;

    private  String dealerName;



    /**
     * 是否签收标志
     */
   // private  String receiveFlg;


    /**
     * 订单编号
     */
    private String orderNos;

    private String orderType;


    /**
     * 20190812 baimin 重构查询
     */
    private String allOrderFlg = SysConstant.Common.LIST_ALL_FLG_NO;

    private String userTypeWeb;

    private String userType;

    private String noNeedStepMatrix;

    /**
     * 步骤号
     */
    private Integer followSeq;

    private String vinNo;

    private String type;

    /**
     * 是否CRM撤回
     */
    private String crmRollbackFlg;



    // private  String dispatchFlg;
    //
    // private  String dispatchKanChaFlg;
    //
    // private  String kanChaFlg;
    //
    // private  String dispatchInstallFlg;
    //
    // private  String installFlg;
    //
    // private  String supConfiemFlg;
    //
    // private  String gradeFlg;
    //
    // private  String verifyFlg;

    /**
     * 派单开始时间
     */
    private Date dispatchStartTime;

    /**
     * 派单结束时间
     */
    private Date dispatchOverTime;
 
}
