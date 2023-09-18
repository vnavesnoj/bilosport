package vnavesnoj.spring.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vnavesnoj.spring.database.entity.ResetPasswordToken;
import vnavesnoj.spring.database.entity.User;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
public interface ResetPasswordTokenRepository extends BaseTokenRepository<ResetPasswordToken>,
        JpaRepository<ResetPasswordToken, Long> {

    @Query("""
            SELECT t FROM ResetPasswordToken t
            WHERE t.token = :token
            AND t.createdAt > :minCreatedAt
            """)
    Optional<ResetPasswordToken> findByToken(String token, LocalDateTime minCreatedAt);


    Optional<ResetPasswordToken> findByToken(String token);

    @Query("""
            SELECT t FROM ResetPasswordToken t
            WHERE t.user = :user
            ORDER BY t.createdAt DESC
            LIMIT 1
            """)
    Optional<ResetPasswordToken> findLastBy(User user);
}
