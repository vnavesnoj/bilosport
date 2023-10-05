package vnavesnoj.spring.dto.tournament.tournamentperson;

import lombok.Value;
import vnavesnoj.spring.database.entity.MemberRole;
import vnavesnoj.spring.database.entity.TournamentPersonStatus;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Value
public class TournamentPersonEditDto {

    MemberRole memberRole;

    TournamentPersonStatus status;

    ResultEditDto result;
}
