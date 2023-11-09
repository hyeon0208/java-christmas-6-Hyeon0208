package christmas.validation;

import java.util.regex.Pattern;

public class InputValidator {
    private static final Pattern ONLY_NUMBER = Pattern.compile(".*[\\d].*");

    private InputValidator() {
    }

    public static void validateIsNumber(String input) {
        if (!ONLY_NUMBER.matcher(input).matches()) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    public static void validateEmpty(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }
}
