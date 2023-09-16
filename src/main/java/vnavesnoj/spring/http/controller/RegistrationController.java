package vnavesnoj.spring.http.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import vnavesnoj.spring.dto.UserCreateDto;
import vnavesnoj.spring.exception.*;
import vnavesnoj.spring.listener.OnRegistrationCompleteEvent;
import vnavesnoj.spring.listener.OnVerificationTokenCreateEvent;
import vnavesnoj.spring.service.UserService;
import vnavesnoj.spring.service.VerificationTokenService;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;

    private final VerificationTokenService verificationTokenService;

    private final MessageSource messageSource;

    private final ApplicationEventPublisher eventPublisher;

    @GetMapping("/registration")
    public String registrationPage(Authentication authentication,
                                   Model model,
                                   UserCreateDto user) {
        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/";
        }
        model.addAttribute("email", user.getEmail());
        model.addAttribute("username", user.getUsername());
        return "user/registration";
    }

    @PostMapping("/registration")
    public String registration(@Valid UserCreateDto user,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes,
                               HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            addValidationErrorAttributes(user, bindingResult, redirectAttributes);
            return "redirect:/registration";
        }
        try {
            final var userReadDto = userService.create(user);
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(
                    userReadDto,
                    request.getLocale(),
                    ServletUriComponentsBuilder.fromRequestUri(request).replacePath(null).toUriString()
            ));
        } catch (RuntimeException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("globalError",
                    "Помилка під час створення користувача. Спробуйте ще раз");
            addUserAttributes(user, redirectAttributes);
            return "redirect:/registration";
        }
        redirectAttributes.addFlashAttribute(
                "success",
                """
                        Дякуємо за реєстрацію на нашому сайті.
                        Для активації вашого акаунту на вказану електронну пошту був надіслан верифікаційний код.""");
        return "redirect:/login";
    }

    @GetMapping("/registrationConfirm")
    public String confirmRegistration(RedirectAttributes redirectAttributes, String token) {
        try {
            verificationTokenService.tryActivateUserByToken(token);
            redirectAttributes.addFlashAttribute("success", "Акаунт успішно активовано.");
        } catch (TokenNotExists e) {
            redirectAttributes.addFlashAttribute("error", "Невірний код активації.");
        } catch (TokenExpired e) {
            redirectAttributes.addFlashAttribute("error", "Термін дії кода активації минув.");
        } catch (UserAlreadyEnabled e) {
            redirectAttributes.addFlashAttribute("info", "Користувач вже активован.");
        } catch (RuntimeException e) {
            log.error(e.getMessage(), e);
            redirectAttributes.addFlashAttribute("error", "Помилка під час активації користувача.");
        }
        return "redirect:/login";
    }

    @GetMapping("/resendConfirmToken")
    public String checkEmail(Authentication authentication) {
        if (authentication != null && !authentication.isAuthenticated()) {
            return "redirect:/";
        }
        return "user/resend-confirm-token";
    }

    @PostMapping("/resendConfirmToken")
    public String resendConfirmToken(String email,
                                     HttpServletRequest request,
                                     RedirectAttributes redirectAttributes) {
        final var locale = request.getLocale();
        try {
            final var token = verificationTokenService.tryCreateVerificationTokenFor(email);
            eventPublisher.publishEvent(new OnVerificationTokenCreateEvent(
                    token,
                    request.getLocale(),
                    ServletUriComponentsBuilder.fromRequestUri(request).replacePath(null).toUriString()
            ));
            redirectAttributes.addFlashAttribute("success", messageSource.getMessage(
                    "resendToken.message.success",
                    new Object[0],
                    locale
            ));
            return "redirect:/login";
        } catch (RegisteredEmailNotFoundException e) {
            redirectAttributes.addFlashAttribute("error", messageSource.getMessage(
                    "resendToken.message.emailNotExists",
                    new Object[]{email},
                    locale
            ));
            redirectAttributes.addAttribute("email", email);
            return "redirect:/resendConfirmToken";
        } catch (UserAlreadyEnabled e) {
            redirectAttributes.addFlashAttribute("error", messageSource.getMessage(
                    "resendToken.message.emailAlreadyEnabled",
                    new Object[]{email},
                    locale
            ));
            redirectAttributes.addFlashAttribute("email", email);
            return "redirect:/resendConfirmToken";
        } catch (TokenCreatedRecently e) {
            redirectAttributes.addFlashAttribute("error", messageSource.getMessage(
                    "resendToken.message.needToWait",
                    new Object[]{email},
                    locale
            ));
            redirectAttributes.addFlashAttribute("email", email);
            return "redirect:/resendConfirmToken";
        } catch (RuntimeException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", messageSource.getMessage(
                    "resendToken.message.error",
                    new Object[]{email},
                    locale
            ));
            redirectAttributes.addFlashAttribute("email", email);
            return "redirect:/resendConfirmToken";
        }
    }

    private static void addValidationErrorAttributes(UserCreateDto user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("emailErrors",
                bindingResult.getFieldErrors(UserCreateDto.Fields.email).stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .toList());
        redirectAttributes.addFlashAttribute("usernameErrors",
                bindingResult.getFieldErrors(UserCreateDto.Fields.username).stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .toList());
        redirectAttributes.addFlashAttribute("rawPasswordErrors",
                bindingResult.getFieldErrors(UserCreateDto.Fields.rawPassword).stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .toList());
        addUserAttributes(user, redirectAttributes);
    }

    private static void addUserAttributes(UserCreateDto user, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("email", user.getEmail());
        redirectAttributes.addAttribute("username", user.getUsername());
    }
}
