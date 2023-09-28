package vnavesnoj.spring.validation.impl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import vnavesnoj.spring.database.repository.TournamentPersonRepository;
import vnavesnoj.spring.validation.PersonTournamentFree;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@RequiredArgsConstructor
public class PersonTournamentFreeConstraintValidator implements ConstraintValidator<PersonTournamentFree, Long> {

    private final TournamentPersonRepository tournamentPersonRepository;

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext context) {
        return id == null || !tournamentPersonRepository.existsByPersonId(id);
    }
}
