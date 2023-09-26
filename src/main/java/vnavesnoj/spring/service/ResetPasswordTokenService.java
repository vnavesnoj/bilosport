package vnavesnoj.spring.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vnavesnoj.spring.database.entity.ResetPasswordToken;
import vnavesnoj.spring.database.entity.User;
import vnavesnoj.spring.database.repository.ResetPasswordTokenRepository;
import vnavesnoj.spring.database.repository.UserRepository;
import vnavesnoj.spring.dto.ResetPasswordTokenDto;
import vnavesnoj.spring.dto.UserEditPasswordDto;
import vnavesnoj.spring.exception.RegisteredEmailNotFoundException;
import vnavesnoj.spring.exception.TokenCreatedRecentlyException;
import vnavesnoj.spring.exception.TokenExpiredException;
import vnavesnoj.spring.exception.TokenNotExistsException;
import vnavesnoj.spring.mapper.Mapper;

import java.time.LocalDateTime;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Service
@Transactional(readOnly = true)
public class ResetPasswordTokenService extends BaseTokenService<ResetPasswordToken, ResetPasswordTokenDto> {

    private final PasswordEncoder passwordEncoder;

    public ResetPasswordTokenService(ResetPasswordTokenRepository resetPasswordTokenRepository,
                                     UserRepository userRepository,
                                     Mapper<ResetPasswordToken, ResetPasswordTokenDto> resetPasswordReadMapper,
                                     PasswordEncoder passwordEncoder) {
        super(resetPasswordTokenRepository,
                resetPasswordReadMapper,
                userRepository);
        this.passwordEncoder = passwordEncoder;
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
            TokenCreatedRecentlyException {
        final var user = getUserRepository().findByEmail(email).orElseThrow(
                () -> new RegisteredEmailNotFoundException("Registered email " + email + " not found"));
        if (notAvailableToResendToken(user)) {
            throw new TokenCreatedRecentlyException("Token to resend will available every + " + TOKEN_TO_RESEND);
        }
        return createTokenFor(email);
    }

    public ResetPasswordTokenDto tryFindActualToken(String token) throws TokenNotExistsException, TokenExpiredException {
        final ResetPasswordToken resetPasswordToken = tryGetActualToken(token);
        return getBaseTokenReadMapper().map(resetPasswordToken);
    }

    @Transactional
    public void tryResetUserPassword(UserEditPasswordDto userEditPasswordDto) throws TokenExpiredException, TokenNotExistsException {
        final var resetPasswordToken = tryGetActualToken(userEditPasswordDto.getResetPasswordToken());
        resetPasswordToken.getUser().setPassword(passwordEncoder.encode(userEditPasswordDto.getRawPassword()));
    }

    private ResetPasswordToken tryGetActualToken(String token) throws TokenNotExistsException, TokenExpiredException {
        final var resetPasswordToken = getBaseTokenRepository().findByToken(token).orElseThrow(
                () -> new TokenNotExistsException("Token for reset password " + token + " not exists in repository"));
        if (isExpired(resetPasswordToken)) {
            throw new TokenExpiredException("Token life time has expired at "
                    + resetPasswordToken.getCreatedAt().plus(TOKEN_LIFE_TIME));
        }
        return resetPasswordToken;
    }
}
