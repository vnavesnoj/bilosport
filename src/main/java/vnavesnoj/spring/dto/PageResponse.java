package vnavesnoj.spring.dto;

import lombok.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Value
public class PageResponse<T> {
    List<T> content;
    Metadata metadata;

    public static <T> PageResponse<T> of(Page<T> page) {
        final Optional<Sort.Order> order = page.getSort().get().findFirst();
        final var metadata = new Metadata(
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                order.map(Sort.Order::getProperty).orElse(null),
                order.map(Sort.Order::getDirection).orElse(null));
        return new PageResponse<>(page.getContent(), metadata);
    }

    @Value
    public static class Metadata {
        int page;
        int size;
        long totalElements;
        String sortBy;
        Sort.Direction direction;
    }
}
