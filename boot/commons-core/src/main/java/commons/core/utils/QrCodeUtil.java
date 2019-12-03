package commons.core.utils;

public class QrCodeUtil {
    public static byte[] generate(String id){
        return cn.hutool.extra.qrcode.QrCodeUtil.generatePng(id, 400,400);
    }
}
