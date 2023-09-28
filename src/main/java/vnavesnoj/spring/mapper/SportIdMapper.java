package vnavesnoj.spring.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vnavesnoj.spring.database.entity.Sport;
import vnavesnoj.spring.database.repository.SportRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Component
@RequiredArgsConstructor
public class SportIdMapper implements Mapper<List<Integer>, Set<Sport>> {

    private final SportRepository sportRepository;

    @Override
    public Set<Sport> map(List<Integer> sportIds) {
        return Optional.ofNullable(sportIds)
                .map(ids -> ids.stream()
                        .map(id -> sportRepository.findById(id).orElseThrow(
                                () -> new IllegalArgumentException("Sport does not exist by id: " + id)
                        ))
                        .collect(Collectors.toSet()))
                .orElse(null);
    }
}
