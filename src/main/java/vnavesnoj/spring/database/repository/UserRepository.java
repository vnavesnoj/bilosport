package vnavesnoj.spring.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import vnavesnoj.spring.database.entity.Role;
import vnavesnoj.spring.database.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
public interface UserRepository extends
        JpaRepository<User, Long>,
        QuerydslPredicateExecutor<User> {

    List<User> findAllByRole(Role role);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    @Query("""
            SELECT u FROM User u
            WHERE u.username = :usernameOrEmail
            OR u.email = :usernameOrEmail
            """)
    Optional<User> findByUsernameOrEmail(String usernameOrEmail);

    boolean existsByEmail(String email);
}
