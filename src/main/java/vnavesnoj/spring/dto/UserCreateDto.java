package vnavesnoj.spring.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;
import lombok.experimental.FieldNameConstants;
import vnavesnoj.spring.validation.UniqueEmail;
import vnavesnoj.spring.validation.UniqueUsername;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Value
@FieldNameConstants
public class UserCreateDto {

    @UniqueUsername(message = "Користувач ${validatedValue} вже існує.")
    @Size(min = 4, max = 16,
            message = "Username має бути від 4 до 16 символів.")
    @NotNull(message = "Username не має бути пустим.")
    String username;

    @UniqueEmail(message = "Користувач з поштою ${validatedValue} вже існує.")
    @Email(message = "Це має бути електронна пошта.")
    @NotNull(message = "Email не має бути пустим.")
    String email;

    @Size(min = 8, max = 64,
            message = "Пароль має бути від 8 до 64 символів.")
    @NotNull(message = "Пароль не має бути пустим.")
    String rawPassword;

    String registrationToken;
}
