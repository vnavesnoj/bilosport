package vnavesnoj.spring.integration.validation.impl;

import jakarta.validation.ConstraintValidator;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vnavesnoj.spring.database.repository.SportRepository;
import vnavesnoj.spring.integration.IntegrationTestBase;
import vnavesnoj.spring.validation.SportExists;
import vnavesnoj.spring.validation.impl.SportExistsConstraintValidator;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@RequiredArgsConstructor
class SportExistsConstraintValidatorTest extends IntegrationTestBase {

    private ConstraintValidator<SportExists, List<Integer>> validator;

    private final SportRepository sportRepository;

    @BeforeEach
    void setUp() {
        validator = new SportExistsConstraintValidator(sportRepository);
    }

    @Test
    void nullIsValid() {
        final var actual = validator.isValid(null, null);
        assertThat(actual).isTrue();
    }

    @Test
    void emptyIsValid() {
        final var actual = validator.isValid(Collections.emptyList(), null);
        assertThat(actual).isTrue();
    }

    @Test
    void existedSportIsValid() {
        final var actual1 = validator.isValid(List.of(1), null);
        final var actual2 = validator.isValid(List.of(2), null);
        assertThat(actual1).isTrue();
        assertThat(actual2).isTrue();
    }

    @Test
    void existedSportsAreValid() {
        final var actual1 = validator.isValid(List.of(1, 3), null);
        final var actual2 = validator.isValid(List.of(3, 2), null);
        assertThat(actual1).isTrue();
        assertThat(actual2).isTrue();
    }

    @Test
    void notExistedSportIsValid() {
        final var actual1 = validator.isValid(List.of(4), null);
        final var actual2 = validator.isValid(List.of(0), null);
        assertThat(actual1).isFalse();
        assertThat(actual2).isFalse();
    }

    @Test
    void notExistedSportsAreValid() {
        final var actual1 = validator.isValid(List.of(4, 3), null);
        final var actual2 = validator.isValid(List.of(5, 6), null);
        assertThat(actual1).isFalse();
        assertThat(actual2).isFalse();
    }
}