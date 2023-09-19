package vnavesnoj.spring.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;
import lombok.experimental.FieldNameConstants;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Value
@FieldNameConstants
public class UserEditPasswordDto {

    String resetPasswordToken;

    @Size(min = 8, max = 64,
            message = "Пароль має бути від 8 до 64 символів.")
    @NotNull(message = "Пароль не має бути пустим.")
    String rawPassword;
}
