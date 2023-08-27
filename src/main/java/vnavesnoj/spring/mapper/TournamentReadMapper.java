package vnavesnoj.spring.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vnavesnoj.spring.database.entity.MemberRole;
import vnavesnoj.spring.database.entity.Tournament;
import vnavesnoj.spring.database.entity.TournamentUser;
import vnavesnoj.spring.database.entity.User;
import vnavesnoj.spring.dto.TournamentReadDto;
import vnavesnoj.spring.dto.UserReadDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Component
@RequiredArgsConstructor
public class TournamentReadMapper implements Mapper<Tournament, TournamentReadDto> {

    private final Mapper<User, UserReadDto> userReadMapper;

    @Override
    public TournamentReadDto map(Tournament tournament) {
        return new TournamentReadDto(
                tournament.getId(),
                tournament.getName(),
                tournament.getSport().getName(),
                tournament.getTournamentDate(),
                createMapOfMembers(tournament)
        );
    }

    private Map<MemberRole, List<UserReadDto>> createMapOfMembers(Tournament tournament) {
        Map<MemberRole, List<UserReadDto>> members = new HashMap<>();
        for (MemberRole role : MemberRole.values()) {
            members.put(role, new ArrayList<>());
        }
        for (TournamentUser tournamentUser : tournament.getTournamentUsers()) {
            final var users = members.get(tournamentUser.getMemberRole());
            final var userReadDto = userReadMapper.map(tournamentUser.getUser());
            users.add(userReadDto);
        }
        return members;
    }
}
