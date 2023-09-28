package vnavesnoj.spring.mapper.person;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vnavesnoj.spring.database.entity.Person;
import vnavesnoj.spring.database.repository.SportRepository;
import vnavesnoj.spring.database.repository.UserRepository;
import vnavesnoj.spring.dto.person.PersonCreateDto;
import vnavesnoj.spring.mapper.Mapper;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Component
@RequiredArgsConstructor
public class PersonCreateMapper implements Mapper<PersonCreateDto, Person> {

    private final SportRepository sportRepository;

    private final UserRepository userRepository;

    @Override
    public Person map(PersonCreateDto personDto) {
        final var sports = Optional.ofNullable(personDto.getSportIds())
                .map(sportsId -> sportsId.stream()
                        .map(id -> sportRepository.findById(id).orElseThrow(
                                () -> new IllegalArgumentException("Sport does not exist by id: " + id)
                        ))
                        .collect(Collectors.toSet()))
                .orElse(null);
        final var user = Optional.ofNullable(personDto.getUserId())
                .map(userId -> userRepository.findById(userId).orElseThrow(
                        () -> new IllegalArgumentException("User does not exist by id: " + personDto.getUserId())
                ))
                .orElse(null);
        return Person.builder()
                .firstname(personDto.getFirstname())
                .lastname(personDto.getLastname())
                .surname(personDto.getSurname())
                .birthDate(personDto.getBirthDate())
                .role(personDto.getRole())
                .sport(sports)
                .user(user)
                .build();
    }
}
