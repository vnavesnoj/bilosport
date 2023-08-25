package vnavesnoj.spring.mapper;

import org.springframework.stereotype.Component;
import vnavesnoj.spring.database.entity.TournamentUser;
import vnavesnoj.spring.database.entity.User;
import vnavesnoj.spring.database.entity.UserSport;
import vnavesnoj.spring.dto.UserReadDto;

import java.util.stream.Collectors;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Component
public class UserReadMapper implements Mapper<User, UserReadDto> {

    @Override
    public UserReadDto map(User user) {
        final var sports = user.getUserSports().stream()
                .map(UserSport::getSport)
                .collect(Collectors.toSet());
        final var tournaments = user.getTournamentUsers().stream()
                .map(TournamentUser::getTournament)
                .collect(Collectors.toSet());
        return new UserReadDto(
                user.getId(),
                user.getUsername(),
                user.getFirstname(),
                user.getLastname(),
                user.getSurname(),
                user.getBirthDate(),
                user.getRole(),
                sports,
                tournaments,
                user.getImage());
    }
}
