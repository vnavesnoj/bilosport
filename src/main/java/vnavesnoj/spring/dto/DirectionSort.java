package vnavesnoj.spring.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Getter
@RequiredArgsConstructor
public enum DirectionSort {

    ASC("filter.sort.asc"),
    DESC("filter.sort.desc");

    private final String messageSource;
}
