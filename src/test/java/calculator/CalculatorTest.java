package calculator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import calculator.exception.IllegalValueContainedException;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CalculatorTest {

    static Stream<Arguments> generateValidArguments() {
        return Stream.of(Arguments.of(List.of(1, 2, 3, 4, 5)), Arguments.of(List.of(0, 0, 0, 0, 0)),
                Arguments.of(List.of()));
    }

    static Stream<Arguments> generateInvalidArguments() {
        return Stream.of(Arguments.of(List.of(1, 2, 3, 4, -5)), Arguments.of(List.of(-1, -2, -3, -4, -5)),
                Arguments.of(List.of(-1, 2, 3, 4, 5)), Arguments.of(List.of(-5)));
    }

    @ParameterizedTest
    @MethodSource("generateValidArguments")
    @DisplayName("주어진 정수가 올바른 범위인 경우, 정상적으로 생성된다.")
    void initTest(List<Integer> numbers) {
        Assertions.assertDoesNotThrow(() -> new Calculator(numbers));
    }

    @ParameterizedTest
    @MethodSource("generateInvalidArguments")
    @DisplayName("주어진 정수가 음수를 포함하는 경우, 예외를 발생한다.")
    void illegalValueInitTest(List<Integer> numbers) {
        assertThatThrownBy(() -> new Calculator(numbers))
                .isInstanceOf(IllegalValueContainedException.class);
    }

    @ParameterizedTest
    @MethodSource("generateValidArguments")
    @DisplayName("주어진 정수들의 합을 정상적으로 반환한다.")
    void calculateSumTest(List<Integer> numbers) {
        // given
        Calculator calculator = new Calculator(numbers);
        int expected = numbers.stream().reduce(0, Integer::sum);

        // when
        int actual = calculator.calculateSum();

        // then
        assertThat(actual).isEqualTo(expected);
    }
}