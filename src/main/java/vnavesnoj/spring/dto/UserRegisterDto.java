package vnavesnoj.spring.dto;

import lombok.Value;

import java.util.Locale;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Value
public class UserRegisterDto {

    UserCreateDto userCreateDto;

    Locale locale;

    String appUrl;
}
