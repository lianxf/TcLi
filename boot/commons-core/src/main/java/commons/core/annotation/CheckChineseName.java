package commons.core.annotation;

import commons.core.validators.CheckChineseNameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckChineseNameValidator.class)
@Documented
public @interface CheckChineseName {
    String minLength() default "1";

    String maxLength() default "18";

    String message() default "用户名格式不对";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
