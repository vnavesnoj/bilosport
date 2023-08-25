package vnavesnoj.spring.dto;

import lombok.Value;
import org.springframework.web.multipart.MultipartFile;
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
public class UserCreateEditDto {

    String username;

    String rawPassword;

    String firstname;

    String lastname;

    String surname;

    LocalDate birthDate;

    Role role;

    Set<Sport> sports;

    Set<Tournament> tournaments;

    MultipartFile image;
}
