/*
 */
package com.airhacks.ping.boundary;

import javax.json.JsonString;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author airhacks.com
 */
public class JsonValidator implements ConstraintValidator<ValidJson, javax.json.JsonObject> {

    private ValidJson annotation;

    @Override
    public void initialize(ValidJson constraintAnnotation) {
        this.annotation = constraintAnnotation;

    }

    @Override
    public boolean isValid(javax.json.JsonObject value, ConstraintValidatorContext context) {
        System.out.println("value = " + value);
        System.out.println("annotation= " + this.annotation);
        JsonString jsonString = value.getJsonString(this.annotation.key());
        if (jsonString == null) {
            return false;
        }

        if (!jsonString.getString().contains("java")) {
            return false;
        }
        return true;
    }
}
