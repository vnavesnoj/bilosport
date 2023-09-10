package vnavesnoj.spring.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Value;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Value
public class UserCreateDto {

    @Size(min = 4, max = 16)
    String username;

    @Email
    String email;

    @Size(min = 8)
    String rawPassword;
}
