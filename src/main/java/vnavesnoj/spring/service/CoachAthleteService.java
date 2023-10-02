package vnavesnoj.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import vnavesnoj.spring.database.entity.CoachAthlete;
import vnavesnoj.spring.database.repository.CoachAthleteRepository;
import vnavesnoj.spring.dto.person.coachathlete.AthleteReadDto;
import vnavesnoj.spring.dto.person.coachathlete.CoachAthleteCreateDto;
import vnavesnoj.spring.dto.person.coachathlete.CoachAthleteReadDto;
import vnavesnoj.spring.dto.person.coachathlete.CoachReadDto;
import vnavesnoj.spring.mapper.Mapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Service
@RequiredArgsConstructor
public class CoachAthleteService {

    private final CoachAthleteRepository coachAthleteRepository;

    private final Mapper<CoachAthlete, CoachAthleteReadDto> coachAthleteReadMapper;

    public Optional<CoachAthleteReadDto> findById(Long id) {
        return coachAthleteRepository.findById(id)
                .map(coachAthleteReadMapper::map);
    }

    public Page<AthleteReadDto> findAllAthletesByCoachId(Long id) {
        return Page.empty();
    }

    public Page<CoachReadDto> findAllCoachesByAthleteId(Long id) {
        return Page.empty();
    }

    public CoachAthleteReadDto create(CoachAthleteCreateDto coachAthlete) {
        return null;
    }

    public List<CoachAthleteReadDto> createAll(CoachAthleteCreateDto... coachAthletes) {
        return Collections.emptyList();
    }

    public boolean deleteById(Long id) {
        return false;
    }

    public long deleteAllById(Long... id) {
        return -1L;
    }

    public long deleteAllByCoachId(Long id) {
        return -1L;
    }
}
