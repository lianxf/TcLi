package commons.core.annotation;

import commons.core.validators.IsMoneyValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsMoneyValidator.class)
@Documented
public @interface IsMoney {

    String mantissaLength() default "2";

    String message() default "不是钱的格式";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
