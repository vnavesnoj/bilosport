package vnavesnoj.spring.integration.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Rollback;
import vnavesnoj.spring.database.entity.MemberRole;
import vnavesnoj.spring.dto.TournamentCreateEditDto;
import vnavesnoj.spring.integration.IntegrationTestBase;
import vnavesnoj.spring.service.TournamentService;

import java.time.LocalDate;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@RequiredArgsConstructor
class TournamentServiceTest extends IntegrationTestBase {

    private final TournamentService tournamentService;

    private static final TournamentCreateEditDto NEW_TOURNAMENT = new TournamentCreateEditDto(
            "Король пінг-понгу",
            2,
            LocalDate.now()
    );

    @SneakyThrows
    @Rollback(value = false)
    @Test
    void tryAddPerson() {
        tournamentService.tryAddPerson(1L, 2L, MemberRole.REFEREE);
    }
}