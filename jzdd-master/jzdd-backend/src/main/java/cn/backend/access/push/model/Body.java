package cn.backend.access.push.model;


import cn.backend.access.push.config.DisplayType;
import cn.backend.access.push.config.OpenType;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 对应与Android系统
 */
@Getter
@Setter
public class Body {
    private final JSONObject bodyJson = new JSONObject();

    // 通知展现内容

    /**
     * 通知栏提示文字
     *
     * @param ticker
     * @throws JSONException
     */
    public void setTicker(String ticker) throws JSONException {
        bodyJson.put("ticker", ticker);
    }

    /**
     * 通知标题
     *
     * @param title
     * @throws JSONException
     */
    public void setTitle(String title) throws JSONException {
        bodyJson.put("title", title);
    }

    /**
     * 通知文字描述
     *
     * @param text
     * @throws JSONException
     */
    public void setText(String text) throws JSONException {
        bodyJson.put("text", text);
    }

    // 自定义通知图标

    /**
     * 状态栏图标ID
     *
     * @param icon
     * @throws JSONException
     */
    public void setIcon(String icon) throws JSONException {
        bodyJson.put("icon", icon);
    }

    /**
     * 通知栏拉开后左侧图标ID
     *
     * @param largeIcon
     * @throws JSONException
     */
    public void setLargeIcon(String largeIcon) throws JSONException {
        bodyJson.put("largeIcon", largeIcon);
    }

    /**
     * 通知栏大图标的URL链接。该字段的优先级大于largeIcon
     *
     * @param img
     * @throws JSONException
     */
    public void setImg(String img) throws JSONException {
        if (!img.startsWith("http") && !img.startsWith("https")) {
            throw new RuntimeException("必须以http或者https开头");
        }
        bodyJson.put("img", img);
    }

    /**
     * 自定义通知声音
     *
     * @param sound
     * @throws JSONException
     */
    public void setSound(String sound) throws JSONException {
        bodyJson.put("sound", sound);
    }

    /**
     * 自定义通知样式,默认为0
     *
     * @param builderId
     * @throws JSONException
     */
    public void setBuilderId(int builderId) throws JSONException {
        bodyJson.put("builder_id", builderId);
    }

    // 通知到达设备后的提醒方式

    /**
     * 收到通知是否震动,默认为"true"
     *
     * @param playVibrate
     * @throws JSONException
     */
    public void setPlayVibrate(Boolean playVibrate) throws JSONException {
        bodyJson.put("play_vibrate", playVibrate.toString());
    }

    /**
     * 收到通知是否闪灯,默认为"true"
     *
     * @param playLights
     * @throws JSONException
     */
    public void setPlayLights(Boolean playLights) throws JSONException {
        bodyJson.put("play_lights", playLights.toString());
    }

    /**
     * 收到通知是否发出声音,默认为"true"
     *
     * @param playSound
     * @throws JSONException
     */
    public void setPlaySound(Boolean playSound) throws JSONException {
        bodyJson.put("play_sound", playSound.toString());
    }

    // 点击"通知"的后续行为，默认为打开app

    /**
     * 后续行为
     *
     * @param type
     * @throws JSONException
     */
    public void setAfterOpen(OpenType type) throws JSONException {
        bodyJson.put("after_open", type.getValue());
    }

    /**
     * 通知栏点击后跳转的URL，要求以http或者https开头
     *
     * @param url
     * @throws JSONException
     */
    public void setUrl(String url) throws JSONException {
        if (!bodyJson.has("after_open")) {
            throw new RuntimeException("请先设置点击通知的后续行为");
        }
        String type = bodyJson.get("after_open").toString();
        if (!type.equals(OpenType.url.getValue())) {
            throw new RuntimeException("通知的后续行为未go_url时才能设置url");
        }
        bodyJson.put("url", url);
    }

    /**
     * 通知栏点击后打开的Activity
     *
     * @param activity
     * @throws JSONException
     */
    public void setActivity(String activity) throws JSONException {
        if (!bodyJson.has("after_open")) {
            throw new RuntimeException("请先设置点击通知的后续行为");
        }
        String type = bodyJson.get("after_open").toString();
        if (!type.equals(OpenType.activity.getValue())) {
            throw new RuntimeException("通知的后续行为未go_activity时才能设置activity");
        }
        bodyJson.put("activity", activity);
    }

    /**
     * 用户自定义内容, 可以为字符串或者JSON格式
     *
     * @param custom
     * @throws JSONException
     */
    public void setCustom(String custom) throws JSONException {
        String displayType = bodyJson.get("display_type").toString();
        if (displayType.equals(DisplayType.message.getValue())) {
            bodyJson.put("custom", custom);
        }
        if (displayType.equals(DisplayType.notification.getValue())) {
            if (!bodyJson.has("after_open")) {
                throw new RuntimeException("请先设置点击通知的后续行为");
            }
            String type = bodyJson.get("after_open").toString();
            if (!type.equals(OpenType.custom.getValue())) {
                throw new RuntimeException("通知的后续行为未go_custom时才能设置custom");
            }
            bodyJson.put("custom", custom);
        }
    }


}
