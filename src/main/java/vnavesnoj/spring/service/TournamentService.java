package vnavesnoj.spring.service;

import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vnavesnoj.spring.database.entity.Tournament;
import vnavesnoj.spring.database.repository.TournamentRepository;
import vnavesnoj.spring.dto.tournament.TournamentCreateEditDto;
import vnavesnoj.spring.dto.tournament.TournamentFilter;
import vnavesnoj.spring.dto.tournament.TournamentReadDto;
import vnavesnoj.spring.dto.tournament.TournamentShortReadDto;
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

    private final Mapper<Tournament, TournamentReadDto> tournamentReadMapper;

    private final Mapper<Tournament, TournamentShortReadDto> tournamentShortReadMapper;

    private final Mapper<TournamentFilter, Predicate> tournamentPredicateMapper;

    private final Mapper<TournamentCreateEditDto, Tournament> tournamentCreateEditMapper;

    public Optional<TournamentReadDto> findById(Long id) {
        return tournamentRepository.findById(id)
                .map(tournamentReadMapper::map);
    }

    public Page<TournamentReadDto> findAll(TournamentFilter filter, Pageable pageable) {
        final Predicate predicate = tournamentPredicateMapper.map(filter);
        return tournamentRepository.findAll(predicate, pageable)
                .map(tournamentReadMapper::map);
    }

    public Page<TournamentShortReadDto> findAllShort(TournamentFilter filter, Pageable pageable) {
        final var predicate = tournamentPredicateMapper.map(filter);
        return tournamentRepository.findAll(predicate, pageable)
                .map(tournamentShortReadMapper::map);
    }

    @Transactional
    public TournamentReadDto create(TournamentCreateEditDto tournament) {
        return Optional.of(tournament)
                .map(tournamentCreateEditMapper::map)
                .map(tournamentRepository::save)
                .map(tournamentReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<TournamentReadDto> update(Long id, TournamentCreateEditDto tournament) {
        return tournamentRepository.findById(id)
                .map(entity -> tournamentCreateEditMapper.map(tournament, entity))
                .map(tournamentRepository::saveAndFlush)
                .map(tournamentReadMapper::map);
    }

    @Transactional
    public boolean delete(Long id) {
        return tournamentRepository.findById(id)
                .map(tournament -> {
                    tournamentRepository.delete(tournament);
                    tournamentRepository.flush();
                    return true;
                })
                .orElse(false);
    }
/*
    //TODO change all
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
 */
}
