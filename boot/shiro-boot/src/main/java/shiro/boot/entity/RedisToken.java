package shiro.boot.entity;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class RedisToken implements Serializable {
    private String userId;
    private String token;
    private Date createTime;
    private List<String> roles;
}
