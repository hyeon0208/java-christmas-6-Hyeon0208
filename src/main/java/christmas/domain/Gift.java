package christmas.domain;

import java.util.EnumSet;

public enum Gift {
    CHAMPAGNE(120000);

    private final int minOrderPrice;

    Gift(int minOrderPrice) {
        this.minOrderPrice = minOrderPrice;
    }

    public static boolean matches(int totalOrderPrice) {
        return EnumSet.allOf(Gift.class).stream()
                .anyMatch(gift -> totalOrderPrice >= gift.minOrderPrice);
    }
}
