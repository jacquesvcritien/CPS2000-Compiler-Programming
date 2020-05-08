package exceptions;

/**
 * Already declared exception thrown when an identifier is already declared
 */
public class AlreadyDeclaredException extends Exception {
    public AlreadyDeclaredException(String errorMessage) {
        super(errorMessage);
    }
}