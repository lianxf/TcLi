package commons.core.jwt;

import lombok.Data;

import java.io.Serializable;

@Data
public class Header implements Serializable {
    private String alg = "HS256";
    private String typ = "JWT";
}
