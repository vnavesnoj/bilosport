package vnavesnoj.spring.listener;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.springframework.context.ApplicationEvent;
import vnavesnoj.spring.dto.UserReadDto;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@EqualsAndHashCode(callSuper = true)
@Value
public class OnRegistrationCompleteEvent extends ApplicationEvent {

    String appUrl;

    UserReadDto newUser;

    public OnRegistrationCompleteEvent(UserReadDto newUser, String appUrl) {
        super(newUser);
        this.appUrl = appUrl;
        this.newUser = newUser;
    }
}
