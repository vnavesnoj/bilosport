package vnavesnoj.spring.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vnavesnoj.spring.database.entity.User;
import vnavesnoj.spring.database.entity.VerificationToken;
import vnavesnoj.spring.dto.UserReadDto;
import vnavesnoj.spring.dto.VerificationTokenReadDto;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Component
@RequiredArgsConstructor
public class VerificationTokenReadMapper implements Mapper<VerificationToken, VerificationTokenReadDto> {

    private final Mapper<User, UserReadDto> userReadMapper;

    @Override
    public VerificationTokenReadDto map(VerificationToken verificationToken) {
        return new VerificationTokenReadDto(
                verificationToken.getId(),
                verificationToken.getToken(),
                verificationToken.getCreatedAt(),
                userReadMapper.map(verificationToken.getUser())
        );
    }
}
