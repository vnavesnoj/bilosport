package vnavesnoj.spring.dto;

import lombok.Getter;

import static vnavesnoj.spring.database.entity.QTournament.tournament;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Getter
public enum TournamentSortBy {

    DATE(tournament.tournamentDate.getMetadata().getName());
    private final String property;

    TournamentSortBy(String property) {
        this.property = property;
    }
}
