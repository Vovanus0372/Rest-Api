package exception;

public class InvalidPinCodeException extends RuntimeException {
    public InvalidPinCodeException(String message) {
        super(message);
    }
}
