package cn.backend.access.sms.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author James
 * @date 2019/3/19 14:44
 */
@Data
@Component
public class AliSMSConfig {

    /**
     * 短信服务Id
     */
    @Value("${AliSMSConfig.AccessKeyId}")
    private String accessKeyId;

    /**
     * 短信服务密码
     */
    @Value("${AliSMSConfig.AccessKeySecret}")
    private String accessKeySecret;

    /**
     * 短信签名
     */
    @Value("${AliSMSConfig.SMSSignName}")
    private String signName;

    /**
     * 抢修工单受理模板
     */
    @Value("${AliSMSConfig.RepairReceiveTemplateCode}")
    private String repairReceiveTemplateCode;

    /**
     * 抢修工单评价模板
     */
    @Value("${AliSMSConfig.RepairEvaluateTemplateCode}")
    private String repairEvaluateTemplateCode;
}
