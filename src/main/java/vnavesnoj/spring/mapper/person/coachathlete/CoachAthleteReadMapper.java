package vnavesnoj.spring.mapper.person.coachathlete;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vnavesnoj.spring.database.entity.CoachAthlete;
import vnavesnoj.spring.database.entity.Person;
import vnavesnoj.spring.dto.person.coachathlete.AthleteReadDto;
import vnavesnoj.spring.dto.person.coachathlete.CoachAthleteReadDto;
import vnavesnoj.spring.dto.person.coachathlete.CoachReadDto;
import vnavesnoj.spring.mapper.Mapper;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Component
@RequiredArgsConstructor
public class CoachAthleteReadMapper implements Mapper<CoachAthlete, CoachAthleteReadDto> {

    private final Mapper<Person, CoachReadDto> coachReadMapper;

    private final Mapper<Person, AthleteReadDto> athleteReadDto;

    @Override
    public CoachAthleteReadDto map(CoachAthlete entity) {
        return new CoachAthleteReadDto(
                entity.getId(),
                coachReadMapper.map(entity.getCoach()),
                athleteReadDto.map(entity.getAthlete())
        );
    }
}
