package task2;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        start(
                new SiteReaderImpl(),
                new Scanner(System.in)::nextLine,
                System.out::println
        );
    }

    private static void start(SiteReader siteReader, Supplier<String> input, Consumer<String> output) {
        boolean succeed = false;
        while (!succeed) {
            try {
                output.accept("Введите URL");
                String url = input.get();
                String content = siteReader.readContent(url);
                output.accept(content);
                succeed = true;
            } catch (MalformedURLException e) {
                output.accept("URL неправильного формата");
            } catch (IOException e) {
                output.accept("Нет доступа до указанного ресурса");
            }
        }
    }
}