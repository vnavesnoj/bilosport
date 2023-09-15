package vnavesnoj.spring.http.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Controller
public class LoginController {

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
}
