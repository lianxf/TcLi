package commons.boot.enable.share;

import commons.boot.redisson.RedissonClientBuilder;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnBean(EnableShareCodeService.class)
public class ShareCodeAutoConfig {

    @Autowired(required = false)
    EnableShareCodeService enableShareCodeService;

    @Bean(name = "shareCodeRedisClient")
    public RedissonClient shareCodeRedisClient(){
        String host = enableShareCodeService.getRedisHost();
        Integer port = enableShareCodeService.getRedisPort();
        String password = enableShareCodeService.getRedisPassword();
        Integer dataBase = enableShareCodeService.getRedisDataBase();
        RedissonClient redissonClient = RedissonClientBuilder.redissonClient(host, port, password, dataBase);
        return redissonClient;
    }

    @Bean
    public ShareCodeService shareCodeService(){
        ShareCodeService shareCodeService = new ShareCodeService(enableShareCodeService,shareCodeRedisClient());
        return shareCodeService;
    }
}
