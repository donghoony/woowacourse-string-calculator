package calculator.exception;

public class ContainingNegativeException extends CalculatorException {
    public ContainingNegativeException() {
        super("주어지는 수는 모두 0 이상이어야 합니다.");
    }
}
