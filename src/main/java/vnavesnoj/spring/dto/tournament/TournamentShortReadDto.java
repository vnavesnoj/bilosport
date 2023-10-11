package vnavesnoj.spring.dto.tournament;

import lombok.Value;
import vnavesnoj.spring.dto.SportReadDto;

import java.time.LocalDate;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Value
public class TournamentShortReadDto {

    Long id;
    String name;
    SportReadDto sport;
    LocalDate tournamentDate;
}
