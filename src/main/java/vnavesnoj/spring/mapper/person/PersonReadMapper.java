package vnavesnoj.spring.mapper.person;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vnavesnoj.spring.database.entity.Person;
import vnavesnoj.spring.database.entity.Sport;
import vnavesnoj.spring.database.entity.User;
import vnavesnoj.spring.dto.SportReadDto;
import vnavesnoj.spring.dto.person.PersonReadDto;
import vnavesnoj.spring.mapper.Mapper;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Component
@RequiredArgsConstructor
public class PersonReadMapper implements Mapper<Person, PersonReadDto> {

    private final Mapper<Sport, SportReadDto> sportReadMapper;

    @Override
    public PersonReadDto map(Person person) {
        return new PersonReadDto(
                person.getId(),
                person.getFirstname(),
                person.getLastname(),
                person.getSurname(),
                person.getBirthDate(),
                person.getRole(),
                Optional.ofNullable(person.getSport())
                        .map(sports -> sports.stream()
                                .map(sportReadMapper::map)
                                .collect(Collectors.toSet()))
                        .orElse(Collections.emptySet()),
                Optional.ofNullable(person.getUser())
                        .map(User::getUsername)
                        .orElse(null)
        );
    }
}
