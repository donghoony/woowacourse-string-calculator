package calculator.parser;

public class InputParser {
    private static final String CUSTOM_DELIMITER_PREFIX = "//";

    public boolean isCustomDelimiter(String input) {
        return input.startsWith(CUSTOM_DELIMITER_PREFIX);
    }

    public String getCustomDelimiter(String input) {
        return input.substring(CUSTOM_DELIMITER_PREFIX.length());
    }
}
