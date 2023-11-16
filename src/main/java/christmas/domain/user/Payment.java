package christmas.domain.user;

import christmas.domain.benefit.Benefit;
import christmas.domain.benefit.Gift;

public class Payment {
    private final User user;
    private final Benefit benefit;

    public Payment(User user, Benefit benefit) {
        this.user = user;
        this.benefit = benefit;
    }

    public int getActualPaymentPrice() {
        int totalOrderPrice = user.getTotalOrderPrice();
        int totalBenefit = getBenefitPriceExcludingGiftPrice();
        return totalOrderPrice - totalBenefit;
    }

    private int getBenefitPriceExcludingGiftPrice() {
        int giftPrice = Gift.findPriceByTotalOrderPrice(user.getTotalOrderPrice());
        return benefit.getTotalBenefitPrice() - giftPrice;
    }
}
