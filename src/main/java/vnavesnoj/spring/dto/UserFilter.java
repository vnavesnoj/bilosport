package vnavesnoj.spring.dto;

import com.querydsl.core.types.Order;
import lombok.Builder;
import lombok.Data;
import vnavesnoj.spring.database.entity.Role;

import java.time.LocalDate;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Data
@Builder
public class UserFilter {

    private String name;
    private LocalDate afterBirthDate;
    private LocalDate beforeBirthDate;
    private Role role;
    private Integer sportId;
    private SortBy sortBy;
    private Order order;
}
