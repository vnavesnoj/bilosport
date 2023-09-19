package vnavesnoj.spring.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import vnavesnoj.spring.service.MailSenderService;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Component
@RequiredArgsConstructor
public class CreateVerificationTokenListener implements ApplicationListener<OnVerificationTokenCreateEvent> {

    private final MailSenderService mailSenderService;

    @Override
    public void onApplicationEvent(OnVerificationTokenCreateEvent event) {
        mailSenderService.sendVerificationToken(
                event.getVerificationToken(),
                event.getAppUrl(),
                event.getLocale()
        );
    }
}
