package cn.likepeng.commons.core.annotation;

import cn.likepeng.commons.core.validators.CheckIdCardValidator;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckIdCardValidator.class)
@Documented
public @interface CheckIdCard {
    String message() default "身份证格式不对";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
