package vnavesnoj.spring.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import vnavesnoj.spring.service.VerificationTokenService;

import java.util.UUID;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Component
@RequiredArgsConstructor
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    private final VerificationTokenService verificationTokenService;

    private final MessageSource messageSource;

    private final JavaMailSender mailSender;


    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        final var user = event.getNewUser();
        final var token = UUID.randomUUID().toString();
        verificationTokenService.createVerificationToken(user, token);

        final var recipientAddress = user.getEmail();
        final var subject = "Підтвердження реєстрації";
        final var confirmationUrl = event.getAppUrl() + "/registrationConfirm?token=" + token;
        final var message = messageSource.getMessage(
                "message.regConfirm",
                new Object[]{confirmationUrl},
                event.getLocale());

        final var email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message);
//        mailSender.send(email);
        System.out.println(recipientAddress);
        System.out.println(subject);
        System.out.println(message);
    }
}
