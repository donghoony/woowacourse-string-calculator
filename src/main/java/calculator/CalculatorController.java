package calculator;

import calculator.io.Input;
import calculator.io.Output;
import calculator.parser.CalculatorExpressionParser;
import calculator.parser.InputParser;

public class CalculatorController {
    private final Input input;
    private final Output output;
    private final InputParser inputParser;

    public CalculatorController(Input input, Output output, InputParser inputParser) {
        this.input = input;
        this.output = output;
        this.inputParser = inputParser;
    }

    public void run() {
        String customDelimiter = "";
        String expression = input.readLine();
        if (inputParser.isCustomDelimiter(expression)) {
            customDelimiter = inputParser.getCustomDelimiter(expression);
            expression = input.readLine();
        }
        CalculatorExpressionParser parser = new CalculatorExpressionParser(customDelimiter);
        Calculator calculator = new Calculator(parser);
        int result = calculator.calculateSum(expression);
        output.printResult(result);
    }

}
