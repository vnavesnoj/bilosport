package vnavesnoj.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vnavesnoj.spring.database.entity.Tournament;
import vnavesnoj.spring.database.repository.TournamentRepository;
import vnavesnoj.spring.dto.TournamentCreateEditDto;
import vnavesnoj.spring.dto.TournamentReadDto;
import vnavesnoj.spring.mapper.Mapper;

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

    private final Mapper<TournamentCreateEditDto, Tournament> tournamentCreateReadMapper;

}
