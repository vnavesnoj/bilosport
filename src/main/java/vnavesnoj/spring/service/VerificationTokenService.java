package vnavesnoj.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vnavesnoj.spring.database.entity.VerificationToken;
import vnavesnoj.spring.database.repository.UserRepository;
import vnavesnoj.spring.database.repository.VerificationTokenRepository;
import vnavesnoj.spring.dto.UserReadDto;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.UUID;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class VerificationTokenService {

    private final VerificationTokenRepository verificationTokenRepository;

    private final UserRepository userRepository;

    private static final Period TOKEN_LIFE_TIME = Period.of(0, 0, 1);

    @Transactional
    public String createVerificationTokenFor(UserReadDto userDto) {
        final var token = UUID.randomUUID().toString();
        final var minCreatedAt = LocalDateTime.now().minus(TOKEN_LIFE_TIME);
        if (verificationTokenRepository.findBy(token, minCreatedAt).isEmpty()) {
            throw new RuntimeException("Created verification token is already exist");
        }
        final var user = userRepository.findById(userDto.getId()).orElseThrow();
        final var verificationToken = VerificationToken.builder()
                .token(token)
                .createdAt(LocalDateTime.now())
                .user(user)
                .build();
        verificationTokenRepository.save(verificationToken);
        return token;
    }
}
