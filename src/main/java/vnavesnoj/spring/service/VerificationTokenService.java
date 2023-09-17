package vnavesnoj.spring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vnavesnoj.spring.database.entity.User;
import vnavesnoj.spring.database.entity.VerificationToken;
import vnavesnoj.spring.database.repository.BaseTokenRepository;
import vnavesnoj.spring.database.repository.UserRepository;
import vnavesnoj.spring.dto.VerificationTokenReadDto;
import vnavesnoj.spring.mapper.Mapper;

import java.time.LocalDateTime;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Service
@Transactional(readOnly = true)
public class VerificationTokenService extends BaseTokenService<VerificationToken, VerificationTokenReadDto> {


    public VerificationTokenService(BaseTokenRepository<VerificationToken> verificationTokenRepository,
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
}
