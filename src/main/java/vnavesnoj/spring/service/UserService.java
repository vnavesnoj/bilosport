package vnavesnoj.spring.service;

import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vnavesnoj.spring.database.entity.User;
import vnavesnoj.spring.database.repository.UserRepository;
import vnavesnoj.spring.dto.UserCreateDto;
import vnavesnoj.spring.dto.UserFilter;
import vnavesnoj.spring.dto.UserReadDto;
import vnavesnoj.spring.mapper.Mapper;

import java.util.Collections;
import java.util.Optional;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final Mapper<User, UserReadDto> userReadMapper;
    private final Mapper<UserCreateDto, User> userCreateMapper;
    private final Mapper<UserFilter, Predicate> userPredicateMapper;

    public Page<UserReadDto> findAll(UserFilter filter, Pageable pageable) {
        final Predicate predicate = userPredicateMapper.map(filter);
        return userRepository.findAll(predicate, pageable)
                .map(userReadMapper::map);
    }

    public Optional<UserReadDto> findById(Long id) {
        return userRepository.findById(id)
                .map(userReadMapper::map);
    }

    @Transactional
    public UserReadDto create(UserCreateDto userDto) {
        return Optional.of(userDto)
                .map(userCreateMapper::map)
                .map(userRepository::save)
                .map(userReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<UserReadDto> update(Long id, UserCreateDto userDto) {
        return userRepository.findById(id)
                .map(entity -> userCreateMapper.map(userDto, entity))
                .map(userRepository::saveAndFlush)
                .map(userReadMapper::map);
    }

    @Transactional
    public boolean delete(Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    userRepository.delete(user);
                    userRepository.flush();
                    return true;
                })
                .orElse(false);
    }

    public boolean usernameUnique(String username) {
        return userRepository.findByUsername(username)
                .isEmpty();
    }

    public boolean emailUnique(String email) {
        return userRepository.findByEmail(email)
                .isEmpty();
    }

    @Transactional
    public void activate(UserReadDto userReadDto) {
        final var user = userRepository.findById(userReadDto.getId()).orElseThrow();
        user.setEnabled(true);
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        return userRepository.findByUsernameOrEmail(usernameOrEmail)
                .map(user -> new org.springframework.security.core.userdetails.User(
                        user.getUsername(),
                        user.getPassword(),
                        user.isEnabled(),
                        true,
                        true,
                        true,
                        Collections.singleton(user.getRole())
                ))
                .orElseThrow(() -> new UsernameNotFoundException("Failed to retrieve user: " + usernameOrEmail));
    }
}
