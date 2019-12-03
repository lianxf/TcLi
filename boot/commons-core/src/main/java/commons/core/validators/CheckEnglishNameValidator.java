package commons.core.validators;

import commons.core.annotation.CheckEnglishName;
import commons.core.utils.ValidateUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckEnglishNameValidator implements ConstraintValidator<CheckEnglishName, String> {

    private CheckEnglishName englishName;

    @Override
    public void initialize(CheckEnglishName constraintAnnotation) {
        this.englishName = constraintAnnotation;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        String minLength = englishName.minLength();
        String maxLength = englishName.maxLength();
        return ValidateUtil.englishNumberUnderlineName(value,minLength,maxLength);
    }
}
