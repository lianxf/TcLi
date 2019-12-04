package cn.likepeng.commons.core.validators;

import cn.likepeng.commons.core.annotation.CheckEmail;
import cn.likepeng.commons.core.utils.ValidateUtil;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckEmailValidator implements ConstraintValidator<CheckEmail, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        return ValidateUtil.isEmail(value);
    }
}
