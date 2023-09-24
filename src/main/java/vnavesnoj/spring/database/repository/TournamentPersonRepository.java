package vnavesnoj.spring.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vnavesnoj.spring.database.entity.TournamentPerson;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
public interface TournamentPersonRepository extends JpaRepository<TournamentPerson, Long> {
}
