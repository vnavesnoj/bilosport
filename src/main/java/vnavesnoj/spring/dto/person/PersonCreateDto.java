package vnavesnoj.spring.dto.person;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;
import vnavesnoj.spring.database.entity.Role;
import vnavesnoj.spring.validation.SportExists;
import vnavesnoj.spring.validation.UserExists;
import vnavesnoj.spring.validation.UserFree;

import java.time.LocalDate;
import java.util.List;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Value
public class PersonCreateDto {

    @Size(max = 64)
    String firstname;

    @Size(max = 64)
    String lastname;

    @Size(max = 64)
    String surname;

    LocalDate birthDate;

    @NotNull
    Role role;

    @SportExists
    List<Integer> sportIds;

    @UserExists
    @UserFree
    Long userId;
}
