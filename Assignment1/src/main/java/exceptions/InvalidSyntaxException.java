package exceptions;

/**
 * Invalid Syntax exception thrown by parser
 */
public class InvalidSyntaxException extends Exception {
    public InvalidSyntaxException(String errorMessage) {
        super(errorMessage);
    }
}