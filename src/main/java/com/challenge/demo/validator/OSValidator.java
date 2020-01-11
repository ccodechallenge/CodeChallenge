package com.challenge.demo.validator;

import com.challenge.demo.Constants;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OSValidator implements ConstraintValidator<OSConstraint, String> {

    @Override
    public void initialize(OSConstraint constraintAnnotation) { }

    @Override
    public boolean isValid(String os, ConstraintValidatorContext constraintValidatorContext) {
        return os != null
                && !os.isEmpty()
                && (os.equals(Constants.OS_ANDROID) || os.equals(Constants.OS_IOS));
    }
}
