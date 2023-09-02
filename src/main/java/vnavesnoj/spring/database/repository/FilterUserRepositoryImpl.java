package vnavesnoj.spring.database.repository;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import vnavesnoj.spring.database.entity.User;
import vnavesnoj.spring.database.querydsl.QPredicates;
import vnavesnoj.spring.dto.UserFilter;

import java.util.List;

import static vnavesnoj.spring.database.entity.QUser.user;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@RequiredArgsConstructor
public class FilterUserRepositoryImpl implements FilterUserRepository {

    private final EntityManager entityManager;

    @Override
    public List<User> findAllByFilter(UserFilter filter) {
        final Predicate predicate = createPredicateByFilter(filter);
        final var query = new JPAQuery<User>(entityManager)
                .select(user)
                .from(user)
                .where(predicate);
        if (filter.getSortBy() != null) {
            final OrderSpecifier<?> orderSpecifier = createOrderByFilter(filter);
            query.orderBy(orderSpecifier);
        }
        return query.fetch();
    }

    private static OrderSpecifier<?> createOrderByFilter(UserFilter filter) {
        Order order = Order.ASC;
        if (filter.getOrder() != null) {
            order = filter.getOrder();
        }
        final var fieldPath = Expressions.path(Comparable.class, user, filter.getSortBy().getProperty());
        return new OrderSpecifier<>(order, fieldPath);
    }

    private static Predicate createPredicateByFilter(UserFilter filter) {
        final var namePredicate = QPredicates.builder()
                .add(filter.getName(), user.firstname::containsIgnoreCase)
                .add(filter.getName(), user.lastname::containsIgnoreCase)
                .add(filter.getName(), user.surname::containsIgnoreCase)
                .buildOr();
        return QPredicates.builder()
                .add(namePredicate)
                .add(filter.getBeforeBirthDate(), user.birthDate::before)
                .add(filter.getAfterBirthDate(), user.birthDate::after)
                .add(filter.getRole(), user.role::eq)
                .add(filter.getSportId(), user.userSports.any().sport.id::eq)
                .build();
    }
}
