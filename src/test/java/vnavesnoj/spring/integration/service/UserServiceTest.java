package vnavesnoj.spring.integration.service;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Pageable;
import vnavesnoj.spring.dto.UserFilter;
import vnavesnoj.spring.integration.IntegrationTestBase;
import vnavesnoj.spring.service.UserService;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@RequiredArgsConstructor
class UserServiceTest extends IntegrationTestBase {

    private final UserService userService;

    @Test
    void findAll() {
        final var filter = UserFilter.builder().build();
        final var unpaged = Pageable.unpaged();
        final var page = userService.findAll(filter, unpaged);
        Assertions.assertThat(page).hasSize(12);
    }
}