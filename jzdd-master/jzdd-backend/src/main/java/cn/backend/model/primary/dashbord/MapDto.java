package cn.backend.model.primary.dashbord;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MapDto {

    private  String key;

    private  String supplierName;
    private  String carVehicle;

    private  String value;

    private Integer finishNum;

    private Integer unfinishNum;

    private Integer totalNum;

   // private String carVehicle;

}
