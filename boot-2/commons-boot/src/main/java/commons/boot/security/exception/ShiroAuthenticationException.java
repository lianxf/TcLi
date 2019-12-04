package commons.boot.security.exception;

import lombok.Getter;
import org.apache.shiro.authc.AuthenticationException;

@Getter
public class ShiroAuthenticationException extends AuthenticationException {
    private Integer code;

    public ShiroAuthenticationException(String message, Integer code) {
        super(message);
        this.code = code;
    }
}
