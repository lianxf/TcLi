package cn.likepeng.commons.core.utils;

import cn.hutool.core.net.NetUtil;
import org.junit.Test;
import java.util.Date;
import static org.junit.Assert.*;

public class StringUtilTest {

    @Test
    public void getNowString() {

    }

    @Test
    public void camelCase() {

        String data = "NameServer";
        String s = StringUtil.toSymbolCase(data,'-');
        System.out.println(s);
    }

    @Test
    public void ip(){
        String localhostStr = NetUtil.getLocalhostStr();
        System.out.println(localhostStr);
    }
}