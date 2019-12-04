package cn.likepeng.commons.core.utils;

import cn.likepeng.commons.core.regex.NormalRegex;
import org.junit.Test;
import static org.junit.Assert.*;

public class ValidateUtilTest {

    @Test
    public void isMatchRegex() {
        String data = "沾上干ksdnajf1515l";
        String regex = NormalRegex.CHINESE_ENGLISH_NUMBER.getRegex();
        Boolean matchRegex = ValidateUtil.isMatchRegex(data, regex);
        assertTrue(matchRegex);
    }

    @Test
    public void checkName() {
        String name = "111151.015";
        Boolean matchRegex = ValidateUtil.isMoney(name,3);
        assertTrue(matchRegex);
    }
}