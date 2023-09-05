package vnavesnoj.spring.dto;

import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Value
public class BlogViewReadDto {

    Integer id;

    String title;

    String announcement;

    LocalDateTime publicationTime;

    List<String> lines;
}
