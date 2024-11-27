package task1.exception;

public class AccountIsLockedException extends PinException {
    public AccountIsLockedException() {
    }

    public AccountIsLockedException(String message) {
        super(message);
    }

    public AccountIsLockedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountIsLockedException(Throwable cause) {
        super(cause);
    }
}
