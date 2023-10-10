package vnavesnoj.spring.dto.tournament;

import lombok.Value;
import vnavesnoj.spring.database.entity.Scope;
import vnavesnoj.spring.database.entity.TournamentStatus;
import vnavesnoj.spring.dto.SportReadDto;

import java.time.LocalDate;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Value
public class TournamentReadDto {

    Long id;
    String name;
    SportReadDto sport;
    LocalDate tournamentDate;
    Integer minAge;
    Integer maxAge;
    Integer participantCount;
    String description;
    TournamentStatus tournamentStatus;
    Scope scope;
}
