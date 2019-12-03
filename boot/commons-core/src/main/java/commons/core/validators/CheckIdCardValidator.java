package commons.core.validators;

import commons.core.annotation.CheckIdCard;
import commons.core.utils.ValidateUtil;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckIdCardValidator  implements ConstraintValidator<CheckIdCard, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return ValidateUtil.isIdCardNum(value);
    }
}
