package cn.backend.model.primary.dashbord;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@ToString
public class VehicleDto {

    private Map<String,MapDto> mapDtoMap;

   // private String carVehicle;

}
