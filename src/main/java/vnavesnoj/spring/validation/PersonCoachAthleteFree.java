package vnavesnoj.spring.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import vnavesnoj.spring.validation.impl.PersonCoachAthleteFreeConstraintValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Constraint(validatedBy = PersonCoachAthleteFreeConstraintValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PersonCoachAthleteFree {

    String message() default "{person.coachAthleteFree}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
