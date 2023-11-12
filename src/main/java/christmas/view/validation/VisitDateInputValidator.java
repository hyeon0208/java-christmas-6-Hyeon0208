package christmas.view.validation;

import christmas.constant.ErrorMessage;
import christmas.constant.RegexPattern;

public class VisitDateInputValidator {

    private VisitDateInputValidator() {
    }

    public static void validate(String input) {
        validateIsNumber(input);
        validateEmpty(input);
    }

    private static void validateIsNumber(String input) {
        if (!RegexPattern.ONLY_NUMBER.matches(input)) {
            throw new IllegalArgumentException(ErrorMessage.VISIT_DATE_ERROR);
        }
    }

    private static void validateEmpty(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.VISIT_DATE_ERROR);
        }
    }
}
