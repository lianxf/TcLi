package commons.core.validators;

import commons.core.annotation.CheckNormalPassword;
import commons.core.utils.ValidateUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckNormalPasswordValidator implements ConstraintValidator<CheckNormalPassword, String> {

    private CheckNormalPassword normalPassword;

    @Override
    public void initialize(CheckNormalPassword constraintAnnotation) {
        this.normalPassword = constraintAnnotation;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        String minLength = normalPassword.minLength();
        String maxLength = normalPassword.maxLength();
        return ValidateUtil.normalPassword(value,minLength,maxLength);
    }
}
