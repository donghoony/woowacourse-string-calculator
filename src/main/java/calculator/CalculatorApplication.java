package calculator;

import calculator.io.Input;
import calculator.io.Output;
import calculator.parser.InputParser;

public class CalculatorApplication {
    public static void main(String[] args) {
        Input input = new Input(System.in);
        Output output = new Output(System.out);
        InputParser inputParser = new InputParser();

        CalculatorController controller = new CalculatorController(input, output, inputParser);
        controller.run();
    }
}
