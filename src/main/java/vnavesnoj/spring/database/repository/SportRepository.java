package vnavesnoj.spring.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vnavesnoj.spring.database.entity.Sport;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
public interface SportRepository extends JpaRepository<Sport, Integer> {
}
