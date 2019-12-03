package commons.core.validators;

import commons.core.annotation.CheckChineseName;
import commons.core.utils.ValidateUtil;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckChineseNameValidator implements ConstraintValidator<CheckChineseName, String> {

    private CheckChineseName chineseName;

    @Override
    public void initialize(CheckChineseName constraintAnnotation) {
        this.chineseName = constraintAnnotation;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        String minLength = chineseName.minLength();
        String maxLength = chineseName.maxLength();
        return ValidateUtil.chineseEnglishNumberUnderlineName(value,minLength,maxLength);
    }
}
