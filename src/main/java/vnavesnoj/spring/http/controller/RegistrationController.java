package vnavesnoj.spring.http.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
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
import vnavesnoj.spring.dto.UserReadDto;
import vnavesnoj.spring.listener.OnRegistrationCompleteEvent;
import vnavesnoj.spring.service.UserService;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Controller
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;

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
            return "redirect:/registration";
        }
        UserReadDto newUser;
        try {
            newUser = userService.create(user);
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("globalError",
                    "Помилка при створенні користувача. Спробуйте ще раз");
            addUserAttributes(user, redirectAttributes);
            return "redirect:/registration";
        }
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(
                newUser,
                request.getLocale(),
                ServletUriComponentsBuilder.fromRequestUri(request).replacePath(null).toUriString()));
        return "redirect:/login";
    }

    private static void addUserAttributes(UserCreateDto user, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("email", user.getEmail());
        redirectAttributes.addAttribute("username", user.getUsername());
    }
}
