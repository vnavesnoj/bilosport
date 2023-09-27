package vnavesnoj.spring.integration.service;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
        final var filter = UserFilter.builder()
                .name("Во")
                .build();
        final var unpaged = PageRequest.of(0, 20, Sort.unsorted());
        final var page = userService.findAll(filter, unpaged);
        Assertions.assertThat(page.getTotalElements()).isEqualTo(2);
    }
}