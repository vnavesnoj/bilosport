package vnavesnoj.spring.dto.person;

import jakarta.validation.constraints.NotNull;
import lombok.Value;
import vnavesnoj.spring.validation.PersonCoachAthleteFree;
import vnavesnoj.spring.validation.PersonExists;
import vnavesnoj.spring.validation.PersonTournamentFree;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Value
public class PersonDeleteDto {

    @NotNull
    @PersonExists
    @PersonTournamentFree
    @PersonCoachAthleteFree
    Long id;
}
