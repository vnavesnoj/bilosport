package vnavesnoj.spring.integration.repository;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import vnavesnoj.spring.database.entity.User;
import vnavesnoj.spring.database.repository.UserRepository;
import vnavesnoj.spring.integration.IntegrationTestBase;
import vnavesnoj.spring.integration.annotation.IT;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static vnavesnoj.spring.database.entity.Role.ATHLETE;
import static vnavesnoj.spring.database.entity.Role.COACH;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@IT
@RequiredArgsConstructor
public class UserRepositoryTest extends IntegrationTestBase {

    private static final Long PETR_ID = 2L;
    private static final User PETR = User.builder()
            .id(2L)
            .username("petr@gmail.com")
            .firstname("Петро")
            .lastname("Петров")
            .birthDate(LocalDate.of(1972, 2, 1))
            .role(COACH)
            .build();

    private final UserRepository userRepository;

    @Test
    void findAll() {
        final var users = userRepository.findAll();
        assertThat(users).hasSize(12);
    }

    @Test
    void findAllByByFilterAndSort() {
//        final var list = userRepository.findAllByFilter(UserFilter.builder()
//                .sortBy("birthDate")
//                .order(Order.DESC)
//                .build());
//        assertThat(list).hasSize(12);
    }

    @Test
    void findById() {
        final var maybePetr = userRepository.findById(PETR_ID);
        assertThat(maybePetr).isPresent();
        maybePetr.ifPresent(user -> assertThat(user).isEqualTo(PETR));
    }

    @Test
    void findAllByRole() {
        final var users = userRepository.findAllByRole(ATHLETE);
        assertThat(users).hasSize(8);
    }
}
