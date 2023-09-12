package sefakpsz.allInOnce.utils.validations.MyNotBlank;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import sefakpsz.allInOnce.utils.validations.MyNotBlank.MyNotBlank;

public class MyNotBlankValidator implements ConstraintValidator<MyNotBlank, String> {

    @Override
    public void initialize(MyNotBlank constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && !value.trim().isEmpty();
    }
}