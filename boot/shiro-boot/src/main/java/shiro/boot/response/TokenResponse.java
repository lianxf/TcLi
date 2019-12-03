package shiro.boot.response;

import lombok.Data;
import shiro.boot.entity.RedisToken;
import java.io.Serializable;

@Data
public class TokenResponse implements Serializable {

    private Integer code = 200;

    private RedisToken data;

    private String msg = "ok";
}