package task1.exception;

public class IllegalCharException extends PinException {
    public IllegalCharException() {
    }

    public IllegalCharException(String message) {
        super(message);
    }

    public IllegalCharException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalCharException(Throwable cause) {
        super(cause);
    }
}
