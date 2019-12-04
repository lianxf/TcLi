package commons.boot.code.captcha;

import cn.likepeng.commons.core.utils.CaptchaCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.http.MediaType;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Slf4j
@SuppressWarnings("all")
public class CaptchaCodeService {

    private RedissonClient captchaCodeRedisClient;

    private EnableCaptchaService enableCaptchaService;

    public CaptchaCodeService(RedissonClient captchaCodeRedisClient, EnableCaptchaService enableCaptchaService) {
        this.captchaCodeRedisClient = captchaCodeRedisClient;
        this.enableCaptchaService = enableCaptchaService;
    }

    public void generate(HttpServletResponse response){
        Integer width = enableCaptchaService.getCodeWidth();
        Integer height = enableCaptchaService.getCodeHeight();
        Integer codeCount = enableCaptchaService.getCodeLength();
        Integer lineCount = enableCaptchaService.getLineCount();

        Integer liveMinute = enableCaptchaService.getCaptchaCodeLiveMinute();

        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
        } catch (IOException e) {
            log.error("生成验证码出错");
        }
        String code = CaptchaCodeUtil.generate(outputStream, width, height, codeCount, lineCount);
        RBucket<Object> bucket = captchaCodeRedisClient.getBucket(code);
        bucket.set(code,liveMinute, TimeUnit.MINUTES);
    }

    public Boolean checkCaptchaCode(String code){
        RBucket<Object> bucket = captchaCodeRedisClient.getBucket(code);
        boolean flag = bucket.isExists();
        if (flag){
            bucket.delete();
        }
        return flag;
    }
}
