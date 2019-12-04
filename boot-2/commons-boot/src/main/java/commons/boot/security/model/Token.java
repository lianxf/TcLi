package commons.boot.security.model;

import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
public class Token implements Serializable {
    private Long userId;
    private String token;
    private Long createTime;
    private List<String> roles;
    private List<String> permissions;
}