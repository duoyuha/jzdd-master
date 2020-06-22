package cn.backend.model.primary.scrminfo;

import cn.backend.model.BaseEntity;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 *
 * @author sunkw
 * @date 2019/07/17
 */
@Getter
@Setter
@ToString
public class ScrmCarInfoEntity extends BaseEntity{


    /**
     * 车主联系方式
     */
    private String ContactPhone;

    /**
     * 车主姓名
     */
    private String ContactName;

    /**
     * 车型编码
     */
    private String CarTypeCode;

    /**
     * 店简称
     */
    private String ProductKindID;

    /**
     * SimpleName
     */
    private String SimpleName;

    /**
     * 车型名称
     */
    private String CarType;

    /**
     * Vin
     */
    private String Vin;

    /**
     * 车主编号
     * vin
     */
    private String CustomerId;
    private String CustomerName;

    /**
     * 专营店
     */
    private String Shnumber;

    /**
     * 经销商联系人
     */
    private String EmployeeName;

    /**
     * 经销商联系人联系电话
     */
    private String EmployeePhone;

    private String EmployeeID;

    /**
     * 销售日期
     */
    private Long SaleDate;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date SaleDateStr;

    /**
     * 公司名称
     */
    private String WholeName;

    /**
     * 车类型名称
     */
    private String ProductKind;

    /**
     * 发动机
     */
    private String Engine;

    /**
     * 款式
     */
    private String Pattern;

    /**
     * 配置
     */
    private String Configuration;

}

