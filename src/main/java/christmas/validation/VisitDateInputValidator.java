package christmas.validation;

import christmas.constant.ErrorMessage;
import java.util.regex.Pattern;

public class VisitDateInputValidator {
    private static final Pattern ONLY_NUMBER = Pattern.compile("\\d+");

    private VisitDateInputValidator() {
    }

    public static void validate(String input) {
        validateIsNumber(input);
        validateEmpty(input);
    }

    private static void validateIsNumber(String input) {
        if (!ONLY_NUMBER.matcher(input).matches()) {
            throw new IllegalArgumentException(ErrorMessage.VISIT_DATE_ERROR);
        }
    }

    private static void validateEmpty(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.VISIT_DATE_ERROR);
        }
    }
}
