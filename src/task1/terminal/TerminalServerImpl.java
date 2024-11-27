package task1.terminal;

import task1.exception.IllegalAmountException;

import java.math.BigInteger;

public class TerminalServerImpl implements TerminalServer {
    private BigInteger money;

    public TerminalServerImpl(BigInteger initMoney) {
        this.money = initMoney;
    }

    @Override
    public BigInteger checkMoney() {
        return money;
    }

    @Override
    public void takeMoney(BigInteger money) {
        validateMoney(money);

        if (this.money.compareTo(money) < 0) {
            throw new IllegalAmountException(
                    "Введенная сумма %d превышает сумму на счете %d".formatted(money, this.money)
            );
        }

        this.money = this.money.subtract(money);
    }

    @Override
    public void putMoney(BigInteger money) {
        validateMoney(money);

        this.money = this.money.add(money);
    }

    private void validateMoney(BigInteger money) {
        if (money.compareTo(BigInteger.ZERO) < 0) {
            throw new IllegalAmountException(
                    "Введенная сумма не может быть отрицательной"
            );
        }
        if (!money.mod(BigInteger.valueOf(100)).equals(BigInteger.ZERO)) {
            throw new IllegalAmountException(
                    "Введенная сумма должна быть кратная 100"
            );
        }
    }

}
