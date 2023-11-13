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
        System.out.printf("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n", user.getVisitDate());
        System.out.println();
    }

    public void printMenu(User user) {
        System.out.println("<주문 메뉴>");
        StringBuilder orderMenus = new StringBuilder();
        user.getOrderDetails().stream()
                .map(orderDetail -> String.format("%s %s개", orderDetail.getMenuName(), orderDetail.getQuantity()))
                .forEach(menu -> orderMenus.append(menu).append("\n"));
        System.out.println(orderMenus);
    }

    public void printTotalOrderPriceFor(User user) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.printf("%,d원\n", user.getTotalOrderPrice());
        System.out.println();
    }

    public void printGiftMenu(User user) {
        System.out.println("<증정 메뉴>");
        String output = Gift.findByTotalOrderPrice(user.getTotalOrderPrice())
                .map(gift -> String.format("%s %d개", gift.getProduct(), gift.getQuantity()))
                .orElse("없음");
        System.out.println(output);
        System.out.println();
    }

    public void printBenefitDetails(Benefit benefit) {
        System.out.println("<혜택 내역>");
        String output = "없음";
        if (benefit.isAnyAppliedBenefit()) {
            output = benefit.getAppliedBenefit().stream()
                    .map(eventInfo -> String.format("%s: -%,d원", eventInfo.name(), eventInfo.discountPrice()))
                    .collect(Collectors.joining("\n"));
        }
        System.out.println(output);
        System.out.println();
    }
}
