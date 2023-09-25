package vnavesnoj.spring.integration.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import vnavesnoj.spring.database.entity.MemberRole;
import vnavesnoj.spring.database.repository.TournamentPersonRepository;
import vnavesnoj.spring.dto.TournamentCreateEditDto;
import vnavesnoj.spring.integration.IntegrationTestBase;
import vnavesnoj.spring.service.TournamentService;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@RequiredArgsConstructor
class TournamentServiceTest extends IntegrationTestBase {

    private final TournamentService tournamentService;
    private final TournamentPersonRepository tournamentPersonRepository;

    private static final TournamentCreateEditDto NEW_TOURNAMENT = new TournamentCreateEditDto(
            "Король пінг-понгу",
            2,
            LocalDate.now()
    );

    @SneakyThrows
    @Test
    void tryAddPerson() {
        tournamentService.tryAddPerson(1L, 2L, MemberRole.JUDGE);
        assertThat(tournamentPersonRepository.findById(1L)).isPresent().map(
                entity -> {
                    assertThat(entity.getTournament().getId()).isEqualTo(1L);
                    assertThat(entity.getPerson().getId()).isEqualTo(2L);
                    return entity;
                }
        );
    }

    @SneakyThrows
    @Test
    void tryAddPerson2() {
        tournamentService.tryAddPerson(1L, 2L, MemberRole.JUDGE);
        assertThat(tournamentPersonRepository.findById(1L)).isPresent().map(
                entity -> {
                    assertThat(entity.getTournament().getId()).isEqualTo(1L);
                    assertThat(entity.getPerson().getId()).isEqualTo(2L);
                    return entity;
                }
        );
    }
}