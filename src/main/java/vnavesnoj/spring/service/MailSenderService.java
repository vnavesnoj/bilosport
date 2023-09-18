package vnavesnoj.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import vnavesnoj.spring.dto.ResetPasswordTokenDto;

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

    //TODO метод должен принимать в качестве параметра VerificationTokenReadDto
    public void sendVerificationToken(String recipientEmail, String token, String appUrl, Locale locale) {
        final var subject = "Підтвердження реєстрації";
        final var confirmationUrl = appUrl + "/registrationConfirm?token=" + token;
        final var message = messageSource.getMessage(
                "email.message.regConfirm",
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

    public void sendResetPasswordToken(ResetPasswordTokenDto tokenDto, String appUrl, Locale locale) {
        final var subject = "Відновлення паролю";
        final var confirmationUrl = appUrl + "/resetPassword?token=" + tokenDto.getToken();
        final var message = messageSource.getMessage(
                "email.message.resetPasswordToken",
                new Object[]{confirmationUrl},
                locale);
        final var mailToSend = new SimpleMailMessage();
        mailToSend.setTo(tokenDto.getUser().getEmail());
        mailToSend.setSubject(subject);
        mailToSend.setText(message);
//        mailSender.send(mailToSend);
        System.out.println(tokenDto.getUser().getEmail());
        System.out.println(subject);
        System.out.println(message);
    }
}
