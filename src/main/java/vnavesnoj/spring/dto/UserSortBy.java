package vnavesnoj.spring.dto;

import lombok.Getter;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Getter
public enum UserSortBy {


    LASTNAME("lastname"), BIRTH_DATE("birthDate");

    private final String property;

    UserSortBy(String property) {
        this.property = property;
    }
}
