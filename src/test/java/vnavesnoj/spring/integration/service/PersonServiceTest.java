package vnavesnoj.spring.integration.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Pageable;
import vnavesnoj.spring.database.entity.Role;
import vnavesnoj.spring.database.entity.Sport;
import vnavesnoj.spring.database.repository.PersonRepository;
import vnavesnoj.spring.dto.SportReadDto;
import vnavesnoj.spring.dto.person.PersonCreateDto;
import vnavesnoj.spring.dto.person.PersonEditDto;
import vnavesnoj.spring.dto.person.PersonFilter;
import vnavesnoj.spring.dto.person.PersonReadDto;
import vnavesnoj.spring.exception.PersonNotExistsException;
import vnavesnoj.spring.exception.UserAlreadyHasPersonException;
import vnavesnoj.spring.exception.UserNotExistsException;
import vnavesnoj.spring.integration.IntegrationTestBase;
import vnavesnoj.spring.service.PersonService;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@RequiredArgsConstructor
class PersonServiceTest extends IntegrationTestBase {

    private final PersonService personService;
    private final PersonRepository personRepository;
    private static final int PERSON_COUNT = 14;

    @Test
    void findAllWithEmptyFilterAndPageable() {
        final var emptyFilter = new PersonFilter(null, null, null, null, null);
        final var unpaged = Pageable.unpaged();
        final var people = personService.findAll(emptyFilter, unpaged);
        assertThat(people).hasSize(PERSON_COUNT);
    }

    @Test
    void findAllWithNameAndEmptyPageable() {
        final var filter = new PersonFilter("Во", null, null, null, null);
        final var unpaged = Pageable.unpaged();
        final var people = personService.findAll(filter, unpaged);
        assertThat(people).hasSize(3);
    }

    @Test
    void findAllWithNameBirthDate() {
        final var filter = new PersonFilter(
                "Во",
                LocalDate.of(1997, 12, 7),
                LocalDate.of(1999, 9, 19),
                null,
                null);
        final var unpaged = Pageable.unpaged();
        final var people = personService.findAll(filter, unpaged);
        assertThat(people).hasSize(2);
    }

    @Test
    void findOneWithFullFilter() {
        final var filter = new PersonFilter("гіга",
                LocalDate.of(1995, 1, 1),
                LocalDate.of(2000, 1, 1),
                Role.ATHLETE,
                3);
        final var unpaged = Pageable.unpaged();
        final var person = personService.findAll(filter, unpaged);
        assertThat(person).hasSize(1);
    }

    @Test
    void findAllWithSportFilter() {
        final var filter = new PersonFilter(
                null,
                null,
                null,
                null,
                1
        );
        final var unpaged = Pageable.unpaged();
        final var people = personService.findAll(filter, unpaged);
        assertThat(people).hasSize(4);
    }

    @Test
    void findNoneWithFullFilter() {
        final var filter = new PersonFilter(
                "во",
                LocalDate.of(1995, 1, 1),
                LocalDate.of(2010, 1, 1),
                Role.COACH,
                null
        );
        final var unpaged = Pageable.unpaged();
        final var none = personService.findAll(filter, unpaged);
        assertThat(none).isEmpty();
    }

    @SneakyThrows
    @Test
    void findOneById() {
        final var actual = personService.findById(12L);
        final var expected = new PersonReadDto(
                12L,
                "Олександр",
                "Волков",
                null,
                LocalDate.of(1997, 12, 7),
                Role.ATHLETE,
                Set.of(new SportReadDto(3, "шахи")),
                "Volk"
        );
        assertThat(actual).isPresent().contains(expected);
    }

    @Test
    void emptyOptionalWhenPersonNotExistById() {
        final var actual = personService.findById(20L);
        assertThat(actual).isEmpty();
    }

    @SneakyThrows
    @Test
    void create() {
        final var newPerson = new PersonCreateDto(
                "Ім'я",
                "Прізвище",
                "По-батькові",
                LocalDate.of(1996, 3, 13),
                Role.COACH,
                null,
                13L
        );
        final var actual = personService.create(newPerson);
        final var expected = new PersonReadDto(
                15L,
                newPerson.getFirstname(),
                newPerson.getLastname(),
                newPerson.getSurname(),
                newPerson.getBirthDate(),
                newPerson.getRole(),
                Collections.emptySet(),
                "New"
        );
        assertThat(actual).isEqualTo(expected);
        final var maybePerson = personRepository.findById(15L);
        assertThat(maybePerson).isPresent();
        final var person = maybePerson.orElseThrow();
        assertThat(newPerson.getFirstname()).isEqualTo(person.getFirstname());
        assertThat(newPerson.getLastname()).isEqualTo(person.getLastname());
        assertThat(newPerson.getSurname()).isEqualTo(person.getSurname());
        assertThat(newPerson.getBirthDate()).isEqualTo(person.getBirthDate());
        assertThat(newPerson.getRole()).isEqualTo(person.getRole());
        assertThat(person.getSport()).isNull();
        assertThat(newPerson.getUserId()).isEqualTo(person.getUser().getId());
    }

    @Test
    void createWithNotExistUser() {
        assertThatExceptionOfType(Exception.class)
                .isThrownBy(() -> personService.create(new PersonCreateDto(
                        "Ім'я",
                        "Прізвище",
                        "По-батькові",
                        LocalDate.of(1996, 3, 13),
                        Role.COACH,
                        Collections.emptyList(),
                        20L
                )));
    }

    @Test
    void createWithAlreadyVerifiedUser() {
        assertThatExceptionOfType(Exception.class)
                .isThrownBy(() -> personService.create(new PersonCreateDto(
                        "Ім'я",
                        "Прізвище",
                        "По-батькові",
                        LocalDate.of(1996, 3, 13),
                        Role.COACH,
                        Collections.emptyList(),
                        2L
                )));
    }

    @Test
    void createWithNotExistedSport() {
        assertThatExceptionOfType(Exception.class)
                .isThrownBy(() -> personService.create(new PersonCreateDto(
                        "Ім'я",
                        "Прізвище",
                        "По-батькові",
                        LocalDate.of(1996, 3, 13),
                        Role.COACH,
                        List.of(5),
                        13L
                )));
    }

    @Test
    void createWithNullUser() {
        final var newPerson = new PersonCreateDto(
                "Ім'я",
                "Прізвище",
                "По-батькові",
                LocalDate.of(1996, 3, 13),
                Role.COACH,
                List.of(3),
                null
        );
        final var actual = personService.create(newPerson);
        final var expected = new PersonReadDto(
                15L,
                newPerson.getFirstname(),
                newPerson.getLastname(),
                newPerson.getSurname(),
                newPerson.getBirthDate(),
                newPerson.getRole(),
                Set.of(new SportReadDto(3, "шахи")),
                null
        );
        assertThat(actual).isEqualTo(expected);
        final var maybePerson = personRepository.findById(15L);
        assertThat(maybePerson).isPresent();
        final var person = maybePerson.orElseThrow();
        assertThat(newPerson.getFirstname()).isEqualTo(person.getFirstname());
        assertThat(newPerson.getLastname()).isEqualTo(person.getLastname());
        assertThat(newPerson.getSurname()).isEqualTo(person.getSurname());
        assertThat(newPerson.getBirthDate()).isEqualTo(person.getBirthDate());
        assertThat(newPerson.getRole()).isEqualTo(person.getRole());
        assertThat(newPerson.getSportIds()).isEqualTo(person.getSport().stream()
                .map(Sport::getId)
                .toList());
        assertThat(person.getUser()).isNull();
    }

    @SneakyThrows
    @Test
    void tryUpdateLastname() {
        final var updatePerson = new PersonEditDto(
                "Олександр",
                "Новий",
                null,
                LocalDate.of(1997, 12, 7),
                List.of(3),
                12L
        );
        final var actual = personService.update(12L, updatePerson);
        final var expected = new PersonReadDto(
                12L,
                updatePerson.getFirstname(),
                updatePerson.getLastname(),
                updatePerson.getSurname(),
                updatePerson.getBirthDate(),
                Role.ATHLETE,
                Set.of(new SportReadDto(3, "шахи")),
                "Volk"
        );

        assertThat(actual).isPresent().contains(expected);
        final var person = personRepository.findById(12L).orElseThrow();
        assertThat(actual.orElseThrow().getLastname()).isEqualTo(
                person.getLastname()
        );
    }

    @SneakyThrows
    @Test
    void updatePersonUser() {
        final var updatePerson = new PersonEditDto(
                "Олександр",
                "Волков",
                null,
                LocalDate.of(1997, 12, 7),
                List.of(3),
                13L
        );
        final var actual = personService.update(12L, updatePerson);
        final var expected = new PersonReadDto(
                12L,
                updatePerson.getFirstname(),
                updatePerson.getLastname(),
                updatePerson.getSurname(),
                updatePerson.getBirthDate(),
                Role.ATHLETE,
                Set.of(new SportReadDto(3, "шахи")),
                "New"
        );
        assertThat(actual).isPresent().contains(expected);
        assertThat(updatePerson.getUserId()).isEqualTo(
                personRepository.findById(12L).orElseThrow().getUser().getId());
    }

    @Test
    void updateNotExistPerson() {
        assertThatExceptionOfType(PersonNotExistsException.class).isThrownBy(
                () -> new PersonEditDto(
                        "Олександр",
                        "Новий",
                        null,
                        LocalDate.of(1997, 12, 7),
                        List.of(3),
                        12L
                )
        );
    }

    @Test
    void updatePersonWithNotExistUser() {
        assertThatExceptionOfType(UserNotExistsException.class).isThrownBy(
                () -> new PersonEditDto(
                        "Олександр",
                        "Волков",
                        null,
                        LocalDate.of(1997, 12, 7),
                        List.of(3),
                        20L
                )
        );
    }

    @Test
    void updatePersonWithAlreadyVerifiedUser() {
        assertThatExceptionOfType(UserAlreadyHasPersonException.class).isThrownBy(
                () -> new PersonEditDto(
                        "Олександр",
                        "Волков",
                        null,
                        LocalDate.of(1997, 12, 7),
                        List.of(3),
                        10L
                )
        );
    }

    @SneakyThrows
    @Test
    void tryDelete() {
        personService.delete(12L);
        assertThat(personRepository.findAll()).hasSize(PERSON_COUNT - 1);
        assertThat(personRepository.findById(12L)).isEmpty();
    }

    @SneakyThrows
    @Test
    void deleteNotExistPerson() {
        final var result = personService.delete(20L);
        assertThat(personRepository.findAll()).hasSize(PERSON_COUNT);
        assertThat(result).isFalse();
    }
}