package vnavesnoj.spring.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import vnavesnoj.spring.validation.impl.UniqueUsernameConstraintValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Constraint(validatedBy = UniqueUsernameConstraintValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueUsername {

    String message() default "Username already exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
