package commons.boot.enable.share;

import lombok.Data;
import java.io.Serializable;

@Data
public class EnableShareCodeService implements Serializable {
    private String redisHost  = "127.0.0.1";

    private Integer redisPort = 6379;

    private String redisPassword;

    private Integer redisDataBase = 4;
}
