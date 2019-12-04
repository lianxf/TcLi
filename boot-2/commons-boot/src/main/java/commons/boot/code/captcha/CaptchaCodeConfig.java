package commons.boot.code.captcha;

import commons.boot.constant.RedissonClientBuilder;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnBean(EnableCaptchaService.class)
public class CaptchaCodeConfig {

    @Autowired(required = false)
    EnableCaptchaService enableCaptchaService;


    @Bean(name = "captchaCodeRedisClient")
    public RedissonClient captchaCodeRedisClient(){
        String host = enableCaptchaService.getRedisHost();
        Integer port = enableCaptchaService.getRedisPort();
        String password = enableCaptchaService.getRedisPassword();
        Integer dataBase = enableCaptchaService.getRedisDataBase();
        RedissonClient redissonClient = RedissonClientBuilder.redissonClient(host, port, password, dataBase);
        return redissonClient;
    }

    @Bean
    public CaptchaCodeService captchaService(){
        CaptchaCodeService captchaCodeService = new CaptchaCodeService(captchaCodeRedisClient(),enableCaptchaService);
        return captchaCodeService;
    }
}
