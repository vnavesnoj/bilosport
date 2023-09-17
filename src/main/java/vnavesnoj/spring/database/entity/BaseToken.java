package vnavesnoj.spring.database.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
public interface BaseToken<ID extends Serializable> {

    ID getId();

    String getToken();

    User getUser();

    LocalDateTime getCreatedAt();

    void setId(Long id);

    void setToken(String token);

    void setUser(User user);

    void setCreatedAt(LocalDateTime createdAt);
}
