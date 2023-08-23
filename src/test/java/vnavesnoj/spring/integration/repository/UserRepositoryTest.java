package vnavesnoj.spring.integration.repository;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import vnavesnoj.spring.database.repository.UserRepository;
import vnavesnoj.spring.integration.IntegrationTestBase;
import vnavesnoj.spring.integration.annotation.IT;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@IT
@RequiredArgsConstructor
public class UserRepositoryTest extends IntegrationTestBase {

    private final UserRepository userRepository;

    @Test
    void findAll() {
        final var users = userRepository.findAll();
        assertThat(users).hasSize(0);
    }
}
