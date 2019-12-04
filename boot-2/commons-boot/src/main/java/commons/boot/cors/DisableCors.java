package commons.boot.cors;

import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
public class DisableCors implements Serializable {
    private List<String> exposedHeader;
}
