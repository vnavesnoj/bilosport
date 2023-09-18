package vnavesnoj.spring.http.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import vnavesnoj.spring.exception.RegisteredEmailNotFoundException;
import vnavesnoj.spring.exception.TokenCreatedRecently;
import vnavesnoj.spring.listener.onResetPasswordTokenCreateEvent;
import vnavesnoj.spring.service.ResetPasswordTokenService;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final ResetPasswordTokenService resetPasswordTokenService;

    private final ApplicationEventPublisher eventPublisher;

    private final MessageSource messageSource;

    @GetMapping("/login")
    public String loginPage(Model model,
                            Authentication authentication,
                            String emailOrUsername) {
        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/";
        }
        model.addAttribute("emailOrUsername", emailOrUsername);
        return "user/login";
    }

    @GetMapping("/forgotPassword")
    public String checkEmailForResetPassword(Authentication authentication) {
        if (authentication != null && !authentication.isAuthenticated()) {
            return "redirect:/";
        }
        return "user/forgotPassword";
    }

    @PostMapping("/forgotPassword")
    public String sendResetPasswordToken(String email,
                                         HttpServletRequest request,
                                         RedirectAttributes redirectAttributes) {
        final var locale = request.getLocale();
        try {
            final var token = resetPasswordTokenService.tryCreateTokenFor(email);
            eventPublisher.publishEvent(new onResetPasswordTokenCreateEvent(
                    token,
                    locale,
                    ServletUriComponentsBuilder.fromRequestUri(request).replacePath(null).toUriString()
            ));
            redirectAttributes.addFlashAttribute("success", messageSource.getMessage(
                    "resetPass.message.tokenSent",
                    new Object[0],
                    locale
            ));
            return "redirect:/login";
        } catch (RegisteredEmailNotFoundException e) {
            redirectAttributes.addFlashAttribute("error", messageSource.getMessage(
                    "resetPass.message.emailNotExists",
                    new Object[]{email},
                    locale
            ));
            redirectAttributes.addAttribute("email", email);
            return "redirect:/forgotPassword";
        } catch (TokenCreatedRecently e) {
            redirectAttributes.addFlashAttribute("error", messageSource.getMessage(
                    "resetPass.message.needToWait",
                    new Object[]{email},
                    locale
            ));
            redirectAttributes.addFlashAttribute("email", email);
            return "redirect:/forgotPassword";
        } catch (RuntimeException e) {
            log.error(e.getMessage(), e);
            redirectAttributes.addFlashAttribute("error", messageSource.getMessage(
                    "resetPass.message.errorSendToken",
                    new Object[]{email},
                    locale
            ));
            redirectAttributes.addFlashAttribute("email", email);
            return "redirect:/forgotPassword";
        }
    }
}
