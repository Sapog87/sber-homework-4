package task1;

import task1.terminal.PinValidatorImpl;
import task1.terminal.TerminalImpl;
import task1.terminal.TerminalServerImpl;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String pin = "1111";

        new TerminalImpl(
                new TerminalServerImpl(BigInteger.ZERO),
                new PinValidatorImpl(pin, 3),
                new Scanner(System.in)::next,
                System.out::println
        ).interact();
    }
}