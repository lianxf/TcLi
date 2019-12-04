package commons.boot.upload;

import lombok.Data;
import java.io.Serializable;

@Data
public class ChangeUploadFileMaxSize implements Serializable {
    private Long maxFileSize = 500L;
    private Long maxRequestSize = 500L;
}
