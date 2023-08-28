package vnavesnoj.spring.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import vnavesnoj.spring.database.entity.Sport;
import vnavesnoj.spring.database.entity.Tournament;
import vnavesnoj.spring.dto.TournamentCreateEditDto;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Component
@RequiredArgsConstructor
public class TournamentCreateEditMapper implements Mapper<TournamentCreateEditDto, Tournament> {

    private final CrudRepository<Sport, Integer> sportRepository;

    @Override
    public Tournament map(TournamentCreateEditDto tournamentDto) {
        final var tournament = new Tournament();
        copy(tournamentDto, tournament);
        return tournament;
    }

    @Override
    public Tournament map(TournamentCreateEditDto tournamentDto, Tournament tournament) {
        copy(tournamentDto, tournament);
        return tournament;
    }

    private void copy(TournamentCreateEditDto tournamentDto, Tournament tournament) {
        tournament.setName(tournamentDto.getName());
        tournament.setSport(sportRepository.findById(tournamentDto.getSportId())
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Sport does not exist by id: " + tournamentDto.getSportId())));
        tournament.setTournamentDate(tournamentDto.getTournamentDate());
    }

//    private void setTournamentUsers(TournamentCreateEditDto tournamentDto, Tournament tournament) {
//        Set<TournamentUser> tournamentUsers = new HashSet<>();
//        for (Long userId : tournamentDto.getUserIds()) {
//            final var user = userRepository.findById(userId)
//                    .orElseThrow(() ->
//                            new IllegalArgumentException(
//                                    "User does not exist by id: " + userId
//                            ));
//            final var tournamentUser = new TournamentUser();
//            tournamentUser.setTournament(tournament);
//            tournamentUser.setUser(user);
//        }
//        tournament.setTournamentUsers(tournamentUsers);
//    }
}
