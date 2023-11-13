package christmas.domain.benefit;

import java.util.EnumSet;

public enum Badge {
    SANTA("산타", 20000),
    TREE("트리", 10000),
    STAR("별", 5000),
    NONE("없음", 0);

    private final String name;
    private final int minBenefitPrice;

    Badge(String name, int minBenefitPrice) {
        this.name = name;
        this.minBenefitPrice = minBenefitPrice;
    }

    public static String giveEventBadge(int totalBenefitPrice) {
        return EnumSet.allOf(Badge.class).stream()
                .filter(badge -> totalBenefitPrice >= badge.minBenefitPrice)
                .findFirst()
                .map(badge -> badge.name)
                .orElse(NONE.name);
    }
}
