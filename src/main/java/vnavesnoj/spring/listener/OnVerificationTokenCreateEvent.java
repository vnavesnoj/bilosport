package vnavesnoj.spring.listener;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.springframework.context.ApplicationEvent;
import vnavesnoj.spring.dto.VerificationTokenReadDto;

import java.util.Locale;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@EqualsAndHashCode(callSuper = true)
@Value
public class OnVerificationTokenCreateEvent extends ApplicationEvent {

    VerificationTokenReadDto verificationToken;

    Locale locale;

    String appUrl;

    public OnVerificationTokenCreateEvent(VerificationTokenReadDto verificationToken, Locale locale, String appUrl) {
        super(verificationToken);
        this.verificationToken = verificationToken;
        this.locale = locale;
        this.appUrl = appUrl;
    }
}
