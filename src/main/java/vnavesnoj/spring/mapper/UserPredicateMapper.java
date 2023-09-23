package vnavesnoj.spring.mapper;

import com.querydsl.core.types.Predicate;
import org.springframework.stereotype.Component;
import vnavesnoj.spring.database.querydsl.QPredicates;
import vnavesnoj.spring.dto.UserFilter;

import static vnavesnoj.spring.database.entity.QUser.user;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Component
public class UserPredicateMapper implements PredicateMapper<UserFilter> {

    @Override
    public Predicate map(UserFilter filter) {
        final var namePredicate = QPredicates.builder()
                .add(filter.getName(), user.firstname::containsIgnoreCase)
                .add(filter.getName(), user.lastname::containsIgnoreCase)
                .add(filter.getName(), user.surname::containsIgnoreCase)
                .buildOr();
        return QPredicates.builder()
                .add(namePredicate)
                .add(filter.getAfterBirthDate(), user.birthDate::goe)
                .add(filter.getBeforeBirthDate(), user.birthDate::loe)
                .add(filter.getRole(), user.role::eq)
                .build();
    }
}
