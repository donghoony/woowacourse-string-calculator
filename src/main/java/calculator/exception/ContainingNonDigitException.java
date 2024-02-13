package calculator.exception;

public class ContainingNonDigitException extends CalculatorException {
    public ContainingNonDigitException() {
        super("수식이 잘못되었습니다.");
    }
}
