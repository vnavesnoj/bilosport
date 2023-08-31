package vnavesnoj.spring.http.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import vnavesnoj.spring.service.TournamentService;

import static vnavesnoj.spring.database.entity.MemberRole.PARTICIPANT;
import static vnavesnoj.spring.database.entity.MemberRole.REFEREE;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Controller
@RequestMapping("/tournaments")
@RequiredArgsConstructor
public class TournamentController {

    private final TournamentService tournamentService;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("tournaments", tournamentService.findAll());
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
