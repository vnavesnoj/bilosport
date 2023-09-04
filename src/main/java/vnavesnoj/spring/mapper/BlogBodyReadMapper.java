package vnavesnoj.spring.mapper;

import org.springframework.stereotype.Component;
import vnavesnoj.spring.database.entity.BlogBody;
import vnavesnoj.spring.dto.BlogBodyReadDto;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Component
public class BlogBodyReadMapper implements Mapper<BlogBody, BlogBodyReadDto> {

    @Override
    public BlogBodyReadDto map(BlogBody blogBody) {
        return new BlogBodyReadDto(
                blogBody.getId(),
                blogBody.getBody()
        );
    }
}
