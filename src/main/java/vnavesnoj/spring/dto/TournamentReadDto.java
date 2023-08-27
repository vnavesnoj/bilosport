package vnavesnoj.spring.dto;

import lombok.Value;
import vnavesnoj.spring.database.entity.MemberRole;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Value
public class TournamentReadDto {

    Long id;
    String name;
    String sport;
    LocalDate tournamentDate;
    Map<MemberRole, List<UserReadDto>> members;
}
