package cn.likepeng.commons.core.annotation;

import cn.likepeng.commons.core.validators.CheckNormalPasswordValidator;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckNormalPasswordValidator.class)
@Documented
public @interface CheckNormalPassword {
    String minLength() default "1";

    String maxLength() default "18";

    String message() default "密码格式不对";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
