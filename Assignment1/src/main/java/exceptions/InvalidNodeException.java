package exceptions;

public class InvalidNodeException extends Exception {
    public InvalidNodeException(String errorMessage) {
        super(errorMessage);
    }
}