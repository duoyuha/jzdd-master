package cn.backend.access.push.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 友盟配置文件
 */
@Component
@Getter
@Setter
public class UMengConfig {

    @Value("${UMengConfig.appkey_android}")
    private String appkey_android;

    @Value("${UMengConfig.appkey_ios}")
    private String appkey_ios;

    @Value("${UMengConfig.appMasterSecret_android}")
    private String appMasterSecret_android;

    @Value("${UMengConfig.appMasterSecret_ios}")
    private String appMasterSecret_ios;

}