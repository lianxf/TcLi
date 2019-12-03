package commons.core.utils;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;

import java.io.OutputStream;

@SuppressWarnings("all")
public class ImgCodeUtil {

    public static String  generate(String outImg){
        return generate(outImg,400,100,6,60);
    }

    public static String  generate(String outImg,Integer width,Integer height,Integer codeCount,Integer lineCount){
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(width, height, codeCount, lineCount);
        String code = lineCaptcha.getCode();
        lineCaptcha.write(outImg);
        return code;
    }

    public static String  generate(OutputStream out){
        return generate(out,400,100,6,60);
    }

    public static String  generate(OutputStream out, Integer width, Integer height, Integer codeCount, Integer lineCount){
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(width, height, codeCount, lineCount);
        String code = lineCaptcha.getCode();
        lineCaptcha.write(out);
        return code;
    }
}
