package commons.boot.annotation;

import commons.boot.CommonsAutoConfig;
import org.springframework.context.annotation.Import;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(value = {CommonsAutoConfig.class})
public @interface EnableCommonsBoot {
}
