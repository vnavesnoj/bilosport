package vnavesnoj.spring.dto.tournament;

import lombok.Value;
import vnavesnoj.spring.database.entity.TournamentStatus;

import java.time.LocalDate;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Value
public class TournamentCreateEditDto {

    String name;
    Integer sportId;
    LocalDate tournamentDate;
    TournamentStatus tournamentStatus;
}
