package calculator.io;

import java.io.OutputStream;
import java.io.PrintStream;

public class Output {
    private final PrintStream out;

    public Output(OutputStream out) {
        this.out = new PrintStream(out);
    }

    public void printResult(int result) {
        out.println(result);
    }
}
