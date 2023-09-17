package vnavesnoj.spring.dto;

import java.time.LocalDateTime;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
public interface BaseTokenReadDto {

    Long getId();

    public String getToken();

    LocalDateTime getCreatedAt();

    UserReadDto getUser();
}
