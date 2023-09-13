package vnavesnoj.spring.http.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
import vnavesnoj.spring.dto.UserRegisterDto;
import vnavesnoj.spring.service.UserService;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Controller
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;

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
            userService.register(new UserRegisterDto(
                    user,
                    request.getLocale(),
                    ServletUriComponentsBuilder.fromRequestUri(request).replacePath(null).toUriString()
            ));
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("globalError",
                    "Помилка під час створення користувача. Спробуйте ще раз");
            addUserAttributes(user, redirectAttributes);
            return "redirect:/registration";
        }
        redirectAttributes.addFlashAttribute(
                "registrationSuccess",
                """
                        Дякуємо за реєстрацію на нашому сайті.
                        Для активації вашого акаунту на вказану електронну пошту був надіслан верифікаційний код.""");
        return "redirect:/login";
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
