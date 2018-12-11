package ohtu.kivipaperisakset.io;

import java.util.Scanner;

public class SystemIO implements IO {

    private final Scanner scanner;

    public SystemIO() {
        scanner = new Scanner(System.in);
    }

    @Override
    public void print(Object msg) {
        System.out.print(msg);
    }

    @Override
    public String input() {
        return scanner.nextLine();
    }
}
