package vnavesnoj.spring.integration.validation.impl;

import jakarta.validation.ConstraintValidator;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vnavesnoj.spring.database.repository.UserRepository;
import vnavesnoj.spring.integration.IntegrationTestBase;
import vnavesnoj.spring.validation.UniqueEmail;
import vnavesnoj.spring.validation.impl.UniqueEmailConstraintValidator;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@RequiredArgsConstructor
class UniqueEmailConstraintValidatorTest extends IntegrationTestBase {

    private ConstraintValidator<UniqueEmail, String> validator;

    private final UserRepository userRepository;

    @BeforeEach
    void setUp() {
        validator = new UniqueEmailConstraintValidator(userRepository);
    }

    @Test
    void nullIsValid() {
        final var actual = validator.isValid(null, null);
        assertThat(actual).isTrue();
    }

    @Test
    void blankIsValid() {
        final var actual = validator.isValid("", null);
        assertThat(actual).isTrue();
    }

    @Test
    void isValid() {
        final var actual1 = validator.isValid("newemail@gmail.com", null);
        final var actual2 = validator.isValid("  newemail2@gmail.com", null);
        assertThat(actual1).isTrue();
        assertThat(actual2).isTrue();
    }

    @Test
    void isNotValid() {
        final var actual1 = validator.isValid("IvAn@gmail.com", null);
        final var actual2 = validator.isValid("volk@gmail.com ", null);
        assertThat(actual1).isFalse();
        assertThat(actual2).isFalse();
    }
}