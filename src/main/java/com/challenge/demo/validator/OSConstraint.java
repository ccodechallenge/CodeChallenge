package com.challenge.demo.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = OSValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface OSConstraint {

    String message() default "Unknown operation system.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
