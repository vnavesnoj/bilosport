package vnavesnoj.spring.http.controller;

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
import org.springframework.web.server.ResponseStatusException;
import vnavesnoj.spring.dto.PageResponse;
import vnavesnoj.spring.dto.TournamentFilter;
import vnavesnoj.spring.dto.TournamentReadDto;
import vnavesnoj.spring.dto.TournamentSortBy;
import vnavesnoj.spring.service.SportService;
import vnavesnoj.spring.service.TournamentService;

import static vnavesnoj.spring.database.entity.MemberRole.PARTICIPANT;
import static vnavesnoj.spring.database.entity.MemberRole.REFEREE;
import static vnavesnoj.spring.database.entity.QTournament.tournament;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Controller
@RequestMapping("/tournaments")
@RequiredArgsConstructor
public class TournamentController {

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

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id,
                           Model model) {
        tournamentService.findById(id).ifPresentOrElse(
                tournament -> model.addAttribute("tournament", tournament),
                () -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                }
        );
        final var members = tournamentService.findAllMembers(id);
        model.addAttribute("referees", members.get(REFEREE));
        model.addAttribute("participants", members.get(PARTICIPANT));
        return "tournament/tournament";
    }
}
