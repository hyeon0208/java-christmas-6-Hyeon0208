package christmas.validation;

import christmas.constant.ErrorMessage;
import java.util.regex.Pattern;

public class OrderInputValidator {
    private static final String COMMA = ",";

    private OrderInputValidator() {
    }

    public static void validate(String input) {
        validateEmpty(input);
        validateComma(input);
    }

    private static void validateEmpty(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.ORDER_ERROR);
        }
    }

    private static void validateComma(String input) {
        if (!input.contains(COMMA)) {
            throw new IllegalArgumentException(ErrorMessage.ORDER_ERROR);
        }
    }
}
