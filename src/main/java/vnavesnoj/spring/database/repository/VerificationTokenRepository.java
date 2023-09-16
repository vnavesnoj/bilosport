package vnavesnoj.spring.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vnavesnoj.spring.database.entity.User;
import vnavesnoj.spring.database.entity.VerificationToken;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    @Query("""
            SELECT t FROM VerificationToken t
            WHERE t.token = :token
            AND t.createdAt > :minCreatedAt
            """)
    Optional<VerificationToken> findByToken(String token, LocalDateTime minCreatedAt);


    Optional<VerificationToken> findByToken(String token);

    Optional<VerificationToken> findByUser(User user);

    Optional<VerificationToken> findByUserId(Long id);
}
