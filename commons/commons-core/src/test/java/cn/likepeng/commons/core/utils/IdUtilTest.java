package cn.likepeng.commons.core.utils;

import org.junit.Test;
import java.util.HashSet;

import static org.junit.Assert.*;

public class IdUtilTest {

    @Test
    public void snowflakeId() {
        long l = IdUtil.snowflakeId();
        System.out.println(l);
    }

    @Test
    public void snowflakeStringId() {
        String s = IdUtil.snowflakeStringId();
        System.out.println(s);
    }

    @Test
    public void simpleUUID() {
        String s = IdUtil.simpleUUID();
        System.out.println(s);
    }

    @Test
    public void UUID() {
        String s = IdUtil.UUID();
        System.out.println(s);
    }

    @Test
    public void objectId() {
        String s = IdUtil.objectId();
        System.out.println(s);
    }

    @Test
    public void inviteCode() throws Exception {

        HashSet<Long> codes = new HashSet<>();

        for (int i = 0; i < 1000000; i++) {
            long l = IdUtil.snowflakeId();
            codes.add(l);
        }
        System.out.println(codes.size());
    }
}