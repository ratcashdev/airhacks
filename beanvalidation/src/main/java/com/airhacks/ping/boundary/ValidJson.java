/*
 */
package com.airhacks.ping.boundary;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 *
 * @author airhacks.com
 */
@Documented
@Constraint(validatedBy = JsonValidator.class)
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidJson {

    String key();
    String expectedValue();

    String message() default "INVALID JSON -- no JAVA";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
