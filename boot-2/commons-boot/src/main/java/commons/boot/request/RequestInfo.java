package commons.boot.request;

import cn.likepeng.commons.core.utils.IdUtil;
import lombok.Data;
import java.io.Serializable;

@Data
public class RequestInfo implements Serializable {
    private String logId = IdUtil.simpleUUID();
    private String sessionId;
    private String userName;
    private String clientIp;
    private String clientIpProvince;
    private String clientIpCity;
    private String clientIpRectangle;
    private String path;
    private String requestMethod;
    private String method;
    private String params;
    private Object response;
    private String startTime;
    private String executionTime;
    private String clientBrowser;
    private String clientOperatingSystem;
}
