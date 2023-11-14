package christmas.domain.user.order;

import christmas.util.StringConvertor;

public class OrderDetail {
    private final OrderMenuName menuName;
    private final Quantity quantity;
    private final OrderPrice price;

    public OrderDetail(OrderMenuName menuName, Quantity quantity, OrderPrice price) {
        this.menuName = menuName;
        this.quantity = quantity;
        this.price = price;
    }

    public static OrderDetail from(String orderMenu) {
        String[] orderInfo = StringConvertor.splitByHyphen(orderMenu);
        OrderMenuName menuName = OrderMenuName.from(orderInfo);
        Quantity quantity = Quantity.from(orderInfo);
        OrderPrice orderPrice = OrderPrice.of(menuName, quantity);
        return new OrderDetail(menuName, quantity, orderPrice);
    }

    public String getMenuName() {
        return menuName.getName();
    }

    public int getQuantity() {
        return quantity.getQuantity();
    }

    public int getOrderPrice() {
        return price.getPrice();
    }
}
