package cn.likepeng.commons.core.utils;

import cn.hutool.core.lang.Validator;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.likepeng.commons.core.utils.gmap.IPInfo;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;


@SuppressWarnings("all")
public class IPUtil {

    public static IPInfo ipInfo(String ip) {
        boolean ipv4 = Validator.isIpv4(ip);
        IPInfo ipInfo = new IPInfo();
        if (ipv4){
            HashMap<String, Object> form = new HashMap<>();
            form.put("key","25ac2669bea17175aae5184f083160d8");
            form.put("ip",ip);

            HttpResponse response = HttpRequest
                    .get("https://restapi.amap.com/v3/ip")
                    .form(form)
                    .disableCookie()
                    .disableCache()
                    .execute();

            JSONObject jsonObject = JSON.parseObject(response.body());
            String status = jsonObject.getString("status");
            if ("1".equals(status)){
                ipInfo.setProvince(jsonObject.getString("province"));
                ipInfo.setCity(jsonObject.getString("city"));
                ipInfo.setAdcode(jsonObject.getString("adcode"));
                ipInfo.setRectangle(jsonObject.getString("rectangle"));
            }
        }
        return ipInfo;
    }
}
