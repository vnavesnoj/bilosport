package vnavesnoj.spring.validation.impl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import vnavesnoj.spring.database.repository.PersonRepository;
import vnavesnoj.spring.validation.UserFree;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@RequiredArgsConstructor
public class UserFreeConstraintValidator implements ConstraintValidator<UserFree, Long> {

    private final PersonRepository personRepository;

    @Override
    public boolean isValid(Long userId, ConstraintValidatorContext context) {
        return userId == null || personRepository.findByUserId(userId).isEmpty();
    }
}
