package cn.backend.access.push.model;

import lombok.Getter;
import lombok.Setter;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 对应于iOS系统
 */
@Getter
@Setter
public class Aps {

    private final JSONObject apsJson = new JSONObject();
    private final JSONObject alert = new JSONObject();

    public void setAlert(String title, String text) throws JSONException {
        alert.put("title", title);
        alert.put("subtitle", "");
        alert.put("body", text);
        apsJson.put("alert", alert);
    }

    public void setBadge(String badge) throws JSONException {
        apsJson.put("badge", badge);
    }

    public void setSound(String sound) throws JSONException {
        apsJson.put("sound", sound);
    }

    public void setContentAvailable(String contentAvailable) throws JSONException {
        apsJson.put("content-available", contentAvailable);
    }

    public void setCategory(String category) throws JSONException {
        apsJson.put("category", category);
    }


}
