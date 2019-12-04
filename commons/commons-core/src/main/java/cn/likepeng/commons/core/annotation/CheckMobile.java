package cn.likepeng.commons.core.annotation;

import cn.likepeng.commons.core.validators.CheckMobileValidator;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckMobileValidator.class)
@Documented
public @interface CheckMobile {
    String message() default "手机号格式不对";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
