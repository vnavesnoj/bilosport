package vnavesnoj.spring.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import vnavesnoj.spring.validation.impl.TournamentExistsConstraintValidator;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Constraint(validatedBy = TournamentExistsConstraintValidator.class)
@Target({FIELD, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface TournamentExist {

    String message() default "{tournament.exists}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
