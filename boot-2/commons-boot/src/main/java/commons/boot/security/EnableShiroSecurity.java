package commons.boot.security;

import lombok.Data;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
public class EnableShiroSecurity implements Serializable {
    private String redisHost  = "127.0.0.1";

    private Integer redisPort = 6379;

    private String redisPassword;

    private Integer redisDataBase = 2;

    private Integer tokenLiveHours = 12;

    private Integer freshTokenIntervalMinutes = 30 ;

    private String tokenSalt = "token加密盐";

    private String header = "authorization";

    private List<String> anonUrls;

    private List<String> authUrls;
}
