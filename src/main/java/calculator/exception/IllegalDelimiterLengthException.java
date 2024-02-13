package calculator.exception;

public class IllegalDelimiterLengthException extends CalculatorException {
    public IllegalDelimiterLengthException() {
        super("구분자는 한 자리여야 합니다.");
    }
}
