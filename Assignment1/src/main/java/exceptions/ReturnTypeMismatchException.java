package exceptions;

public class ReturnTypeMismatchException extends Exception {
    public ReturnTypeMismatchException(String errorMessage) {
        super(errorMessage);
    }
}