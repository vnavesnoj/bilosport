package vnavesnoj.spring.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vnavesnoj.spring.database.entity.ResetPasswordToken;
import vnavesnoj.spring.database.entity.User;
import vnavesnoj.spring.dto.ResetPasswordTokenDto;
import vnavesnoj.spring.dto.UserReadDto;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Component
@RequiredArgsConstructor
public class ResetPasswordReadMapper implements Mapper<ResetPasswordToken, ResetPasswordTokenDto> {

    private final Mapper<User, UserReadDto> userReadMapper;

    @Override
    public ResetPasswordTokenDto map(ResetPasswordToken token) {
        return new ResetPasswordTokenDto(
                token.getId(),
                token.getToken(),
                token.getCreatedAt(),
                userReadMapper.map(token.getUser())
        );
    }
}
