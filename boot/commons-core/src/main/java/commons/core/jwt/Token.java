package commons.core.jwt;

import lombok.Data;

import java.io.Serializable;

@Data
public class Token implements Serializable {
    private Header header;
    private Payload payload;
}
