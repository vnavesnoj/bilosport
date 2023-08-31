package vnavesnoj.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vnavesnoj.spring.database.entity.MemberRole;
import vnavesnoj.spring.database.entity.Tournament;
import vnavesnoj.spring.database.entity.TournamentUser;
import vnavesnoj.spring.database.entity.User;
import vnavesnoj.spring.dto.TournamentCreateEditDto;
import vnavesnoj.spring.dto.TournamentReadDto;
import vnavesnoj.spring.dto.UserReadDto;
import vnavesnoj.spring.mapper.Mapper;

import java.util.*;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TournamentService {

    private final JpaRepository<Tournament, Long> tournamentRepository;

    private final Mapper<Tournament, TournamentReadDto> tournamentReadMapper;

    private final Mapper<TournamentCreateEditDto, Tournament> tournamentCreateEditMapper;

    private final Mapper<User, UserReadDto> userReadMapper;

    public Optional<TournamentReadDto> findById(Long id) {
        return tournamentRepository.findById(id)
                .map(tournamentReadMapper::map);
    }

    public List<TournamentReadDto> findAll() {
        return tournamentRepository.findAll().stream()
                .map(tournamentReadMapper::map)
                .toList();
    }

    public Map<MemberRole, List<UserReadDto>> findAllMembers(Long tournamentId) {
        final var tournament = tournamentRepository.findById(tournamentId).orElseThrow(
                () -> new IllegalArgumentException("Tournament does not exist by id: " + tournamentId)
        );
        Map<MemberRole, List<UserReadDto>> members = new HashMap<>();
        for (MemberRole role : MemberRole.values()) {
            List<UserReadDto> users = new ArrayList<>();
            for (TournamentUser tournamentUser : tournament.getTournamentUsers()) {
                if (tournamentUser.getMemberRole() == role) {
                    users.add(userReadMapper.map(tournamentUser.getUser()));
                }
            }
            members.put(role, users);

        }
        return members;
    }

    @Transactional
    public TournamentReadDto create(TournamentCreateEditDto tournamentDto) {
        return Optional.of(tournamentDto)
                .map(tournamentCreateEditMapper::map)
                .map(tournamentRepository::save)
                .map(tournamentReadMapper::map)
                .orElseThrow();

    }
}
