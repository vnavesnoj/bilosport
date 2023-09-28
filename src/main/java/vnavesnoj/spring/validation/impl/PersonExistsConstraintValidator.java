package vnavesnoj.spring.validation.impl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import vnavesnoj.spring.database.repository.PersonRepository;
import vnavesnoj.spring.validation.PersonExists;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@RequiredArgsConstructor
public class PersonExistsConstraintValidator implements ConstraintValidator<PersonExists, Long> {

    private final PersonRepository personRepository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return value == null || personRepository.existsById(value);
    }
}
