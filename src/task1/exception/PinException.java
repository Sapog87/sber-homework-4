package task1.exception;

public class PinException extends RuntimeException {
    public PinException() {
    }

    public PinException(String message) {
        super(message);
    }

    public PinException(String message, Throwable cause) {
        super(message, cause);
    }

    public PinException(Throwable cause) {
        super(cause);
    }

}
