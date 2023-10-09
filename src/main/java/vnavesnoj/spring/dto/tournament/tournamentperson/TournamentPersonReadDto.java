package vnavesnoj.spring.dto.tournament.tournamentperson;

import lombok.Value;
import vnavesnoj.spring.database.entity.MemberRole;
import vnavesnoj.spring.database.entity.TournamentPersonStatus;
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

    MemberRole role;

    TournamentPersonStatus status;

    ResultReadDto result;
}
