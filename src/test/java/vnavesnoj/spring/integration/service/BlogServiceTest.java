package vnavesnoj.spring.integration.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import vnavesnoj.spring.dto.BlogCreateEditDto;
import vnavesnoj.spring.dto.BlogInfoReadDto;
import vnavesnoj.spring.dto.BlogReadDto;
import vnavesnoj.spring.integration.IntegrationTestBase;
import vnavesnoj.spring.service.BlogService;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@RequiredArgsConstructor
class BlogServiceTest extends IntegrationTestBase {

    private final BlogService blogService;

    @Test
    void findAllBlogInfo() {
        final List<BlogInfoReadDto> allBlogInfo = blogService.findAllBlogInfo();
        assertThat(allBlogInfo).hasSize(4);
    }

    @Test
    void findById() {
        final Optional<BlogReadDto> maybeBlog = blogService.findById(4);
        assertThat(maybeBlog).isPresent();
    }

    @Test
    void create() {
        final var blogCreateEditDto =
                new BlogCreateEditDto("dummyTitle", "dummyAnno", "dummyBody");
        final var blog = blogService.create(blogCreateEditDto);
        assertThat(blog).isEqualTo(blogService.findById(blog.getId()).orElseThrow());
    }

    @Test
    void update() {
        final var blogDto = new BlogCreateEditDto("Updated", "Updated", "Updated");
        final var updated = blogService.update(4, blogDto);
        assertThat(updated).isPresent().map(
                blog -> assertThat(blog).isEqualTo(blogService.findById(blog.getId()).orElseThrow())
        );
    }

    @Test
//    @Rollback(false)
    void delete() {
        blogService.delete(1);
    }
}