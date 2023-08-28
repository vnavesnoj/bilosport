package vnavesnoj.spring.dto;

import lombok.Value;

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
}
