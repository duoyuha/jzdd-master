package cn.backend.access.push.config;

public enum OpenType {
    app("go_app"),
    url("go_url"),
    activity("go_activity"),
    custom("go_custom");

    private String value;

    OpenType(String value){
        this.value=value;
    }

    public String getValue() {
        return value;
    }
}
