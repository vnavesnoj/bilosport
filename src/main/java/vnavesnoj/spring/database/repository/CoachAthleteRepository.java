package vnavesnoj.spring.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vnavesnoj.spring.database.entity.CoachAthlete;
import vnavesnoj.spring.database.entity.Person;

import java.util.List;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
public interface CoachAthleteRepository extends JpaRepository<CoachAthlete, Long> {

    List<CoachAthlete> findAllByCoachId(Long id);

    List<CoachAthlete> findAllByAthlete(Person id);

    @Query("""
            SELECT exists(CoachAthlete)
            FROM CoachAthlete
            WHERE CoachAthlete.coach.id = :id
            OR
            CoachAthlete.athlete.id = :id
            """)
    boolean existsByCoachOrAthleteId(Long id);
}
