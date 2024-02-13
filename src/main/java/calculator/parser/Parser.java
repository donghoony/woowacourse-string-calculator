package calculator.parser;

import calculator.exception.ContainingNonDigitException;
import calculator.exception.IllegalDelimiterException;
import calculator.exception.IllegalDelimiterLengthException;
import java.util.Arrays;
import java.util.List;

public class Parser {
    private static final List<String> DEFAULT_DELIMITERS = List.of(":", ",");
    private static final int DELIMITER_LENGTH = 1;

    private final String customDelimiter;

    public Parser() {
        this.customDelimiter = "";
    }

    public Parser(String customDelimiter) {
        validateCustomDelimiter(customDelimiter);
        this.customDelimiter = customDelimiter;
    }

    public List<Integer> parse(String input) {
        List<String> split = splitByDelimiters(input);
        split.forEach(this::validateContainingOnlyDigits);
        return split.stream()
                .map(Integer::parseInt)
                .toList();
    }

    private void validateContainingOnlyDigits(String input) {
        if (input.isEmpty()) {
            throw new ContainingNonDigitException();
        }
        for (char c : input.toCharArray()) {
            if (!Character.isDigit(c)) {
                throw new ContainingNonDigitException();
            }
        }
    }

    private List<String> splitByDelimiters(String input) {
        String delimiterPattern = getDelimiterPattern();
        return Arrays.stream(input.split(delimiterPattern, -1)).toList();
    }

    private String getDelimiterPattern() {
        return "[" + String.join("", DEFAULT_DELIMITERS) + customDelimiter + "]";
    }

    private void validateCustomDelimiter(String customDelimiter) {
        validateLength(customDelimiter);
        validateNotContainingDigit(customDelimiter);
    }

    private void validateLength(String customDelimiter) {
        if (customDelimiter.length() != DELIMITER_LENGTH) {
            throw new IllegalDelimiterLengthException();
        }
    }

    private void validateNotContainingDigit(String customDelimiter) {
        for (char c : customDelimiter.toCharArray()) {
            if (Character.isDigit(c)) {
                throw new IllegalDelimiterException();
            }
        }
    }
}
