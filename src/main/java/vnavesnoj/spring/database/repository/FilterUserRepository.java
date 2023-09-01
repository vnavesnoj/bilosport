package vnavesnoj.spring.database.repository;

import org.springframework.stereotype.Repository;
import vnavesnoj.spring.database.entity.User;
import vnavesnoj.spring.dto.UserFilter;

import java.util.List;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Repository
public interface FilterUserRepository {

    List<User> findAllByFilter(UserFilter filter);
}
