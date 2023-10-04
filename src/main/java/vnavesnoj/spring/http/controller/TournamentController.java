package vnavesnoj.spring.http.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Controller
@RequestMapping("/tournaments")
@RequiredArgsConstructor
public class TournamentController {
/*
    private final TournamentService tournamentService;
    private final SportService sportService;

    @GetMapping
    public String findAll(Model model, TournamentFilter filter, Pageable pageable) {
        final Page<TournamentReadDto> page;
        if (pageable.getSort().isSorted()) {
            page = tournamentService.findAll(filter, pageable);
        } else {
            final var pageRequest = PageRequest.of(
                    pageable.getPageNumber(),
                    pageable.getPageSize(),
                    Sort.by(tournament.tournamentDate.getMetadata().getName()).descending());
            page = tournamentService.findAll(filter, pageRequest);
        }
        model.addAttribute("tournaments", PageResponse.of(page));
        model.addAttribute("filter", filter);
        model.addAttribute("sports", sportService.findAll());
        model.addAttribute("sortBys", TournamentSortBy.values());
        model.addAttribute("directions", Sort.Direction.values());
        return "tournament/tournaments";
    }
 */
}
