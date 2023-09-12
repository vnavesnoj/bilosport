package vnavesnoj.spring.dto;

import lombok.Value;
import vnavesnoj.spring.database.entity.Role;

import java.time.LocalDate;
import java.util.List;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Value
public class UserReadDto {

    Long id;
    String username;
    String email;
    String firstname;
    String lastname;
    String surname;
    LocalDate birthDate;
    Role role;
    List<SportReadDto> sports;
    String image;
}
