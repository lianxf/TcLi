package cn.likepeng.commons.core.utils;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.likepeng.commons.core.utils.ant.ObjectUtils;
import cn.likepeng.commons.core.utils.gmap.Gmap;
import cn.likepeng.commons.core.utils.gmap.Weather;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class WeatherUtil {

    private static Gmap gmap;

    public static Weather weather(String city) {

        gmapInit();
        String key = gmap.getKey();

        if (!ObjectUtils.isEmpty(key)){
            return getWeather(key, city);
        }

        return null;
    }

    public static Weather weather(String key, String city) {
        gmapInit(key);
        gmap.getKey();

        if (!ObjectUtils.isEmpty(key)){
            return getWeather(key, city);
        }
        return null;
    }


    private static void gmapInit(){
        gmapInit("25ac2669bea17175aae5184f083160d8");
    }

    private static void gmapInit(String key){
        if (gmap == null){
            synchronized (WeatherUtil.class){
                if (gmap == null){
                    Gmap gmaps = new Gmap();
                    gmaps.setKey(key);
                    gmap = gmaps;
                }
            }
        }
    }

    private static Weather getWeather(String key, String city){
        HttpResponse httpResponse = HttpRequest.get(Gmap.WEATHER_URL)
                .form("key", key)
                .form("city",city)
                .form("extensions","all")
                .execute();
        JSONObject response = JSON.parseObject(httpResponse.body());
        String status = response.getString("status");

        System.out.println(httpResponse.body());
        if ("1".equals(status)){
            JSONArray forecast = response.getJSONArray("forecasts");
            Weather weather = forecast.getObject(0, Weather.class);
            return weather;
        }
        return null;
    }
}
