package vnavesnoj.spring.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vnavesnoj.spring.database.entity.VerificationToken;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {


}
