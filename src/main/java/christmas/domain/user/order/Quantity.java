package christmas.domain.user.order;

import christmas.constant.ErrorMessage;
import christmas.util.StringConvertor;

public class Quantity {
    private static final int MIN_ORDER_QUANTITY = 1;
    private int quantity;

    private Quantity(int quantity) {
        this.quantity = quantity;
    }

    public static Quantity from(String[] orderInfo) {
        int quantity = StringConvertor.convertStringToInt(orderInfo[1]);
        validateOrderQuantity(quantity);
        return new Quantity(quantity);
    }

    private static void validateOrderQuantity(int quantity) {
        if (quantity < MIN_ORDER_QUANTITY) {
            throw new IllegalArgumentException(ErrorMessage.ORDER_ERROR);
        }
    }

    public int getQuantity() {
        return quantity;
    }
}
