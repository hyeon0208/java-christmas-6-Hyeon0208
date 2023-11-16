package christmas.view.validation;

import christmas.constant.ErrorMessage;
import christmas.constant.RegexPattern;
import christmas.util.StringConvertor;
import java.util.Arrays;

public class OrderInputValidator {
    private static final int ORDER_FORMAT_LIMIT_SIZE = 2;

    private OrderInputValidator() {
    }

    public static void validate(String input) {
        validateEmpty(input);
        validateOrderMenus(input);
    }

    private static void validateEmpty(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.ORDER_ERROR);
        }
    }

    private static void validateOrderMenus(String input) {
        String[] orderMenus = StringConvertor.splitByComma(input);
        validateOrderMenuFormat(orderMenus);
        validateDuplicateOrder(orderMenus);
    }

    private static void validateOrderMenuFormat(String[] orderMenus) {
        for (String orderInfo : orderMenus) {
            if (!sameOrderFormatLimitSize(orderInfo) || !checkPriceIsNumber(orderInfo)) {
                throw new IllegalArgumentException(ErrorMessage.ORDER_ERROR);
            }
        }
    }

    private static boolean sameOrderFormatLimitSize(String orderInfo) {
        String[] orderDetail = StringConvertor.splitByHyphen(orderInfo);
        if (orderDetail.length == ORDER_FORMAT_LIMIT_SIZE) {
            return true;
        }
        return false;
    }

    private static boolean checkPriceIsNumber(String orderInfo) {
        String price = extractOrderMenuPrice(orderInfo);
        return RegexPattern.ONLY_NUMBER.matches(price);
    }

    private static void validateDuplicateOrder(String[] orderMenus) {
        if (getDuplicatedCount(orderMenus) != orderMenus.length) {
            throw new IllegalArgumentException(ErrorMessage.ORDER_ERROR);
        }
    }

    private static int getDuplicatedCount(String[] orderMenus) {
        return (int) Arrays.stream(orderMenus)
                .map(orderInfo -> extractOrderMenuName(orderInfo))
                .distinct()
                .count();
    }

    private static String extractOrderMenuName(String orderInfo) {
        return StringConvertor.splitByHyphen(orderInfo)[0];
    }

    private static String extractOrderMenuPrice(String orderInfo) {
        return StringConvertor.splitByHyphen(orderInfo)[1];
    }
}
