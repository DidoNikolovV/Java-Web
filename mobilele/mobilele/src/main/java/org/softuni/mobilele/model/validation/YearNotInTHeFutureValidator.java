package org.softuni.mobilele.model.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.Year;

public class YearNotInTHeFutureValidator implements ConstraintValidator<YearNotInTheFuture, Number> {
    @Override
    public boolean isValid(Number value, ConstraintValidatorContext context) {

        if(value == null) {
            return true;
        } else {
            int currentYear = Year.now().getValue();
            return value.intValue() <= currentYear;
        }
    }
}
