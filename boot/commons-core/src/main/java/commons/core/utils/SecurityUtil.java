package commons.core.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import cn.hutool.crypto.digest.HmacAlgorithm;

public class SecurityUtil {
    private static String privateBase64Key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCY4J5pgpiOZkapW0R0oedZZF8TZ8Vk39MoC0zBfUR6xdyTUcxs+ZqZKR2Afn3e8BA+x3tPrEwZsgMQkF5erreCWy/6WOktSPKOG3Vsg3LHROh1azNXzLesToODgz/X0ZA7Avgf2/H1O4ca40As1JBzLZP7QFHLspWlnhwiJZl9BmKyh00QWzDYuiVRcaMunFhM4iH1sIjCvVoQS4qh45mztB6oWwXI6SPcwWLx98OZVyQAKrfjOmRVXKSwd1ALtjjPlgKezgJKtIw1xD3KMRbQZBHQ5eNCZ60FILGuALF9k8CWcK99Z/fTVUVQNcigEMAHK1KC7HtXb0YhoyiMb/6PAgMBAAECggEAEq64jTpZfGWBUycFKZ+zjxgWCmAPHnG/80/WNHsjiEFv4E0Jx3OFchw1L8PFjB4YTiPArQ7oSeeYOkj6T2XYpUSRi22cKV2VzsoKQpWZzEJeiiLsplktRRBfQA4lGjHRB3Uob3RIkGiS7TVCXXgoovq57+V/WX5cjV3SIFYp+L5DdhJA4yU/bV7t7i8jK4oafIeUVLQA1F5q7sOegtzffDrTl47vzoV1LmRhYvMAuUEYM6NpdRMCRAamVxxHB+eZwCtwgBjvTGxd/xFNRy8JJs+eTcOuWy0ToNioSurUuD8FufjCjVEibRtBwTQjGyn/l83028Zm6R6M5g3e9OEp2QKBgQDZaGlYDBisnvVoAJwYDfY8JFzAGYg8XMHKixER9VkZ31r232O/EznRqsU5sC3yMZjw2kUCXfvRLi18y7QSUH+016f9xM4eQnZcZHzllmk5RBT9evdgmTJ5Y/RSnwWxkl/BIxOhqOM5wxruoa71uzgC5CmGDvl1dLcCbYT1ZJSXfQKBgQC0A8X2H2SalKmwNPWXkmZv0eV4e0ODx7iFu84rHSNXGqCRmwx06kHajwOuBmsu0/JySRoghULxafW0aknmr/kwAZvZlkkr8JmningDxQwZEe0KxCToymeVUGxLgpcn1uHZ5KDlne8YUneumaApUKhy7CvPZvatS+5AS8tx0C0D+wKBgBIQ8SAV8T72/qGj21TMZDffNa3o8T9C3f+8yrBZWQwjWWXGbn2EVXus2zLOByNnJ7RsDuwBb1mUoscKDm6vLc2TVixqXE57a2aGGzLg6uGNXMkrnL70PBOGUNlEya5V4YslJQ58AFXedrB50xYsdp0W5LR1Lhmql0KmYzQZJZfpAoGAPMh9YpLNFIkYFwB64Ng1MJKuKhVtAaVBgB6fCd3H3hSGnUO+XAfxfowF0RAnxuMYHVDjupFkvkFqDqZJF9TZjkUsy7a8NFMEfxb5WUSCxkBQSu9aCyS0/yzttD1/LpuXHUzeq0m9vvexo8oJ04mBp3Rpgpmgt3lJFHrDUkXZvVsCgYEA0Ok7bQd+khhK5UvZumlpPmvEHqAkVkZRA+G0AI7FpfKfz4HSvgOF2pwDjYZWwFiz1LVvo/Wz7SgIcUsmQb8fZpXzXxWyhK8GBNbtyN3KlZkuIRqkTyb4hqgJd9JLLaF3FB0QRkvCU3bbw6R8pNgSRW+YRBkxcOGO1V7zRmEM/wU=";
    private static String publicBase64Key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmOCeaYKYjmZGqVtEdKHnWWRfE2fFZN/TKAtMwX1EesXck1HMbPmamSkdgH593vAQPsd7T6xMGbIDEJBeXq63glsv+ljpLUjyjht1bINyx0TodWszV8y3rE6Dg4M/19GQOwL4H9vx9TuHGuNALNSQcy2T+0BRy7KVpZ4cIiWZfQZisodNEFsw2LolUXGjLpxYTOIh9bCIwr1aEEuKoeOZs7QeqFsFyOkj3MFi8ffDmVckACq34zpkVVyksHdQC7Y4z5YCns4CSrSMNcQ9yjEW0GQR0OXjQmetBSCxrgCxfZPAlnCvfWf301VFUDXIoBDABytSgux7V29GIaMojG/+jwIDAQAB";
    private static final RSA rsa = SecureUtil.rsa(privateBase64Key, publicBase64Key);


    public static String rsaEncoder(String data){
        return rsa.encryptBase64(data, KeyType.PublicKey);
    }

    public static String rsaDecoder(String data){
        return rsa.decryptStr(data, KeyType.PrivateKey);
    }

    public static String base64Encoder(String data){
        return Base64.encodeUrlSafe(data);
    }

    public static String base64Decoder(String data){
        return Base64.decodeStr(data);
    }

    public static String MD5(String data){
        return SecureUtil.md5(data);
    }

    public static String MD5(String... data){
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            stringBuffer.append(data[i]);
        }
        return SecureUtil.md5(stringBuffer.toString());
    }

    public static String SHA1(String data){
        return SecureUtil.sha1(data);
    }

    public static String SHA256(String data){
        return SecureUtil.sha256(data);
    }

    public static String SHA384(String data){
        return new Digester(DigestAlgorithm.SHA384).digestHex(data);
    }

    public static String SHA512(String data){
        return new Digester(DigestAlgorithm.SHA512).digestHex(data);
    }

    public static String HmacSHA1(String data,String secret){
        String password = SHA1(secret);
        return SecureUtil.hmacSha1(password).digestHex(data);
    }

    public static String HmacSHA256(String data,String secret){
        String password = SHA256(secret);
        return SecureUtil.hmac(HmacAlgorithm.HmacSHA256,password).digestHex(data);
    }

    public static String HmacSHA384(String data,String secret){
        String password = SHA384(secret);
        return SecureUtil.hmac(HmacAlgorithm.HmacSHA384,password).digestHex(data);
    }

    public static String HmacSHA512(String data,String secret){
        String password = SHA512(secret);
        return SecureUtil.hmac(HmacAlgorithm.HmacSHA512,password).digestHex(data);
    }
}
