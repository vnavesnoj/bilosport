package vnavesnoj.spring.mapper.person.coachathlete;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vnavesnoj.spring.database.entity.Person;
import vnavesnoj.spring.database.entity.Sport;
import vnavesnoj.spring.dto.SportReadDto;
import vnavesnoj.spring.dto.person.coachathlete.CoachReadDto;
import vnavesnoj.spring.mapper.Mapper;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Component
@RequiredArgsConstructor
public class CoachReadMapper implements Mapper<Person, CoachReadDto> {

    private final Mapper<Sport, SportReadDto> sportReadMapper;

    @Override
    public CoachReadDto map(Person entity) {
        return new CoachReadDto(
                entity.getId(),
                entity.getFirstname(),
                entity.getLastname(),
                Optional.ofNullable(entity.getSport())
                        .map(sports -> sports.stream()
                                .map(sportReadMapper::map)
                                .collect(Collectors.toSet()))
                        .orElse(null),
                entity.getUser().getUsername()
        );
    }
}
