package vnavesnoj.spring.dto;

import lombok.Value;
import org.springframework.web.multipart.MultipartFile;
import vnavesnoj.spring.database.entity.Role;

import java.time.LocalDate;
import java.util.List;

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

    List<Integer> sportIds;

    MultipartFile image;
}
