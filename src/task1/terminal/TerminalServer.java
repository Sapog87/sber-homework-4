package task1.terminal;

import java.math.BigInteger;

public interface TerminalServer {
    BigInteger checkMoney();

    void takeMoney(BigInteger money);

    void putMoney(BigInteger money);
}
