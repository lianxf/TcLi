package cn.likepeng.commons.core.validators;

import cn.likepeng.commons.core.annotation.IsMoney;
import cn.likepeng.commons.core.utils.ValidateUtil;
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
