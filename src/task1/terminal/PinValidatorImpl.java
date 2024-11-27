package task1.terminal;

import task1.exception.AccountIsLockedException;
import task1.exception.IllegalCharException;
import task1.exception.InvalidPinException;
import task1.exception.TooManyAttempsToEnterPinException;

public class PinValidatorImpl implements PinValidator {
    private final String pin;
    private final int allowedErrorsCount;

    private int errors;
    private boolean valid;
    private long endOfBlock = -1;
    private final StringBuilder inputPin;

    public PinValidatorImpl(String pin, int allowedErrorsCount) {
        this.pin = pin;
        this.allowedErrorsCount = allowedErrorsCount;
        this.errors = 0;
        this.valid = false;
        this.inputPin = new StringBuilder();
    }

    @Override
    public boolean isValid() {
        return valid;
    }

    private int getRemainingErrors() {
        return allowedErrorsCount - errors;
    }

    @Override
    public void add(char c) {
        if (!Character.isDigit(c))
            throw new IllegalCharException("Введенный символ %s не является цифрой".formatted(c));

        if (inputPin.length() == pin.length())
            throw new IllegalCharException("Слишком много символов");

        inputPin.append(c);

        tryEnter();
    }

    private void tryEnter() {
        if (inputPin.length() == pin.length()) {
            if (pin.contentEquals(inputPin)) {
                valid = true;
            } else {
                errors++;
                inputPin.setLength(0);
                if (errors >= allowedErrorsCount) {
                    errors = 0;
                    endOfBlock = System.currentTimeMillis() + 10000;
                    throw new TooManyAttempsToEnterPinException(
                            "Слишком много попыток ввода pin, аккаунт заблокирован на 10 секунд"
                    );
                } else {
                    throw new InvalidPinException(
                            "Введен неверный pin, оставшееся количество попыток: %d".formatted(getRemainingErrors())
                    );
                }
            }
        }
    }

    @Override
    public int getRemainingSymbolsCounter() {
        return pin.length() - inputPin.length();
    }

    @Override
    public void access() {
        if (endOfBlock == -1 || endOfBlock < System.currentTimeMillis())
            return;
        long remainingTimeOfBlock = endOfBlock - System.currentTimeMillis();
        if (remainingTimeOfBlock > 0) {
            throw new AccountIsLockedException(
                    "Аккаунт заблокирован (до конца %.3f секунд)".formatted(remainingTimeOfBlock / 1000.0)
            );
        }
    }
}
