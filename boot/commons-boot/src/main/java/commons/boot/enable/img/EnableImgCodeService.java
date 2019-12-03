package commons.boot.enable.img;

import lombok.Data;
import java.io.Serializable;

@Data
public class EnableImgCodeService implements Serializable {
    private String redisHost  = "127.0.0.1";

    private Integer redisPort = 6379;

    private String redisPassword;

    private Integer redisDataBase = 3;

    private Integer captchaCodeLiveMinute = 1;

    private Integer codeLength = 6;

    private Integer codeWidth = 400;

    private Integer codeHeight = 100;

    private Integer lineCount = 60;
}