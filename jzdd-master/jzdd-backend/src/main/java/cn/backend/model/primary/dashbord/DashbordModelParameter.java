package cn.backend.model.primary.dashbord;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class DashbordModelParameter {

    List<MapDto>   unfinishVehicle;


    List<MapDto>  finishVehicle;

    List<MapDto>  ttlVehicle;


    List<MapDto>  finishProvince;


    List<MapDto>  unfinishProvince;


   // List<MapDto>  ttlProvince;

    SupplieDto supplieDto;


}
