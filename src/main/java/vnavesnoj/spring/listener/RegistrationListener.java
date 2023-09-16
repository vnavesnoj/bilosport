package vnavesnoj.spring.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import vnavesnoj.spring.service.MailSenderService;
import vnavesnoj.spring.service.VerificationTokenService;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Component
@RequiredArgsConstructor
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    private final VerificationTokenService verificationTokenService;

    private final MailSenderService mailSenderService;


    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        mailSenderService.sendVerificationToken(
                event.getNewUser().getEmail(),
                verificationTokenService.createVerificationTokenFor(event.getNewUser().getEmail()).getToken(),
                event.getAppUrl(),
                event.getLocale());
    }
}
