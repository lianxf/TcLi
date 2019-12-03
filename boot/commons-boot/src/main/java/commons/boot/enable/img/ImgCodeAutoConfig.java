package commons.boot.enable.img;

import commons.boot.redisson.RedissonClientBuilder;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnBean(EnableImgCodeService.class)
public class ImgCodeAutoConfig {

    @Autowired(required = false)
    EnableImgCodeService enableImgCodeService;


    @Bean(name = "imgCodeRedisClient")
    public RedissonClient imgCodeRedisClient(){
        String host = enableImgCodeService.getRedisHost();
        Integer port = enableImgCodeService.getRedisPort();
        String password = enableImgCodeService.getRedisPassword();
        Integer dataBase = enableImgCodeService.getRedisDataBase();
        RedissonClient redissonClient = RedissonClientBuilder.redissonClient(host, port, password, dataBase);
        return redissonClient;
    }

    @Bean
    public ImgCodeService imgCodeService(){
        ImgCodeService imgCodeService = new ImgCodeService(imgCodeRedisClient(),enableImgCodeService);
        return imgCodeService;
    }
}