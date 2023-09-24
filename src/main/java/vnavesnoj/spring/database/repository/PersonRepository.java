package vnavesnoj.spring.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vnavesnoj.spring.database.entity.Person;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
public interface PersonRepository extends JpaRepository<Person, Long> {
}
