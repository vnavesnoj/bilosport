package vnavesnoj.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vnavesnoj.spring.database.entity.VerificationToken;
import vnavesnoj.spring.database.repository.UserRepository;
import vnavesnoj.spring.database.repository.VerificationTokenRepository;
import vnavesnoj.spring.dto.UserReadDto;
import vnavesnoj.spring.dto.VerificationTokenReadDto;
import vnavesnoj.spring.mapper.Mapper;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Optional;
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

    private final Mapper<VerificationToken, VerificationTokenReadDto> verificationTokenReadMapper;

    private static final Period TOKEN_LIFE_TIME = Period.of(0, 0, 1);
    private static final Duration TOKEN_TO_RESEND = Duration.ofMinutes(15L);

    @Transactional
    public String createVerificationTokenFor(UserReadDto userDto) {
        final var token = UUID.randomUUID().toString();
        final var now = LocalDateTime.now();
        final var minCreatedAt = now.minus(TOKEN_LIFE_TIME);
        if (verificationTokenRepository.findByToken(token, minCreatedAt).isPresent()) {
            throw new RuntimeException("Created verification token is already exist");
        }
        final var user = userRepository.findById(userDto.getId()).orElseThrow();
        final var verificationToken = VerificationToken.builder()
                .token(token)
                .createdAt(now)
                .user(user)
                .build();
        verificationTokenRepository.save(verificationToken);
        return token;
    }

    public Optional<VerificationTokenReadDto> findByToken(String token) {
        return verificationTokenRepository.findByToken(token)
                .map(verificationTokenReadMapper::map);
    }

    public boolean isExpired(VerificationTokenReadDto verificationToken) {
        final var minCreatedAt = LocalDateTime.now().minus(TOKEN_LIFE_TIME);
        return verificationToken.getCreatedAt().isBefore(minCreatedAt);
    }

    private Boolean canToResend(VerificationToken verificationToken) {
        final var now = LocalDateTime.now();
        return verificationToken.getCreatedAt().plus(TOKEN_TO_RESEND).isBefore(now);
    }

    public boolean canToResendToken(String emailOrUsername) {
        return userRepository.findByUsernameOrEmail(emailOrUsername)
                .flatMap(verificationTokenRepository::findByUser)
                .map(this::canToResend)
                .orElse(false);
    }
}
