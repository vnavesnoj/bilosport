package vnavesnoj.spring.dto.person;

import lombok.Value;
import vnavesnoj.spring.database.entity.Role;
import vnavesnoj.spring.dto.SportReadDto;

import java.time.LocalDate;
import java.util.Set;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Value
public class PersonReadDto {

    Long id;
    String firstname;
    String lastname;
    String surname;
    LocalDate birthDate;
    Role role;
    Set<SportReadDto> sports;
    String username;
}
