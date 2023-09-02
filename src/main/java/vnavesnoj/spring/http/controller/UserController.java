package vnavesnoj.spring.http.controller;

import com.querydsl.core.types.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import vnavesnoj.spring.database.entity.Role;
import vnavesnoj.spring.dto.PageResponse;
import vnavesnoj.spring.dto.SortBy;
import vnavesnoj.spring.dto.UserFilter;
import vnavesnoj.spring.dto.UserReadDto;
import vnavesnoj.spring.service.SportService;
import vnavesnoj.spring.service.UserService;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final SportService sportService;

    @GetMapping
    public String findAll(Model model,
                          UserFilter filter,
                          Pageable pageable) {
        final Page<UserReadDto> userPage = userService.findAll(filter, pageable);
        model.addAttribute("users", PageResponse.of(userPage));
        model.addAttribute("roles", Role.values());
        model.addAttribute("sortBys", SortBy.values());
        model.addAttribute("directions", Sort.Direction.values());
        model.addAttribute("sports", sportService.findAll());
        model.addAttribute("filter", filter);
        return "user/users";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id,
                           Model model) {
        userService.findById(id).ifPresentOrElse(
                user -> model.addAttribute("user", user),
                () -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                }

        );
        return "user/user";
    }
}