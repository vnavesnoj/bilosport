package vnavesnoj.spring.dto.person.coachathlete;

import lombok.Value;
import vnavesnoj.spring.dto.SportReadDto;

import java.util.Set;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Value
public class CoachReadDto {

    Long id;

    String firstname;

    String lastname;

    Set<SportReadDto> sports;

    String username;
}
