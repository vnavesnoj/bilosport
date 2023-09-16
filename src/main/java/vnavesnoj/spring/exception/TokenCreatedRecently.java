package vnavesnoj.spring.exception;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
public class TokenCreatedRecently extends Exception {
    public TokenCreatedRecently(String s) {
        super(s);
    }
}
