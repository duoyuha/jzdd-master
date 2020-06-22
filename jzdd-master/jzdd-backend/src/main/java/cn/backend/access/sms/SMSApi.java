package cn.backend.access.sms;

import cn.backend.access.sms.config.AliSMSConfig;
import cn.backend.access.sms.util.SMSSend;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;

/**
 * @author James
 * @date 2019/3/19 14:40
 */
@Slf4j
@Component
public class SMSApi {


    @Autowired
    private AliSMSConfig config;

    private SMSSend send;

    public SMSApi(AliSMSConfig config) {
        this.config = config;
        if (this.send == null) {
            send = new SMSSend(config);
        }
    }

    /**
     * 发送普通客户短信
     *
     * @param mobile   待发送手机号
     * @param tempCode 模板编号
     * @param params   模板参数
     * @return 发送是否成功
     */
    public boolean sendNormalMessage(String mobile, String tempCode, LinkedHashMap<String, String> params) {
        return send.sendMessage(mobile, tempCode, params);
    }

    // /**
    //  * 验证码短信
    //  *
    //  * @param mobile    待发送手机号
    //  * @param validCode 验证码
    //  * @return 发送是否成功
    //  */
    // public boolean sendValidMessage(String mobile, String validCode) {
    //     LinkedHashMap<String, String> param = new LinkedHashMap<>();
    //     param.put("code", validCode);
    //     return sendNormalMessage(mobile, "SMS_126364808", param);
    // }

    /**
     * 接单短信
     *
     * @param mobile
     * @param repairPersonName
     * @param arrivalTime
     * @param repairNo
     * @return
     */
    public boolean sendReceiveOrderMessage(String mobile, String repairPersonName, String arrivalTime, String repairNo) {
        LinkedHashMap<String, String> param = new LinkedHashMap<>();
        param.put("name", repairPersonName);
        param.put("time", arrivalTime);
        param.put("code", repairNo);
        return sendNormalMessage(mobile, config.getRepairReceiveTemplateCode(), param);
    }

    /**
     * 评价短信
     *
     * @param mobile
     * @param repairNo
     * @return
     */
    public boolean sendRepairCompleteMessage(String mobile, String repairNo) {
        LinkedHashMap<String, String> param = new LinkedHashMap<>();
        param.put("code", repairNo);
        return sendNormalMessage(mobile, config.getRepairEvaluateTemplateCode(), param);
    }
}
