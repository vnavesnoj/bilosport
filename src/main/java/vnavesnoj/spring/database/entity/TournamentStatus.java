package vnavesnoj.spring.database.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Getter
@RequiredArgsConstructor
public enum TournamentStatus {

    PREPARATION("tournament.status.preparation"),
    FINISHED("tournament.status.finished"),
    CANCELED("tournament.status.canceled");

    private final String messageSource;
}
