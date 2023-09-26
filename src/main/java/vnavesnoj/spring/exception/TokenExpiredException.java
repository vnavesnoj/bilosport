package vnavesnoj.spring.exception;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
public class TokenExpiredException extends Exception {

    public TokenExpiredException(String s) {
        super(s);
    }
}
