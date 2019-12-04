package commons.boot.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface DistributedLock {
    String name() default "";
    String timeOut() default "20";
    TimeUnit timeUnit() default TimeUnit.SECONDS;
}
