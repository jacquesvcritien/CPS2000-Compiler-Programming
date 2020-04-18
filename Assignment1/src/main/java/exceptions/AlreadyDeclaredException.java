package exceptions;

public class AlreadyDeclaredException extends Exception {
    public AlreadyDeclaredException(String errorMessage) {
        super(errorMessage);
    }
}