package vnavesnoj.spring.integration.repository;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Rollback;
import vnavesnoj.spring.database.repository.BlogRepository;
import vnavesnoj.spring.integration.IntegrationTestBase;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@RequiredArgsConstructor
class BlogRepositoryTest extends IntegrationTestBase {

    private final BlogRepository blogRepository;

    @Test
    @Rollback(value = false)
    public void saveBlog() {
//        final var blog = Blog.builder()
//                .title("tittle")
//                .announcement("announcement")
//                .publicationTime(LocalDateTime.now())
//                .blogBody(BlogBody.builder()
//                        .body("body")
//                        .build())
//                        .build();
//        final Blog savedBlog = blogRepository.save(blog);
//        final Optional<Blog> byId = blogRepository.findById(savedBlog.getId());
//        System.out.println();
    }
}