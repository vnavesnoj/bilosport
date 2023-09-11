package vnavesnoj.spring.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;
import lombok.experimental.FieldNameConstants;
import vnavesnoj.spring.validation.UniqueUsername;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Value
@FieldNameConstants
public class UserCreateDto {

    @UniqueUsername
    @Size(min = 4, max = 16)
    @NotNull
    String username;

    @Email
    @NotNull
    String email;

    @Size(min = 8)
    @NotNull
    String rawPassword;
}
