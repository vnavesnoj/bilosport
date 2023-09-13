package vnavesnoj.spring.dto;

import lombok.Value;

import java.time.LocalDateTime;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Value
public class VerificationTokenReadDto {

    Long id;

    String token;

    LocalDateTime createdAt;

    UserReadDto user;
}
