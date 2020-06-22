package cn.backend.model.primary.report;


import cn.backend.config.swagger.DataType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author sunkw
 * @date 2019/07/08
 */
@Getter
@Setter
@ApiModel(value = "settleReport", description = "用于返回结算报表数据对象")
public class SettleReport {



    /**
     * 安装服务商名称
     */
    @ApiModelProperty(value = "安装服务商名称", dataType = DataType.STRING)
    private String supplierName;




    /**
     * 已订单总数
     */
    @ApiModelProperty(value = "已订单总数", dataType = DataType.INT)
    private  int SubmitOrderNum;


    /**
     * 未提交订单数
     */
    @ApiModelProperty(value = "未提交订单数", dataType = DataType.INT)
    private  int unSubmitOrderNum;


    /**
     * 已提交订单金额
     */
    @ApiModelProperty(value = "已提交订单金额", dataType = DataType.INT)
    private  Double SubmitOrderAmt;


    /**
     * 未结算订单金额
     */
    @ApiModelProperty(value = "未结算订单金额", dataType = DataType.INT)
    private  Double unSubmitOrderAmt;





}
