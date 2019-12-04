package cn.likepeng.commons.core.annotation;

import cn.likepeng.commons.core.validators.CheckEmailValidator;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckEmailValidator.class)
@Documented
public @interface CheckEmail {
    String message() default "邮箱格式不对";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
