package vnavesnoj.spring.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import vnavesnoj.spring.validation.impl.UserFreeConstraintValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
// Not existed user is valid
@Constraint(validatedBy = UserFreeConstraintValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UserFree {

    String message() default "{user.free}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
