package vnavesnoj.spring.mapper.person;

import com.querydsl.core.types.Predicate;
import org.springframework.stereotype.Component;
import vnavesnoj.spring.database.querydsl.QPredicates;
import vnavesnoj.spring.dto.person.PersonFilter;
import vnavesnoj.spring.mapper.PredicateMapper;

import static vnavesnoj.spring.database.entity.QPerson.person;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Component
public class PersonPredicateMapper implements PredicateMapper<PersonFilter> {

    @Override
    public Predicate map(PersonFilter filter) {
        final var namePredicate = QPredicates.builder()
                .add(filter.getName(), person.firstname::containsIgnoreCase)
                .add(filter.getName(), person.lastname::containsIgnoreCase)
                .add(filter.getName(), person.surname::containsIgnoreCase)
                .buildOr();
        return QPredicates.builder()
                .add(namePredicate)
                .add(filter.getAfterBirthDate(), person.birthDate::goe)
                .add(filter.getBeforeBirthDate(), person.birthDate::loe)
                .add(filter.getRole(), person.role::eq)
                .add(filter.getSportId(), person.sport.any().id::eq)
                .build();
    }
}
