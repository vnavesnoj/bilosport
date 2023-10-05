package vnavesnoj.spring.integration.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Pageable;
import vnavesnoj.spring.database.entity.TournamentStatus;
import vnavesnoj.spring.database.repository.TournamentRepository;
import vnavesnoj.spring.dto.SportReadDto;
import vnavesnoj.spring.dto.tournament.TournamentCreateEditDto;
import vnavesnoj.spring.dto.tournament.TournamentFilter;
import vnavesnoj.spring.dto.tournament.TournamentReadDto;
import vnavesnoj.spring.integration.IntegrationTestBase;
import vnavesnoj.spring.service.TournamentService;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatException;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@RequiredArgsConstructor
class TournamentServiceTest extends IntegrationTestBase {

    private final TournamentService tournamentService;

    private final TournamentRepository tournamentRepository;

    private final static SportReadDto FOOTBALL_SPORT_DTO = new SportReadDto(1, "футбол");

    private final static SportReadDto PING_PONG_SPORT_DTO = new SportReadDto(2, "теніс");

    private final static SportReadDto CHESS_SPORT_DTO = new SportReadDto(3, "шахи");


    private final static TournamentReadDto TOURNAMENT_1_DTO = new TournamentReadDto(
            1L,
            "Великий футбол",
            FOOTBALL_SPORT_DTO,
            LocalDate.of(2023, 8, 1),
            TournamentStatus.FINISHED
    );

    private final static TournamentReadDto TOURNAMENT_2_DTO = new TournamentReadDto(
            2L,
            "Шахи над шахами",
            CHESS_SPORT_DTO,
            LocalDate.of(2023, 11, 15),
            TournamentStatus.PREPARATION
    );


    @Test
    void findExistedById() {
        final var actual = tournamentService.findById(1L);
        assertThat(actual).isPresent().contains(TOURNAMENT_1_DTO);
    }

    @Test
    void notFindNotExistedById() {
        final var actual = tournamentService.findById(20L);
        assertThat(actual).isEmpty();
    }

    @Test
    void findAllWithEmptyFilterAndPageable() {
        final var actual = tournamentService.findAll(
                new TournamentFilter(null, null, null, null, null),
                Pageable.unpaged()
        );
        assertThat(actual).hasSize(2).containsExactly(
                TOURNAMENT_1_DTO,
                TOURNAMENT_2_DTO
        );
    }

    @Test
    void findOneByName() {
        final var actual = tournamentService.findAll(
                new TournamentFilter("шахи", null, null, null, null),
                Pageable.unpaged()
        );
        assertThat(actual).hasSize(1).containsExactly(TOURNAMENT_2_DTO);
    }

    @Test
    void findOneBySportId() {
        final var actual = tournamentService.findAll(
                new TournamentFilter(null, null, null, 3, null),
                Pageable.unpaged()
        );
        assertThat(actual).hasSize(1).containsExactly(TOURNAMENT_2_DTO);
    }

    @Test
    void findOneByStatus() {
        final var actual = tournamentService.findAll(
                new TournamentFilter(null, null, null, null, TournamentStatus.PREPARATION),
                Pageable.unpaged()
        );
        assertThat(actual).hasSize(1).containsExactly(TOURNAMENT_2_DTO);
    }

    @Test
    void findAllByDate() {
        final var actual = tournamentService.findAll(
                new TournamentFilter(
                        null,
                        LocalDate.of(2023, 8, 1),
                        LocalDate.of(2023, 11, 15),
                        null,
                        null
                ),
                Pageable.unpaged()
        );
        assertThat(actual).hasSize(2).containsExactly(TOURNAMENT_1_DTO, TOURNAMENT_2_DTO);
    }

    @Test
    void noneFindByFilter() {
        final var actual = tournamentService.findAll(
                new TournamentFilter(null, null, LocalDate.of(2023, 7, 1), null, null),
                Pageable.unpaged()
        );
        assertThat(actual).hasSize(0);
    }

    @Test
    void createNotExisted() {
        final var tournament = new TournamentCreateEditDto(
                "Теорія тенісу",
                2,
                LocalDate.of(2023, 12, 18),
                TournamentStatus.PREPARATION
        );
        final var actual = tournamentService.create(tournament);
        final var expected = new TournamentReadDto(
                3L,
                "Теорія тенісу",
                PING_PONG_SPORT_DTO,
                LocalDate.of(2023, 12, 18),
                TournamentStatus.PREPARATION
        );
        assertThat(actual).isEqualTo(expected);
        assertThat(tournamentRepository.count()).isEqualTo(3);
        final var maybeSaved = tournamentRepository.findById(3L);
        assertThat(maybeSaved).isPresent();
        final var saved = maybeSaved.orElseThrow();
        assertThat(tournament.getName()).isEqualTo(saved.getName());
        assertThat(tournament.getSportId()).isEqualTo(saved.getSport().getId());
        assertThat(tournament.getTournamentDate()).isEqualTo(saved.getTournamentDate());
        assertThat(tournament.getTournamentStatus()).isEqualTo(saved.getStatus());
    }

    @Test
    void exceptionWhenCreateWithNotExistedSport() {
        assertThatException().isThrownBy(
                () -> tournamentService.create(new TournamentCreateEditDto(
                        "Теорія тенісу",
                        20,
                        LocalDate.of(2023, 12, 18),
                        TournamentStatus.PREPARATION
                ))
        );
        assertThat(tournamentRepository.count()).isEqualTo(2);
    }

    @Test
    void updateNameAndStatus() {
        final var tournament = new TournamentCreateEditDto(
                "New name",
                1,
                LocalDate.of(2023, 8, 1),
                TournamentStatus.CANCELED
        );
        final var actual = tournamentService.update(1L, tournament);
        final var expected = new TournamentReadDto(
                1L,
                "New name",
                FOOTBALL_SPORT_DTO,
                LocalDate.of(2023, 8, 1),
                TournamentStatus.CANCELED
        );
        assertThat(actual).isPresent().contains(expected);
        final var maybeUpdated = tournamentRepository.findById(1L);
        assertThat(maybeUpdated).isPresent();
        final var updated = maybeUpdated.orElseThrow();
        assertThat(tournament.getName()).isEqualTo(updated.getName());
        assertThat(tournament.getSportId()).isEqualTo(updated.getSport().getId());
        assertThat(tournament.getTournamentDate()).isEqualTo(updated.getTournamentDate());
        assertThat(tournament.getTournamentStatus()).isEqualTo(updated.getStatus());
    }

    @Test
    void exceptionWhenUpdateNotExistedSport() {
        final var tournament = new TournamentCreateEditDto(
                "Великий футбол",
                20,
                LocalDate.of(2023, 8, 1),
                TournamentStatus.FINISHED
        );
        assertThatException().isThrownBy(
                () -> tournamentService.update(1L, tournament)
        );
    }

    @Test
    void emptyOptionalWhenUpdateNotExistedTournament() {
        final var tournament = new TournamentCreateEditDto(
                "New name",
                1,
                LocalDate.of(2023, 8, 1),
                TournamentStatus.CANCELED
        );
        final var actual = tournamentService.update(20L, tournament);
        assertThat(actual).isEmpty();
    }

    @Test
    void deleteExisted() {
        final var actual = tournamentService.delete(1L);
        assertThat(actual).isTrue();
        assertThat(tournamentRepository.count()).isEqualTo(1L);
        assertThat(tournamentRepository.findById(1L)).isEmpty();
    }

    @Test
    void falseWhenDeleteNotExisted() {
        final var actual = tournamentService.delete(20L);
        assertThat(actual).isFalse();
        assertThat(tournamentRepository.count()).isEqualTo(2L);
    }
}