package christmas.domain.user.order;

import christmas.domain.menu.Menu;

public class OrderPrice {
    private final int price;

    private OrderPrice(int price) {
        this.price = price;
    }

    public static OrderPrice of(OrderMenuName orderMenuName, Quantity quantity) {
        int menuPrice = Menu.getPriceOf(orderMenuName.getName());
        int orderPrice = menuPrice * quantity.getQuantity();
        return new OrderPrice(orderPrice);
    }

    public int getPrice() {
        return price;
    }
}
