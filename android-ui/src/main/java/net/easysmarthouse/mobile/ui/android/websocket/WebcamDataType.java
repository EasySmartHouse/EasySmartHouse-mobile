package net.easysmarthouse.mobile.ui.android.websocket;

/**
 * Created by rusakovich on 26.02.2017.
 */
public enum WebcamDataType {
    LIST("list"),
    IMAGE("image"),
    UNKNOWN("unknown");

    private final String type;

    private WebcamDataType(String type) {
        this.type = type;
    }

    public static WebcamDataType get(String typeString){
        if (typeString == null){
            return UNKNOWN;
        }

        WebcamDataType[] types = WebcamDataType.values();
        for (WebcamDataType type: types){
            if (type.type.equalsIgnoreCase(typeString)){
                return type;
            }
        }

        return UNKNOWN;
    }
}
