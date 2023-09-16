package vnavesnoj.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Service
@RequiredArgsConstructor
public class MailSenderService {

    private final JavaMailSender mailSender;

    private final MessageSource messageSource;

    public void sendVerificationToken(String recipientEmail, String token, String appUrl, Locale locale) {
        final var subject = "Підтвердження реєстрації";
        final var confirmationUrl = appUrl + "/registrationConfirm?token=" + token;
        final var message = messageSource.getMessage(
                "message.regConfirm",
                new Object[]{confirmationUrl},
                locale);

        final var mailToSend = new SimpleMailMessage();
        mailToSend.setTo(recipientEmail);
        mailToSend.setSubject(subject);
        mailToSend.setText(message);
//        mailSender.send(mailToSend);
        System.out.println(recipientEmail);
        System.out.println(subject);
        System.out.println(message);
    }

}
