package cn.likepeng.commons.core.utils;

@SuppressWarnings("all")
public class TokenUtil {

    public static String token(String id,String fixSuffix){
        String token = CryptoUtil.SHA256(id+fixSuffix);
        return token;
    }

    public static String token(Long id,String fixSuffix){
        String token = CryptoUtil.SHA256(id+fixSuffix);
        return token;
    }
}
