package vnavesnoj.spring.integration.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
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

    private static final TournamentCreateEditDto NEW_TOURNAMENT = new TournamentCreateEditDto(
            "Король пінг-понгу",
            2,
            LocalDate.now()
    );

    @Test
    void create() {
        final var actual = tournamentService.create(NEW_TOURNAMENT);
        final var maybeTournament = tournamentService.findById(actual.getId());
        assertThat(maybeTournament).isPresent();
        maybeTournament.ifPresent(tournament -> assertThat(actual).isEqualTo(tournament));
    }
}