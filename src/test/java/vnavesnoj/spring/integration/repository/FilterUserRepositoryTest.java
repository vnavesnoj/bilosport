package vnavesnoj.spring.integration.repository;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import vnavesnoj.spring.database.repository.UserRepository;
import vnavesnoj.spring.integration.IntegrationTestBase;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@RequiredArgsConstructor
class FilterUserRepositoryTest extends IntegrationTestBase {

    private final UserRepository userRepository;

    @Test
    void findAllByFilter() {

    }
}