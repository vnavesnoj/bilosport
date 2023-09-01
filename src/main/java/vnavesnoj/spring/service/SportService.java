package vnavesnoj.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vnavesnoj.spring.database.entity.Sport;
import vnavesnoj.spring.database.repository.SportRepository;
import vnavesnoj.spring.dto.SportReadDto;
import vnavesnoj.spring.mapper.Mapper;

import java.util.List;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SportService {

    private final SportRepository sportRepository;
    private final Mapper<Sport, SportReadDto> sportReadMapper;

    public List<SportReadDto> findAll() {
        return sportRepository.findAll().stream()
                .map(sportReadMapper::map)
                .toList();
    }
}
