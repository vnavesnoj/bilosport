package vnavesnoj.spring.http.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import vnavesnoj.spring.dto.UserCreateEditDto;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Controller
public class RegistrationController {

    @GetMapping("/registration")
    public String registrationPage(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/";
        }
        return "user/registration";
    }

    @PostMapping("/registration")
    public String registration(UserCreateEditDto user) {
        return null;
    }
}
