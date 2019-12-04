package cn.likepeng.commons.core.utils;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.likepeng.commons.core.utils.gmap.City;
import cn.likepeng.commons.core.utils.gmap.Gmap;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.util.List;

public class CityUtil {

    private static Gmap gmap;

    /**
     *  省市县
     * @return
     */
    public static List<City> cityInfo(){

        gmapInit();
        String key = gmap.getKey();

        HttpResponse httpResponse = HttpRequest.get(Gmap.DISTRICT_URL)
                .form("key", key)
                .form("keywords","中国")
                .form("subdistrict",3)
                .form("offset",20000)
                .execute();
        JSONObject response = JSON.parseObject(httpResponse.body());
        String status = response.getString("status");
        if ("1".equals(status)){
            JSONArray districts = response.getJSONArray("districts");
            JSONArray cities = districts.getJSONObject(0).getJSONArray("districts");
            List<City> result = cities.toJavaList(City.class);
            return result;
        }
        return null;
    }


    public static String regeo(String longitude,String latitude){
        gmapInit();
        String key = gmap.getKey();
        HttpResponse httpResponse = HttpRequest.get("https://restapi.amap.com/v3/geocode/regeo")
                .form("location", longitude+","+latitude)
                .form("key",key)
                .form("extensions","base")
                .form("batch",false)
                .form("roadlevel","1")
                .execute();
        JSONObject response = JSON.parseObject(httpResponse.body());
        String status = response.getString("status");
        if ("1".equals(status)){
            return response.getJSONObject("regeocode").getJSONObject("addressComponent")
                    .getString("city");
        }
        return null;
    }

    private static void gmapInit(){
        gmapInit("25ac2669bea17175aae5184f083160d8");
    }

    private static void gmapInit(String key){
        if (gmap == null){
            synchronized (IPUtil.class){
                if (gmap == null){
                    Gmap gmaps = new Gmap();
                    gmaps.setKey(key);
                    gmap = gmaps;
                }
            }
        }
    }
}
