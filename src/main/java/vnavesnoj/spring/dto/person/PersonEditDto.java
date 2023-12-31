package vnavesnoj.spring.dto.person;

import jakarta.validation.constraints.Size;
import lombok.Value;
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
public class PersonEditDto {

    @Size(max = 64)
    String firstname;

    @Size(max = 64)
    String lastname;

    @Size(max = 64)
    String surname;

    LocalDate birthDate;

    @SportExists
    List<Integer> sportId;

    @UserExists
    @UserFree
    Long userId;
}
