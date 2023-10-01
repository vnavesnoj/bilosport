package vnavesnoj.spring.validation.impl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import vnavesnoj.spring.database.repository.SportRepository;
import vnavesnoj.spring.validation.SportExists;

import java.util.List;
import java.util.Set;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@RequiredArgsConstructor
public class SportExistsConstraintValidator implements ConstraintValidator<SportExists, List<Integer>> {

    private final SportRepository sportRepository;

    @Override
    public boolean isValid(List<Integer> sports, ConstraintValidatorContext context) {
        return sports == null ? true
                : (Set.copyOf(sports).stream().allMatch(sportRepository::existsById));
    }
}
