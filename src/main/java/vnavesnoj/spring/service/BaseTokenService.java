package vnavesnoj.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import vnavesnoj.spring.database.entity.BaseToken;
import vnavesnoj.spring.database.entity.User;
import vnavesnoj.spring.database.repository.BaseTokenRepository;
import vnavesnoj.spring.database.repository.UserRepository;
import vnavesnoj.spring.dto.BaseTokenReadDto;
import vnavesnoj.spring.exception.*;
import vnavesnoj.spring.mapper.Mapper;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.UUID;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@RequiredArgsConstructor
public abstract class BaseTokenService<T extends BaseToken<? extends Serializable>, D extends BaseTokenReadDto> {

    private final BaseTokenRepository<T> baseTokenRepository;

    private final Mapper<T, D> baseTokenReadMapper;

    private final UserRepository userRepository;

    private static final Period TOKEN_LIFE_TIME = Period.of(0, 0, 1);
    private static final Duration TOKEN_TO_RESEND = Duration.ofMinutes(15L);

    @Transactional
    public D createVerificationTokenFor(String email) {
        final var baseToken = UUID.randomUUID().toString();
        final var now = LocalDateTime.now();
        final var minCreatedAt = now.minus(TOKEN_LIFE_TIME);
        if (baseTokenRepository.findByToken(baseToken, minCreatedAt).isPresent()) {
            throw new RuntimeException("Created verification token is already exist");
        }
        final var user = userRepository.findByEmail(email).orElseThrow();


        final T token = getToken(baseToken, now, user);
        baseTokenRepository.save(token);
        return baseTokenReadMapper.map(baseTokenRepository.save(token));
    }

    protected abstract T getToken(String token, LocalDateTime now, User user);

    private boolean isExpired(T token) {
        final var minCreatedAt = LocalDateTime.now().minus(TOKEN_LIFE_TIME);
        return token.getCreatedAt().isBefore(minCreatedAt);
    }

    private Boolean checkTokenTimeToResend(T token) {
        final var now = LocalDateTime.now();
        return token.getCreatedAt().plus(TOKEN_TO_RESEND).isBefore(now);
    }

    private boolean alreadyCanToResendToken(User user) {
        return baseTokenRepository.findLastBy(user)
                .map(this::checkTokenTimeToResend)
                .orElse(true);
    }

    @Transactional
    public D tryCreateVerificationTokenFor(String email) throws RegisteredEmailNotFoundException,
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
        final var baseToken = baseTokenRepository.findByToken(token).orElseThrow(() ->
                new TokenNotExists("Token " + token + " does not exist in repository"));
        if (isExpired(baseToken)) {
            throw new TokenExpired("Token life time has expired at "
                    + baseToken.getCreatedAt().plus(TOKEN_LIFE_TIME));
        }
        if (baseToken.getUser().isEnabled()) {
            throw new UserAlreadyEnabled("User with email + " + baseToken.getUser().getEmail() + " already enabled");
        }
        baseToken.getUser().setEnabled(true);
        baseTokenRepository.save(baseToken);
    }
}
