package calculator.exception;

public class IllegalValueContainedException extends CalculatorException {
    public IllegalValueContainedException() {
        super("주어지는 수는 모두 0 이상이어야 합니다.");
    }
}
