package calculator.parser;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import calculator.exception.CalculatorException;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CalculatorExpressionParserTest {

    @Test
    @DisplayName("기본 구분자로 문자열을 올바르게 파싱한다.")
    void defaultDelimiterTest() {
        // given
        CalculatorExpressionParser parser = new CalculatorExpressionParser();
        // when
        List<Integer> parsed = parser.parse("1,2,3:4");
        // then
        assertThat(parsed).containsExactly(1, 2, 3, 4);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            ":1:2:3:4:",
            "1,2,,3,4",
            "1abc",
            "2 2 3 4",
            "2,3,4,5:",
            "a,b,c,d"
    })
    @DisplayName("잘못된 문자열을 파싱하는 경우, 예외를 발생한다.")
    void illegalParseTest(String input) {
        // given
        CalculatorExpressionParser parser = new CalculatorExpressionParser();
        // when
        assertThatThrownBy(() -> parser.parse(input))
                .isInstanceOf(CalculatorException.class);
    }

    @Test
    @DisplayName("커스텀 구분자로 문자열을 올바르게 파싱한다.")
    void customDelimiterTest() {
        // given
        CalculatorExpressionParser parser = new CalculatorExpressionParser("-");
        List<Integer> parsed = parser.parse("1:2-3,4");
        // when
        assertThat(parsed).containsExactly(1, 2, 3, 4);
        // then
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "ab", "", "::", "-----"})
    @DisplayName("올바르지 않은 커스텀 구분자를 사용하는 경우, 예외를 발생한다.")
    void illegalDelimiterTest(String delimiter) {
        assertThatThrownBy(() -> new CalculatorExpressionParser(delimiter))
                .isInstanceOf(CalculatorException.class);
    }
}