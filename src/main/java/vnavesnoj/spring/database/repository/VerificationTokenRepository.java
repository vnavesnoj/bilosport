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
public interface VerificationTokenRepository extends BaseTokenRepository<VerificationToken>,
        JpaRepository<VerificationToken, Long> {

    @Query("""
            SELECT t FROM VerificationToken t
            WHERE t.token = :token
            AND t.createdAt > :minCreatedAt
            """)
    Optional<VerificationToken> findByToken(String token, LocalDateTime minCreatedAt);


    Optional<VerificationToken> findByToken(String token);

    @Query("""
            SELECT t FROM VerificationToken t
            WHERE t.user = :user
            ORDER BY t.createdAt DESC
            LIMIT 1
            """)
    Optional<VerificationToken> findLastBy(User user);
}
