package vnavesnoj.spring.handler;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Component
public class ImageLineHtmlHandler {

    public final static String IMG_PREFIX = "[IMG]";
    public final static String IMG_POSTFIX = "[/IMG]";

    private final static String IMG_REGEX = "^\\" + IMG_PREFIX + "(?<src>.+?)\\" + IMG_POSTFIX + "$";

    private final static Pattern PATTERN = Pattern.compile(IMG_REGEX);

    public boolean isImageLine(String text) {
        return PATTERN.matcher(text).matches();
    }

    public String getImageSrcFrom(String text) {
        final var matcher = PATTERN.matcher(text);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(
                    "text must starts with + " + IMG_PREFIX + " and ends with " + IMG_POSTFIX);
        }
        return matcher.group("src");
    }
}
