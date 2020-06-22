package cn.backend.model.primary.ordercar;

import cn.backend.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import java.util.Date;

/**
 * @author sunkw
 * @date 2019/07/17
 */
@Getter
@Setter
@ToString
public class OrderCarResponseEntity extends BaseEntity {

    /**
     * 主键
     */
    private String id;

    /**
     * 公司编号
     */
    private String corpNo;

    /**
     * 订单编码
     */
    private String orderNo;

    /**
     * 车主名称
     */
    private String carOwner;

    /**
     * 车主电话
     */
    private String carOwnerPhone;

    /**
     * 经销商名称
     */
    private String dealerName;

    /**
     * 经销商联系人
     */
    private String dealerContact;

    /**
     * 经销商电话
     */
    private String dealerTel;

    /**
     * 车型
     */
    private String carVehicle;

    /**
     * 车架号
     */
    private String vinNo;

    /**
     * 发动机编号
     */
    private String engineNo;

    /**
     * 销售日期 yyyymmdd
     */
    private Date saleDate;


}

