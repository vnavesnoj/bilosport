package vnavesnoj.spring.database.repository;

import lombok.NonNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vnavesnoj.spring.database.entity.CoachAthlete;
import vnavesnoj.spring.database.entity.Person;

import java.util.List;
import java.util.Optional;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
public interface CoachAthleteRepository extends JpaRepository<CoachAthlete, Long> {

    List<CoachAthlete> findAllByCoachId(Long id);

    List<CoachAthlete> findAllByAthlete(Person id);

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
