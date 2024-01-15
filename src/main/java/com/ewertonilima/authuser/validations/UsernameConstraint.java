package com.ewertonilima.authuser.validations;

import com.ewertonilima.authuser.validations.impl.UsernameConstraintImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = UsernameConstraintImpl.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UsernameConstraint {
    String message() default "Invalid username";
    Class<?>[] groups() default  {};
    Class<? extends Payload>[] payload() default {};
}
