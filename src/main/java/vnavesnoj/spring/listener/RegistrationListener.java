package vnavesnoj.spring.listener;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import vnavesnoj.spring.service.VerificationTokenService;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Component
@RequiredArgsConstructor
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    private final VerificationTokenService verificationTokenService;

    private final ApplicationEventPublisher eventPublisher;


    @SneakyThrows
    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        final var token =
                verificationTokenService.tryCreateVerificationTokenFor(event.getNewUser().getEmail());
        eventPublisher.publishEvent(
                new OnVerificationTokenCreateEvent(token, event.getLocale(), event.getAppUrl()));
    }
}
