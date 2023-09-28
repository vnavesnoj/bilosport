package vnavesnoj.spring.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vnavesnoj.spring.database.entity.User;
import vnavesnoj.spring.database.repository.UserRepository;

import java.util.Optional;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Component
@RequiredArgsConstructor
public class UserIdMapper implements Mapper<Long, User> {

    private final UserRepository userRepository;

    @Override
    public User map(Long userId) {
        return Optional.ofNullable(userId)
                .map(id -> userRepository.findById(id).orElseThrow(
                        () -> new IllegalArgumentException("User does not exist by id: " + userId)
                ))
                .orElse(null);
    }
}
