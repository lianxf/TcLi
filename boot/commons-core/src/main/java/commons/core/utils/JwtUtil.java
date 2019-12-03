package commons.core.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.HmacAlgorithm;
import com.alibaba.fastjson.JSON;
import commons.core.jwt.Header;
import commons.core.jwt.Payload;

import java.util.Arrays;
import java.util.Date;

public class JwtUtil {


    public static String createToken(){
        Header header = new Header();
        String headerBase64 = SecurityUtil.base64Encoder(JSON.toJSONString(header));
        Date createTime = new Date();
        Payload payload = new Payload();
        payload.setUserId("415151511");
        payload.setJti("415151511");
        payload.setIat(createTime);
        payload.setExp(DateUtil.offsetDay(createTime,10));
        payload.setNbf(createTime);

        payload.setSub("张三");
        payload.setIss("TcLi");
        payload.setRoles(Arrays.asList("admin","guest"));

        String payloadBase64 = SecurityUtil.base64Encoder(JSON.toJSONString(payload));

        String secret = SecurityUtil.SHA256("密钥");
        String signature = SecureUtil.hmac(HmacAlgorithm.HmacSHA256, secret).digestHex(headerBase64 + "." + payloadBase64);
        return headerBase64+"."+payloadBase64+"."+signature;
    }

    public static void main(String[] args) {
        String token = createToken();
        String secret = SecurityUtil.SHA256("密钥");
        System.out.println(token);
    }
}
