package vnavesnoj.spring.dto;

import lombok.Getter;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Getter
public enum TournamentSortBy {

    NAME("name"), DATE("tournamentDate");
    private final String property;

    TournamentSortBy(String property) {
        this.property = property;
    }
}
