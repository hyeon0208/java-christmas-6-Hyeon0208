package christmas.validation;

public class OrderInputValidator {
    private static final String ORDER_INPUT_ERROR = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";

    private OrderInputValidator() {
    }

    public static void validate(String input) {
        validateEmpty(input);
    }

    private static void validateEmpty(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException(ORDER_INPUT_ERROR);
        }
    }
}
