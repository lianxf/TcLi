package cn.likepeng.commons.core.utils;

import cn.hutool.crypto.asymmetric.Sign;
import org.junit.Test;
import static org.junit.Assert.*;

public class CryptoUtilTest {

    private String data = "测试";

    @Test
    public void base64Encoder() {
        String s = CryptoUtil.base64Encoder(data);
        String s1 = CryptoUtil.base64Decoder(s);
        assertTrue(data.equals(s1));
    }

    @Test
    public void sign() {
        Sign sign = CryptoUtil.signMD5withRSA();
        byte[] signed = sign.sign(data.getBytes());
        Boolean verify = CryptoUtil.verify(sign, signed, data);
        assertTrue(verify);
    }

    @Test
    public void SHA256() {
        String data = "测试";
        String s = CryptoUtil.HmacSHA256(data);

        System.out.println(s);

    }
}