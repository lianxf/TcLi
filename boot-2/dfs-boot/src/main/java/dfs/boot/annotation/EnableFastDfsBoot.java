package dfs.boot.annotation;

import dfs.boot.FastDfsAutoConfig;
import org.springframework.context.annotation.Import;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(value = {FastDfsAutoConfig.class})
public @interface EnableFastDfsBoot {
}
