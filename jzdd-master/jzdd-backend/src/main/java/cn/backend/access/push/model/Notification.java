package cn.backend.access.push.model;


import cn.backend.access.push.config.PushType;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Set;

@Getter
@Setter
public class Notification {

    private final JSONObject rootJson = new JSONObject();

    private Payload payload = new Payload();

    public String getPostBody() throws JSONException {
        rootJson.put("payload", payload.getPayloadJson());
        return rootJson.toString();
    }

    /**
     * 设置appKey
     *
     * @param appkey
     * @return
     * @throws JSONException
     */
    public void setAppkey(String appkey) throws JSONException {
        rootJson.put("appkey", appkey);
    }

    /**
     * 设置时间戳，时间戳有效时间10分钟
     *
     * @param timestamp
     * @return
     * @throws JSONException
     */
    public void setTimestamp(long timestamp) throws JSONException {
        rootJson.put("timestamp", timestamp);
    }

    /**
     * 设置推送方式
     *
     * @param type
     * @return
     * @throws JSONException
     */
    public void setType(PushType type) throws JSONException {
        rootJson.put("type", type.getValue());
    }

    /**
     * 设置设备token
     *
     * @param deviceTokens
     * @throws JSONException
     */
    public void setDeviceTokens(Set<String> deviceTokens) throws JSONException {
        if (!rootJson.has("type")) {
            throw new RuntimeException("请先设置推送方式");
        }
        String type = rootJson.get("type").toString();
        if (type.equals(PushType.unicast.getValue())) {
            if (deviceTokens.size() > 1) {
                throw new RuntimeException("单推模式只能设置一个device_token");
            }
            rootJson.put("device_tokens", StringUtils.join(deviceTokens, ","));
        }
        if (type.equals(PushType.listcast.getValue())) {
            if (deviceTokens.size() > 500) {
                throw new RuntimeException("群推模式device_token的数量不能超过500个");
            }
            rootJson.put("device_tokens", StringUtils.join(deviceTokens, ","));
        }
    }

    /**
     * 设置别名
     *
     * @param alias
     * @param aliasType
     * @throws JSONException
     */
    public void setAlias(Set<String> alias, String aliasType) throws JSONException {
        if (!rootJson.has("type")) {
            throw new RuntimeException("请先设置推送方式");
        }
        String type = rootJson.get("type").toString();
        if (type.equals(PushType.customizedcast.getValue())) {
            if (alias.size() > 50) {
                throw new RuntimeException("customizedcast模式alias的数量不能超过50个");
            }
            setAlias(alias);
            setAliasType(aliasType);
        }

    }

    /**
     * 设置文件id
     *
     * @param fileId
     * @throws JSONException
     */
    public void setFileId(String fileId) throws JSONException {
        if (!rootJson.has("type")) {
            throw new RuntimeException("请先设置推送方式");
        }
        String type = rootJson.get("type").toString();
        if (type.equals(PushType.filecast.getValue())) {
            rootJson.put("file_id", fileId);
        }
    }

    /**
     * 设置发送环境：true 正式环境,false 测试环境
     *
     * @param mode
     * @throws JSONException
     */
    public void setProductionMode(Boolean mode) throws JSONException {
        rootJson.put("production_mode", mode.toString());
    }

    /**
     * 发送消息描述
     *
     * @param description
     * @throws JSONException
     */
    public void setDescription(String description) throws JSONException {
        rootJson.put("description", description);
    }


    private void setAlias(Set<String> alias) throws JSONException {
        rootJson.put("alias", alias);
    }

    private void setAliasType(String aliasType) throws JSONException {
        rootJson.put("aliasType", aliasType);
    }

    public void setMiPush(Boolean miPush, String miActivity) throws JSONException {
        //MIUI、EMUI、Flyme系统设备离线转为系统下发
        rootJson.put("mipush", miPush);
        //mipush值为true时生效，表示走系统通道时打开指定页面acitivity的完整包路径。
        rootJson.put("mi_activity", miActivity);
    }


}
