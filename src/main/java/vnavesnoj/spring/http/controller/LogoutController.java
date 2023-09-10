package vnavesnoj.spring.http.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Controller
public class LogoutController {

    @GetMapping("/logout")
    public String logoutCheck() {
        return "user/logout";
    }
}
