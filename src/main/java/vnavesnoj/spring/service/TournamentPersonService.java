package vnavesnoj.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vnavesnoj.spring.database.entity.TournamentPerson;
import vnavesnoj.spring.database.repository.TournamentPersonRepository;
import vnavesnoj.spring.dto.tournament.tournamentperson.TournamentPersonCreateDto;
import vnavesnoj.spring.dto.tournament.tournamentperson.TournamentPersonEditDto;
import vnavesnoj.spring.dto.tournament.tournamentperson.TournamentPersonReadDto;
import vnavesnoj.spring.mapper.Mapper;

import java.util.Optional;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TournamentPersonService {

    private final TournamentPersonRepository tournamentPersonRepository;

    private final Mapper<TournamentPerson, TournamentPersonReadDto> tournamentPersonReadMapper;

    private final Mapper<TournamentPersonCreateDto, TournamentPerson> tournamentPersonCreateMapper;

    private final Mapper<TournamentPersonEditDto, TournamentPerson> tournamentPersonEditMapper;

    public Optional<TournamentPersonReadDto> findById(Long id) {
        return tournamentPersonRepository.findById(id)
                .map(tournamentPersonReadMapper::map);
    }

    public Page<TournamentPersonReadDto> findAllByTournament(Long tournamentId, Pageable pageable) {
        return tournamentPersonRepository.findAllByTournamentId(tournamentId, pageable)
                .map(tournamentPersonReadMapper::map);
    }

    public Page<TournamentPersonReadDto> findAllByPerson(Long personId, Pageable pageable) {
        return tournamentPersonRepository.findAllByPersonId(personId, pageable)
                .map(tournamentPersonReadMapper::map);
    }

    @Transactional
    public TournamentPersonReadDto create(TournamentPersonCreateDto tournamentPerson) {
        return Optional.of(tournamentPerson)
                .map(tournamentPersonCreateMapper::map)
                .map(tournamentPersonRepository::save)
                .map(tournamentPersonReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<TournamentPersonReadDto> update(Long id, TournamentPersonEditDto tournamentPerson) {
        return tournamentPersonRepository.findById(id)
                .map(entity -> tournamentPersonEditMapper.map(tournamentPerson, entity))
                .map(tournamentPersonRepository::saveAndFlush)
                .map(tournamentPersonReadMapper::map);
    }

    @Transactional
    public boolean delete(Long id) {
        return tournamentPersonRepository.findById(id)
                .map(entity -> {
                    tournamentPersonRepository.delete(entity);
                    tournamentPersonRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
