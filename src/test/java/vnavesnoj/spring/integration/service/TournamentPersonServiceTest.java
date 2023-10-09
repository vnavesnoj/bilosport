package vnavesnoj.spring.integration.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Pageable;
import vnavesnoj.spring.database.entity.MemberRole;
import vnavesnoj.spring.database.entity.TournamentPersonStatus;
import vnavesnoj.spring.database.entity.TournamentStatus;
import vnavesnoj.spring.database.repository.TournamentPersonRepository;
import vnavesnoj.spring.dto.SportReadDto;
import vnavesnoj.spring.dto.person.PersonShortReadDto;
import vnavesnoj.spring.dto.tournament.TournamentReadDto;
import vnavesnoj.spring.dto.tournament.tournamentperson.*;
import vnavesnoj.spring.integration.IntegrationTestBase;
import vnavesnoj.spring.service.TournamentPersonService;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@RequiredArgsConstructor
class TournamentPersonServiceTest extends IntegrationTestBase {

    private final TournamentPersonService tournamentPersonService;

    private final TournamentPersonRepository tournamentPersonRepository;

    private final static SportReadDto FOOTBALL_SPORT_DTO = new SportReadDto(1, "футбол");

    private final static TournamentReadDto TOURNAMENT_1_DTO = new TournamentReadDto(
            1L,
            "Великий футбол",
            FOOTBALL_SPORT_DTO,
            LocalDate.of(2023, 8, 1),
            TournamentStatus.FINISHED
    );

    private static final PersonShortReadDto SHEVCHENKO_DTO = new PersonShortReadDto(
            5L,
            "Іван",
            "Шевченко",
            "shevchenko"
    );

    private static final PersonShortReadDto TIMOHA_DTO = new PersonShortReadDto(
            11L,
            "Юлія",
            "Тимошенко",
            "Timoha"
    );

    private static final ResultReadDto RESULT_1 = new ResultReadDto(
            1L,
            1,
            2
    );

    private static final TournamentPersonReadDto TOURNAMENT_PERSON_2_READ_DTO = new TournamentPersonReadDto(
            2L,
            TOURNAMENT_1_DTO,
            SHEVCHENKO_DTO,
            MemberRole.PARTICIPANT,
            TournamentPersonStatus.REGISTERED,
            RESULT_1
    );

    @Test
    void findExistedById() {
        final var actual = tournamentPersonService.findById(2L);
        assertThat(actual).isPresent().contains(TOURNAMENT_PERSON_2_READ_DTO);
    }

    @Test
    void notFindNotExistedById() {
        final var actual = tournamentPersonService.findById(20L);
        assertThat(actual).isEmpty();
    }

    @Test
    void findAllPersonsByTournament() {
        final var actual = tournamentPersonService.findAllByTournament(1L, Pageable.unpaged());
        assertThat(actual).hasSize(7).contains(TOURNAMENT_PERSON_2_READ_DTO);
    }

    @Test
    void findNonePersonsByNotExistedTournament() {
        final var actual = tournamentPersonService.findAllByTournament(20L, Pageable.unpaged());
        assertThat(actual).isEmpty();
    }

    @Test
    void findAllTournamentsByPerson() {
        final var actual = tournamentPersonService.findAllByPerson(5L, Pageable.unpaged());
        assertThat(actual).hasSize(1).contains(TOURNAMENT_PERSON_2_READ_DTO);
    }

    @Test
    void findNoneTournamentsByNotExistedPerson() {
        final var actual = tournamentPersonService.findAllByPerson(20L, Pageable.unpaged());
        assertThat(actual).isEmpty();
    }

    @Test
    void create() {
        final var tournamentPersonCreateDto = new TournamentPersonCreateDto(
                1L,
                11L,
                MemberRole.PARTICIPANT,
                TournamentPersonStatus.REGISTERED
        );
        final var actual = tournamentPersonService.create(tournamentPersonCreateDto);
        final var expected = new TournamentPersonReadDto(
                11L,
                TOURNAMENT_1_DTO,
                TIMOHA_DTO,
                MemberRole.PARTICIPANT,
                TournamentPersonStatus.REGISTERED,
                null
        );
        assertThat(actual).isEqualTo(expected);
        assertThat(tournamentPersonRepository.findById(11L)).isPresent();
    }

    @Test
    void update() {
        final var tournamentPersonEditDto = new TournamentPersonEditDto(
                MemberRole.PARTICIPANT,
                TournamentPersonStatus.TOOK_PART,
                new ResultEditDto(2, 2)
        );
        final var actual = tournamentPersonService.update(2L, tournamentPersonEditDto);
        final var expected = new TournamentPersonReadDto(
                2L,
                TOURNAMENT_1_DTO,
                SHEVCHENKO_DTO,
                MemberRole.PARTICIPANT,
                TournamentPersonStatus.TOOK_PART,
                new ResultReadDto(1L, 2, 2)
        );
        assertThat(actual).isPresent().contains(expected);
        assertThat(tournamentPersonRepository.findById(2L).orElseThrow().getStatus()).isEqualTo(TournamentPersonStatus.TOOK_PART);
    }

    @Test
    void delete() {
        final var actual = tournamentPersonService.delete(2L);
        assertThat(actual).isTrue();
        assertThat(tournamentPersonRepository.count()).isEqualTo(9L);
        assertThat(tournamentPersonRepository.findById(2L)).isEmpty();
    }

    @Test
    void falseWhenDeleteNotExisted() {
        final var actual = tournamentPersonService.delete(20L);
        assertThat(actual).isFalse();
        assertThat(tournamentPersonRepository.count()).isEqualTo(10L);
    }
}