package vnavesnoj.spring.database.repository;

import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vnavesnoj.spring.database.entity.CoachAthlete;

import java.util.List;
import java.util.Optional;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
public interface CoachAthleteRepository extends JpaRepository<CoachAthlete, Long> {

    @EntityGraph("CoachAthlete.person.user")
    Page<CoachAthlete> findAllByCoachId(Long id, Pageable pageable);

    List<CoachAthlete> findAllByCoachId(Long id);

    @EntityGraph("CoachAthlete.person.user")
    Page<CoachAthlete> findAllByAthleteId(Long id, Pageable pageable);

    @Query("""
            SELECT exists(SELECT ca
            FROM CoachAthlete ca
            WHERE ca.coach.id = :id
            OR
            ca.athlete.id = :id)
            """)
    boolean existsByCoachOrAthleteId(Long id);

    @EntityGraph("CoachAthlete.person.user")
    @NonNull
    @Override
    Optional<CoachAthlete> findById(@NonNull Long aLong);
}
