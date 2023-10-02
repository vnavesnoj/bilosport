package vnavesnoj.spring.integration.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import vnavesnoj.spring.database.repository.CoachAthleteRepository;
import vnavesnoj.spring.dto.SportReadDto;
import vnavesnoj.spring.dto.person.coachathlete.AthleteReadDto;
import vnavesnoj.spring.dto.person.coachathlete.CoachAthleteCreateDto;
import vnavesnoj.spring.dto.person.coachathlete.CoachAthleteReadDto;
import vnavesnoj.spring.dto.person.coachathlete.CoachReadDto;
import vnavesnoj.spring.integration.IntegrationTestBase;
import vnavesnoj.spring.service.CoachAthleteService;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@RequiredArgsConstructor
class CoachAthleteServiceTest extends IntegrationTestBase {

    private final CoachAthleteService coachAthleteService;

    private final CoachAthleteRepository coachAthleteRepository;

    private static final CoachReadDto IVAN_COACH = new CoachReadDto(
            1L,
            "Іван",
            "Іванов",
            Set.of(new SportReadDto(1, "футбол")),
            "Ivan");

    private static final CoachReadDto PETR_COACH = new CoachReadDto(
            1L,
            "Петро",
            "Петров",
            Set.of(new SportReadDto(2, "теніс")),
            "Petr");

    private static final AthleteReadDto SKOVORODA_ATHLETE = new AthleteReadDto(
            6L,
            "Григорій",
            "Сковорода",
            "skovoroda"
    );

    private static final AthleteReadDto SHEVCHENKO_ATHLETE = new AthleteReadDto(
            5L,
            "Іван",
            "Шевченко",
            "shevchenko"
    );

    private static final AthleteReadDto MASK_ATHLETE = new AthleteReadDto(
            7L,
            "Ілон",
            "Маск",
            "Mask"
    );

    private static final AthleteReadDto ZELIA_ATHLETE = new AthleteReadDto(
            10L,
            "Володимир",
            "Зеленський",
            "Zelia"
    );

    @Test
    void findExistedById() {
        final var actual = coachAthleteService.findById(2L);
        final var expectedResult = new CoachAthleteReadDto(
                2L,
                IVAN_COACH,
                SKOVORODA_ATHLETE
        );
        assertThat(actual).isPresent().contains(expectedResult);
    }

    @Test
    void notFoundNotExistedById() {
        final var actual = coachAthleteService.findById(20L);
        assertThat(actual).isEmpty();
    }

    @Test
    void findAllAthletesByExistedCoachId() {
        final var actual = coachAthleteService.findAllAthletesByCoachId(1L);
        assertThat(actual).hasSize(3)
                .contains(SHEVCHENKO_ATHLETE, SKOVORODA_ATHLETE, MASK_ATHLETE);
    }

    @Test
    void findNoneAthletesByNotExistedCoachId() {
        final var actual = coachAthleteService.findAllAthletesByCoachId(5L);
        assertThat(actual).isEmpty();
    }

    @Test
    void findAllCoachesByExistedAthleteId() {
        final var actual = coachAthleteService.findAllCoachesByAthleteId(6L);
        assertThat(actual).hasSize(1).contains(IVAN_COACH);
    }

    @Test
    void findNoneCoachesByNotExistedAthleteId() {
        final var actual1 = coachAthleteService.findAllCoachesByAthleteId(1L);
        final var actual2 = coachAthleteService.findAllCoachesByAthleteId(10L);
        final var actual3 = coachAthleteService.findAllCoachesByAthleteId(20L);
        assertThat(actual1).isEmpty();
        assertThat(actual2).isEmpty();
        assertThat(actual3).isEmpty();
    }

    @Test
    void createNotExisted() {
        final var coachAthleteCreateDto1 = new CoachAthleteCreateDto(1L, 10L);
        final var coachAthleteCreateDto2 = new CoachAthleteCreateDto(2L, 5L);
        final var actual1 = coachAthleteService.create(coachAthleteCreateDto1);
        final var actual2 = coachAthleteService.create(coachAthleteCreateDto2);
        final var expected1 = new CoachAthleteReadDto(
                9L,
                IVAN_COACH,
                ZELIA_ATHLETE
        );
        final var expected2 = new CoachAthleteReadDto(
                10L,
                PETR_COACH,
                SHEVCHENKO_ATHLETE
        );
        assertThat(actual1).isEqualTo(expected1);
        assertThat(actual2).isEqualTo(expected2);
        assertThat(coachAthleteRepository.count()).isEqualTo(9);
        assertThat(coachAthleteRepository.existsById(9L)).isTrue();
        assertThat(coachAthleteRepository.existsById(10L)).isTrue();
    }

    @Test
    void exceptionWhenCreateExisted() {
        assertThatExceptionOfType(Exception.class).isThrownBy(
                () -> coachAthleteService.create(new CoachAthleteCreateDto(1L, 5L))
        );
        assertThatExceptionOfType(Exception.class).isThrownBy(
                () -> coachAthleteService.create(new CoachAthleteCreateDto(2L, 9L))
        );
    }

    @Test
    void exceptionWhenCreateWithNotExistedPerson() {
        assertThatExceptionOfType(Exception.class).isThrownBy(
                () -> coachAthleteService.create(new CoachAthleteCreateDto(1L, 20L))
        );
        assertThatExceptionOfType(Exception.class).isThrownBy(
                () -> coachAthleteService.create(new CoachAthleteCreateDto(5L, 10L))
        );
    }

    @Test
    void createAllNotExisted() {
        final var coachAthleteCreateDto1 = new CoachAthleteCreateDto(1L, 10L);
        final var coachAthleteCreateDto2 = new CoachAthleteCreateDto(2L, 5L);
        final var actual = coachAthleteService.createAll(coachAthleteCreateDto1, coachAthleteCreateDto2);
        final var expected1 = new CoachAthleteReadDto(
                9L,
                IVAN_COACH,
                ZELIA_ATHLETE
        );
        final var expected2 = new CoachAthleteReadDto(
                10L,
                PETR_COACH,
                SHEVCHENKO_ATHLETE
        );
        assertThat(actual).hasSize(2).isEqualTo(List.of(expected1, expected2));
    }

    @Test
    void exceptionWhenCreateAllWithExistedAndNotExisted() {
        final var coachAthleteCreateDto1 = new CoachAthleteCreateDto(1L, 5L);
        final var coachAthleteCreateDto2 = new CoachAthleteCreateDto(1L, 10L);
        assertThatException().isThrownBy(
                () -> coachAthleteService.createAll(coachAthleteCreateDto1, coachAthleteCreateDto2)
        );
    }

    @Test
    void deleteByExistedId() {
        final var actual = coachAthleteService.deleteById(2L);
        assertThat(actual).isTrue();
        assertThat(coachAthleteRepository.count()).isEqualTo(7L);
        assertThat(coachAthleteRepository.existsById(2L)).isFalse();
    }

    @Test
    void deleteByNotExistedId() {
        final var actual = coachAthleteService.deleteById(20L);
        assertThat(actual).isFalse();
        assertThat(coachAthleteRepository.count()).isEqualTo(8L);
    }

    @Test
    void deleteAllByExistedIds() {
        final var actual = coachAthleteService.deleteAllById(1L, 2L, 3L);
        assertThat(actual).isEqualTo(3);
        assertThat(coachAthleteRepository.count()).isEqualTo(5L);
        assertThat(coachAthleteRepository.existsById(1L)).isFalse();
        assertThat(coachAthleteRepository.existsById(2L)).isFalse();
        assertThat(coachAthleteRepository.existsById(3L)).isFalse();
    }


    @Test
    void deleteByExistedAndNotExistedIds() {
        final var actual = coachAthleteService.deleteAllById(1L, 20L, 2L);
        assertThat(actual).isEqualTo(2);
        assertThat(coachAthleteRepository.count()).isEqualTo(6L);
        assertThat(coachAthleteRepository.existsById(1L)).isFalse();
        assertThat(coachAthleteRepository.existsById(2L)).isFalse();
    }

    @Test
    void notDeleteAllByNotExistedIds() {
        final var actual = coachAthleteService.deleteAllById(19L, 20L, 21L);
        assertThat(actual).isEqualTo(0);
        assertThat(coachAthleteRepository.count()).isEqualTo(8L);
    }

    @Test
    void deleteAllByExistedCoachId() {
        final var actual = coachAthleteService.deleteAllByCoachId(1L);
        assertThat(actual).isEqualTo(3L);
        assertThat(coachAthleteRepository.count()).isEqualTo(5L);
        assertThat(coachAthleteRepository.existsById(1L)).isFalse();
        assertThat(coachAthleteRepository.existsById(2L)).isFalse();
        assertThat(coachAthleteRepository.existsById(3L)).isFalse();
    }

    @Test
    void notDeleteByNotExistedCoachId() {
        final var actual = coachAthleteService.deleteAllByCoachId(5L);
        assertThat(actual).isEqualTo(0L);
        assertThat(coachAthleteRepository.count()).isEqualTo(8L);
    }
}