package map.boot.gaode;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnBean(EnableGaoDeMap.class)
public class GaoDeMapService {
    @Autowired
    EnableGaoDeMap enableGaoDeMap;

    private String regeoUrl = "https://restapi.amap.com/v3/geocode/regeo";

    public  String centerToCity(String longitude,String latitude){
        String key = enableGaoDeMap.getKey();
        HttpResponse httpResponse = HttpRequest.get(regeoUrl)
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
}
