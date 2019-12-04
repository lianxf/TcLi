package cn.likepeng.commons.core.validators;

import cn.likepeng.commons.core.annotation.CheckMobile;
import cn.likepeng.commons.core.utils.ValidateUtil;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckMobileValidator implements ConstraintValidator<CheckMobile, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return ValidateUtil.isMobile(value);
    }
}
