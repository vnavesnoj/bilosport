package vnavesnoj.spring.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vnavesnoj.spring.database.entity.Blog;
import vnavesnoj.spring.database.entity.BlogBody;
import vnavesnoj.spring.dto.BlogBodyReadDto;
import vnavesnoj.spring.dto.BlogReadDto;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Component
@RequiredArgsConstructor
public class BlogReadMapper implements Mapper<Blog, BlogReadDto> {

    private final Mapper<BlogBody, BlogBodyReadDto> blogBodyReadMapper;

    @Override
    public BlogReadDto map(Blog blog) {
        return new BlogReadDto(
                blog.getId(),
                blog.getTitle(),
                blog.getAnnouncement(),
                blog.getPublicationTime(),
                blogBodyReadMapper.map(blog.getBlogBody())
        );
    }
}
