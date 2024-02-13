package calculator.parser;

import java.util.List;

public interface ExpressionParser {
    List<Integer> parse(String expression);
}
