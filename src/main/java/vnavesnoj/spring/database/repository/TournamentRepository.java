package vnavesnoj.spring.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vnavesnoj.spring.database.entity.Tournament;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
public interface TournamentRepository extends JpaRepository<Tournament, Long> {
}
