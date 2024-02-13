

import calculator.Calculator;
import calculator.parser.CalculatorExpressionParser;
import calculator.parser.ExpressionParser;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StringCalculatorTest {
    ExpressionParser parser = new CalculatorExpressionParser();
    Calculator calculator = new Calculator(parser);

    @Test
    void splitAndSum_null_또는_빈문자() {

        assertThat(calculator.calculateSum(null)).isZero();
        assertThat(calculator.calculateSum("")).isZero();
    }

    @Test
    void splitAndSum_숫자하나() {
        assertThat(calculator.calculateSum("1")).isEqualTo(1);
    }

    @Test
    void splitAndSum_쉼표구분자() {
        assertThat(calculator.calculateSum("1,2")).isEqualTo(3);
    }

    @Test
    void splitAndSum_쉼표_또는_콜론_구분자() {
        assertThat(calculator.calculateSum("1,2:3")).isEqualTo(6);
    }

    @Test
    void splitAndSum_custom_구분자() {
        ExpressionParser customParser = new CalculatorExpressionParser(";");
        Calculator customCalculator = new Calculator(customParser);
        assertThat(customCalculator.calculateSum("1;2;3")).isEqualTo(6);
    }

    @Test
    void splitAndSum_negative() {
        assertThatThrownBy(() -> calculator.calculateSum("-1,2,3"))
                .isInstanceOf(RuntimeException.class);
    }
}
