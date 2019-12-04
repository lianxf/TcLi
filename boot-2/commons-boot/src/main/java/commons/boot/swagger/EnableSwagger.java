package commons.boot.swagger;

import lombok.Data;
import java.util.List;

@Data
public class EnableSwagger {
    private String host = "127.0.0.1";
    private Integer port = 8080;

    private String title = "API文档";
    private String description = "API文档描述";
    private String version = "API文档版本";
    private String termsOfServiceUrl = "项目组地址";
    private String contactName = "负责人";
    private String contactEmail = "负责人邮箱";
    private String contactUrl = "负责人线上URL";
    private Header header;
}
