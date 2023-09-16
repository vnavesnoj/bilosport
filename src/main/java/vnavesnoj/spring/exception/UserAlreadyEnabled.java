package vnavesnoj.spring.exception;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
public class UserAlreadyEnabled extends Exception {
    public UserAlreadyEnabled(String s) {
        super(s);
    }
}
