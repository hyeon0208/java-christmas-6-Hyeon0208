package christmas.controller;

import christmas.domain.Benefit;
import christmas.domain.EventApplicator;
import christmas.domain.Order;
import christmas.domain.Payment;
import christmas.domain.User;
import christmas.domain.VisitDate;
import christmas.handler.InputHandler;
import christmas.view.OutputView;

public class PromotionController {
    private InputHandler inputHandler;
    private OutputView outputView;

    public PromotionController(InputHandler inputHandler, OutputView outputView) {
        this.inputHandler = inputHandler;
        this.outputView = outputView;
    }

    public void run() {
        User user = reserveRestaurant();
        previewBenefit(user);
    }

    private User reserveRestaurant() {
        outputView.printGreetingsMessage();
        VisitDate visitDate = inputHandler.receiveValidatedVisitDate();
        Order order = inputHandler.receiveValidatedOrder();
        return new User(visitDate, order);
    }

    private void previewBenefit(User user) {
        outputView.printBenefitPreviewMessage(user);
        showOrderInfo(user);
        outputView.printGiftMenu(user);
        Benefit benefit = receiveBenefit(user);
        showPaymentInfo(user, benefit);
        outputView.printEventBadge(benefit);
    }

    private void showOrderInfo(User user) {
        outputView.printMenu(user);
        outputView.printTotalOrderPrice(user);
    }

    private Benefit receiveBenefit(User user) {
        Benefit benefit = EventApplicator.applyEvents(user);
        outputView.printBenefit(benefit);
        outputView.printTotalBenefitPrice(benefit);
        return benefit;
    }

    private void showPaymentInfo(User user, Benefit benefit) {
        Payment payment = new Payment(user, benefit);
        outputView.printActualPaymentPrice(payment);
    }
}
