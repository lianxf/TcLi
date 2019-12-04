package cn.likepeng.commons.core.validators;

import cn.likepeng.commons.core.annotation.CheckIdCard;
import cn.likepeng.commons.core.utils.ValidateUtil;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckIdCardValidator  implements ConstraintValidator<CheckIdCard, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return ValidateUtil.isIdCardNum(value);
    }
}
