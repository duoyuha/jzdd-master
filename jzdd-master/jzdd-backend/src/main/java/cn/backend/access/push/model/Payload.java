package cn.backend.access.push.model;

import cn.backend.access.push.config.DisplayType;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONException;
import org.json.JSONObject;

@Getter
@Setter
public class Payload {
    private final JSONObject payloadJson = new JSONObject();

    private Body body = new Body();
    private Aps aps = new Aps();

    public void setDisplayType(DisplayType displayType) throws JSONException {
        payloadJson.put("display_type", displayType.getValue());
        payloadJson.put("body", body.getBodyJson());
        payloadJson.put("aps", aps.getApsJson());
    }
}
