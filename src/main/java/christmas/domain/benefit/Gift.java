package christmas.domain.benefit;

import java.util.EnumSet;
import java.util.Optional;

public enum Gift {
    CHAMPAGNE("샴페인", 1, 25000, 120000);

    private final String product;
    private final int quantity;
    private final int price;
    private final int minOrderPrice;

    Gift(String product, int quantity, int price, int minOrderPrice) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.minOrderPrice = minOrderPrice;
    }

    public static boolean givable(int totalOrderPrice) {
        return findMatchingGift(totalOrderPrice).isPresent();
    }

    public static Gift findByTotalOrderPrice(int totalOrderPrice) {
        return findMatchingGift(totalOrderPrice).get();
    }

    public static int findPriceByTotalOrderPrice(int totalOrderPrice) {
        return findMatchingGift(totalOrderPrice)
                .map(gift -> gift.price)
                .orElse(0);
    }

    private static Optional<Gift> findMatchingGift(int totalOrderPrice) {
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
