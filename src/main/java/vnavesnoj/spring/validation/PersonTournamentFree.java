package vnavesnoj.spring.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import vnavesnoj.spring.validation.impl.PersonTournamentFreeConstraintValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Constraint(validatedBy = PersonTournamentFreeConstraintValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PersonTournamentFree {

    String message() default "{person.tournamentFree}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
