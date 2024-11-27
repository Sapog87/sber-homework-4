package task1.exception;

public class IllegalAmountException extends ServerException {
    public IllegalAmountException() {
    }

    public IllegalAmountException(String message) {
        super(message);
    }

    public IllegalAmountException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalAmountException(Throwable cause) {
        super(cause);
    }
}
