package commons.core.utils;


import cn.hutool.core.io.FileUtil;
import java.net.URL;

public class CityUtil {

    private static  String cityJson;

    static {
        URL cityResource = CityUtil.class.getClassLoader().getResource("city/city.json");
        cityJson  = FileUtil.readString(cityResource, "UTF-8");
    }

    public static String cityJson() {
        return cityJson;
    }
}
