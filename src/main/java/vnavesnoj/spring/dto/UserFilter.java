package vnavesnoj.spring.dto;

import lombok.Builder;
import lombok.Value;
import vnavesnoj.spring.database.entity.Role;

import java.time.LocalDate;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Value
@Builder
public class UserFilter {

    String name;
    LocalDate afterBirthDate;
    LocalDate beforeBirthDate;
    Role role;
}
