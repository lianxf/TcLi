package cn.likepeng.commons.core.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import cn.hutool.http.HttpRequest;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@SuppressWarnings("all")
public class QRCodeUtil{

    public static void generate(String content,String pathWithFileName){
        generate(content,pathWithFileName,400,400);
    }

    public static void generate(String content,String pathWithFileName,Integer width,Integer height){
        QrConfig config = config(width, height);
        QrCodeUtil.generate(content, config, FileUtil.file(pathWithFileName));
    }

    public static void generate(String content,String pathWithFileName,String logoImg){
        generate(content,pathWithFileName,logoImg,400,400);
    }

    public static void generate(String content,String pathWithFileName,String logoImg,Integer width,Integer height){
        QrConfig config = config(logoImg, width, height);
        QrCodeUtil.generate(content, config, FileUtil.file(pathWithFileName));
    }

    public static void generate(String content,  OutputStream out) {
        generate(content,400,400,out);
    }

    public static void generate(String content, String logoImg, OutputStream out) {
        generate(content,logoImg,400,400,out);
    }

    public static void generate(String content,Integer width,Integer height,  OutputStream out) {
        QrConfig config = config(width, height);
        generate(content,config,"png",out);
    }

    public static void generate(String content,String logoImg,Integer width,Integer height,  OutputStream out) {
        QrConfig config = config(logoImg, width, height);
        generate(content,config,"png",out);
    }

    public static void generate(String content, QrConfig config, String imageType, OutputStream out) {
        QrCodeUtil.generate(content, config,imageType,out);
    }

    public static byte[] generatepng(String content,String logoImg,Integer width,Integer height) {
        QrConfig config = config(logoImg, width, height);
        return QrCodeUtil.generatePng(content, config);
    }

    public static byte[] generatepng(String content,String logoImg) {
        QrConfig config = config(logoImg, 400, 400);
        return QrCodeUtil.generatePng(content, config);
    }

    public static byte[] generatepng(String content) {
        return generatepng(content, 400,400);
    }

    public static byte[] generatepng(String content,Integer width,Integer height) {
        return QrCodeUtil.generatePng(content, width,height);
    }

    private static QrConfig config(String logoImg,Integer width,Integer height){
        InputStream inputStream = HttpRequest.get(logoImg)
                .execute()
                .bodyStream();
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        QrConfig config = new QrConfig(width, height);
        config.setForeColor(Color.BLACK.getRGB());
        config.setBackColor(Color.WHITE.getRGB());
        config.setImg(bufferedImage);
        config.setErrorCorrection(ErrorCorrectionLevel.H);
        return config;
    }

    private static QrConfig config(Integer width,Integer height){
        QrConfig config = new QrConfig(width, height);
        config.setForeColor(Color.BLACK.getRGB());
        config.setBackColor(Color.WHITE.getRGB());
        config.setErrorCorrection(ErrorCorrectionLevel.H);
        return config;
    }
}
