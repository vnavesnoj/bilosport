package vnavesnoj.spring.mapper.tournament.tournamentperson;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vnavesnoj.spring.database.entity.Person;
import vnavesnoj.spring.database.entity.Tournament;
import vnavesnoj.spring.database.entity.TournamentPerson;
import vnavesnoj.spring.database.entity.TournamentResult;
import vnavesnoj.spring.dto.person.PersonShortReadDto;
import vnavesnoj.spring.dto.tournament.TournamentReadDto;
import vnavesnoj.spring.dto.tournament.tournamentperson.ResultReadDto;
import vnavesnoj.spring.dto.tournament.tournamentperson.TournamentPersonReadDto;
import vnavesnoj.spring.mapper.Mapper;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Component
@RequiredArgsConstructor
public class TournamentPersonReadMapper implements Mapper<TournamentPerson, TournamentPersonReadDto> {

    private final Mapper<Tournament, TournamentReadDto> tournamentReadMapper;

    private final Mapper<Person, PersonShortReadDto> personShortReadMapper;

    private final Mapper<TournamentResult, ResultReadDto> tournamentResultReadMapper;

    @Override
    public TournamentPersonReadDto map(TournamentPerson entity) {
        return new TournamentPersonReadDto(
                entity.getId(),
                tournamentReadMapper.map(entity.getTournament()),
                personShortReadMapper.map(entity.getPerson()),
                entity.getMemberRole(),
                entity.getStatus(),
                tournamentResultReadMapper.map(entity.getTournamentResult())
        );
    }
}
