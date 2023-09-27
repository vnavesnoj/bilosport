package vnavesnoj.spring.validation.impl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import vnavesnoj.spring.service.UserService;
import vnavesnoj.spring.validation.UserExists;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@RequiredArgsConstructor
public class UserExistsConstraintValidator implements ConstraintValidator<UserExists, Long> {

    private final UserService userService;

    @Override
    public boolean isValid(Long userId, ConstraintValidatorContext context) {
        return userId == null || userService.findById(userId).isPresent();
    }
}
