package christmas.domain;

import christmas.dto.MenuInfo;
import christmas.enums.Menu;
import christmas.util.Convertor;

public class OrderDetail {
    private final MenuInfo menuInfo;
    private final int quantity;

    private OrderDetail(MenuInfo menuInfo, int quantity) {
        this.menuInfo = menuInfo;
        this.quantity = quantity;
    }

    public static OrderDetail of(String[] orderInfo) {
        String name = orderInfo[0];
        int price = Menu.getPriceOf(name);
        int quantity = Convertor.convertStringToInt(orderInfo[1]);
        return new OrderDetail(new MenuInfo(name, price), quantity);
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
