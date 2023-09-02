package vnavesnoj.spring.dto;

import lombok.Getter;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Getter
public enum SortBy {


    LASTNAME("lastname"), BIRTH_DATE("birthDate");

    private final String property;

    SortBy(String property) {
        this.property = property;
    }
}
