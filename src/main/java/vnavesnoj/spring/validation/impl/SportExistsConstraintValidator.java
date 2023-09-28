package vnavesnoj.spring.validation.impl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import vnavesnoj.spring.database.repository.SportRepository;
import vnavesnoj.spring.validation.SportExists;

import java.util.Objects;
import java.util.Set;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@RequiredArgsConstructor
public class SportExistsConstraintValidator implements ConstraintValidator<SportExists, Set<Integer>> {

    private final SportRepository sportRepository;

    @Override
    public boolean isValid(Set<Integer> sports, ConstraintValidatorContext context) {
        return sports == null ? true :
                sports.stream().noneMatch(Objects::isNull)
                        && (sports.stream().anyMatch(sportRepository::existsById));
    }
}
