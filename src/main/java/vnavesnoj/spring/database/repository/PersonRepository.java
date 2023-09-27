package vnavesnoj.spring.database.repository;

import com.querydsl.core.types.Predicate;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import vnavesnoj.spring.database.entity.Person;

import java.util.Optional;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
public interface PersonRepository extends
        JpaRepository<Person, Long>,
        QuerydslPredicateExecutor<Person> {

    @EntityGraph("Person.user.sports")
    @NonNull
    @Override
    Page<Person> findAll(@NonNull Predicate predicate, @NonNull Pageable pageable);

    Optional<Person> findByUserId(Long userId);
}
