package vnavesnoj.spring.dto;

import lombok.Value;

import java.time.LocalDateTime;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Value
public class BlogInfoReadDto {

    Integer id;

    String title;

    String announcement;

    LocalDateTime publicationTime;
}
