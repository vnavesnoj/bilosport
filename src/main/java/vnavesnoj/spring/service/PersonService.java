package vnavesnoj.spring.service;

import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vnavesnoj.spring.database.entity.Person;
import vnavesnoj.spring.database.repository.PersonRepository;
import vnavesnoj.spring.dto.person.PersonCreateDto;
import vnavesnoj.spring.dto.person.PersonEditDto;
import vnavesnoj.spring.dto.person.PersonFilter;
import vnavesnoj.spring.dto.person.PersonReadDto;
import vnavesnoj.spring.mapper.Mapper;

import java.util.Optional;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    private final Mapper<Person, PersonReadDto> personReadMapper;
    private final Mapper<PersonCreateDto, Person> personCreateMapper;
    private final Mapper<PersonEditDto, Person> personEditMapper;
    private final Mapper<PersonFilter, Predicate> personPredicateMapper;

    public Page<PersonReadDto> findAll(PersonFilter filter, Pageable pageable) {
        final var predicate = personPredicateMapper.map(filter);
        return personRepository.findAll(predicate, pageable)
                .map(personReadMapper::map);
    }

    public Optional<PersonReadDto> findById(Long id) {
        return personRepository.findById(id)
                .map(personReadMapper::map);
    }

    public PersonReadDto create(PersonCreateDto person) {
        return Optional.of(person)
                .map(personCreateMapper::map)
                .map(personRepository::save)
                .map(personReadMapper::map)
                .orElseThrow();
    }

    public Optional<PersonReadDto> update(Long id, PersonEditDto person) {
        return personRepository.findById(id)
                .map(entity -> personEditMapper.map(person, entity))
                .map(personRepository::saveAndFlush)
                .map(personReadMapper::map);
    }

    public boolean delete(Long id) {
        return false;
    }
}
