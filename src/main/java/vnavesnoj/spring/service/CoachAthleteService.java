package vnavesnoj.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vnavesnoj.spring.database.entity.CoachAthlete;
import vnavesnoj.spring.database.repository.CoachAthleteRepository;
import vnavesnoj.spring.dto.person.coachathlete.AthleteReadDto;
import vnavesnoj.spring.dto.person.coachathlete.CoachAthleteCreateDto;
import vnavesnoj.spring.dto.person.coachathlete.CoachAthleteReadDto;
import vnavesnoj.spring.dto.person.coachathlete.CoachReadDto;
import vnavesnoj.spring.mapper.Mapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
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

    @Transactional
    public CoachAthleteReadDto create(CoachAthleteCreateDto coachAthlete) {
        return Optional.of(coachAthlete)
                .map(coachAthleteCreateMapper::map)
                .map(coachAthleteRepository::save)
                .map(coachAthleteReadMapper::map)
                .orElseThrow();
    }

    //TODO поиск самих сущностей для вставки можно оптимизировать
    @Transactional
    public List<CoachAthleteReadDto> createAll(CoachAthleteCreateDto... coachAthletes) {
        return Optional.of(coachAthletes)
                .map(Arrays::asList)
                .map(list -> list.stream()
                        .map(coachAthleteCreateMapper::map)
                        .toList())
                .map(coachAthleteRepository::saveAll)
                .map(list -> list.stream()
                        .map(coachAthleteReadMapper::map)
                        .toList())
                .orElseThrow();
    }

    @Transactional
    public boolean deleteById(Long id) {
        return coachAthleteRepository.findById(id)
                .map(entity -> {
                    coachAthleteRepository.delete(entity);
                    coachAthleteRepository.flush();
                    return true;
                })
                .orElse(false);
    }

    @Transactional
    public long deleteAllById(Long... id) {
        return Optional.of(coachAthleteRepository.findAllById(List.of(id)))
                .map(entities -> {
                    coachAthleteRepository.deleteAll(entities);
                    coachAthleteRepository.flush();
                    return entities.size();
                })
                .orElseThrow();
    }

    @Transactional
    public long deleteAllByCoachId(Long id) {
        return Optional.of(coachAthleteRepository.findAllByCoachId(id))
                .map(entities -> {
                    coachAthleteRepository.deleteAll(entities);
                    coachAthleteRepository.flush();
                    return entities.size();
                })
                .orElseThrow();
    }
}
