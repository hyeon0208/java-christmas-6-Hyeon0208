package christmas.domain;

import christmas.constant.ErrorMessage;
import christmas.enums.Menu;
import christmas.util.Convertor;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Order {
    private static final int MIN_ORDER_TOTAL_COUNT_LIMIT = 1;
    private static final int MAX_ORDER_TOTAL_COUNT_LIMIT = 20;
    private final List<OrderDetail> orders;

    private Order(List<OrderDetail> orders) {
        this.orders = orders;
    }

    public static Order from(String input) {
        List<OrderDetail> orderDetails = Arrays.stream(Convertor.splitByComma(input))
                .map(Convertor::splitByHyphen)
                .peek(Order::validateExistMenu)
                .map(OrderDetail::of)
                .collect(Collectors.toList());
        validateOrderCount(orderDetails);
        validateOnlyOrderDrink(orderDetails);
        return new Order(orderDetails);
    }

    private static void validateExistMenu(String[] menuInfo) {
        String name = menuInfo[0];
        if (!Menu.contains(name)) {
            throw new IllegalArgumentException(ErrorMessage.ORDER_ERROR);
        }
    }

    private static void validateOrderCount(List<OrderDetail> orderDetails) {
        int totalOrderCount = getTotalOrderCount(orderDetails);
        if (totalOrderCount < MIN_ORDER_TOTAL_COUNT_LIMIT || totalOrderCount > MAX_ORDER_TOTAL_COUNT_LIMIT) {
            throw new IllegalArgumentException(ErrorMessage.ORDER_ERROR);
        }
    }

    private static int getTotalOrderCount(List<OrderDetail> orderDetails) {
        return orderDetails.stream()
                .mapToInt(OrderDetail::getQuantity)
                .sum();
    }

    private static void validateOnlyOrderDrink(List<OrderDetail> orderDetails) {
        OrderDetail firstMenuInfo = orderDetails.get(0);
        if (isOrderOnce(orderDetails) && Menu.isDrinkMenu(firstMenuInfo.getMenuName())) {
            throw new IllegalArgumentException(ErrorMessage.ORDER_ERROR);
        }
    }

    private static boolean isOrderOnce(List<OrderDetail> orderMenuInfos) {
        if (orderMenuInfos.size() == MIN_ORDER_COUNT_LIMIT) {
            return true;
        }
        return false;
    }

    public List<OrderDetail> getMenuInfos() {
        return Collections.unmodifiableList(orders);
    }

    public int getTotalOrderPrice() {
        return orders.stream()
                .mapToInt(OrderDetail::getOrderPrice)
                .sum();
    }
}
