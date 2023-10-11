package vnavesnoj.spring.http.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import vnavesnoj.spring.dto.DirectionSort;
import vnavesnoj.spring.dto.PageResponse;
import vnavesnoj.spring.dto.tournament.TournamentFilter;
import vnavesnoj.spring.dto.tournament.TournamentShortReadDto;
import vnavesnoj.spring.dto.tournament.TournamentSortBy;
import vnavesnoj.spring.service.SportService;
import vnavesnoj.spring.service.TournamentService;

import java.util.Locale;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Controller
@RequestMapping("/tournaments")
@Validated
@RequiredArgsConstructor
public class TournamentController {

    private final TournamentService tournamentService;
    private final SportService sportService;
    private final MessageSource messageSource;

    @GetMapping
    public String findAll(Model model, TournamentFilter filter, Pageable pageable, Locale locale) {
        final Page<TournamentShortReadDto> page;
        if (pageable.getSort().isSorted()) {
            page = tournamentService.findAllShort(filter, pageable);
        } else {
            final var pageRequest = PageRequest.of(
                    pageable.getPageNumber(),
                    pageable.getPageSize(),
                    Sort.by(TournamentSortBy.DATE.getProperty()).descending());
            page = tournamentService.findAllShort(filter, pageRequest);
        }
        model.addAttribute("tournaments", PageResponse.of(page));
        model.addAttribute("filter", filter);
        model.addAttribute("sports", sportService.findAll());
        model.addAttribute("sortBys", TournamentSortBy.values());
        model.addAttribute("directions", DirectionSort.values());
        return "tournament/tournaments";
    }

    @GetMapping("/{id}")
    public String findById(Model model,
                           @PathVariable Long id) {
        return tournamentService.findById(id)
                .map(tournament -> {
                    model.addAttribute("tournament", tournament);
                    return "tournament/tournament";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
