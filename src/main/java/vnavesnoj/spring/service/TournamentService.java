package vnavesnoj.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vnavesnoj.spring.database.entity.Tournament;
import vnavesnoj.spring.dto.TournamentCreateEditDto;
import vnavesnoj.spring.dto.TournamentReadDto;
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

    private final JpaRepository<Tournament, Long> tournamentRepository;

    private final Mapper<Tournament, TournamentReadDto> tournamentReadMapper;

    private final Mapper<TournamentCreateEditDto, Tournament> tournamentCreateEditMapper;

    public Optional<TournamentReadDto> findById(Long id) {
        return tournamentRepository.findById(id)
                .map(tournamentReadMapper::map);
    }

    @Transactional
    public TournamentReadDto create(TournamentCreateEditDto tournamentDto) {
        return Optional.of(tournamentDto)
                .map(tournamentCreateEditMapper::map)
                .map(tournamentRepository::save)
                .map(tournamentReadMapper::map)
                .orElseThrow();

    }
}
