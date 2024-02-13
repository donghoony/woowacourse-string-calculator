package calculator.io;

import java.io.InputStream;
import java.util.Scanner;

public class Input {
    private final Scanner scanner;

    public Input(InputStream in) {
        this.scanner = new Scanner(in);
    }

    public String readLine() {
        return scanner.nextLine();
    }
}
