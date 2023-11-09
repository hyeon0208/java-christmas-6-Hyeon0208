package christmas.validation;

import java.util.regex.Pattern;

public class VisitDateInputValidator {
    private static final Pattern ONLY_NUMBER = Pattern.compile(".*[\\d].*");
    private static final String VISIT_DATE_INPUT_ERROR = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";

    private VisitDateInputValidator() {
    }

    public static void validate(String input) {
        validateIsNumber(input);
        validateEmpty(input);
    }

    private static void validateIsNumber(String input) {
        if (!ONLY_NUMBER.matcher(input).matches()) {
            throw new IllegalArgumentException(VISIT_DATE_INPUT_ERROR);
        }
    }

    private static void validateEmpty(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException(VISIT_DATE_INPUT_ERROR);
        }
    }
}
