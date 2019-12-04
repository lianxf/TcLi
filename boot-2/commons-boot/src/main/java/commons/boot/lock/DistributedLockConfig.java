package commons.boot.lock;

import commons.boot.aop.DistributedLockAop;
import commons.boot.constant.RedissonClientBuilder;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnBean(EnableDistributedLock.class)
public class DistributedLockConfig {

    @Autowired(required = false)
    EnableDistributedLock enableDistributedLock;


    @Bean(name = "distributedLockClient")
    public RedissonClient distributedLockClient(){
        String host = enableDistributedLock.getHost();
        Integer port = enableDistributedLock.getPort();
        String password = enableDistributedLock.getPassword();
        Integer dataBase = enableDistributedLock.getDataBase();
        RedissonClient redissonClient = RedissonClientBuilder.redissonClient(host, port, password, dataBase);
        return redissonClient;
    }


    @Bean
    public DistributedLockAop distributedLockAop(){
        DistributedLockAop distributedLockAop = new DistributedLockAop();
        return distributedLockAop;
    }

}
