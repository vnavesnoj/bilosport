package vnavesnoj.spring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vnavesnoj.spring.database.entity.User;
import vnavesnoj.spring.database.entity.VerificationToken;
import vnavesnoj.spring.database.repository.UserRepository;
import vnavesnoj.spring.database.repository.VerificationTokenRepository;
import vnavesnoj.spring.dto.VerificationTokenReadDto;
import vnavesnoj.spring.exception.*;
import vnavesnoj.spring.mapper.Mapper;

import java.time.LocalDateTime;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Service
@Transactional(readOnly = true)
public class VerificationTokenService extends BaseTokenService<VerificationToken, VerificationTokenReadDto> {


    public VerificationTokenService(VerificationTokenRepository verificationTokenRepository,
                                    UserRepository userRepository,
                                    Mapper<VerificationToken, VerificationTokenReadDto> verificationTokenReadMapper) {
        super(verificationTokenRepository,
                verificationTokenReadMapper,
                userRepository);
    }

    @Override
    protected VerificationToken getToken(String token, LocalDateTime now, User user) {
        return VerificationToken.builder()
                .token(token)
                .createdAt(now)
                .user(user)
                .build();
    }

    @Transactional
    public VerificationTokenReadDto tryCreateTokenFor(String email) throws RegisteredEmailNotFoundException,
            TokenCreatedRecently, UserAlreadyEnabled {
        final var user = getUserRepository().findByEmail(email).orElseThrow(
                () -> new RegisteredEmailNotFoundException("Registered email " + email + " not found"));
        if (user.isEnabled()) {
            throw new UserAlreadyEnabled("User with email + " + user.getEmail() + " already enabled");
        }
        if (notAvailableToResendToken(user)) {
            throw new TokenCreatedRecently("Token to resend will available every + " + TOKEN_TO_RESEND);
        }
        return createTokenFor(email);
    }

    @Transactional
    public void tryActivateUserByToken(String token) throws TokenNotExists, TokenExpired, UserAlreadyEnabled {
        final var verificationToken = getBaseTokenRepository().findByToken(token).orElseThrow(() ->
                new TokenNotExists("Token " + token + " does not exist in repository"));
        if (isExpired(verificationToken)) {
            throw new TokenExpired("Token life time has expired at "
                    + verificationToken.getCreatedAt().plus(TOKEN_LIFE_TIME));
        }
        if (verificationToken.getUser().isEnabled()) {
            throw new UserAlreadyEnabled("User with email + " + verificationToken.getUser().getEmail() + " already enabled");
        }
        verificationToken.getUser().setEnabled(true);
        getBaseTokenRepository().save(verificationToken);
    }

    private boolean isExpired(VerificationToken token) {
        final var minCreatedAt = LocalDateTime.now().minus(TOKEN_LIFE_TIME);
        return token.getCreatedAt().isBefore(minCreatedAt);
    }
}
