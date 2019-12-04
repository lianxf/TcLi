package cn.likepeng.commons.core.utils;

import cn.likepeng.commons.core.utils.gmap.Weather;
import org.junit.Test;

public class WeatherUtilTest {

    @Test
    public void weather() {
        Weather weather = WeatherUtil.weather("禹州");
        System.out.println(weather);

    }
}