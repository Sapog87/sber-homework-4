package task1.exception;

public class InvalidPinException extends PinException {
    public InvalidPinException() {
    }

    public InvalidPinException(String message) {
        super(message);
    }

    public InvalidPinException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidPinException(Throwable cause) {
        super(cause);
    }
}
