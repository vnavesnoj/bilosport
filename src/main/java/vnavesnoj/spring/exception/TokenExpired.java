package vnavesnoj.spring.exception;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
public class TokenExpired extends Exception {

    public TokenExpired(String s) {
        super(s);
    }
}
