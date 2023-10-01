package vnavesnoj.spring.integration.validation.impl;

import jakarta.validation.ConstraintValidator;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vnavesnoj.spring.database.repository.PersonRepository;
import vnavesnoj.spring.integration.IntegrationTestBase;
import vnavesnoj.spring.validation.UserFree;
import vnavesnoj.spring.validation.impl.UserFreeConstraintValidator;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@RequiredArgsConstructor
class UserFreeConstraintValidatorTest extends IntegrationTestBase {

    private ConstraintValidator<UserFree, Long> validator;

    private final PersonRepository personRepository;

    @BeforeEach
    void setUp() {
        validator = new UserFreeConstraintValidator(personRepository);
    }

    @Test
    void nullIsValid() {
        final var actual = validator.isValid(null, null);
        assertThat(actual).isTrue();
    }

    @Test
    void notExistedUserIsValid() {
        final var actual1 = validator.isValid(15L, null);
        final var actual2 = validator.isValid(20L, null);
        assertThat(actual1).isTrue();
        assertThat(actual2).isTrue();
    }

    @Test
    void isValid() {
        final var actual = validator.isValid(13L, null);
        assertThat(actual).isTrue();
    }

    @Test
    void isNotValid() {
        final var actual1 = validator.isValid(10L, null);
        final var actual2 = validator.isValid(3L, null);
        assertThat(actual1).isFalse();
        assertThat(actual2).isFalse();
    }
}