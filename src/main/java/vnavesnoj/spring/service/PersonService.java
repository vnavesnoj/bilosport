package vnavesnoj.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vnavesnoj.spring.database.repository.PersonRepository;
import vnavesnoj.spring.dto.person.PersonCreateDto;
import vnavesnoj.spring.dto.person.PersonEditDto;
import vnavesnoj.spring.dto.person.PersonFilter;
import vnavesnoj.spring.dto.person.PersonReadDto;
import vnavesnoj.spring.exception.PersonNotExists;

import java.util.List;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

//    private final Mapper<Person, PersonReadDto> personReadMapper;
//    private final Mapper<PersonCreateDto, Person> personCreateMapper;
//    private final Mapper<PersonEditDto, Person> personEditMapper;
//    private final Mapper<PersonFilter, Predicate> personPredicateMapper;

    public Page<PersonReadDto> findAll(PersonFilter filter, Pageable pageable) {
        return null;
    }

    public PersonReadDto tryFindById(Long id) throws PersonNotExists {
        return null;
    }

    public PersonReadDto create(PersonCreateDto person) {
        return null;
    }

    public PersonReadDto tryUpdate(PersonEditDto person) throws PersonNotExists {
        return null;
    }

    public void tryDelete(Long id) throws PersonNotExists {

    }

    public List<PersonReadDto> findSimilar(PersonCreateDto person) {
        return null;
    }
}
