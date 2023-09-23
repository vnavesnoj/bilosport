package vnavesnoj.spring.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
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
}
