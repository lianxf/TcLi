package shiro.boot.config;

import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
public class EnableShiroSecurity implements Serializable {
    private String redisHost  = "127.0.0.1";

    private Integer redisPort = 6379;

    private String redisPassword;

    private Integer redisDataBase = 2;

    private long tokenLiveHours = 12;

    private long freshTokenIntervalMinutes = 5;

    private String tokenSecret = "密钥";

    private String header = "Authorization";

    private List<String> anonUrls;

    private List<String> authUrls;
}
