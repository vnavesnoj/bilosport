package vnavesnoj.spring.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vnavesnoj.spring.database.entity.Sport;
import vnavesnoj.spring.database.entity.Tournament;
import vnavesnoj.spring.dto.SportReadDto;
import vnavesnoj.spring.dto.tournament.TournamentReadDto;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Component
@RequiredArgsConstructor
public class TournamentReadMapper implements Mapper<Tournament, TournamentReadDto> {

    private final Mapper<Sport, SportReadDto> sportReadMapper;

    @Override
    public TournamentReadDto map(Tournament tournament) {
        return new TournamentReadDto(
                tournament.getId(),
                tournament.getName(),
                sportReadMapper.map(tournament.getSport()),
                tournament.getTournamentDate(),
                //TODO
                null
        );
    }

//    private Map<MemberRole, List<UserReadDto>> createMapOfMembers(Tournament tournament) {
//        Map<MemberRole, List<UserReadDto>> members = new HashMap<>();
//        for (MemberRole role : MemberRole.values()) {
//            members.put(role, new ArrayList<>());
//        }
//        for (TournamentUser tournamentUser : tournament.getTournamentUsers()) {
//            final var users = members.get(tournamentUser.getMemberRole());
//            final var userReadDto = userReadMapper.map(tournamentUser.getUser());
//            users.add(userReadDto);
//        }
//        return members;
//    }
}
