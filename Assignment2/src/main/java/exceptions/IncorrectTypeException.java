package exceptions;

/**
 * Incorrect type exception thrown when a variable is set a wrong time
 */
public class IncorrectTypeException extends Exception {
    public IncorrectTypeException(String errorMessage) {
        super(errorMessage);
    }
}