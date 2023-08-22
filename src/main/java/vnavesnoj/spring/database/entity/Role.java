package vnavesnoj.spring.database.entity;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
public enum Role implements GrantedAuthority {

    ATHLETE, COACH, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
