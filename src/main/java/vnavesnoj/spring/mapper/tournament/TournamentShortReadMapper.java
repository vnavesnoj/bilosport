package vnavesnoj.spring.mapper.tournament;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vnavesnoj.spring.database.entity.Sport;
import vnavesnoj.spring.database.entity.Tournament;
import vnavesnoj.spring.dto.SportReadDto;
import vnavesnoj.spring.dto.tournament.TournamentShortReadDto;
import vnavesnoj.spring.mapper.Mapper;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Component
@RequiredArgsConstructor
public class TournamentShortReadMapper implements Mapper<Tournament, TournamentShortReadDto> {

    private final Mapper<Sport, SportReadDto> sportReadMapper;

    @Override
    public TournamentShortReadDto map(Tournament tournament) {
        return new TournamentShortReadDto(
                tournament.getId(),
                tournament.getName(),
                sportReadMapper.map(tournament.getSport()),
                tournament.getTournamentDate()
        );
    }
}
