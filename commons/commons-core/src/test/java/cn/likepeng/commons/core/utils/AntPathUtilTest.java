package cn.likepeng.commons.core.utils;

import org.junit.Test;
import static org.junit.Assert.*;

public class AntPathUtilTest {

    @Test
    public void matchPathAndMethod() {
        String path = "/user/name";
        String method = "get";

        Boolean aBoolean = AntPathUtil.matchPathAndMethod("/user/**", path,"POST", method);
        assertFalse(aBoolean);
    }
}