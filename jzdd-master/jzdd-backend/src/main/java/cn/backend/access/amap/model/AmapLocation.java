package cn.backend.access.amap.model;

import lombok.Data;

/**
 * @author James
 * @date 2019/3/18 17:18
 */
@Data
public class AmapLocation {

    /**
     * 经度
     */
    private String lon;

    /**
     * 维度
     */
    private String lat;

    private String locations;

    public AmapLocation(){

    };

    public AmapLocation(String locations) {
        this.locations = locations;
        String[] var1 = locations.split(",");
        this.lon = var1[0];
        this.lat = var1[1];
    }

    public AmapLocation(String lon, String lat) {
        this.lon = lon;
        this.lat = lat;
    }
}
