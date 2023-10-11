package vnavesnoj.spring.validation.impl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import vnavesnoj.spring.database.repository.TournamentRepository;
import vnavesnoj.spring.validation.TournamentExist;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@RequiredArgsConstructor
public class TournamentExistsConstraintValidator implements ConstraintValidator<TournamentExist, Long> {

    private final TournamentRepository tournamentRepository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return value == null || tournamentRepository.existsById(value);
    }
}
