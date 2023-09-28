package vnavesnoj.spring.mapper.person;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vnavesnoj.spring.database.entity.Person;
import vnavesnoj.spring.database.repository.SportRepository;
import vnavesnoj.spring.database.repository.UserRepository;
import vnavesnoj.spring.dto.person.PersonEditDto;
import vnavesnoj.spring.mapper.Mapper;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Component
@RequiredArgsConstructor
public class PersonEditMapper implements Mapper<PersonEditDto, Person> {

    private final SportRepository sportRepository;

    private final UserRepository userRepository;

    @Override
    public Person map(PersonEditDto personDto) {
        final var person = new Person();
        copy(personDto, person);
        return person;
    }

    @Override
    public Person map(PersonEditDto fromObject, Person toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(PersonEditDto fromObject, Person toObject) {
        toObject.setFirstname(fromObject.getFirstname());
        toObject.setLastname(fromObject.getLastname());
        toObject.setSurname(fromObject.getSurname());
        toObject.setBirthDate(fromObject.getBirthDate());

        final var sports = Optional.ofNullable(fromObject.getSportId())
                .map(ids -> ids.stream()
                        .map(id -> sportRepository.findById(id).orElseThrow(
                                () -> new IllegalArgumentException("Sport does not exist by id: " + id)
                        ))
                        .collect(Collectors.toSet()))
                .orElse(null);

        toObject.setSport(sports);

        final var user = Optional.ofNullable(fromObject.getUserId())
                .map(userId -> userRepository.findById(userId).orElseThrow(
                        () -> new IllegalArgumentException("User does not exist by id: " + fromObject.getUserId())
                ))
                .orElse(null);

        toObject.setUser(user);
    }
}
