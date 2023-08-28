package vnavesnoj.spring.dto;

import lombok.Value;

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
}
