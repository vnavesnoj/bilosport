package vnavesnoj.spring.mapper.person;

import org.springframework.stereotype.Component;
import vnavesnoj.spring.database.entity.Person;
import vnavesnoj.spring.dto.person.PersonShortReadDto;
import vnavesnoj.spring.mapper.Mapper;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Component
public class PersonShortReadMapper implements Mapper<Person, PersonShortReadDto> {

    @Override
    public PersonShortReadDto map(Person entity) {
        return new PersonShortReadDto(
                entity.getId(),
                entity.getFirstname(),
                entity.getLastname(),
                entity.getUser().getUsername()
        );
    }
}
