package vnavesnoj.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vnavesnoj.spring.dto.tournament.tournamentperson.TournamentPersonCreateDto;
import vnavesnoj.spring.dto.tournament.tournamentperson.TournamentPersonEditDto;
import vnavesnoj.spring.dto.tournament.tournamentperson.TournamentPersonReadDto;

import java.util.Optional;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Service
@RequiredArgsConstructor
public class TournamentPersonService {

    public Optional<TournamentPersonReadDto> findById(Long id) {
        return Optional.empty();
    }

    public Page<TournamentPersonReadDto> findAllPersonsByTournament(Long tournamentId, Pageable pageable) {
        return Page.empty();
    }

    public Page<TournamentPersonReadDto> findAllTournamentsByPerson(Long personId, Pageable pageable) {
        return Page.empty();
    }

    public TournamentPersonReadDto create(TournamentPersonCreateDto tournamentPerson) {
        return null;
    }

    public Optional<TournamentPersonReadDto> update(Long id, TournamentPersonEditDto tournamentPerson) {
        return Optional.empty();
    }

    public boolean delete(Long id) {
        return false;
    }
}
