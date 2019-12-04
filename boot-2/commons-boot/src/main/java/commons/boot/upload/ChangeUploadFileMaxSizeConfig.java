package commons.boot.upload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import javax.servlet.MultipartConfigElement;

@ConditionalOnBean(ChangeUploadFileMaxSize.class)
@Configuration
public class ChangeUploadFileMaxSizeConfig {

    @Autowired(required = false)
    ChangeUploadFileMaxSize changeUploadFileMaxSize;

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();

        long maxFileSize = changeUploadFileMaxSize.getMaxFileSize();
        long maxRequestSize = changeUploadFileMaxSize.getMaxRequestSize();

        factory.setMaxFileSize(DataSize.ofMegabytes(maxFileSize));
        factory.setMaxRequestSize(DataSize.ofMegabytes(maxRequestSize));
        return factory.createMultipartConfig();
    }
}
