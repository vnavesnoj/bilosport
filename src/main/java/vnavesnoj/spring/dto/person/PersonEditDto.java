package vnavesnoj.spring.dto.person;

import lombok.Value;

import java.time.LocalDate;
import java.util.List;

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

    List<Integer> sportId;

    Long userId;
}