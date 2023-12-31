package vnavesnoj.spring.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import vnavesnoj.spring.database.entity.Role;
import vnavesnoj.spring.database.entity.Sport;
import vnavesnoj.spring.database.entity.User;
import vnavesnoj.spring.dto.UserCreateDto;

import java.util.Optional;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Component
@RequiredArgsConstructor
public class UserCreateMapper implements Mapper<UserCreateDto, User> {

    private final PasswordEncoder passwordEncoder;

    private final CrudRepository<Sport, Integer> sportRepository;

//    private final CrudRepository<Tournament, Long> tournamentRepository;

    @Override
    public User map(UserCreateDto userDto) {
        final var user = new User();
        copy(userDto, user);
        return user;
    }

    public User map(UserCreateDto userDto, User user) {
        copy(userDto, user);
        return user;
    }

    private void copy(UserCreateDto userDto, User user) {
        user.setUsername(userDto.getUsername().trim());
        user.setEmail(userDto.getEmail().trim().toLowerCase());
        Optional.ofNullable(userDto.getRawPassword())
                .filter(StringUtils::hasText)
                .map(passwordEncoder::encode)
                .ifPresent(user::setPassword);
//        user.setFirstname(userDto.getFirstname());
//        user.setLastname(userDto.getLastname());
//        user.setSurname(userDto.getSurname());
//        user.setBirthDate(userDto.getBirthDate());
//        user.setRole(userDto.getRole());
//        Optional.ofNullable(userDto.getImage())
//                .filter(not(MultipartFile::isEmpty))
//                .ifPresent(image -> user.setImage(image.getOriginalFilename()));
//        setUserSports(userDto, user);
        user.setRole(Role.ATHLETE);
    }

//    private void setTournamentUsers(UserCreateEditDto userDto, User user) {
//        final var tournamentUsers = new HashSet<TournamentUser>();
//        userDto.getTournamentIds().forEach(tournamentId -> {
//            final var tournamentUser = new TournamentUser();
//            tournamentUser.setUser(user);
//            final var tournament = tournamentRepository.findById(tournamentId)
//                    .orElseThrow(() ->
//                            new IllegalArgumentException("Tournament does not exist by id: " + tournamentId));
//            tournamentUser.setTournament(tournament);
//            tournamentUsers.add(tournamentUser);
//        });
//        user.setTournamentUsers(tournamentUsers);
//    }

//    private void setUserSports(UserCreateDto userDto, User user) {
//        final var userSports = new HashSet<UserSport>();
//        userDto.getSportIds().forEach(sportId -> {
//            final var userSport = new UserSport();
//            userSport.setUser(user);
//            final var sport = sportRepository.findById(sportId)
//                    .orElseThrow(() ->
//                            new IllegalArgumentException("Sport does not exist by id: " + sportId));
//            userSport.setSport(sport);
//            userSports.add(userSport);
//        });
//        user.setUserSports(userSports);
//    }
}
