package calculator.exception;

public class IllegalDelimiterException extends CalculatorException {
    public IllegalDelimiterException() {
        super("숫자는 구분자가 될 수 없습니다.");
    }
}
