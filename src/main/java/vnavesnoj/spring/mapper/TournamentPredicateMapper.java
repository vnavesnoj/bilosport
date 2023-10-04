package vnavesnoj.spring.mapper;

import com.querydsl.core.types.Predicate;
import org.springframework.stereotype.Component;
import vnavesnoj.spring.database.querydsl.QPredicates;
import vnavesnoj.spring.dto.tournament.TournamentFilter;

import static vnavesnoj.spring.database.entity.QTournament.tournament;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Component
public class TournamentPredicateMapper implements PredicateMapper<TournamentFilter> {

    @Override
    public Predicate map(TournamentFilter filter) {
        return QPredicates.builder()
                .add(filter.getName(), tournament.name::containsIgnoreCase)
                .add(filter.getAfterDate(), tournament.tournamentDate::goe)
                .add(filter.getBeforeDate(), tournament.tournamentDate::loe)
                .add(filter.getSportId(), tournament.sport.id::eq)
                .build();
    }
}
