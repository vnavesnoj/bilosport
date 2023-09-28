package vnavesnoj.spring.mapper.person;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vnavesnoj.spring.database.entity.Person;
import vnavesnoj.spring.database.entity.Sport;
import vnavesnoj.spring.database.entity.User;
import vnavesnoj.spring.dto.person.PersonCreateDto;
import vnavesnoj.spring.mapper.Mapper;

import java.util.List;
import java.util.Set;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Component
@RequiredArgsConstructor
public class PersonCreateMapper implements Mapper<PersonCreateDto, Person> {

    private final Mapper<List<Integer>, Set<Sport>> sportIdMapper;

    private final Mapper<Long, User> userIdMapper;

    @Override
    public Person map(PersonCreateDto personDto) {
        return Person.builder()
                .firstname(personDto.getFirstname())
                .lastname(personDto.getLastname())
                .surname(personDto.getSurname())
                .birthDate(personDto.getBirthDate())
                .role(personDto.getRole())
                .sport(sportIdMapper.map(personDto.getSportIds()))
                .user(userIdMapper.map(personDto.getUserId()))
                .build();
    }
}
