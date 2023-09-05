package vnavesnoj.spring.mapper;

import org.springframework.stereotype.Component;
import vnavesnoj.spring.dto.BlogReadDto;
import vnavesnoj.spring.dto.BlogViewReadDto;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Component
public class BlogViewReadMapper implements Mapper<BlogReadDto, BlogViewReadDto> {

    @Override
    public BlogViewReadDto map(BlogReadDto blog) {
        return new BlogViewReadDto(
                blog.getId(),
                blog.getTitle(),
                blog.getAnnouncement(),
                blog.getPublicationTime(),
                blog.getBlogBody().getBody().lines().toList()
        );
    }
}
