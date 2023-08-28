package vnavesnoj.spring.integration.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import vnavesnoj.spring.dto.UserCreateEditDto;
import vnavesnoj.spring.integration.IntegrationTestBase;
import vnavesnoj.spring.service.UserService;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static vnavesnoj.spring.database.entity.Role.ATHLETE;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@RequiredArgsConstructor
class UserServiceTest extends IntegrationTestBase {

    private final UserService userService;
    private static final UserCreateEditDto NEW_USER = new UserCreateEditDto(
            "potter@gmail.com",
            null,
            "Гаррі",
            "Поттер",
            null,
            LocalDate.of(1997, 7, 7),
            ATHLETE,
            List.of(3),
            null
    );

    @Test
    void create() {
        final var actual = userService.create(NEW_USER);
        final var maybeUser = userService.findById(actual.getId());
        assertThat(maybeUser).isPresent();
        maybeUser.ifPresent(user -> assertThat(actual).isEqualTo(user));
    }
}