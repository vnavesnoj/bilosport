package vnavesnoj.spring.validation.impl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import vnavesnoj.spring.database.repository.CoachAthleteRepository;
import vnavesnoj.spring.validation.PersonCoachAthleteFree;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@RequiredArgsConstructor
public class PersonCoachAthleteFreeConstraintValidator
        implements ConstraintValidator<PersonCoachAthleteFree, Long> {

    private final CoachAthleteRepository coachAthleteRepository;

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext context) {
        return id == null || !coachAthleteRepository.existsByCoachOrAthleteId(id);
    }
}
