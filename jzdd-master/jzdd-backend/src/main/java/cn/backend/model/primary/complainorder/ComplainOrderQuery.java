package cn.backend.model.primary.complainorder;

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
public class ComplainOrderQuery extends BaseQuery{


    /**
     * 订单号
     */
    private String orderNo;

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
    private Date complainTimeStart;

    /**
     * 投诉日期结束
     */
    private Date complainTimeEnd;

    /**
     * 公司
     */
    private String corpNo;

    private String dealerName;

    /**
     * 职位
     */
    private String userPosition;

    /**
     * 职位
     */
    private String userType;

    private String userTypeWeb;

    private String allOrderFlg = SysConstant.Common.LIST_ALL_FLG_NO;

    private String finishFlg=SysConstant.InstallOrder.FINISH_FLG_N;

    private  String stepMatrix;

    private String solutionSupplierNo;

//    private String reduce1;
//
//    private String reduce2;

}
