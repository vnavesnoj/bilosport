package vnavesnoj.spring.mapper.tournament.tournamentperson;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vnavesnoj.spring.database.entity.TournamentPerson;
import vnavesnoj.spring.database.repository.PersonRepository;
import vnavesnoj.spring.database.repository.TournamentRepository;
import vnavesnoj.spring.dto.tournament.tournamentperson.TournamentPersonCreateDto;
import vnavesnoj.spring.mapper.Mapper;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Component
@RequiredArgsConstructor
public class TournamentPersonCreateMapper implements Mapper<TournamentPersonCreateDto, TournamentPerson> {

    private final TournamentRepository tournamentRepository;

    private final PersonRepository personRepository;

    @Override
    public TournamentPerson map(TournamentPersonCreateDto entity) {
        return TournamentPerson.builder()
                .tournament(tournamentRepository.findById(entity.getTournamentId()).orElseThrow(
                        () -> new IllegalArgumentException(
                                "Tournament with id = " + entity.getTournamentId() + " does not exist")
                ))
                .person(personRepository.findById(entity.getPersonId()).orElseThrow(
                        () -> new IllegalArgumentException(
                                "Person with id = " + entity.getPersonId() + " does not exist"
                        )
                ))
                .memberRole(entity.getMemberRole())
                .status(entity.getStatus())
                .build();
    }
}
