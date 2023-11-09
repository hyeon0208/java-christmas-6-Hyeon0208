package christmas.domain;

import christmas.constant.ErrorMessage;
import christmas.dto.MenuInfo;
import christmas.enums.Menu;
import christmas.util.Convertor;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Order {
    private static final int MIN_ORDER_LIMIT = 1;
    private static final int MAX_ORDER_LIMIT = 20;
    private final List<MenuInfo> menuInfos;

    private Order(List<MenuInfo> menuInfos) {
        this.menuInfos = menuInfos;
    }

    public static Order from(String input) {
        List<MenuInfo> orderMenuInfos = Arrays.stream(Convertor.splitByComma(input))
                .map(Convertor::splitByHyphen)
                .peek(Order::validateExistMenu)
                .map(Order::createMenuInfo)
                .collect(Collectors.toList());
        validateOrderCount(orderMenuInfos);
        validateOnlyOrderDrink(orderMenuInfos);
        return new Order(orderMenuInfos);
    }

    private static MenuInfo createMenuInfo(String[] menuInfo) {
        String name = menuInfo[0];
        String price = menuInfo[1];
        return new MenuInfo(name, Convertor.convertStringToInt(price));
    }

    private static void validateExistMenu(String[] menuInfo) {
        String name = menuInfo[0];
        if (!Menu.isContains(name)) {
            throw new IllegalArgumentException(ErrorMessage.ORDER_ERROR);
        }
    }

    private static void validateOrderCount(List<MenuInfo> orderMenuInfos) {
        int orderCount = orderMenuInfos.size();
        if (orderCount < MIN_ORDER_LIMIT || orderCount > MAX_ORDER_LIMIT) {
            throw new IllegalArgumentException(ErrorMessage.ORDER_ERROR);
        }
    }

    private static void validateOnlyOrderDrink(List<MenuInfo> orderMenuInfos) {
        MenuInfo firstMenuInfo = orderMenuInfos.get(0);
        if (isOrderOnce(orderMenuInfos) && Menu.isDrinkMenu(firstMenuInfo.name())) {
            throw new IllegalArgumentException(ErrorMessage.ORDER_ERROR);
        }
    }

    private static boolean isOrderOnce(List<MenuInfo> orderMenuInfos) {
        if (orderMenuInfos.size() == MIN_ORDER_LIMIT) {
            return true;
        }
        return false;
    }

    public List<MenuInfo> getMenuInfos() {
        return Collections.unmodifiableList(menuInfos);
    }
}
