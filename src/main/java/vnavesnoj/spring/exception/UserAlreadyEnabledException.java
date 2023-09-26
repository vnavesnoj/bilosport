package vnavesnoj.spring.exception;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
public class UserAlreadyEnabledException extends Exception {
    public UserAlreadyEnabledException(String s) {
        super(s);
    }
}
