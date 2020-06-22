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
@ApiModel(value = "orderReport", description = "用于返回统计报表数据对象")
public class OrderReport {



    /**
     * 安装服务商名称
     */
    @ApiModelProperty(value = "安装服务商名称", dataType = DataType.STRING)
    private String supplierName;


    /**
     * 城市
     */
    @ApiModelProperty(value = "安装城市", dataType = DataType.STRING)
    private String city;

    /**
     * 订单总数
     */
    @ApiModelProperty(value = "订单总数", dataType = DataType.INT)
    private  int ttlOrderNum;


    /**
     * 完成订单总数
     */
    @ApiModelProperty(value = "完成订单数量", dataType = DataType.INT)
    private  int finishOrderNum;

    /**
     * 完成百分比
     */
    @ApiModelProperty(value = "完成百分比", dataType = DataType.INT)
    private  double finishRate;


    /**
     * 订单存量
     */
    @ApiModelProperty(value = "订单存量", dataType = DataType.INT)
    private  int remainOrderNum;





}
