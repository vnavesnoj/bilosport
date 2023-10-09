package vnavesnoj.spring.mapper.tournament.tournamentperson;

import org.springframework.stereotype.Component;
import vnavesnoj.spring.database.entity.TournamentPerson;
import vnavesnoj.spring.database.entity.TournamentResult;
import vnavesnoj.spring.dto.tournament.tournamentperson.TournamentPersonEditDto;
import vnavesnoj.spring.mapper.Mapper;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Component
public class TournamentPersonEditMapper implements Mapper<TournamentPersonEditDto, TournamentPerson> {

    @Override
    public TournamentPerson map(TournamentPersonEditDto object) {
        final var tournamentPerson = new TournamentPerson();
        copy(object, tournamentPerson);
        return tournamentPerson;
    }

    @Override
    public TournamentPerson map(TournamentPersonEditDto fromObject, TournamentPerson toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    private void copy(TournamentPersonEditDto fromObject, TournamentPerson toObject) {
        toObject.setMemberRole(fromObject.getMemberRole());
        toObject.setStatus(fromObject.getStatus());
        if (toObject.getTournamentResult() != null && fromObject.getResult() != null) {
            final var result = toObject.getTournamentResult();
            final var resultEditDto = fromObject.getResult();
            toObject.setTournamentResult(new TournamentResult(
                    result.getId(), toObject, resultEditDto.getPlace(), resultEditDto.getOutOf())
            );
        }
    }
}
