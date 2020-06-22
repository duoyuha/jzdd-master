package cn.backend.access.push.config;

public enum DisplayType {
    notification("notification"),
    message("message");
    private String value;

    DisplayType(String value){
        this.value=value;
    }

    public String getValue() {
        return value;
    }
}
