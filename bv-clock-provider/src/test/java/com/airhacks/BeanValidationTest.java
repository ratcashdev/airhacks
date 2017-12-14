/*
 */
package com.airhacks;

import java.time.Clock;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Positive;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author airhacks.com
 */
public class BeanValidationTest {

    private Validator validator;

    @Before
    public void init() {
        ValidatorFactory factory = Validation
                .byDefaultProvider()
                .configure()
                .clockProvider(this::configureClockWithFutureTime)
                .buildValidatorFactory();
        this.validator = factory.getValidator();
    }

    Clock configureClockWithFutureTime() {
        return Clock.offset(Clock.systemDefaultZone(), Duration.ofSeconds(10));
    }

    @Test
    public void vacationsAreOver() {
        Set<ConstraintViolation<Vacations>> violations = this.validator.validate(new Vacations());
        assertTrue(violations.isEmpty());
    }

    @Test
    public void weAreWorkingNow() {
        Set<ConstraintViolation<Development>> violations = this.validator.validate(new Development());
        boolean rightMessage = violations.stream().
                map(violation -> violation.getMessage()).
                allMatch(message -> "must be a future date".equalsIgnoreCase(message));
        assertTrue(rightMessage);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void list() {
        List<@Positive Integer> list = new ArrayList<>();
        list.add(-1);

        Set<ConstraintViolation<List<Integer>>> constraints = this.validator.validate(list);
        System.out.println("constraints = " + constraints);
    }


}
