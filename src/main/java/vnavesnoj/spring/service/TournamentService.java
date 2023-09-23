package vnavesnoj.spring.service;

import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vnavesnoj.spring.database.entity.Tournament;
import vnavesnoj.spring.database.repository.TournamentRepository;
import vnavesnoj.spring.dto.TournamentFilter;
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

    private final TournamentRepository tournamentRepository;

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
}
