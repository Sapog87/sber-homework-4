package task1.exception;

public class TooManyAttempsToEnterPinException extends PinException {
    public TooManyAttempsToEnterPinException() {
    }

    public TooManyAttempsToEnterPinException(String message) {
        super(message);
    }

    public TooManyAttempsToEnterPinException(String message, Throwable cause) {
        super(message, cause);
    }

    public TooManyAttempsToEnterPinException(Throwable cause) {
        super(cause);
    }
}
