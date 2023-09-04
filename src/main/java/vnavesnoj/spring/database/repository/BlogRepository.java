package vnavesnoj.spring.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vnavesnoj.spring.database.entity.Blog;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {
}
