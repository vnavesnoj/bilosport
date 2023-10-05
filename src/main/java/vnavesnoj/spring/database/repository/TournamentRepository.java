package vnavesnoj.spring.database.repository;

import com.querydsl.core.types.Predicate;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import vnavesnoj.spring.database.entity.Tournament;

import java.util.Optional;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
public interface TournamentRepository extends
        JpaRepository<Tournament, Long>,
        QuerydslPredicateExecutor<Tournament> {

    @EntityGraph("Tournament.sport")
    @NonNull
    @Override
    Optional<Tournament> findById(@NonNull Long id);

    @EntityGraph(value = "Tournament.sport")
    @NonNull
    @Override
    Page<Tournament> findAll(@NonNull Predicate predicate, @NonNull Pageable pageable);
}
