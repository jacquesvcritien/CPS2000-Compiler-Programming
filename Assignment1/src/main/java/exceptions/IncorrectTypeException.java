package exceptions;

public class IncorrectTypeException extends Exception {
    public IncorrectTypeException(String errorMessage) {
        super(errorMessage);
    }
}