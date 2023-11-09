package christmas.validation;

import christmas.constant.ErrorMessage;
import christmas.util.Convertor;
import java.util.Arrays;
import java.util.regex.Pattern;

public class OrderInputValidator {
    private static final Pattern ONLY_NUMBER = Pattern.compile("\\d+");
    private static final int MENU_INFO_SIZE = 2;
    private static final String COMMA = ",";
    private static final String HYPHEN = "-";

    private OrderInputValidator() {
    }

    public static void validate(String input) {
        validateEmpty(input);
        validateComma(input);
        validateOrderMenus(input);
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

    private static void validateOrderMenus(String input) {
        String[] orderMenus = Convertor.splitByComma(input);
        validateOrderMenuFormat(orderMenus);
        validateDuplicateOrder(orderMenus);
    }

    private static void validateOrderMenuFormat(String[] orderMenus) {
        validateHyphen(orderMenus);
        for (String orderMenu : orderMenus) {
            String[] menuInfo = Convertor.splitByHyphen(orderMenu);
            String price = menuInfo[1];
            if (menuInfo.length != MENU_INFO_SIZE || !isNumber(price)) {
                throw new IllegalArgumentException(ErrorMessage.ORDER_ERROR);
            }
        }
    }

    private static void validateHyphen(String[] orderMenus) {
        if (isNotContainsHyphen(orderMenus)) {
            throw new IllegalArgumentException(ErrorMessage.ORDER_ERROR);
        }
    }

    private static boolean isNotContainsHyphen(String[] orderMenus) {
        return Arrays.stream(orderMenus)
                .anyMatch(orderMenu -> !orderMenu.contains(HYPHEN));
    }

    private static boolean isNumber(String price) {
        return ONLY_NUMBER.matcher(price).matches();
    }

    private static void validateDuplicateOrder(String[] orderMenus) {
        if (getDuplicatedCount(orderMenus) != orderMenus.length) {
            throw new IllegalArgumentException(ErrorMessage.ORDER_ERROR);
        }
    }

    private static int getDuplicatedCount(String[] orderMenus) {
        return (int) Arrays.stream(orderMenus)
                .distinct()
                .count();
    }
}
