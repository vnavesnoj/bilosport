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
public class CreateResetPasswordTokenListener implements ApplicationListener<onResetPasswordTokenCreateEvent> {

    private final MailSenderService mailSenderService;

    @Override
    public void onApplicationEvent(onResetPasswordTokenCreateEvent event) {
        mailSenderService.sendResetPasswordToken(
                event.getTokenDto(),
                event.getAppUrl(),
                event.getLocale()
        );
    }
}
