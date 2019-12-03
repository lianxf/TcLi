package commons.core.validators;

import commons.core.annotation.IsMoney;
import commons.core.utils.ValidateUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsMoneyValidator implements ConstraintValidator<IsMoney, String> {

    private IsMoney isMoney;

    @Override
    public void initialize(IsMoney constraintAnnotation) {
        this.isMoney = constraintAnnotation;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        String mantissaLength = isMoney.mantissaLength();
        return ValidateUtil.isMoney(value,mantissaLength);
    }
}
