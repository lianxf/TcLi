package commons.boot.redisson;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;

public class RedissonClientBuilder {

    public static RedissonClient redissonClient(String host, Integer port, String password, Integer dataBase){
        Config config = new Config();
        config.setTransportMode(TransportMode.NIO);
        config.useSingleServer()
                .setAddress("redis://"+host+":"+port)
                .setPassword(password)
                .setDatabase(dataBase)
                .setTimeout(30000)
                .setConnectTimeout(900000);
        RedissonClient redissonClient = Redisson.create(config);
        return redissonClient;
    }
}
