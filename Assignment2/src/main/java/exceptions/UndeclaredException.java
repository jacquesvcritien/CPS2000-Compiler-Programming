package exceptions;

/**
 * Undeclared exception thrown when an undeclared variable is used
 */
public class UndeclaredException extends Exception {
    public UndeclaredException(String errorMessage) {
        super(errorMessage);
    }
}