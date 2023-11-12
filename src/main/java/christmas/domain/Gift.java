package christmas.domain;

import java.util.EnumSet;
import java.util.Optional;

public enum Gift {
    CHAMPAGNE("샴페인", 1, 120000);

    private final String product;
    private final int quantity;
    private final int minOrderPrice;

    Gift(String product, int quantity, int minOrderPrice) {
        this.product = product;
        this.quantity = quantity;
        this.minOrderPrice = minOrderPrice;
    }

    public static boolean canReceive(int totalOrderPrice) {
        return EnumSet.allOf(Gift.class).stream()
                .anyMatch(gift -> totalOrderPrice >= gift.minOrderPrice);
    }

    public static Optional<Gift> findByTotalOrderPrice(int totalOrderPrice) {
        return EnumSet.allOf(Gift.class).stream()
                .filter(gift -> totalOrderPrice >= gift.minOrderPrice)
                .findFirst();
    }

    public String getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
}
