package vnavesnoj.spring.dto.tournament.tournamentperson;

import lombok.Value;
import vnavesnoj.spring.dto.person.PersonShortReadDto;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Value
public class PersonResultReadDto {

    PersonShortReadDto person;

    ResultReadDto result;
}
