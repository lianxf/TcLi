package commons.boot.swagger;

import cn.likepeng.commons.core.utils.ValidateUtil;
import com.fasterxml.classmate.TypeResolver;
import com.google.common.base.Predicates;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import java.util.Collections;

@Configuration
@ConditionalOnBean(EnableSwagger.class)
@SuppressWarnings("all")
public class Swagger2SpringBootConfig {

    @Autowired(required = false)
    private TypeResolver typeResolver;

    @Autowired(required = false)
    private EnableSwagger enableSwagger;


    @Bean
    public Docket petApi() {
        String host = enableSwagger.getHost();
        Integer port = enableSwagger.getPort();
        Header header = enableSwagger.getHeader();
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .enable(true)
                .apiInfo(apiInfo());
        if (!ValidateUtil.isEmpty(header)){
            Parameter parameter = new ParameterBuilder()
                    .name(header.getHead())
                    .description(header.getHeadDesc())
                    .modelRef(new ModelRef("string"))
                    .parameterType("header")
                    .required(false)
                    .build();
            docket.globalOperationParameters(Collections.singletonList(parameter));
        }

        docket.host(host + ":" + port)
        .select()
        .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
        .paths(Predicates.alwaysTrue())
        .build();
        return docket.useDefaultResponseMessages(false);
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact(enableSwagger.getContactName(),enableSwagger.getContactUrl(),enableSwagger.getContactEmail());
        return new ApiInfoBuilder()
                .title(enableSwagger.getTitle())
                .description(enableSwagger.getDescription())
                .version(enableSwagger.getVersion())
                .contact(contact)
                .build();
    }
}
