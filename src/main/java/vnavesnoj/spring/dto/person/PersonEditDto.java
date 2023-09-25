package vnavesnoj.spring.dto.person;

import lombok.Value;
import vnavesnoj.spring.database.entity.Role;

import java.time.LocalDate;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Value
public class PersonEditDto {

    String firstname;

    String lastname;

    String surname;

    LocalDate birthDate;

    Role role;

    Long userId;
}
