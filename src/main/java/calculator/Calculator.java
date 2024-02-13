package calculator;

import calculator.exception.ContainingNegativeException;
import calculator.parser.ExpressionParser;
import java.util.List;

public class Calculator {
    private final ExpressionParser expressionParser;

    public Calculator(ExpressionParser expressionParser) {
        this.expressionParser = expressionParser;
    }

    public int calculateSum(String expression) {
        List<Integer> parsedNumbers = expressionParser.parse(expression);
        validateNonNegative(parsedNumbers);

        return parsedNumbers.stream()
                .reduce(0, Integer::sum);
    }

    private void validateNonNegative(List<Integer> numbers) {
        if (numbers.stream().anyMatch(number -> number < 0)) {
            throw new ContainingNegativeException();
        }
    }
}
