package vnavesnoj.spring.dto.tournament.tournamentperson;

import lombok.Value;
import vnavesnoj.spring.database.entity.MemberRole;
import vnavesnoj.spring.database.entity.TournamentPersonStatus;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Value
public class TournamentPersonCreateDto {

    Long tournamentId;

    Long personId;

    MemberRole memberRole;

    TournamentPersonStatus status;
}
