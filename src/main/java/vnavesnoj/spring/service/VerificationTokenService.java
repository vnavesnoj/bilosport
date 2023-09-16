package vnavesnoj.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vnavesnoj.spring.database.entity.User;
import vnavesnoj.spring.database.entity.VerificationToken;
import vnavesnoj.spring.database.repository.UserRepository;
import vnavesnoj.spring.database.repository.VerificationTokenRepository;
import vnavesnoj.spring.dto.VerificationTokenReadDto;
import vnavesnoj.spring.exception.*;
import vnavesnoj.spring.mapper.Mapper;

import java.time.Duration;
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

    private final Mapper<VerificationToken, VerificationTokenReadDto> verificationTokenReadMapper;

    private static final Period TOKEN_LIFE_TIME = Period.of(0, 0, 1);
    private static final Duration TOKEN_TO_RESEND = Duration.ofMinutes(15L);

    @Transactional
    public VerificationTokenReadDto createVerificationTokenFor(String email) {
        final var token = UUID.randomUUID().toString();
        final var now = LocalDateTime.now();
        final var minCreatedAt = now.minus(TOKEN_LIFE_TIME);
        if (verificationTokenRepository.findByToken(token, minCreatedAt).isPresent()) {
            throw new RuntimeException("Created verification token is already exist");
        }
        final var user = userRepository.findByEmail(email).orElseThrow();
        final var verificationToken = VerificationToken.builder()
                .token(token)
                .createdAt(now)
                .user(user)
                .build();
        verificationTokenRepository.save(verificationToken);
        return verificationTokenReadMapper.map(verificationTokenRepository.save(verificationToken));
    }

    private boolean isExpired(VerificationToken verificationToken) {
        final var minCreatedAt = LocalDateTime.now().minus(TOKEN_LIFE_TIME);
        return verificationToken.getCreatedAt().isBefore(minCreatedAt);
    }

    private Boolean checkTokenTimeToResend(VerificationToken verificationToken) {
        final var now = LocalDateTime.now();
        return verificationToken.getCreatedAt().plus(TOKEN_TO_RESEND).isBefore(now);
    }

    private boolean alreadyCanToResendToken(User user) {
        return verificationTokenRepository.findLastBy(user)
                .map(this::checkTokenTimeToResend)
                .orElse(false);
    }

    @Transactional
    public VerificationTokenReadDto tryCreateVerificationTokenFor(String email) throws RegisteredEmailNotFoundException,
            UserAlreadyEnabled, TokenCreatedRecently {
        final var user = userRepository.findByEmail(email).orElseThrow(
                () -> new RegisteredEmailNotFoundException("Registered email " + email + " not found"));
        if (user.isEnabled()) {
            throw new UserAlreadyEnabled("User with email + " + user.getEmail() + " already enabled");
        }
        if (!alreadyCanToResendToken(user)) {
            throw new TokenCreatedRecently("Token to resend will available every + " + TOKEN_TO_RESEND);
        }
        return createVerificationTokenFor(email);
    }

    @Transactional
    public void tryActivateUserByToken(String token) throws TokenNotExists, TokenExpired, UserAlreadyEnabled {
        final var verificationToken = verificationTokenRepository.findByToken(token).orElseThrow(() ->
                new TokenNotExists("Token " + token + " does not exist in repository"));
        if (isExpired(verificationToken)) {
            throw new TokenExpired("Token life time has expired at "
                    + verificationToken.getCreatedAt().plus(TOKEN_LIFE_TIME));
        }
        if (verificationToken.getUser().isEnabled()) {
            throw new UserAlreadyEnabled("User with email + " + verificationToken.getUser().getEmail() + " already enabled");
        }
        verificationToken.getUser().setEnabled(true);
        verificationTokenRepository.save(verificationToken);
    }
}
