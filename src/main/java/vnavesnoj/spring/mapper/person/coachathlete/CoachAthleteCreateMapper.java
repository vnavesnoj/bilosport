package vnavesnoj.spring.mapper.person.coachathlete;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vnavesnoj.spring.database.entity.CoachAthlete;
import vnavesnoj.spring.database.repository.PersonRepository;
import vnavesnoj.spring.dto.person.coachathlete.CoachAthleteCreateDto;
import vnavesnoj.spring.mapper.Mapper;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Component
@RequiredArgsConstructor
public class CoachAthleteCreateMapper implements Mapper<CoachAthleteCreateDto, CoachAthlete> {

    private final PersonRepository personRepository;

    @Override
    public CoachAthlete map(CoachAthleteCreateDto object) {
        return CoachAthlete.builder()
                .coach(personRepository.findById(object.getCoachId()).orElseThrow())
                .athlete(personRepository.findById(object.getAthleteId()).orElseThrow())
                .build();
    }
}
