package vnavesnoj.spring.database.repository;

import vnavesnoj.spring.database.entity.BaseToken;
import vnavesnoj.spring.database.entity.User;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
public interface BaseTokenRepository<T extends BaseToken<? extends Serializable>> {

    T save(T entity);

    Optional<T> findByToken(String token, LocalDateTime minCreatedAt);


    Optional<T> findByToken(String token);

    Optional<T> findLastBy(User user);
}
