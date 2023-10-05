package vnavesnoj.spring.mapper.tournament;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vnavesnoj.spring.database.entity.Tournament;
import vnavesnoj.spring.database.repository.SportRepository;
import vnavesnoj.spring.dto.tournament.TournamentCreateEditDto;
import vnavesnoj.spring.mapper.Mapper;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Component
@RequiredArgsConstructor
public class TournamentCreateEditMapper implements Mapper<TournamentCreateEditDto, Tournament> {

    private final SportRepository sportRepository;

    @Override
    public Tournament map(TournamentCreateEditDto tournamentDto) {
        final var tournament = new Tournament();
        copy(tournamentDto, tournament);
        return tournament;
    }

    @Override
    public Tournament map(TournamentCreateEditDto fromObject, Tournament toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    public void copy(TournamentCreateEditDto fromObject, Tournament toObject) {
        toObject.setName(fromObject.getName());
        toObject.setSport(sportRepository.findById(fromObject.getSportId()).orElseThrow());
        toObject.setTournamentDate(fromObject.getTournamentDate());
        toObject.setStatus(fromObject.getTournamentStatus());
    }
}
