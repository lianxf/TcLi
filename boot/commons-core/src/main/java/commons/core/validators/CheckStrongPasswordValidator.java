package commons.core.validators;

import commons.core.annotation.CheckStrongPassword;
import commons.core.utils.ValidateUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckStrongPasswordValidator implements ConstraintValidator<CheckStrongPassword, String> {

    private CheckStrongPassword strongPassword;

    @Override
    public void initialize(CheckStrongPassword constraintAnnotation) {
        this.strongPassword = constraintAnnotation;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        String minLength = strongPassword.minLength();
        String maxLength = strongPassword.maxLength();
        return ValidateUtil.strongPassword(value,minLength,maxLength);
    }
}
