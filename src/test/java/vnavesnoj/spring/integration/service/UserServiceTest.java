package vnavesnoj.spring.integration.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import vnavesnoj.spring.database.entity.Sport;
import vnavesnoj.spring.database.entity.Tournament;
import vnavesnoj.spring.dto.UserCreateEditDto;
import vnavesnoj.spring.dto.UserReadDto;
import vnavesnoj.spring.integration.IntegrationTestBase;
import vnavesnoj.spring.service.UserService;

import java.time.LocalDate;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static vnavesnoj.spring.database.entity.Role.ATHLETE;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@RequiredArgsConstructor
class UserServiceTest extends IntegrationTestBase {

    private final UserService userService;
    private static final UserCreateEditDto newUser = new UserCreateEditDto(
            "potter@gmail.com",
            null,
            "Гаррі",
            "Поттер",
            null,
            LocalDate.of(1997, 7, 7),
            ATHLETE,
            Set.of(Sport.builder()
                    .name("шахи")
                    .build()),
            Set.of(Tournament.builder()
                    .name("Шахи над шахами")
                    .build()),
            null
    );

    @Test
    void create() {
        final var actual = userService.create(newUser);
        assertThat(actual).isEqualTo(new UserReadDto(
                13L,
                "potter@gmail.com",
                "Гаррі",
                "Поттер",
                null,
                LocalDate.of(1997, 7, 7),
                ATHLETE,
                Set.of(Sport.builder()
                        .name("шахи")
                        .build()),
                Set.of(Tournament.builder()
                        .name("Шахи над шахами")
                        .build()),
                null
        ));
        final var maybeUser = userService.findById(actual.getId());
        assertThat(maybeUser).isPresent();
        maybeUser.ifPresent(user -> assertThat(actual).isEqualTo(user));
    }
}