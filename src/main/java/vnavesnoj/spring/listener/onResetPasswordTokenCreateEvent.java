package vnavesnoj.spring.listener;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.springframework.context.ApplicationEvent;
import vnavesnoj.spring.dto.ResetPasswordTokenDto;

import java.util.Locale;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@EqualsAndHashCode(callSuper = true)
@Value
public class onResetPasswordTokenCreateEvent extends ApplicationEvent {

    ResetPasswordTokenDto tokenDto;

    Locale locale;

    String appUrl;

    public onResetPasswordTokenCreateEvent(ResetPasswordTokenDto token, Locale locale, String appUrl) {
        super(token);
        this.tokenDto = token;
        this.locale = locale;
        this.appUrl = appUrl;
    }
}
