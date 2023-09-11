package vnavesnoj.spring.validation.impl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vnavesnoj.spring.service.UserService;
import vnavesnoj.spring.validation.UniqueUsername;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Component
@RequiredArgsConstructor
public class UniqueUsernameConstraintValidator implements ConstraintValidator<UniqueUsername, String> {

    private final UserService userService;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        return userService.usernameUnique(username);
    }
}
