package christmas.view;

import christmas.domain.Date;
import christmas.domain.Order;

public class OutputView {

    public void printGreetingsMessage() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void printPreviewOfBenefits(Date date) {
        System.out.printf("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n", date.getVisitDate());
        System.out.println();
    }

    public void printMenu(Order order) {
        System.out.println("<주문 메뉴>");
        StringBuilder orderMenus = new StringBuilder();
        order.getMenuInfos().stream()
                .map(orderDetail -> String.format("%s %s개", orderDetail.getMenuName(), orderDetail.getQuantity()))
                .forEach(menu -> orderMenus.append(menu).append("\n"));
        System.out.println(orderMenus);
    }

    public void printTotalOrderPrice(Order order) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.printf("%,d원\n", order.getTotalOrderPrice());
    }
}
