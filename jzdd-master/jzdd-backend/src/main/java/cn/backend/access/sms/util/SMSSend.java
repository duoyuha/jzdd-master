package cn.backend.access.sms.util;

import cn.backend.access.sms.config.AliSMSConfig;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author James
 * @date 2019/3/19 14:34
 */
@Slf4j
public class SMSSend {

    private static final String SUCCESS = "OK";

    private AliSMSConfig config;

    public SMSSend(AliSMSConfig config) {
        this.config = config;
    }

    /**
     * 初始化短信客户端
     *
     * @return 短信客户端
     */
    private IAcsClient buildClient() {
        log.info("初始化短信客户端");
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        final String product = "Dysmsapi";
        final String domain = "dysmsapi.aliyuncs.com";

        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", config.getAccessKeyId(), config.getAccessKeySecret());
        try {
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        } catch (ClientException ignored) {

        }
        return new DefaultAcsClient(profile);
    }

    /**
     * @param mobile       待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码.
     * @param templateCode 模板编号
     * @param param        短信模板参数
     */
    public boolean sendMessage(String mobile, String templateCode, LinkedHashMap<String, String> param) {
        SendSmsRequest request = new SendSmsRequest();
        request.setMethod(MethodType.POST);
        request.setSignName(config.getSignName());
        request.setPhoneNumbers(mobile);
        request.setTemplateCode(templateCode);
        request.setTemplateParam(map2Json(param));
        IAcsClient client = buildClient();
        try {
            log.info("开始发送短信,手机号[{}],模板[{}],参数{}", mobile, templateCode, param);
            SendSmsResponse response = client.getAcsResponse(request);
            if (SUCCESS.equalsIgnoreCase(response.getCode())) {
                log.info("短信发送成功:{}", mobile);
                return true;
            } else {
                log.info("短信发送失败:{},{}", mobile, response.getMessage());
            }
        } catch (ClientException e) {
            log.info("短信发送出错:{},{}", mobile, e.getErrMsg());
        }
        return false;
    }

    /**
     * 将Map转换为Json
     *
     * @param param 参数
     * @return json
     */
    private static String map2Json(LinkedHashMap<String, String> param) {
        if (param == null || param.isEmpty()) {
            return "";
        }
        StringBuilder json = new StringBuilder("{");
        int size = param.size();
        for (Map.Entry<String, String> entry : param.entrySet()) {
            json.append(entry.getKey()).append(":").append("'").append(entry.getValue()).append("'");
            if (--size > 0) {
                json.append(",");
            }
        }
        json.append("}");
        return json.toString();
    }
}
