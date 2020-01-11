package com.challenge.demo.configuration.exceptionhandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 * Created by nurisezgin on 11.01.2020.
 */
public class PropertyValidationConsumer implements ExceptionConsumer {

    @Override
    public boolean handle(Exception ex, StringBuilder builder) {
        if (ex instanceof ConstraintViolationException) {
            for (ConstraintViolation e: ((ConstraintViolationException) ex).getConstraintViolations()) {
                String message = e.getMessage();
                builder.append(message);
            }

            return true;
        }

        return false;
    }
}
