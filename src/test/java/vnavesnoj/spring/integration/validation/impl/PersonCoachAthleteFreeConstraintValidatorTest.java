package vnavesnoj.spring.integration.validation.impl;

import jakarta.validation.ConstraintValidator;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vnavesnoj.spring.database.repository.CoachAthleteRepository;
import vnavesnoj.spring.integration.IntegrationTestBase;
import vnavesnoj.spring.validation.PersonCoachAthleteFree;
import vnavesnoj.spring.validation.impl.PersonCoachAthleteFreeConstraintValidator;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@RequiredArgsConstructor
class PersonCoachAthleteFreeConstraintValidatorTest extends IntegrationTestBase {

    private ConstraintValidator<PersonCoachAthleteFree, Long> validator;

    private final CoachAthleteRepository coachAthleteRepository;

    @BeforeEach
    void setUp() {
        validator = new PersonCoachAthleteFreeConstraintValidator(coachAthleteRepository);
    }

    @Test
    void nullIsValid() {
        final var actual = validator.isValid(null, null);
        assertThat(actual).isTrue();
    }

    @Test
    void notExistedPersonIsValid() {
        final var actual1 = validator.isValid(20L, null);
        final var actual2 = validator.isValid(15L, null);
        assertThat(actual1).isTrue();
        assertThat(actual2).isTrue();
    }

    @Test
    void isValid() {
        final var actual1 = validator.isValid(10L, null);
        final var actual2 = validator.isValid(11L, null);
        assertThat(actual1).isTrue();
        assertThat(actual2).isTrue();
    }

    @Test
    void isNotValid() {
        final var actual1 = validator.isValid(1L, null);
        final var actual2 = validator.isValid(7L, null);
        assertThat(actual1).isFalse();
        assertThat(actual2).isFalse();
    }
}