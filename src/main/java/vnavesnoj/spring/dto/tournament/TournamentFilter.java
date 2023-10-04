package vnavesnoj.spring.dto.tournament;

import lombok.Value;

import java.time.LocalDate;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Value
public class TournamentFilter {

    String name;
    LocalDate afterDate;
    LocalDate beforeDate;
    Integer sportId;
}
