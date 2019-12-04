package cn.likepeng.commons.core.utils;

import cn.likepeng.commons.core.utils.gmap.IPInfo;
import org.junit.Test;

public class IPUtilTest {

    @Test
    public void ipInfo() {

        for (int i = 0; i < 1; i++) {
            IPInfo ipInfo = IPUtil.ipInfo("1.1.8.0");
        }
    }
}