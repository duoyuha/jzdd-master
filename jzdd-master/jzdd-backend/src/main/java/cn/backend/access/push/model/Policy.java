package cn.backend.access.push.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashSet;

public class Policy {
    protected final JSONObject policyJson = new JSONObject();

    private final HashSet<String> policyKeys = new HashSet<>(Arrays.asList("start_time", "expire_time", "max_send_num", "out_biz_no"));

    /**
     * 定时发送时间，若不填写表示立即发送
     *
     * @param startTime
     * @throws JSONException
     */
    public void setStartTime(String startTime) throws JSONException {
        policyJson.put("start_time", startTime);
    }

    /**
     * 消息过期时间,其值不可小于发送时间或者
     * 默认3天后过期
     *
     * @param expireTime
     * @throws JSONException
     */
    public void setExpireTime(String expireTime) throws JSONException {
        policyJson.put("expire_time", expireTime);
    }

    /**
     * 发送限速，每秒发送的最大条数
     *
     * @param maxSendNum
     * @throws JSONException
     */
    public void setMaxSendNum(int maxSendNum) throws JSONException {
        policyJson.put("max_send_num", maxSendNum);
    }

    /**
     * uuid
     *
     * @param outBizNo
     * @throws JSONException
     */
    public void setOutBizNo(String outBizNo) throws JSONException {
        policyJson.put("out_biz_no", outBizNo);
    }


}
