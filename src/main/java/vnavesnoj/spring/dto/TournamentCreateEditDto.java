package vnavesnoj.spring.dto;

import lombok.Value;

import java.time.LocalDate;
import java.util.List;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Value
public class TournamentCreateEditDto {

    String name;

    Integer sportId;

    LocalDate tournamentDate;

    List<Long> userIds;
}
