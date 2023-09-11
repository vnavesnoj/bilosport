package vnavesnoj.spring.http.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vnavesnoj.spring.dto.UserCreateDto;
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
    public String registrationPage(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/";
        }
        return "user/registration";
    }

    @PostMapping("/registration")
    public String registration(@Valid UserCreateDto user,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors",
                    bindingResult.getAllErrors().stream()
                            .map(DefaultMessageSourceResolvable::getDefaultMessage)
                            .toList());
//            redirectAttributes.addAttribute("emailErrors",
//                    bindingResult.getFieldError(UserCreateDto.Fields.email));
//            redirectAttributes.addAttribute("usernameErrors",
//                    bindingResult.getFieldError(UserCreateDto.Fields.username));
//            redirectAttributes.addAttribute("rawPasswordErrors",
//                    bindingResult.getFieldError(UserCreateDto.Fields.rawPassword));
//            redirectAttributes.addAttribute("confirmPasswordErrors",
//                    bindingResult.getGlobalErrors());
            return "redirect:/registration";
        }

        userService.create(user);
        return "redirect:/login";
    }
}
