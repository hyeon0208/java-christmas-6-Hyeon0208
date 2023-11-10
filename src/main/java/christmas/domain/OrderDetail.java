package christmas.domain;

import christmas.constant.ErrorMessage;
import christmas.util.StringConvertor;

public class OrderDetail {
    private static final int MIN_ORDER_QUANTITY = 1;
    private final MenuInfo menuInfo;
    private final int quantity;

    private OrderDetail(MenuInfo menuInfo, int quantity) {
        this.menuInfo = menuInfo;
        this.quantity = quantity;
    }

    public static OrderDetail of(String orderMenu) {
        String[] orderInfo = StringConvertor.splitByHyphen(orderMenu);
        String name = makeValidatedName(orderInfo);
        int price = Menu.getPriceOf(name);
        int quantity = makeValidatedQuantity(orderInfo);
        return new OrderDetail(new MenuInfo(name, price), quantity);
    }

    private static String makeValidatedName(String[] orderInfo) {
        String name = orderInfo[0];
        validateExistMenu(name);
        return name;
    }

    private static void validateExistMenu(String name) {
        if (!Menu.contains(name)) {
            throw new IllegalArgumentException(ErrorMessage.ORDER_ERROR);
        }
    }

    private static int makeValidatedQuantity(String[] orderInfo) {
        int quantity = StringConvertor.convertStringToInt(orderInfo[1]);
        validateOrderQuantity(quantity);
        return quantity;
    }

    private static void validateOrderQuantity(int quantity) {
        if (quantity < MIN_ORDER_QUANTITY) {
            throw new IllegalArgumentException(ErrorMessage.ORDER_ERROR);
        }
    }

    public String getMenuName() {
        return menuInfo.name();
    }

    public int getQuantity() {
        return quantity;
    }

    public int getOrderPrice() {
        return menuInfo.price() * quantity;
    }
}
