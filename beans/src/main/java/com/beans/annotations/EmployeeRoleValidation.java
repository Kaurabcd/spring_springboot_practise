package com.beans.annotations;

import jakarta.servlet.annotation.HttpConstraint;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Constraint(validatedBy= EmailValidator.class)
public @interface EmployeeRoleValidation  {

    String message() default "ROle must be ADMIN or USERS";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
