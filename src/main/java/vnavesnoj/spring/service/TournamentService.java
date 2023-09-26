package vnavesnoj.spring.service;

import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vnavesnoj.spring.database.entity.MemberRole;
import vnavesnoj.spring.database.entity.Tournament;
import vnavesnoj.spring.database.entity.TournamentPerson;
import vnavesnoj.spring.database.repository.PersonRepository;
import vnavesnoj.spring.database.repository.TournamentPersonRepository;
import vnavesnoj.spring.database.repository.TournamentRepository;
import vnavesnoj.spring.dto.TournamentFilter;
import vnavesnoj.spring.dto.TournamentReadDto;
import vnavesnoj.spring.exception.PersonNotExistsException;
import vnavesnoj.spring.exception.TournamentNotExistsException;
import vnavesnoj.spring.exception.TournamentPersonAlreadyExistsException;
import vnavesnoj.spring.mapper.Mapper;

import java.util.Optional;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TournamentService {

    private final TournamentRepository tournamentRepository;

    private final TournamentPersonRepository tournamentPersonRepository;

    private final PersonRepository personRepository;

    private final Mapper<Tournament, TournamentReadDto> tournamentReadMapper;

    private final Mapper<TournamentFilter, Predicate> tournamentPredicateMapper;

    public Optional<TournamentReadDto> findById(Long id) {
        return tournamentRepository.findById(id)
                .map(tournamentReadMapper::map);
    }

    public Page<TournamentReadDto> findAll(TournamentFilter filter, Pageable pageable) {
        final Predicate predicate = tournamentPredicateMapper.map(filter);
        return tournamentRepository.findAll(predicate, pageable)
                .map(tournamentReadMapper::map);
    }

    @Transactional
    public void tryAddPerson(Long tournamentId, Long personId, MemberRole memberRole)
            throws TournamentNotExistsException, PersonNotExistsException, TournamentPersonAlreadyExistsException {
        final var tournament = tournamentRepository.findById(tournamentId).orElseThrow(
                () -> new TournamentNotExistsException("Tournament by id " + tournamentId + " does not exist")
        );
        final var person = personRepository.findById(personId).orElseThrow(
                () -> new PersonNotExistsException("Person by id " + personId + " + does not exist")
        );
        final var tournamentPerson = TournamentPerson.builder()
                .tournament(tournament)
                .person(person)
                .build();
        if (tournamentPersonRepository.exists(Example.of(tournamentPerson))) {
            throw new TournamentPersonAlreadyExistsException("Person id=" + personId + " already exists in tournament id=" + tournamentId);
        }
        tournamentPerson.setMemberRole(memberRole);
        tournamentPersonRepository.save(tournamentPerson);
    }
}
