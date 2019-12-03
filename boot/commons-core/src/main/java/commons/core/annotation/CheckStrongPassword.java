package commons.core.annotation;

import commons.core.validators.CheckStrongPasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckStrongPasswordValidator.class)
@Documented
public @interface CheckStrongPassword {
    String minLength() default "3";

    String maxLength() default "18";

    String message() default "密码格式不对";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
