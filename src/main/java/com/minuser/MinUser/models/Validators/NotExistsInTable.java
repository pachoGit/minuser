package com.minuser.MinUser.models.Validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * ExistsInTable
 */
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotExistsInTableValidator.class)
public @interface NotExistsInTable
{
    String message() default "The register already exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    // References to the table
    Class<?> entityClass();

    // References to column of the table
    String column();
}
