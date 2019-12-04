package commons.boot.annotation;

import commons.boot.CommonsBootAutoConfig;
import org.springframework.context.annotation.Import;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(value = {CommonsBootAutoConfig.class})
public @interface EnableCommonsBoot {
}
