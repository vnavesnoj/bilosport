package vnavesnoj.spring.handler.failure;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;

import java.io.IOException;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private final MessageSource messages;

    private final LocaleResolver localeResolver;

    public CustomAuthenticationFailureHandler(MessageSource messages, LocaleResolver localeResolver) {
        super("/login?error");
        this.messages = messages;
        this.localeResolver = localeResolver;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        final var emailOrUsername = request.getParameter("emailOrUsername");
        final var urlBuilder = new StringBuilder("/login?emailOrUsername=" + emailOrUsername + "&error=");
        final var errorMessageBuilder = new StringBuilder();
        final var locale = localeResolver.resolveLocale(request);
        if (exception instanceof DisabledException) {
            errorMessageBuilder.append(messages.getMessage("auth.message.disabled", null, locale));
            urlBuilder.append("disabled");
        } else {
            errorMessageBuilder.append(messages.getMessage("auth.message.badCredentials", null, locale));
            urlBuilder.append("badCredentials");
        }
        setDefaultFailureUrl(urlBuilder.toString());
        super.onAuthenticationFailure(request, response, exception);
        request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, errorMessageBuilder.toString());
    }
}
