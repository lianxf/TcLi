package commons.boot.enable.img;

import commons.core.utils.ImgCodeUtil;
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
public class ImgCodeService {

    private RedissonClient captchaCodeRedisClient;

    private EnableImgCodeService enableImgCodeService;

    public ImgCodeService(RedissonClient captchaCodeRedisClient, EnableImgCodeService enableImgCodeService) {
        this.captchaCodeRedisClient = captchaCodeRedisClient;
        this.enableImgCodeService = enableImgCodeService;
    }

    public void generate(HttpServletResponse response){
        Integer width = enableImgCodeService.getCodeWidth();
        Integer height = enableImgCodeService.getCodeHeight();
        Integer codeCount = enableImgCodeService.getCodeLength();
        Integer lineCount = enableImgCodeService.getLineCount();

        Integer liveMinute = enableImgCodeService.getCaptchaCodeLiveMinute();

        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
        } catch (IOException e) {
            log.error("生成验证码出错");
        }
        String code = ImgCodeUtil.generate(outputStream, width, height, codeCount, lineCount);
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
