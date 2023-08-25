package vnavesnoj.spring.dto;

import lombok.Value;
import vnavesnoj.spring.database.entity.Role;
import vnavesnoj.spring.database.entity.Sport;
import vnavesnoj.spring.database.entity.Tournament;

import java.time.LocalDate;
import java.util.Set;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Value
public class UserReadDto {

    Long id;
    String username;
    String firstname;
    String lastname;
    String surname;
    LocalDate birthdate;
    Role role;
    Set<Sport> sports;
    Set<Tournament> tournaments;
    String image;
}
