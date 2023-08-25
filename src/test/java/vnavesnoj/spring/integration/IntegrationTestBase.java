package vnavesnoj.spring.integration;

import org.springframework.test.context.jdbc.Sql;
import vnavesnoj.spring.integration.annotation.IT;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@IT
@Sql({
        "classpath:sql/setval.sql"
})
public abstract class IntegrationTestBase {
}
