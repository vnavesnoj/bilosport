package vnavesnoj.spring.mapper;

import org.springframework.stereotype.Component;
import vnavesnoj.spring.database.entity.Blog;
import vnavesnoj.spring.database.entity.BlogBody;
import vnavesnoj.spring.dto.BlogCreateEditDto;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Component
public class BlogCreateEditMapper implements Mapper<BlogCreateEditDto, Blog> {

    @Override
    public Blog map(BlogCreateEditDto blogDto) {
        final Blog blog = new Blog();
        copy(blogDto, blog);
        return blog;
    }

    @Override
    public Blog map(BlogCreateEditDto blogDto, Blog blog) {
        copy(blogDto, blog);
        return blog;
    }

    private void copy(BlogCreateEditDto blogDto, Blog blog) {
        blog.setTitle(blogDto.getTitle());
        blog.setAnnouncement(blogDto.getAnnouncement());
        if (blog.getBlogBody() != null) {
            blog.getBlogBody().setBody(blogDto.getBody());
        } else {
            final BlogBody blogBody = BlogBody.builder()
                    .body(blogDto.getBody())
                    .build();
            blog.setBlogBody(blogBody);
        }
    }
}
