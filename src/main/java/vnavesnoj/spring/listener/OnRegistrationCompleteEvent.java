package vnavesnoj.spring.listener;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.springframework.context.ApplicationEvent;
import vnavesnoj.spring.dto.UserReadDto;

import java.util.Locale;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@EqualsAndHashCode(callSuper = true)
@Value
public class OnRegistrationCompleteEvent extends ApplicationEvent {

    String appUrl;

    Locale locale;

    UserReadDto newUser;

    public OnRegistrationCompleteEvent(UserReadDto newUser, Locale locale, String appUrl) {
        super(newUser);
        this.appUrl = appUrl;
        this.locale = locale;
        this.newUser = newUser;
    }
}
