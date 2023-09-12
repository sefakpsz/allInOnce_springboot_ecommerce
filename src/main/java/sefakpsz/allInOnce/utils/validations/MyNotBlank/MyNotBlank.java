package sefakpsz.allInOnce.utils.validations.MyNotBlank;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MyNotBlankValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyNotBlank {
    String message() default "field has to be filled!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}