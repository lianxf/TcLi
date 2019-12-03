package commons.boot.enable.upload;

import lombok.Data;
import java.io.Serializable;

@Data
public class ChangeMaxUploadFileSize implements Serializable {

    private Long maxFileSize = 500L;

    private Long maxRequestSize = 500L;
}
