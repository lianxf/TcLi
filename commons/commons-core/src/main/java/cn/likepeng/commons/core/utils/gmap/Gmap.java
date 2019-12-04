package cn.likepeng.commons.core.utils.gmap;

import java.io.Serializable;

public class Gmap implements Serializable {
    public static final String WEATHER_URL  = "https://restapi.amap.com/v3/weather/weatherInfo";
    public static final String DISTRICT_URL  = "https://restapi.amap.com/v3/config/district";

    private String key;


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
