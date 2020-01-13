package com.challenge.demo.configuration.exceptionhandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by nurisezgin on 11.01.2020.
 */
public final class PropertyValidationConsumer implements ExceptionConsumer {

    public static final String DELIMITER = " ";

    @Override
    public String tryToGetMessage(Exception ex) {
        if (ex instanceof ConstraintViolationException) {
            Set<ConstraintViolation<?>> violations = ((ConstraintViolationException) ex)
                    .getConstraintViolations();

            return violations.stream()
                    .map(v -> v.getMessage())
                    .collect(Collectors.joining(DELIMITER));
        }

        return null;
    }
}
