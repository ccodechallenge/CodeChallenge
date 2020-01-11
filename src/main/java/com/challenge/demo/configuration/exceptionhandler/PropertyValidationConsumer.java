package com.challenge.demo.configuration.exceptionhandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by nurisezgin on 11.01.2020.
 */
public class PropertyValidationConsumer implements ExceptionConsumer {

    private static final String DELIMITER = "\n";

    @Override
    public boolean handle(Exception ex, StringBuilder builder) {
        if (ex instanceof ConstraintViolationException) {
            Set<ConstraintViolation<?>> violations = ((ConstraintViolationException) ex)
                    .getConstraintViolations();

            String message = violations.stream()
                    .map(v -> v.getMessage())
                    .collect(Collectors.joining(DELIMITER));

            builder.append(message);

            return true;
        }

        return false;
    }
}
