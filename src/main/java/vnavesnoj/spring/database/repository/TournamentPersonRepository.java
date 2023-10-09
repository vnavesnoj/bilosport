package vnavesnoj.spring.database.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import vnavesnoj.spring.database.entity.TournamentPerson;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
public interface TournamentPersonRepository extends JpaRepository<TournamentPerson, Long> {

    boolean existsByPersonId(Long id);

    Page<TournamentPerson> findAllByTournamentId(Long tournamentId, Pageable pageable);

    Page<TournamentPerson> findAllByPersonId(Long personId, Pageable pageable);
}
