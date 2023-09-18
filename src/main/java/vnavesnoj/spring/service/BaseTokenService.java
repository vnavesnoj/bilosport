package vnavesnoj.spring.service;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import vnavesnoj.spring.database.entity.BaseToken;
import vnavesnoj.spring.database.entity.User;
import vnavesnoj.spring.database.repository.BaseTokenRepository;
import vnavesnoj.spring.database.repository.UserRepository;
import vnavesnoj.spring.dto.BaseTokenReadDto;
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
@Getter(AccessLevel.PROTECTED)
@RequiredArgsConstructor
public abstract class BaseTokenService<T extends BaseToken<? extends Serializable>, DTO extends BaseTokenReadDto> {

    private final BaseTokenRepository<T> baseTokenRepository;

    private final Mapper<T, DTO> baseTokenReadMapper;

    private final UserRepository userRepository;

    public static final Period TOKEN_LIFE_TIME = Period.of(0, 0, 1);
    public static final Duration TOKEN_TO_RESEND = Duration.ofMinutes(15L);

    protected DTO createTokenFor(String email) {
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

    private Boolean tokenNotReadyToResend(T token) {
        final var now = LocalDateTime.now();
        return token.getCreatedAt().plus(TOKEN_TO_RESEND).isAfter(now);
    }

    protected boolean notAvailableToResendToken(User user) {
        return baseTokenRepository.findLastBy(user)
                .map(this::tokenNotReadyToResend)
                .orElse(false);
    }

    public abstract DTO tryCreateTokenFor(String email) throws Exception;
}
