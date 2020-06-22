package cn.backend.access.push.config;

public enum PushType {
    unicast("unicast"),
    listcast("listcast"),
    filecast("filecast"),
    broadcast("broadcast"),
    groupcast("groupcast"),
    customizedcast("customizedcast");

    private String value;

    PushType(String value){
        this.value=value;
    }

    public String getValue() {
        return value;
    }
}
