package vnavesnoj.spring.integration.validation.impl;

import jakarta.validation.ConstraintValidator;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vnavesnoj.spring.database.repository.UserRepository;
import vnavesnoj.spring.integration.IntegrationTestBase;
import vnavesnoj.spring.validation.UserExists;
import vnavesnoj.spring.validation.impl.UserExistsConstraintValidator;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@RequiredArgsConstructor
class UserExistsConstraintValidatorTest extends IntegrationTestBase {

    private ConstraintValidator<UserExists, Long> validator;

    private final UserRepository userRepository;

    @BeforeEach
    void setUp() {
        validator = new UserExistsConstraintValidator(userRepository);
    }

    @Test
    void nullIsValid() {
        final var actual = validator.isValid(null, null);
        assertThat(actual).isTrue();
    }

    @Test
    void existedPersonIsValid() {
        final var actual1 = validator.isValid(3L, null);
        final var actual2 = validator.isValid(13L, null);
        assertThat(actual1).isTrue();
        assertThat(actual2).isTrue();
    }

    @Test
    void notExistedUserIsNotValid() {
        final var actual1 = validator.isValid(20L, null);
        final var actual2 = validator.isValid(15L, null);
        assertThat(actual1).isFalse();
        assertThat(actual2).isFalse();
    }
}