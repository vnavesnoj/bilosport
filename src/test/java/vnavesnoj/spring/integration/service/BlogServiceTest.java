package vnavesnoj.spring.integration.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
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
}