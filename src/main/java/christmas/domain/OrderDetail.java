package christmas.domain;

import christmas.util.StringConvertor;

public class OrderDetail {
    private final OrderMenuName name;
    private final int price;
    private final Quantity quantity;

    public OrderDetail(OrderMenuName name, int price, Quantity quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public static OrderDetail of(String orderMenu) {
        String[] orderInfo = StringConvertor.splitByHyphen(orderMenu);
        OrderMenuName menuName = OrderMenuName.from(orderInfo);
        int price = Menu.getPriceOf(menuName.getName());
        Quantity quantity = Quantity.from(orderInfo);
        return new OrderDetail(menuName, price, quantity);
    }

    public String getMenuName() {
        return name.getName();
    }

    public int getQuantity() {
        return quantity.getQuantity();
    }

    public int getOrderPrice() {
        return price * quantity.getQuantity();
    }
}
