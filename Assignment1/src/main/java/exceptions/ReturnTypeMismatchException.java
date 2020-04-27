package exceptions;

/**
 * Return Type Mismatch exception thrown when a function returns a wrong type
 */
public class ReturnTypeMismatchException extends Exception {
    public ReturnTypeMismatchException(String errorMessage) {
        super(errorMessage);
    }
}