package cn.backend.access.amap;

import cn.backend.access.amap.config.AmapConfig;
import cn.backend.access.amap.model.AmapLocation;
import cn.backend.access.amap.model.GPSResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author James
 * @date 2019/3/18 17:24
 */
@Slf4j
@Component
public class AmapApi {

    @Autowired
    private AmapConfig amapConfig;

    private RestTemplate restTemplate = new RestTemplate();

    /**
     * @param lon 经度
     * @param lat 纬度
     * @return 转换后的高德坐标
     */
    public AmapLocation gpsConvertToAmap(String lon, String lat) {
        String url = "http://restapi.amap.com/v3/assistant/coordinate/convert?locations=%s,%s&coordsys=gps&output=json&key=%s";
        try {
            ResponseEntity<AmapLocation> responseEntity = restTemplate.exchange(String.format(url, lon, lat, amapConfig.getAppKey()), HttpMethod.GET, null, AmapLocation.class);
            return new AmapLocation(responseEntity.getBody().getLocations());
        } catch (Exception e) {
            log.info("GPS坐标转换为高德坐标出错:{},{}", lon, lat, e);
        }
        return null;
    }

    public String gpsLocation(String lon, String lat) {
        String url = "http://restapi.amap.com/v3/geocode/regeo?location=%s,%s&key=%s&radius=1&extensions=base";
        try {
            ResponseEntity<GPSResult> responseEntity = restTemplate.exchange(String.format(url, lon, lat, amapConfig.getAppKey()), HttpMethod.GET, null, GPSResult.class);
            return responseEntity.getBody().getRegeoCode().getFormattedAddress();
        } catch (Exception e) {
            log.info("查询地理位置出错:{},{}", lon, lat, e);
        }
        return null;
    }
}
