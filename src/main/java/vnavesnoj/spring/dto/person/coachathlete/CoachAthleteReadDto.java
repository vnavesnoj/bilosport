package vnavesnoj.spring.dto.person.coachathlete;

import lombok.Value;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Value
public class CoachAthleteReadDto {

    Long id;

    CoachReadDto coach;

    AthleteReadDto athlete;
}
