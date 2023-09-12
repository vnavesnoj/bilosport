package vnavesnoj.spring.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vnavesnoj.spring.database.entity.Sport;
import vnavesnoj.spring.database.entity.User;
import vnavesnoj.spring.database.entity.UserSport;
import vnavesnoj.spring.dto.SportReadDto;
import vnavesnoj.spring.dto.UserReadDto;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Component
@RequiredArgsConstructor
public class UserReadMapper implements Mapper<User, UserReadDto> {

    private final Mapper<Sport, SportReadDto> sportReadMapper;

    @Override
    public UserReadDto map(User user) {
        final var sports = user.getUserSports().stream()
                .map(UserSport::getSport)
                .map(sportReadMapper::map)
                .toList();
        return new UserReadDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getFirstname(),
                user.getLastname(),
                user.getSurname(),
                user.getBirthDate(),
                user.getRole(),
                sports,
                user.getImage());
    }
}
