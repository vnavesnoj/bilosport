package vnavesnoj.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    private final Mapper<CoachAthleteCreateDto, CoachAthlete> coachAthleteCreateMapper;

    public Optional<CoachAthleteReadDto> findById(Long id) {
        return coachAthleteRepository.findById(id)
                .map(coachAthleteReadMapper::map);
    }

    //TODO проблема с 1 + N при фетче sports. Нужно лучше определиться с логикой приложения для дальнейших действий
    public Page<CoachAthleteReadDto> findAllByCoachId(Long id, Pageable pageable) {
        return coachAthleteRepository.findAllByCoachId(id, pageable)
                .map(coachAthleteReadMapper::map);
    }

    public Page<CoachAthleteReadDto> findAllByAthleteId(Long id, Pageable pageable) {
        return coachAthleteRepository.findAllByAthleteId(id, pageable)
                .map(coachAthleteReadMapper::map);
    }

    public Page<AthleteReadDto> findAllAthletesByCoachId(Long id, Pageable pageable) {
        return findAllByCoachId(id, pageable)
                .map(CoachAthleteReadDto::getAthlete);
    }

    public Page<CoachReadDto> findAllCoachesByAthleteId(Long id, Pageable pageable) {
        return findAllByAthleteId(id, pageable)
                .map(CoachAthleteReadDto::getCoach);
    }

    public CoachAthleteReadDto create(CoachAthleteCreateDto coachAthlete) {
        return Optional.of(coachAthlete)
                .map(coachAthleteCreateMapper::map)
                .map(coachAthleteRepository::save)
                .map(coachAthleteReadMapper::map)
                .orElseThrow();
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
