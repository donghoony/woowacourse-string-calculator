package calculator;

import calculator.exception.IllegalValueContainedException;
import java.util.List;

public class Calculator {
    private final List<Integer> numbers;

    public Calculator(List<Integer> numbers) {
        validateNumbers(numbers);
        this.numbers = numbers;
    }

    public int calculateSum() {
        return numbers.stream().reduce(0, Integer::sum);
    }

    private void validateNumbers(List<Integer> numbers) {
        if (hasNegativeInteger(numbers)) {
            throw new IllegalValueContainedException();
        }
    }

    private boolean hasNegativeInteger(List<Integer> numbers) {
        return numbers.stream().anyMatch(number -> number < 0);
    }

}
