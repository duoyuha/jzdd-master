package cn.backend.access.amap.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author James
 * @date 2019/3/18 17:50
 */
@Data
public class RegeoCode {

    @JsonProperty("formatted_address")
    private String formattedAddress;
}
