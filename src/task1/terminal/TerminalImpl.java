package task1.terminal;

import task1.exception.*;

import java.math.BigInteger;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class TerminalImpl implements Terminal, Interactable {
    private final TerminalServer server;
    private final PinValidator pinValidator;
    private final Supplier<String> input;
    private final Consumer<String> output;
    private boolean hasAccess = false;

    public TerminalImpl(
            TerminalServer server,
            PinValidator pinValidator,
            Supplier<String> input,
            Consumer<String> output
    ) {
        this.server = server;
        this.pinValidator = pinValidator;
        this.input = input;
        this.output = output;
    }

    @Override
    public void interact() {
        checkPin();

        while (true) {
            output.accept("""
                    1 - Проверить состояние счета
                    2 - Снять деньги
                    3 - Положить деньги
                    4 - Выйти""");

            String action = input.get();
            switch (action) {
                case "1" -> checkMoney();
                case "2" -> takeMoney();
                case "3" -> putMoney();
                case "4" -> {
                    output.accept("До свидания");
                    return;
                }
                default -> output.accept("Неверная операция");
            }
        }
    }

    private void checkPin() {
        if (!hasAccess) {
            int pinLength = pinValidator.getRemainingSymbolsCounter();
            StringBuilder pinView = new StringBuilder("*".repeat(pinLength));
            int i = 0;

            output.accept("Введите pin");
            output.accept(pinView.toString());
            while (!pinValidator.isValid()) {
//                char symbol = input.read().charAt(0);
                for (char symbol : input.get().toCharArray()) {
                    try {
                        pinValidator.access();
                        pinValidator.add(symbol);
                        pinView.setCharAt(i++, symbol);
                        output.accept(pinView.toString());
                    } catch (InvalidPinException | TooManyAttempsToEnterPinException e) {
                        output.accept(e.getMessage());
                        pinView = new StringBuilder("*".repeat(pinLength));
                        i = 0;
                    } catch (IllegalCharException e) {
                        output.accept(e.getMessage());
                        output.accept(pinView.toString());
                    } catch (AccountIsLockedException e) {
                        output.accept(e.getMessage());
                    }
                }
            }
            hasAccess = true;
        }
    }

    @Override
    public void checkMoney() {
        checkPin();

        BigInteger money = server.checkMoney();
        output.accept("На счете %s".formatted(money));
    }

    @Override
    public void takeMoney() {
        checkPin();

        output.accept("Введите сумму снятия (должна быть кратна 100)");
        try {
            BigInteger money = getAmount();
            server.takeMoney(money);
            output.accept("Со счета успешно снято %s".formatted(money));
        } catch (ServerException | InputException e) {
            output.accept(e.getMessage());
        }
    }

    @Override
    public void putMoney() {
        checkPin();

        output.accept("Введите сумму пополнения (должна быть кратна 100)");
        try {
            BigInteger money = getAmount();
            server.putMoney(money);
            output.accept("Счет успешно пополнен на %s".formatted(money));
        } catch (ServerException | InputException e) {
            output.accept(e.getMessage());
        }
    }

    private BigInteger getAmount() {
        String money = input.get();
        try {
            return new BigInteger(money);
        } catch (NumberFormatException e) {
            throw new InputException("Вы ввели %s, а нужно ввести число кратное 100".formatted(money), e);
        }
    }
}
