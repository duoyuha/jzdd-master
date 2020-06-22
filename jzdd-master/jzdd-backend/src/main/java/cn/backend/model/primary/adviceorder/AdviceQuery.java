package cn.backend.model.primary.adviceorder;

import cn.backend.config.constant.SysConstant;
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
public class AdviceQuery extends BaseQuery {

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 需要的步骤
     */
    private String useSteps;

    /**
     * 咨询类型
     */
    private String adviceType;

    /**
     * VIN
     */
    private String vinNo;

    /**
     * 服务商
     */
    private String supplierNo;

    /**
     * 投诉日期开始
     */
    private Date adviceTimeStart;

    /**
     * 投诉日期结束
     */
    private Date adviceTimeEnd;

    /**
     * 公司
     */
    private String corpNo;

    private String dealerName;

    /**
     * 职位
     */
    private String userType;

    private String userTypeWeb;

    private String allOrderFlg = SysConstant.Common.LIST_ALL_FLG_NO;

    private String finishFlg=SysConstant.InstallOrder.FINISH_FLG_N;

    private  String stepMatrix;

    private String solutionSupplierNo;



}
