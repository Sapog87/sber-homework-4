package task1.terminal;

public interface PinValidator {
    boolean isValid();

    void add(char c);

    int getRemainingSymbolsCounter();

    void access();
}
