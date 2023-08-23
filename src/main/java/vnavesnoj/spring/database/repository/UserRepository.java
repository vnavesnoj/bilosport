package vnavesnoj.spring.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vnavesnoj.spring.database.entity.Role;
import vnavesnoj.spring.database.entity.User;

import java.util.List;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByRole(Role role);
}
