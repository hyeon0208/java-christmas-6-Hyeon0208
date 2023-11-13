package christmas.domain;

import christmas.constant.ErrorMessage;

public class OrderMenuName {
    private final String name;

    private OrderMenuName(String name) {
        this.name = name;
    }

    public static OrderMenuName from(String[] orderInfo) {
        String name = orderInfo[0];
        validateExistMenu(name);
        return new OrderMenuName(name);
    }

    private static void validateExistMenu(String name) {
        if (!Menu.contains(name)) {
            throw new IllegalArgumentException(ErrorMessage.ORDER_ERROR);
        }
    }

    public String getName() {
        return name;
    }
}
