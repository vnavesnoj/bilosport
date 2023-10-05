package vnavesnoj.spring.dto.tournament.tournamentperson;

import lombok.Value;
import vnavesnoj.spring.dto.person.PersonShortReadDto;
import vnavesnoj.spring.dto.tournament.TournamentReadDto;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Value
public class TournamentPersonReadDto {

    Long id;

    TournamentReadDto tournament;

    PersonShortReadDto person;

    ResultReadDto result;
}
