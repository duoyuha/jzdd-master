package cn.backend.model.primary.settle;

import cn.backend.config.constant.SysConstant;
import cn.backend.model.BaseQuery;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author James
 * @date 2019/07/29
 */
@Getter
@Setter
@ToString
public class SettleQuery extends BaseQuery {

    /**
     * 人员类型
     */
    private String userType;

    /**
     * 人员类型
     */
    private String userTypeWeb;

    /**
     * 服务商编号
     */
    private String supplierNo;

    private String vinNo;

    private String pileType;

    private  String allOrderFlg= SysConstant.Common.LIST_ALL_FLG_NO;

    //对于初审和复审
    private  String finishFlg;

    private String orderNo;

    private String corpNo;

    private String orderType;

    private String orderNos;

    private String settleStatus;
    /**
     * 区分外部，内部导出01外部，02内部
     */
    private String exportType;

    private String stepMatrix;

    private Date timeStart;

    private Date timeEnd;

    private String ids;

    private String type;

    private String id;

    private String settleNo;

    private String settleRemark;


}
