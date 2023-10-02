package vnavesnoj.spring.mapper.person.coachathlete;

import org.springframework.stereotype.Component;
import vnavesnoj.spring.database.entity.Person;
import vnavesnoj.spring.dto.person.coachathlete.AthleteReadDto;
import vnavesnoj.spring.mapper.Mapper;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Component
public class AthleteReadMapper implements Mapper<Person, AthleteReadDto> {

    @Override
    public AthleteReadDto map(Person entity) {
        return new AthleteReadDto(
                entity.getId(),
                entity.getFirstname(),
                entity.getLastname(),
                entity.getUser().getUsername()
        );
    }
}
