package cn.backend.model.primary.dashbord;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class RankDto {

    private String supplierNo;

    private  String carVehicle;

    private  String finishFlg;

    private  Integer orderNum;

    private  Long orderNumLong;

    private  String installProvince;

    private Date appointmentDate;

    private  String followCode;

    private  String stepName;

    private  String SupplierEntity;



}
