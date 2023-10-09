package vnavesnoj.spring.database.repository;

import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vnavesnoj.spring.database.entity.TournamentPerson;

import java.util.Optional;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
public interface TournamentPersonRepository extends JpaRepository<TournamentPerson, Long> {

    boolean existsByPersonId(Long id);

    @Query("""
            SELECT tp
            FROM TournamentPerson tp
            JOIN FETCH tp.person
            JOIN FETCH tp.tournament
            LEFT JOIN FETCH tp.tournamentResult
            LEFT JOIN FETCH tp.person.user
            LEFT JOIN FETCH tp.tournament.sport
            WHERE tp.tournament.id = :tournamentId
            """)
    Page<TournamentPerson> findAllByTournamentId(Long tournamentId, Pageable pageable);

    @Query("""
            SELECT tp
            FROM TournamentPerson tp
            JOIN FETCH tp.person
            JOIN FETCH tp.tournament
            LEFT JOIN FETCH tp.tournamentResult
            LEFT JOIN FETCH tp.person.user
            LEFT JOIN FETCH tp.tournament.sport
            WHERE tp.person.id = :personId
            """)
    Page<TournamentPerson> findAllByPersonId(Long personId, Pageable pageable);

    @Query("""
            SELECT tp
            FROM TournamentPerson tp
            JOIN FETCH tp.person
            JOIN FETCH tp.tournament
            LEFT JOIN FETCH tp.tournamentResult
            LEFT JOIN FETCH tp.person.user
            LEFT JOIN FETCH tp.tournament.sport
            WHERE tp.id = :id
            """)
    @NonNull
    @Override
    Optional<TournamentPerson> findById(@NonNull Long id);
}
