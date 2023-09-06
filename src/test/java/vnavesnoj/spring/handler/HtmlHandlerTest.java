package vnavesnoj.spring.handler;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static vnavesnoj.spring.handler.ImageLineHtmlHandler.IMG_POSTFIX;
import static vnavesnoj.spring.handler.ImageLineHtmlHandler.IMG_PREFIX;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@SpringBootTest
@RequiredArgsConstructor
class HtmlHandlerTest {

    private final ImageLineHtmlHandler imageLineHtmlHandler;

    private static final String SRC = "image/path";

    private static final String CORRECT_LINE = IMG_PREFIX + "image/path" + IMG_POSTFIX;
    private static final String WRONG_LINE = IMG_PREFIX + "image/path" + IMG_PREFIX;

    @Test
    void matches() {
        final var actualWithCorrectLine = imageLineHtmlHandler.isImageLine(CORRECT_LINE);
        final var actualWithWrongLine = imageLineHtmlHandler.isImageLine(WRONG_LINE);
        assertThat(actualWithCorrectLine).isTrue();
        assertThat(actualWithWrongLine).isFalse();
    }

    @Test
    void ifGetSrcFromWrongTextMustBeException() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(
                () -> imageLineHtmlHandler.getImageSrcFrom(WRONG_LINE));
    }

    @Test
    void ifGetSrcFromCorrectTextMustReturnSrc() {
        final var actual = imageLineHtmlHandler.getImageSrcFrom(CORRECT_LINE);
        assertThat(actual).isEqualTo(SRC);
    }

    @Test
    void getSrc() {
        final var src = imageLineHtmlHandler.getImageSrcFrom("[IMG]https://img.championat.com/s/735x490/news/big/l/w/skvosh-triatlon-zorbing-chto-vybrat-v-2019_15488563261718964122.jpg[/IMG]");
        System.out.println(src);
    }
}