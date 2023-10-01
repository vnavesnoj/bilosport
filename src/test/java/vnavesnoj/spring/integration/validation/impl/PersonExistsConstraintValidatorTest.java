package vnavesnoj.spring.integration.validation.impl;

import jakarta.validation.ConstraintValidator;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vnavesnoj.spring.database.repository.PersonRepository;
import vnavesnoj.spring.integration.IntegrationTestBase;
import vnavesnoj.spring.validation.PersonExists;
import vnavesnoj.spring.validation.impl.PersonExistsConstraintValidator;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@RequiredArgsConstructor
class PersonExistsConstraintValidatorTest extends IntegrationTestBase {

    private ConstraintValidator<PersonExists, Long> validator;

    private final PersonRepository personRepository;

    @BeforeEach
    void setUp() {
        validator = new PersonExistsConstraintValidator(personRepository);
    }

    @Test
    void nullIsValid() {
        final var actual = validator.isValid(null, null);
        assertThat(actual).isTrue();
    }

    @Test
    void notExistedPersonIsNotValid() {
        final var actual1 = validator.isValid(20L, null);
        final var actual2 = validator.isValid(15L, null);
        assertThat(actual1).isFalse();
        assertThat(actual2).isFalse();
    }

    @Test
    void existedPersonIsValid() {
        final var actual1 = validator.isValid(3L, null);
        final var actual2 = validator.isValid(11L, null);
        assertThat(actual1).isTrue();
        assertThat(actual2).isTrue();
    }
}