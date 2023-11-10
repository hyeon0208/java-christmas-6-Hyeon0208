package christmas.view;

import christmas.domain.Date;

public class OutputView {

    public void printGreetingsMessage() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void printPreviewOfBenefits(Date date) {
        System.out.printf("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n", date.getVisitDate());
        System.out.println();
    }
}
