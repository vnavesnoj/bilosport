package vnavesnoj.spring.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vnavesnoj.spring.database.entity.Sport;
import vnavesnoj.spring.dto.SportReadDto;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Component
@RequiredArgsConstructor
public class SportReadMapper implements Mapper<Sport, SportReadDto> {

    @Override
    public SportReadDto map(Sport sport) {
        return new SportReadDto(
                sport.getId(),
                sport.getName()
        );
    }
}
