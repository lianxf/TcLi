package commons.core.jwt;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class Payload implements Serializable {
    private String jti;
    private String iss;  //公司
    private String userId;
    private List<String> roles;
    private String sub;  //用户名
    private Date   iat;  //颁发时间
    private Date   exp;  //到期时间
    private Date   nbf;  //生效时间
}
