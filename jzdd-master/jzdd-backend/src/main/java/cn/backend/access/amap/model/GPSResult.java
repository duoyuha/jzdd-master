package cn.backend.access.amap.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author James
 * @date 2019/3/18 17:49
 */
@Data
public class GPSResult {

    @JsonProperty("regeocode")
    private RegeoCode regeoCode;
}
