package vnavesnoj.spring.mapper.person;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vnavesnoj.spring.database.entity.Person;
import vnavesnoj.spring.database.entity.Sport;
import vnavesnoj.spring.database.entity.User;
import vnavesnoj.spring.dto.person.PersonEditDto;
import vnavesnoj.spring.mapper.Mapper;

import java.util.List;
import java.util.Set;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Component
@RequiredArgsConstructor
public class PersonEditMapper implements Mapper<PersonEditDto, Person> {

    private final Mapper<List<Integer>, Set<Sport>> sportIdMapper;

    private final Mapper<Long, User> userIdMapper;

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
        toObject.setSport(sportIdMapper.map(fromObject.getSportId()));
        toObject.setUser(userIdMapper.map(fromObject.getUserId()));
    }
}
