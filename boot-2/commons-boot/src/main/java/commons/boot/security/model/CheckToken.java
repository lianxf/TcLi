package commons.boot.security.model;

import commons.boot.enumeration.TokenStatus;
import lombok.Data;
import java.io.Serializable;

@Data
public class CheckToken implements Serializable {
    private String token;
    private TokenStatus tokenStatus;
}
