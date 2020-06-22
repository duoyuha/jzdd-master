package cn.backend.model.primary.delivery;

import cn.backend.model.BaseQuery;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 *
 * @author sunkw
 * @date 2019/07/16
 */
@Getter
@Setter
public class DeliveryOrderQuery extends BaseQuery{

    /**
     * 省
     */
    private String province;

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
     * APP,WEB对应操作步骤
     */
    private Integer step;

    /**
     * 回撤标志Y ，N
     */
    private String isBack;

    /**
     * 服务商编码
     */
    private String supplierNo;

    /**
     * 联系客户时间 yyyymmdd hhmmss
     */
    private Date contactCustTime;

    /**
     * 预约时间 yyyymmdd hhmmss
     */
    private Date appointmentTime;

    /**
     * 预约备注
     */
    private String appointmentRemark;

    /**
     * 配送类型：01 物流 02 自送
     */
    private String deliveryType;

    /**
     * 派单备注
     */
    private String dispatchRemark;

    /**
     * 01 未签收 02 签收 03 拒绝
     */
    private String receiveStatus;


    /**
     * 物流公司
     */
    private String deliveryName;

    /**
     * 物流单号
     */
    private String deliveryNo;

    /**
     * 物流单号
     */
    private String deliveryRemark;

    /**
     * 服务商确认时间 yyyymmdd hhmmss
     */
    private Date supplierConfirmTime;

    /**
     * 是否确认 01 是 02 否
     */
    private String supplierConfirmFlg;

    private String receiveRemark;

    /**
     * 是否确认 01 是 02 否
     */
    private String supplierConfirmFlgName;

    /**
     * 车厂审核 01 未审核 02 审核通过 03 审核拒绝
     */
    private String verifyFlg;

    /**
     * 审核备注
     */
    private String verifyRemark;


    /***
     * 是否开始废弃动作
     */
    private String beginCacle;

    /**
     * vin 用于创建订单时查询车主信息
     */
    private String vin;


    /**
     * 配送开始时间
     */
    private Date deliveryStartTime;

    /**
     * 配送结束时间
     */
    private Date deliveryOverTime;

    /**
     * 订单编码
     */
    private String orderNo;

    /**
     * 公司编码
     */
    private String corpNo;

    /**
     * 安装工人是否按时到达 Y 是 N 否
     */
    private String ontimeFlg;

    /**
     * 是否乱收费 Y 是 N 否
     */
    private String illegalChargeFlg;

    /**
     * 是否乱收费 Y 是 N 否
     */
    private String trainFlg;

    /**
     * 安装服务商评分 1非常不满意 2不满意 3一般 4满意 5非常满意
     */
    private Integer supplierGrade;

    /**
     * 经销商评分 1非常不满意 2不满意 3一般 4满意 5非常满意
     */
    private Integer dealerGrade;

    /**
     * 意见和建议
     */
    private String remark;

    /**
     * 登陆者NO
     */
    private String userNo;

    /**
     * 登陆者名称
     */
    private String userName;

    /**
     * 签收附件地址
     */
    private String signFile;

    /**
     * 物流单号附件
     */
    private String deliveryFile;

    /**
     * 配送保存和完成标志
     */
    private String deliverySign;

    /**
     * 图片编码
     */
    private String delAttachNos;

    /**\
     * 区别APP和WEB
     * 01 app 02 web
     */
    private String show;

    /**
     * 作废：初审 Y通过，N没通过
     */
    private String cancelVerify;


    /**
     * 作废：复审 Y通过，N没通过
     */
    private String cancelVerifySecond;

    private String finishFlg;

    private String orderNos;

    /**
     * 是否CRM撤回
     */
    private String crmRollbackFlg;


}
