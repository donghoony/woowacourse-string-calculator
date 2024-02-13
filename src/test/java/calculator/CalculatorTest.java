package calculator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import calculator.exception.ContainingNegativeException;
import calculator.parser.ExpressionParser;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CalculatorTest {

    ExpressionParser parser = new ExpressionParser() {
        @Override
        public List<Integer> parse(String expression) {
            return Arrays.stream(expression.split(","))
                    .map(Integer::parseInt)
                    .toList();
        }
    };

    @ParameterizedTest
    @ValueSource(strings = {"1,2,3", "1,2,3,4,5"})
    @DisplayName("주어진 정수가 올바른 범위인 경우, 정상적으로 결과를 도출한다.")
    void initTest(String expression) {
        Calculator calculator = new Calculator(parser);
        int actual = calculator.calculateSum(expression);
        int expected = Arrays.stream(expression.split(","))
                .map(Integer::parseInt)
                .reduce(0, Integer::sum);
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,-4,5", "-1,2,3,4,5"})
    @DisplayName("주어진 정수가 음수를 포함하는 경우, 예외를 발생한다.")
    void illegalValueInitTest(String expression) {
        Calculator calculator = new Calculator(parser);
        assertThatThrownBy(() -> calculator.calculateSum(expression))
                .isInstanceOf(ContainingNegativeException.class);
    }

}