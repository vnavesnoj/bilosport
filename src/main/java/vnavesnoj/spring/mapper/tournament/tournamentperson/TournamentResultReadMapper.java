package vnavesnoj.spring.mapper.tournament.tournamentperson;

import org.springframework.stereotype.Component;
import vnavesnoj.spring.database.entity.TournamentResult;
import vnavesnoj.spring.dto.tournament.tournamentperson.ResultReadDto;
import vnavesnoj.spring.mapper.Mapper;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Component
public class TournamentResultReadMapper implements Mapper<TournamentResult, ResultReadDto> {

    @Override
    public ResultReadDto map(TournamentResult entity) {
        return entity != null
                ? new ResultReadDto(entity.getId(), entity.getPlace(), entity.getOutOf())
                : null;
    }
}
