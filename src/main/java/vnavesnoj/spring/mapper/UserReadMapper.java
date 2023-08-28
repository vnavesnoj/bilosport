package vnavesnoj.spring.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vnavesnoj.spring.database.entity.*;
import vnavesnoj.spring.dto.SportReadDto;
import vnavesnoj.spring.dto.TournamentReadDto;
import vnavesnoj.spring.dto.UserReadDto;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Component
@RequiredArgsConstructor
public class UserReadMapper implements Mapper<User, UserReadDto> {

    private final Mapper<Sport, SportReadDto> sportReadMapper;
    private final Mapper<Tournament, TournamentReadDto> tournamentReadMapper;

    @Override
    public UserReadDto map(User user) {
        final var sports = user.getUserSports().stream()
                .map(UserSport::getSport)
                .map(sportReadMapper::map)
                .toList();
        final var tournaments = user.getTournamentUsers().stream()
                .map(TournamentUser::getTournament)
                .map(tournamentReadMapper::map)
                .toList();
        return new UserReadDto(
                user.getId(),
                user.getUsername(),
                user.getFirstname(),
                user.getLastname(),
                user.getSurname(),
                user.getBirthDate(),
                user.getRole(),
                sports,
                user.getImage());
    }
}
