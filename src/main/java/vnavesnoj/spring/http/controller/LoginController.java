package vnavesnoj.spring.http.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import vnavesnoj.spring.dto.UserEditPasswordDto;
import vnavesnoj.spring.exception.RegisteredEmailNotFoundException;
import vnavesnoj.spring.exception.TokenCreatedRecently;
import vnavesnoj.spring.exception.TokenExpired;
import vnavesnoj.spring.exception.TokenNotExists;
import vnavesnoj.spring.listener.onResetPasswordTokenCreateEvent;
import vnavesnoj.spring.service.ResetPasswordTokenService;

import java.util.Locale;

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

    @GetMapping("/resetPassword")
    public String resetPasswordPage(Authentication authentication,
                                    Locale locale,
                                    String token,
                                    RedirectAttributes redirectAttributes) {
        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/";
        }
        try {
            resetPasswordTokenService.tryFindActualToken(token);
            return "user/resetPassword";
        } catch (TokenNotExists e) {
            redirectAttributes.addFlashAttribute("error", messageSource.getMessage(
                    "resetPass.message.tokenNotExists",
                    new Object[0],
                    locale
            ));
            return "redirect:/login";
        } catch (TokenExpired e) {
            redirectAttributes.addFlashAttribute("error", messageSource.getMessage(
                    "resetPass.message.tokenExpired",
                    new Object[0],
                    locale
            ));
            return "redirect:/login";
        } catch (RuntimeException e) {
            log.error(e.getMessage(), e);
            redirectAttributes.addFlashAttribute("error", messageSource.getMessage(
                    "resetPass.message.errorCheckToken",
                    new Object[0],
                    locale
            ));
            return "redirect:/login";
        }
    }

    @PreAuthorize("!isAuthenticated()")
    @PostMapping("/resetPassword")
    public String resetPassword(Authentication authentication,
                                Locale locale,
                                RedirectAttributes redirectAttributes,
                                @Valid UserEditPasswordDto userEditPasswordDto,
                                BindingResult bindingResult) {
        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/";
        }
        if (bindingResult.hasFieldErrors("rawPassword")) {
            redirectAttributes.addFlashAttribute("rawPasswordErrors",
                    bindingResult.getFieldErrors(UserEditPasswordDto.Fields.rawPassword).stream()
                            .map(DefaultMessageSourceResolvable::getDefaultMessage)
                            .toList());
            return "redirect:/resetPassword?token=" + userEditPasswordDto.getResetPasswordToken();
        }
        try {
            resetPasswordTokenService.tryResetUserPassword(userEditPasswordDto);
            redirectAttributes.addFlashAttribute("success", messageSource.getMessage(
                    "resetPass.message.success",
                    new Object[0],
                    locale
            ));
            return "redirect:/login";
        } catch (TokenNotExists e) {
            redirectAttributes.addFlashAttribute("error", messageSource.getMessage(
                    "resetPass.message.tokenNotExists",
                    new Object[0],
                    locale
            ));
            return "redirect:/login";
        } catch (TokenExpired e) {
            redirectAttributes.addFlashAttribute("error", messageSource.getMessage(
                    "resetPass.message.tokenExpired",
                    new Object[0],
                    locale
            ));
            return "redirect:/login";
        } catch (RuntimeException e) {
            log.error(e.getMessage(), e);
            redirectAttributes.addFlashAttribute("error", messageSource.getMessage(
                    "resetPass.message.errorResetPassword",
                    new Object[0],
                    locale
            ));
            return "redirect:/login";
        }
    }
}
