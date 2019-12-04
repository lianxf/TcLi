package cn.likepeng.commons.core.utils;

import cn.likepeng.commons.core.utils.gmap.City;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class CityUtilTest {

    @Test
    public void cityInfo() {
        List<City> pc = CityUtil.cityInfo();

        pc.forEach((p)->{
            System.out.println(p.getName());
            p.getDistricts().forEach((cc)->{
                System.out.println(cc.getName());
                List<City> districts = cc.getDistricts();
                districts.forEach(System.out::println);
            });
        });
    }
}