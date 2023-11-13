package christmas.view;

import christmas.domain.Badge;
import christmas.domain.Benefit;
import christmas.domain.Gift;
import christmas.domain.Payment;
import christmas.domain.User;
import java.util.stream.Collectors;

public class OutputView {

    public void printGreetingsMessage() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void printBenefitPreviewMessageFor(User user) {
        String text = String.format("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!", user.getVisitDate());
        printlnWithNewLine(text);
    }

    public void printMenu(User user) {
        System.out.println("<주문 메뉴>");
        String text = user.getOrderDetails().stream()
                .map(orderDetail -> String.format("%s %s개", orderDetail.getMenuName(), orderDetail.getQuantity()))
                .collect(Collectors.joining("\n"));
        printlnWithNewLine(text);
    }

    public void printTotalOrderPriceFor(User user) {
        System.out.println("<할인 전 총주문 금액>");
        String text = String.format("%,d원", user.getTotalOrderPrice());
        printlnWithNewLine(text);
    }

    public void printGiftMenu(User user) {
        System.out.println("<증정 메뉴>");
        String text = Gift.findByTotalOrderPrice(user.getTotalOrderPrice())
                .map(gift -> String.format("%s %d개", gift.getProduct(), gift.getQuantity()))
                .orElse("없음");
        printlnWithNewLine(text);
    }

    public void printBenefitDetails(Benefit benefit) {
        System.out.println("<혜택 내역>");
        String text = "없음";
        if (benefit.isAnyAppliedBenefit()) {
            text = benefit.getAppliedBenefit().stream()
                    .map(eventInfo -> String.format("%s: -%,d원", eventInfo.name(), eventInfo.discountPrice()))
                    .collect(Collectors.joining("\n"));
        }
        printlnWithNewLine(text);
    }

    public void printTotalBenefitPrice(Benefit benefit) {
        System.out.println("<총혜택 금액>");
        String text = "0원";
        if (benefit.isAnyAppliedBenefit()) {
            text = String.format("-%,d원",  benefit.getTotalBenefitPrice());
        }
        printlnWithNewLine(text);
    }

    public void printActualPaymentPrice(Payment payment) {
        System.out.println("<할인 후 예상 결제 금액>");
        String text = String.format("%,d원", payment.getActualPaymentPrice());
        printlnWithNewLine(text);
    }

    public void printEventBadge(Benefit benefit) {
        System.out.println("<12월 이벤트 배지>");
        System.out.println(Badge.giveEventBadge(benefit.getTotalBenefitPrice()));
    }

    private void printlnWithNewLine(String text) {
        System.out.println(text);
        System.out.println();
    }
}
