package vnavesnoj.spring.dto.tournament;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static vnavesnoj.spring.database.entity.QTournament.tournament;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Getter
@RequiredArgsConstructor
public enum TournamentSortBy {

    DATE(tournament.tournamentDate.getMetadata().getName(), "filter.tournaments.sortBy.date");

    private final String property;

    private final String messageSource;
}
