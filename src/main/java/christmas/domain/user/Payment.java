package christmas.domain.user;

import christmas.domain.benefit.Benefit;

public class Payment {
    private final User user;
    private final Benefit benefit;

    public Payment(User user, Benefit benefit) {
        this.user = user;
        this.benefit = benefit;
    }

    public int getActualPaymentPrice() {
        int totalOrderPrice = user.getTotalOrderPrice();
        int totalBenefit = benefit.getTotalBenefitPrice();
        return totalOrderPrice - totalBenefit;
    }
}
