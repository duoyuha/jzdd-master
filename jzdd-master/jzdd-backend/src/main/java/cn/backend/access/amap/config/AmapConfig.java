package cn.backend.access.amap.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author James
 * @date 2019/3/18 17:32
 */
@Data
@Component
public class AmapConfig {

    @Value("${AmapConfig.web_app_key}")
    private String appKey;
}
