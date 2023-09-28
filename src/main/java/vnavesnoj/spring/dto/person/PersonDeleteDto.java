package vnavesnoj.spring.dto.person;

import lombok.Value;
import vnavesnoj.spring.validation.PersonExists;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Value
public class PersonDeleteDto {

    @PersonExists
//    @PersonNotBusy
    Long id;
}
