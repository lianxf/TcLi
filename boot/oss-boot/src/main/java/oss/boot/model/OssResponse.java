package oss.boot.model;

import lombok.Data;
import java.io.Serializable;

@Data
public class OssResponse implements Serializable {
    private Boolean isSuccess = true;
    private String  url;
    private String  msg = "ok";
}
