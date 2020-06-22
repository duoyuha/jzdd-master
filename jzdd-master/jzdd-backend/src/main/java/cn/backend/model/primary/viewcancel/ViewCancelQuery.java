package cn.backend.model.primary.viewcancel;

import cn.backend.config.constant.SysConstant;
import cn.backend.model.BaseQuery;
import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import java.util.Date;

/**
 *
 * @author sunkw
 * @date 2019/07/16
 */
@Getter
@Setter
public class ViewCancelQuery extends BaseQuery{

    /**
     * 订单编码
     */
    private String orderNo;

    private String finishFlg;

    /**
     * 流程编号
     */
    private String followNo;

    /**
     * 流程编码
     */
    private String followCode;

    /**
     * 步骤号
     */
    private Integer followSeq;

    /**
     * 超时状态
     */
    private String timeoutStatus;

    private String supplierNo;

    /**
     */
    private String allOrderFlg = SysConstant.Common.LIST_ALL_FLG_NO;

    private String userTypeWeb;

    private String userType;


    /**
     * 是否是最新步骤
     */
    private String currentStepFlg;

    /**
     * 是否是最后步骤
     */
    private String lastStep;

    private String positionCode;

    private String stepMatrix;
    /**
     * VIN
     */
    private String vinNo;

    private String dealerName;

    private Date startTime;

    private Date endTime;

    @JsonIgnore
    private  String corpNo;


    @JsonIgnore
    private String followCodes;

}
