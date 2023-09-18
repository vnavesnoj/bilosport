package vnavesnoj.spring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vnavesnoj.spring.database.entity.ResetPasswordToken;
import vnavesnoj.spring.database.entity.User;
import vnavesnoj.spring.database.repository.ResetPasswordTokenRepository;
import vnavesnoj.spring.database.repository.UserRepository;
import vnavesnoj.spring.dto.ResetPasswordTokenDto;
import vnavesnoj.spring.exception.RegisteredEmailNotFoundException;
import vnavesnoj.spring.exception.TokenCreatedRecently;
import vnavesnoj.spring.mapper.Mapper;

import java.time.LocalDateTime;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Service
public class ResetPasswordTokenService extends BaseTokenService<ResetPasswordToken, ResetPasswordTokenDto> {

    public ResetPasswordTokenService(ResetPasswordTokenRepository resetPasswordTokenRepository,
                                     UserRepository userRepository,
                                     Mapper<ResetPasswordToken, ResetPasswordTokenDto> resetPasswordReadMapper) {
        super(resetPasswordTokenRepository,
                resetPasswordReadMapper,
                userRepository);
    }

    @Override
    protected ResetPasswordToken buildTokenEntity(String token, LocalDateTime now, User user) {
        return ResetPasswordToken.builder()
                .token(token)
                .createdAt(now)
                .user(user)
                .build();
    }

    @Transactional
    public ResetPasswordTokenDto tryCreateTokenFor(String email) throws RegisteredEmailNotFoundException,
            TokenCreatedRecently {
        final var user = getUserRepository().findByEmail(email).orElseThrow(
                () -> new RegisteredEmailNotFoundException("Registered email " + email + " not found"));
        if (notAvailableToResendToken(user)) {
            throw new TokenCreatedRecently("Token to resend will available every + " + TOKEN_TO_RESEND);
        }
        return createTokenFor(email);
    }
}
