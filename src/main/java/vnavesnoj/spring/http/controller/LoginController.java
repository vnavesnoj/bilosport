package vnavesnoj.spring.http.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/";
        }
        return "user/login";
    }
}
