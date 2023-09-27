package vnavesnoj.spring.integration;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.testcontainers.containers.PostgreSQLContainer;
import vnavesnoj.spring.integration.annotation.IT;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@IT
@Sql(value = {
        "classpath:sql/data.sql"
},
        config = @SqlConfig(encoding = "UTF8"))
public abstract class IntegrationTestBase {

    private static final PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres");

    @BeforeAll
    static void runContainer() {
        container.addEnv("LC_ALL", "en_US.utf8");
        container.start();
    }

    @DynamicPropertySource
    static void postgresProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
    }
}
