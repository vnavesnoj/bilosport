package vnavesnoj.spring.dto.person;

import lombok.Value;
import vnavesnoj.spring.database.entity.Role;

import java.time.LocalDate;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Value
public class PersonFilter {

    String name;

    LocalDate afterBirthDate;

    LocalDate beforeBirthDate;

    Role role;
}
