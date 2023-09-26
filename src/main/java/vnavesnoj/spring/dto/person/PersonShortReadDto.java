package vnavesnoj.spring.dto.person;

import lombok.Value;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Value
public class PersonShortReadDto {

    Long id;

    String firstname;

    String lastname;

    String username;
}
