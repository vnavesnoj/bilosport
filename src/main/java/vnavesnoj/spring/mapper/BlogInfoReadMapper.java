package vnavesnoj.spring.mapper;

import org.springframework.stereotype.Component;
import vnavesnoj.spring.database.entity.Blog;
import vnavesnoj.spring.dto.BlogInfoReadDto;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Component
public class BlogInfoReadMapper implements Mapper<Blog, BlogInfoReadDto> {

    @Override
    public BlogInfoReadDto map(Blog blog) {
        return new BlogInfoReadDto(
                blog.getId(),
                blog.getTitle(),
                blog.getAnnouncement(),
                blog.getPublicationTime()
        );
    }
}
