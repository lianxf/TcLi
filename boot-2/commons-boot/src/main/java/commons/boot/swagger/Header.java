package commons.boot.swagger;

import lombok.Data;
import java.io.Serializable;

@Data
public class Header implements Serializable {
    private String head;
    private String headDesc;
}
